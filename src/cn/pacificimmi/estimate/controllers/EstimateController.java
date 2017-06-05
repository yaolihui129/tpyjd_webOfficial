package cn.pacificimmi.estimate.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Country;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.EstimateQuestion;
import cn.pacificimmi.common.models.EstimateRecord;
import cn.pacificimmi.common.models.EstimateSelectAdditional;
import cn.pacificimmi.common.models.EstimatetDetailRecord;
import cn.pacificimmi.common.models.Project;
import cn.pacificimmi.common.models.Topics;
import cn.pacificimmi.common.utils.MemcacheKit;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.common.utils.WeiXinConst;
import cn.pacificimmi.estimate.models.view.EstimateQuestionInfo;
import cn.pacificimmi.estimate.models.view.EstimateQuestionVersionInfo;
import cn.pacificimmi.estimate.models.view.EstimateResultInfo;
import cn.pacificimmi.estimate.models.view.EstimateSelectInfo;
import cn.pacificimmi.estimate.models.view.EstimatetDetailRecordInfo;
import net.sf.json.JSONArray;

/**
 * 评估
 * @author yang
 * @date 2017年5月16日 上午9:55:50
 */

@Before({MenuInterCeptor.class, EstimateInterCeptor.class})
public class EstimateController extends Controller {
	
	private static Logger log = LoggerFactory.getLogger(EstimateController.class);
	
	public void index() {
		
		//问卷版本
		Record record = Db.findFirst("select * from wx_estimate_question_version where status="+
				PropKit.use("constants.txt").get("question_version_status_yfb")+" and delete_flag=0");
		
		//匹配项目
		List<Project> projectList = null;
		projectList = (List<Project>) MemcacheKit.get("projectList");
		if(null == projectList || projectList.size()==0) { 
			List<Project> plist = Project.dao.find("select * from crm_project where project_status = "+
					PropKit.use("constants.txt").get("project_status_sx")+
					" AND project_level = "+PropKit.use("constants.txt").get("project_level_one")+"  and delete_flag=0");
			MemcacheKit.set("projectList", plist, 10*60);
			projectList = plist;
		}
		
		// 问卷，匹配项目是否存在数据
		if (record !=null && null != projectList && projectList.size()>0) {
			//获取评估问题
			EstimateQuestionVersionInfo eqvi = new EstimateQuestionVersionInfo();
			eqvi.bindingData(eqvi, record);
			List<Record> result = Db.find("select * from wx_estimate_question where version_id = "+eqvi.getVersion_id()+" and delete_flag=0 order by priority asc");
			if(null != result && result.size()>0) {
				List<EstimateQuestionInfo> eqList = new ArrayList<EstimateQuestionInfo>();
				for(Record r:result) {
					EstimateQuestionInfo eqi = new EstimateQuestionInfo();
					eqi.bindingData(eqi, r);
					//获取评做选择项
					if(null != eqi.getEstimate_question_id()) {
						List<Record> selResult = Db.find("select * from wx_estimate_select where estimate_question_id="+eqi.getEstimate_question_id()+" and delete_flag=0");
						List<EstimateSelectInfo> esList = new ArrayList<EstimateSelectInfo>();
						if(null != selResult && selResult.size()>0) {
							for(Record sel:selResult) {
								EstimateSelectInfo esi = new EstimateSelectInfo();
								esi.bindingData(esi, sel);
								List<EstimateSelectAdditional> esaList = EstimateSelectAdditional.dao.find("select * from wx_estimate_select_additional where estimate_select_id="+esi.getEstimate_select_id());
								esi.setEsaList(esaList);
								esList.add(esi);
							}
						}
						
						if(null != esList && esList.size()>0) {
							eqi.setEsList(esList);
						}
					}
					
					eqList.add(eqi);
				}
				eqvi.setEqList(eqList);
				
				//是否登录客户
				Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
				
				if(null != custinfo) {
					Integer custinfoId = custinfo.getCustinfoId();
					//获取客户信息
					Record estimateRecord = Db.findFirst("select * from wx_estimate_record where custinfo_id ="+custinfoId);
					if(null != estimateRecord) {
						EstimateQuestionVersionInfo ceqvi = new EstimateQuestionVersionInfo();
						ceqvi.bindingData(ceqvi, estimateRecord);
						if(eqvi.getVersion_id().equals(ceqvi.getVersion_id())) {
							List<Record> crList = Db.find("select * from wx_estimatet_detail_record where estimate_record_id ="+ceqvi.getEstimate_record_id());
							if(null != crList && crList.size()>0) {
								//获取客户评估问题
								List<EstimateQuestionInfo> ceqiList = new ArrayList<EstimateQuestionInfo>();
								for(Record cr:crList) {
									EstimateQuestionInfo ceqi = new EstimateQuestionInfo();
									ceqi.bindingData(ceqi, cr);
									ceqiList.add(ceqi);
								}
								this.setAttr("ceqiList", ceqiList);
							}
						}
					}
				}
				
				
				String country = this.getPara(0);
				if(null != country && !"".equals(country)) {
					this.setAttr("country", country);
				}
				String hotspots = this.getPara(1);
				if(null != hotspots && !"".equals(hotspots)) {
					this.setAttr("hotspots", hotspots);
				}
				
				this.setAttr("eqvi", eqvi);
				this.setAttr("danx", PropKit.use("constants.txt").get("estimate_answer_danx"));
				this.setAttr("duox", PropKit.use("constants.txt").get("estimate_answer_duox"));
				this.setAttr("wenb", PropKit.use("constants.txt").get("estimate_answer_wenb"));
				this.setAttr("shi", PropKit.use("constants.txt").get("estimate_isrequired_shi"));
				this.renderJsp("/views/estimate/estimate_list.jsp");
			} else {
				this.renderJsp("/404.html");
			}
		} else {
			this.renderJsp("/404.html");
		}
	}
	
	/**
	 * 保存
	 */
	public void save() {
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		
//		Custinfo custinfo = new Custinfo();
//		custinfo.setCustinfoId(10);
		
		String listStr = getPara("list");
		String versionId = getPara("versionId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null != custinfo) {
			Integer custinfoId = custinfo.getCustinfoId();
			if(null != listStr && !"".equals(listStr.trim()) && 
					null != versionId && !"".equals(versionId.trim())) {
				EstimateRecord erd = EstimateRecord.dao.findFirst("select * from wx_estimate_record where custinfo_id="+custinfoId);
				if(null != erd) {
					Db.update("delete from wx_estimatet_detail_record where estimate_record_id = ?", erd.getEstimateRecordId());
					erd.setVersionId(new Integer(versionId));
					erd.setCustinfoId(custinfoId);
					erd.setCreateTime(new Date());
					erd.update();
				} else {
					erd = new EstimateRecord();
					erd.setVersionId(new Integer(versionId));
					erd.setCustinfoId(custinfoId);
					erd.setCreateTime(new Date());
					erd.save();
				}
				JSONArray jsonArray = JSONArray.fromObject(listStr);
				List<EstimatetDetailRecord> edrList =  JSONArray.toList(jsonArray, EstimatetDetailRecord.class);
				
				if(null != edrList && edrList.size()>0) {
					for(EstimatetDetailRecord edr : edrList) {
						if((null != edr.getEstimateSelectId() && !"".equals(edr.getEstimateSelectId())) || (null !=edr.getIntValue() && !"".equals(edr.getIntValue()))) {
							edr.setEstimateRecordId(erd.getEstimateRecordId());
							edr.setCreateTime(new Date());
							edr.save();
						}
					}
				}
				map.put("status", "0");
				map.put("msg", "保存成功");
			} else {
				map.put("status", "1");
				map.put("msg", "参数错误");
			}
		} else {
			if(null != listStr && !"".equals(listStr.trim()) && 
					null != versionId && !"".equals(versionId.trim())) {
				
				map.put("status", "9");
				map.put("msg", "未登录");
			} else {
				map.put("status", "1");
				map.put("msg", "参数错误");
			}
		}
		this.renderJson(map);
	}
	
	
	
	/**
	 * 客户评估结果
	 */
	public void result() {
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		Integer custinfoId = custinfo.getCustinfoId();
		
//		Integer custinfoId = 10;
		
		//客户选择
		List<Record> reList = Db.find("SELECT "+
				"d.estimate_question_id, "+
				"d.int_value, "+
				"q.answer, "+
				"q.dict_code, "+
				"s.content "+
			"FROM "+
				"wx_estimate_record r "+
			"JOIN wx_estimatet_detail_record d ON r.estimate_record_id = d.estimate_record_id "+
			"LEFT JOIN wx_estimate_question q ON q.estimate_question_id = d.estimate_question_id "+
			"LEFT JOIN wx_estimate_select s ON d.estimate_select_id = s.estimate_select_id "+
			"WHERE "+
				"r.custinfo_id = "+custinfoId+
			" ORDER BY q.dict_code DESC");
		
		EstimateResultInfo eri = new EstimateResultInfo();
		if(null != reList &&reList.size()>0) {
			String coutry = PropKit.use("constants.txt").get("country");
			String hotspots = PropKit.use("constants.txt").get("hotspots");
			String asset = PropKit.use("constants.txt").get("asset");
			String immigration_budget = PropKit.use("constants.txt").get("immigration_budget");
			String qualifications = PropKit.use("constants.txt").get("qualifications");
			String language = PropKit.use("constants.txt").get("language");
			String manage = PropKit.use("constants.txt").get("manage");
			String live_requirement = PropKit.use("constants.txt").get("live_requirement");
			for(Record r:reList) {
				String code = r.getStr("dict_code");
				String answer = r.getStr("answer");
				if(StringUtils.notEmpty(code) && StringUtils.notEmpty(answer)) {
					if(answer.equals(PropKit.use("constants.txt").get("estimate_answer_wenb"))) {
						if(code.equals(coutry)) {
							if(StringUtils.notEmpty(eri.getCountry_name())) {
								eri.setCountry_name(eri.getCountry_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setCountry_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(hotspots)) {
							if(StringUtils.notEmpty(eri.getHotspots_name())) {
								eri.setHotspots_name(eri.getHotspots_name()+"、"+r.getInt("getInt"));
							} else {
								eri.setHotspots_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(asset)) {
							if(StringUtils.notEmpty(eri.getAsset_name())) {
								eri.setAsset_name(eri.getAsset_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setAsset_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(immigration_budget)) {
							if(StringUtils.notEmpty(eri.getImmigration_budget_name())) {
								eri.setImmigration_budget_name(eri.getImmigration_budget_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setImmigration_budget_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(qualifications)) {
							if(StringUtils.notEmpty(eri.getQualifications_name())) {
								eri.setQualifications_name(eri.getQualifications_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setQualifications_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(language)) {
							if(StringUtils.notEmpty(eri.getLanguage_name())) {
								eri.setLanguage_name(eri.getLanguage_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setLanguage_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(manage)) {
							if(StringUtils.notEmpty(eri.getManage_name())) {
								eri.setManage_name(eri.getManage_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setManage_name(r.getInt("int_value").toString());
							}
						} else if(code.equals(live_requirement)) {
							if(StringUtils.notEmpty(eri.getLive_requirement_name())) {
								eri.setLive_requirement_name(eri.getLive_requirement_name()+"、"+r.getInt("int_value"));
							} else {
								eri.setLive_requirement_name(r.getInt("int_value").toString());
							}
						}
					} else {
						if(code.equals(coutry)) {
							if(StringUtils.notEmpty(eri.getCountry_name())) {
								eri.setCountry_name(eri.getCountry_name()+"、"+r.getStr("content"));
							} else {
								eri.setCountry_name(r.getStr("content"));
							}
						} else if(code.equals(hotspots)) {
							if(StringUtils.notEmpty(eri.getHotspots_name())) {
								eri.setHotspots_name(eri.getHotspots_name()+"、"+r.getStr("content"));
							} else {
								eri.setHotspots_name(r.getStr("content"));
							}
						} else if(code.equals(asset)) {
							if(StringUtils.notEmpty(eri.getAsset_name())) {
								eri.setAsset_name(eri.getAsset_name()+"、"+r.getStr("content"));
							} else {
								eri.setAsset_name(r.getStr("content"));
							}
						} else if(code.equals(immigration_budget)) {
							if(StringUtils.notEmpty(eri.getImmigration_budget_name())) {
								eri.setImmigration_budget_name(eri.getImmigration_budget_name()+"、"+r.getStr("content"));
							} else {
								eri.setImmigration_budget_name(r.getStr("content"));
							}
						} else if(code.equals(qualifications)) {
							if(StringUtils.notEmpty(eri.getQualifications_name())) {
								eri.setQualifications_name(eri.getQualifications_name()+"、"+r.getStr("content"));
							} else {
								eri.setQualifications_name(r.getStr("content"));
							}
						} else if(code.equals(language)) {
							if(StringUtils.notEmpty(eri.getLanguage_name())) {
								eri.setLanguage_name(eri.getLanguage_name()+"、"+r.getStr("content"));
							} else {
								eri.setLanguage_name(r.getStr("content"));
							}
						} else if(code.equals(manage)) {
							if(StringUtils.notEmpty(eri.getManage_name())) {
								eri.setManage_name(eri.getManage_name()+"、"+r.getStr("content"));
							} else {
								eri.setManage_name(r.getStr("content"));
							}
						} else if(code.equals(live_requirement)) {
							if(StringUtils.notEmpty(eri.getLive_requirement_name())) {
								eri.setLive_requirement_name(eri.getLive_requirement_name()+"、"+r.getStr("content"));
							} else {
								eri.setLive_requirement_name(r.getStr("content"));
							}
						}
					}
				}
			}
			
			//从缓存获取项目
			List<Project> projectList = null;
			projectList = (List<Project>) MemcacheKit.get("projectList");
			if(null == projectList || projectList.size()==0) {
				List<Project> plist = Project.dao.find("select * from crm_project where project_status = "+
						PropKit.use("constants.txt").get("project_status_sx")+
						" AND project_level = "+PropKit.use("constants.txt").get("project_level_one")+"  and delete_flag=0");
				MemcacheKit.set("projectList", plist, 10*60);
				projectList = plist;
			}
			
			EstimateRecord er = EstimateRecord.dao.findFirst("select * from wx_estimate_record where custinfo_id="+custinfoId);
			
			//是否有匹配项目
			if(null != projectList && projectList.size()>0) {
				List<Record> result = Db.find("select * from wx_estimatet_detail_record WHERE estimate_record_id="+er.getEstimateRecordId());
				List<EstimatetDetailRecordInfo> edrList = new ArrayList<EstimatetDetailRecordInfo>();
				for(Record re:result) {
					EstimatetDetailRecordInfo edr = new EstimatetDetailRecordInfo();
					edr.bindingData(edr, re);
					EstimateQuestion eq = EstimateQuestion.dao.findById(edr.getEstimate_question_id());
					edr.setEstimateQuestion(eq);
					if(null != edr.getEstimate_select_id() && !"".equals(edr.getEstimate_select_id())) {
						List<EstimateSelectAdditional> esaList = EstimateSelectAdditional.dao.find("select * from wx_estimate_select_additional where estimate_select_id ="+edr.getEstimate_select_id());
						edr.setEsaList(esaList);
					}
					edrList.add(edr);
				}
				
				//获取匹配结果
				List<EstimateResultInfo> eriList = new ArrayList<EstimateResultInfo>();
				for(Project pj:projectList) {
					EstimateResultInfo ri = this.EstimateResultByProjectId(edrList, pj);
					if(null != ri) {
						eriList.add(ri);
					}
				}
				
				if(eriList.size()>0) {
					Collections.sort(eriList,new Comparator<EstimateResultInfo>(){
					      public int compare(EstimateResultInfo arg0, EstimateResultInfo arg1) {
					          int i = arg1.getMatching().compareTo(arg0.getMatching());
					          if(i==0) {
					               int j = arg1.getRecommended().compareTo(arg0.getRecommended());
					               if(j==0) {
					            	   int k = arg0.getOnline_time().compareTo(arg1.getOnline_time());
					            	   return k;
					               }
					               return j;
					          }
					          return i;
					    }
					});
					
					this.setAttr("estimate", eri);
					this.setAttr("resultList", eriList);
					this.renderJsp("/views/estimate/estimate_result.jsp");
				} else { //没有匹配上项目
					this.setAttr("msg", "系统内暂无适合您的方案，请联系管家为您推荐个人专属方案");
					this.setAttr("estimate", eri);
					this.renderJsp("/views/estimate/estimate_result.jsp");
				}
			} else { //没有项目
				this.setAttr("msg", "系统目前没有项目上线，请联系管家为您推荐个人专属方案");
				this.setAttr("estimate", eri);
				this.renderJsp("/views/estimate/estimate_result.jsp");
			}
		} else { //没有历史记录
			this.redirect("/estimate");
		}
	}
	
	/**
	 * 获取客户对某个项目的评估结果
	 * @param edriList
	 * @param project
	 * @return 0:完全匹配，1:不匹配，2:部分匹配
	 */
	private EstimateResultInfo EstimateResultByProjectId(List<EstimatetDetailRecordInfo> edriList, Project project) {
		String danx = PropKit.use("constants.txt").get("estimate_answer_danx");
		String duox = PropKit.use("constants.txt").get("estimate_answer_duox");
		String wenb = PropKit.use("constants.txt").get("estimate_answer_wenb");
		String coutry = PropKit.use("constants.txt").get("country");
		String hotspots = PropKit.use("constants.txt").get("hotspots");
		String asset = PropKit.use("constants.txt").get("asset");
		String immigration_budget = PropKit.use("constants.txt").get("immigration_budget");
		String qualifications = PropKit.use("constants.txt").get("qualifications");
		String language = PropKit.use("constants.txt").get("language");
		String manage = PropKit.use("constants.txt").get("manage");
		String live_requirement = PropKit.use("constants.txt").get("live_requirement");
		String age = PropKit.use("constants.txt").get("age");
		
		Dictionary di = Dictionary.dao.findFirst("select value from console_dictionary where dict_code="+
				PropKit.use("constants.txt").get("estimate_weight_value")+" and delete_flag=0");
		Integer weight_value = (di.getValue()!=null && !"".equals(di.getValue()))?new Integer(di.getValue()):0;
		EstimateResultInfo eri = new EstimateResultInfo();
		int num = 0;
		
		for(EstimatetDetailRecordInfo edri:edriList) {
			EstimateQuestion eq = edri.getEstimateQuestion();
			List<EstimateSelectAdditional> esaList = edri.getEsaList();
			String code = eq.getDictCode();
			String answer = eq.getAnswer();
			if(danx.equals(answer) || duox.equals(answer)) {
				if(coutry.equals(code)) { //移民国家
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getCountry() || eri.getCountry() == 1) {
								if(project.getCountry().equals(esa.getDictCode())) {
									eri.setCountry(0);
									num+=eq.getWeight();
								} else {
									eri.setCountry(1);
								}
							}
						}
					}
				} else if(hotspots.equals(code)) { //移民目的
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getHotspots() || eri.getHotspots() == 1) {
								Country country = Country.dao.findFirst("select * from crm_country where country_id = "+project.getCountry()+" and delete_flag=0");
								if(null != country && null !=country.getHotspotsId()) {
									if(country.getHotspotsId().contains(esa.getDictCode())) {
										eri.setHotspots(0);
										num+=eq.getWeight();
									} else {
										eri.setHotspots(1);
									}
								}
							}
						}
					}
				} else if(asset.equals(code)) { //资产要求
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getAsset() || eri.getAsset() == 1) {
								if(project.getAsset().equals(esa.getDictCode())) {
									eri.setAsset(0);
									num+=eq.getWeight();
								} else {
									eri.setAsset(1);
								}
							}
						}
					}
				} else if(immigration_budget.equals(code)) { //移民预算
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getImmigration_budget() || eri.getImmigration_budget() == 1) {
								if(project.getImmigrationBudget().equals(esa.getDictCode())) {
									eri.setImmigration_budget(0);
									num+=eq.getWeight();
								} else {
									eri.setImmigration_budget(1);
								}
							}
						}
					}
				} else if(qualifications.equals(code)) { //学历要求
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getQualifications() || eri.getQualifications() == 1) {
								if(project.getQualifications().equals(esa.getDictCode())) {
									eri.setQualifications(0);
									num+=eq.getWeight();
								} else {
									eri.setQualifications(1);
								}
							}
						}
					}
				} else if(language.equals(code)) { //外语能力
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getLanguage() || eri.getLanguage() == 1) {
								if(project.getLanguage().equals(esa.getDictCode())) {
									eri.setLanguage(0);
									num+=eq.getWeight();
								} else {
									eri.setLanguage(1);
								}
							}
						}
					}
				} else if(manage.equals(code)) { //管理经验
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getManage() || eri.getManage() == 1) {
								if(project.getManage().equals(esa.getDictCode())) {
									eri.setManage(0);
									num+=eq.getWeight();
								} else {
									eri.setManage(1);
								}
							}
						}
					}
				} else if(live_requirement.equals(code)) { //居住条件
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getLive_requirement() || eri.getLive_requirement() == 1) {
								if(project.getLiveRequirement().equals(esa.getDictCode())) {
									eri.setLive_requirement(0);
									num+=eq.getWeight();
								} else {
									eri.setLive_requirement(1);
								}
							}
						}
					}
				} else if(age.equals(code)) { //年龄要求
					if(null != esaList && esaList.size()>0) {
						for(EstimateSelectAdditional esa:esaList) {
							if(null ==eri.getAge() || eri.getAge() == 1) {
								if(project.getAge().equals(esa.getDictCode())) {
									eri.setAge(0);
									num+=eq.getWeight();
								} else {
									eri.setAge(1);
								}
							}
						}
					}
				}
			//数值类型
			} else if(wenb.equals(answer)) {
				if(coutry.equals(code)) { //移民国家
					if(null == eri.getCountry() || eri.getCountry() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getCountry())) {
									eri.setCountry(1);
								} else {
									eri.setCountry(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setCountry(1);
							}
						} else {
							eri.setCountry(1);
						}
					} else {
						eri.setCountry(1);
					}
				} else if(hotspots.equals(code)) { //移民目的
					if(null == eri.getHotspots() || eri.getHotspots() == 1) {
						if(null != edri.getInt_value()) {
							try{
								Country country = Country.dao.findFirst("select * from crm_country where country_id = "+project.getCountry()+" and delete_flag=0");
								if(null != country && null !=country.getHotspotsId() && edri.getInt_value()< new Integer(country.getHotspotsId())) {
									eri.setHotspots(1);
								} else {
									eri.setHotspots(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setHotspots(1);
							}
						} else {
							eri.setHotspots(1);
						}
					} else {
						eri.setHotspots(1);
					}
				} else if(asset.equals(code)) { //资产要求
					if(null == eri.getAsset() || eri.getAsset() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getAsset())) {
									eri.setAsset(1);
								} else {
									eri.setAsset(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setAsset(1);
							}
						} else {
							eri.setAsset(1);
						}
					} else {
						eri.setAsset(1);
					}
				} else if(immigration_budget.equals(code)) { //移民预算
					if(null == eri.getAsset() || eri.getAsset()==1) {
						for(EstimatetDetailRecordInfo dr:edriList) {
							EstimateQuestion eqn = dr.getEstimateQuestion();
							String dcode = eq.getDictCode();
							String danswer = eq.getAnswer();
							if(asset.equals(dcode)) {
								if(danx.equals(danswer) || duox.equals(danswer)) {
									List<EstimateSelectAdditional> esas= dr.getEsaList();
									if(null != esas && esas.size()>0) {
										for(EstimateSelectAdditional desa:esas) {
											if(null ==eri.getAsset() || eri.getAsset() == 1) {
												if(project.getAsset().equals(desa.getDictCode())) {
													eri.setAsset(0);
													num+=eq.getWeight();
												} else {
													eri.setAsset(1);
												}
											}
										}
									}
								} else if(wenb.equals(danswer)){
									if(null == eri.getAsset() || eri.getAsset() == 1) {
										if(null != edri.getInt_value()) {
											try{
												if(edri.getInt_value()<new Integer(project.getAsset())) {
													eri.setAsset(1);
												} else {
													eri.setAsset(0);
													num+=eq.getWeight();
												}
											}catch(Exception e) {
												eri.setAsset(1);
											}
										} else {
											eri.setAsset(1);
										}
									} else {
										eri.setAsset(1);
									}
								}
							}
						}
					}
					
					if(null == eri.getAsset() || eri.getAsset()==1) {
						eri.setImmigration_budget(1);
					} else if(eri.getAsset() == 0) {
						if(null != edri.getInt_value()) {
							if(edri.getInt_value()<project.getImmigrationBudget()) {
								eri.setImmigration_budget(2);
								num+=(eq.getWeight()/2);
							} else {
								eri.setImmigration_budget(0);
								num+=eq.getWeight();
							}
						} else {
							eri.setImmigration_budget(1);
						}
					}
					
				} else if(qualifications.equals(code)) { //学历要求
					if(null == eri.getQualifications() || eri.getQualifications() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getQualifications())) {
									eri.setQualifications(1);
								} else {
									eri.setQualifications(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setQualifications(1);
							}
						} else {
							eri.setQualifications(1);
						}
					} else {
						eri.setQualifications(1);
					}
				} else if(language.equals(code)) { //外语能力
					if(null == eri.getLanguage() || eri.getLanguage() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getLanguage())) {
									eri.setLanguage(1);
								} else {
									eri.setLanguage(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setLanguage(1);
							}
						} else {
							eri.setLanguage(1);
						}
					} else {
						eri.setLanguage(1);
					}
				} else if(manage.equals(code)) { //管理经验
					if(null == eri.getManage() || eri.getManage() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getManage())) {
									eri.setManage(1);
								} else {
									eri.setManage(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setManage(1);
							}
						} else {
							eri.setManage(1);
						}
					} else {
						eri.setManage(1);
					}
				} else if(live_requirement.equals(code)) { //居住条件
					if(null == eri.getLive_requirement() || eri.getLive_requirement() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getLiveRequirement())) {
									eri.setLive_requirement(1);
								} else {
									eri.setLive_requirement(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setLive_requirement(1);
							}
						} else {
							eri.setLive_requirement(1);
						}
					} else {
						eri.setLive_requirement(1);
					}
				} else if(age.equals(code)) { //年龄要求
					if(null == eri.getAge() || eri.getAge() == 1) {
						if(null != edri.getInt_value()) {
							try{
								if(edri.getInt_value()<new Integer(project.getAge())) {
									eri.setAge(1);
								} else {
									eri.setAge(0);
									num+=eq.getWeight();
								}
							}catch(Exception e) {
								eri.setAge(1);
							}
						} else {
							eri.setAge(1);
						}
					} else {
						eri.setAge(1);
					}
				}
			}
		}
		
		if(num>weight_value || num==weight_value) {
			eri.setMatching(num);
			eri.setProjectId(project.getProjectId());
			eri.setProjectName(project.getProjectName());
			eri.setRecommended(new Integer(project.getRecommended()));
			eri.setOnline_time(project.getOnlineTime());
			
			/**
			 * 项目优势
			 */
			String advantage = project.getAdvantage();
			if(StringUtils.notEmpty(advantage)) {
				advantage = advantage.replaceAll("；", ";");
				String[] adary = advantage.split(";");
				eri.setAdvantage(adary);
			}
			
			Record rd = Db.findFirst("SELECT "+
						"p.immigration_budget, "+
						"p.visa_type_description, "+
						"p.asset_description, "+
						"p.qualifications_description, "+
						"p.language_description, "+
						"p.manage_description, "+
						"p.live_requirement_description, "+
						"p.age_description, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.age "+
							"AND d.delete_flag = 0 "+
						") age_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.visa_type "+
							"AND d.delete_flag = 0 "+
						") visa_type_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.country "+
							"AND d.delete_flag = 0 "+
						") country_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.asset "+
							"AND d.delete_flag = 0 "+
						") asset_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.qualifications "+
							"AND d.delete_flag = 0 "+
						") qualifications_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.`language` "+
							"AND d.delete_flag = 0 "+
						") language_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.manage "+
							"AND d.delete_flag = 0 "+
						") manage_name, "+
						"( "+
							"SELECT "+
								"d. NAME "+
							"FROM "+
								"console_dictionary d "+
							"WHERE "+
								"d.dict_code = p.live_requirement "+
							"AND d.delete_flag = 0 "+
						") live_requirement_name, "+
						"( "+
							"SELECT "+
								"c.hotspots_id "+
							"FROM "+
								"crm_country c "+
							"WHERE "+
								"c.country_id = p.country "+
							"AND c.delete_flag = 0 "+
						") hotspots "+
					"FROM "+
						"crm_project p "+
					"WHERE "+
						"p.project_id = "+project.getProjectId());
			
			if(null != rd.getInt("immigration_budget")) {
				eri.setImmigration_budget(rd.getInt("immigration_budget"));
			}
			
			if(null != rd.getStr("country_name")) {
				eri.setCountry_name(rd.getStr("country_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("age_description"))) {
				eri.setAge_name(rd.getStr("age_description"));
			} else {
				eri.setAge_name(rd.getStr("age_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("visa_type_description"))) {
				eri.setVisa_type_name(rd.getStr("visa_type_description"));
			} else {
				eri.setVisa_type_name(rd.getStr("visa_type_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("asset_description"))) {
				eri.setAsset_name(rd.getStr("asset_description"));
			} else {
				eri.setAsset_name(rd.getStr("asset_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("qualifications_description"))) {
				eri.setQualifications_name(rd.getStr("qualifications_description"));
			} else {
				eri.setQualifications_name(rd.getStr("qualifications_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("language_description"))) {
				eri.setLanguage_name(rd.getStr("language_description"));
			} else {
				eri.setLanguage_name(rd.getStr("language_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("manage_description"))) {
				eri.setManage_name(rd.getStr("manage_description"));
			} else {
				eri.setManage_name(rd.getStr("manage_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("live_requirement_description"))) {
				eri.setLive_requirement_name(rd.getStr("live_requirement_description"));
			} else {
				eri.setLive_requirement_name(rd.getStr("live_requirement_name"));
			}
			
			if(StringUtils.notEmpty(rd.getStr("hotspots"))) {
				List<Record> hs = Db.find("SELECT "+
						"d. NAME "+
					"FROM "+
						"console_dictionary d "+
					"WHERE "+
						"d.delete_flag = 0 "+
					"AND d.dict_code IN ("+rd.getStr("hotspots")+")");
				for(Record d:hs) {
					if(StringUtils.notEmpty(eri.getHotspots_name())) {
						eri.setHotspots_name(eri.getHotspots_name()+"、"+d.getStr("NAME"));
					} else {
						eri.setHotspots_name(d.getStr("NAME"));
					}
				}
			}
			return eri;
		}
		return null;
	}
}
