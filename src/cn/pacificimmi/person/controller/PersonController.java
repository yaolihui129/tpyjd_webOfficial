package cn.pacificimmi.person.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.QcloudUploader;
import cn.pacificimmi.common.interceptor.BindingInterceptor;
import cn.pacificimmi.common.models.Country;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.EstimateQuestion;
import cn.pacificimmi.common.models.EstimateRecord;
import cn.pacificimmi.common.models.EstimateSelectAdditional;
import cn.pacificimmi.common.models.Project;
import cn.pacificimmi.common.utils.MemcacheKit;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.common.utils.WeiXinConst;
import cn.pacificimmi.estimate.models.view.EstimateResultInfo;
import cn.pacificimmi.estimate.models.view.EstimatetDetailRecordInfo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;


@Before(BindingInterceptor.class)
public class PersonController extends Controller {
	
	private static Logger log = LoggerFactory.getLogger(PersonController.class);
	private static QcloudUploader qcloudUploader = new QcloudUploader();
	private static final Prop p = PropKit.use("constants.txt");
	
	public void index() {
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			this.setAttr("appId", PropKit.use("debug_config.txt").get("appId"));
			custinfo = Custinfo.dao.findById(custinfo.getCustinfoId());
			if(null != custinfo.getProxyLevel() && null != custinfo.getAuditStatus() && 
					custinfo.getAuditStatus().equals(PropKit.use("constants.txt").get("audit_status_passed"))) {
				this.setAttr("invite", true);
			}
			this.setAttr("custinfo", custinfo);
			this.setAttr("operation", "保存");
			this.setAttr("menu_on", "personInfo");
			this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_info.jsp");
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_evaluate.jsp");  
		}
	}
	
	
	
	    public void personInfo(){
			Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
			if(null != custinfo){
				custinfo = Custinfo.dao.findById(custinfo.getCustinfoId()); //TODO 应该增加是否为空的判断
				ParamsParser pp = new ParamsParser(this);
				String operation = pp.getAllStr("operation");
				if(StringUtils.isEmpty(operation)){
					this.setAttr("custinfo", custinfo);
					this.setAttr("operation", "保存");
					this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_info.jsp");
					return;
				}
				//姓名
				String name = pp.getAllStr("name");
				custinfo.setName(name);
				//昵称
				String wxName = pp.getAllStr("wxName");
				try {
					custinfo.setWxName(URLEncoder.encode(wxName,"UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//性别
				String gender = pp.getAllStr("gender");
				if(null!=gender&&!"".equals(gender)){
					custinfo.setGender(gender);
				}
				//邮箱
				String email = pp.getAllStr("email");
				custinfo.setEmail(email);
				//备用联系方式
				String contact = pp.getAllStr("contact");
				custinfo.setContact(contact);
				//出生日期
				String birthday = pp.getAllStr("birthday");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				if(StringUtils.isEmpty(birthday)){
					custinfo.setBirthday(null);
				}else{
					try {
						System.out.println(sdf.parse(birthday));
						custinfo.setBirthday(sdf.parse(birthday));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				boolean isOk = custinfo.update();
				if(isOk){
					this.setAttr("status", "0");//0代表成功
				}else{
					this.setAttr("status", "1");//1代表异常
					this.setAttr("msg", "数据库异常！");
				}
				this.renderJson();
			}else{
				/**
				 * 未登录
				 */
				this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"index.jsp");  
			}
			
		}
	    
	    public void headImg(){
	    	Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
			if(null != custinfo){
				custinfo = Custinfo.dao.findById(custinfo.getCustinfoId());
				ParamsParser pp = new ParamsParser(this);
				String operation = pp.getAllStr("operation");
				this.setAttr("menu_on", "headImg");
				if(StringUtils.isEmpty(operation)){
					this.setAttr("custinfo", custinfo);
					this.setAttr("operation", "保存");
					this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_headImg.jsp");
					return;
				}
				 //当头像和原来的不等时删除原图片
				String headImg = pp.getAllStr("headImg");
				if(null!=headImg&&!"".equals(headImg)){
					if(StringUtils.notEmpty(custinfo.getHeadImg())&&!headImg.equals(custinfo.getHeadImg())){
						qcloudUploader.deletePic(custinfo.getHeadImg());
					}
					custinfo.setHeadImg(headImg);
					custinfo.update();
					this.setSessionAttr(WeiXinConst.CUSTINFO, custinfo);
					this.setAttr("status", 0);
					this.renderJson();
				}
			}else{
				/**
				 * 未登录
				 */
				this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"index.jsp");
			}	
	    }
	    /**
		 * 客户评估结果
		 */
		public void evaluateResult() {
			Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
			Integer custinfoId = custinfo.getCustinfoId();
//			Integer custinfoId =20;
			this.setAttr("custinfo", custinfo);
			this.setAttr("menu_on", "evaluate");
			List<Project> projectList = null;
			projectList = (List<Project>) MemcacheKit.get("projectList");
			if(null == projectList || projectList.size()==0) {
				List<Project> plist = Project.dao.find("select * from crm_project where project_status = "+
						PropKit.use("constants.txt").get("project_status_sx")+"  and delete_flag=0");
				MemcacheKit.set("projectList", plist, 10*60);
				projectList = plist;
			}
			
			EstimateRecord er = EstimateRecord.dao.findFirst("select * from wx_estimate_record where custinfo_id="+custinfoId);
			if(null != projectList && projectList.size()>0 && null != er) {
				List<Record> result = Db.find("select * from wx_estimatet_detail_record WHERE estimate_record_id="+er.getEstimateRecordId());
				List<EstimatetDetailRecordInfo> edrList = new ArrayList<EstimatetDetailRecordInfo>();
				if(null != result && result.size()>0) {
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
					
					List<EstimateResultInfo> eriList = new ArrayList<EstimateResultInfo>();
					for(Project pj:projectList) {
						EstimateResultInfo eri = this.EstimateResultByProjectId(edrList, pj);
						if(null != eri) {
							eriList.add(eri);
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
						if(eriList.size() > 3){
							for(int i = 3;i<eriList.size();i++){
								eriList.set(i, null);
							}
							
						}
						this.setAttr("resultList", eriList);
						this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_evaluate.jsp");
					} else {
						this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_evaluate.jsp");
					}
				} else {
					this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_evaluate.jsp");
				}
			} else {
				this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_evaluate.jsp");
				/*this.renderJsp("/WEB-INF/jsp/noData.jsp");*/
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
			Integer weight_value = (di.getValue()!=null || !"".equals(di.getValue()))?new Integer(di.getValue()):0;
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
				return eri;
			}
			return null;
		}
		
		public void living(){
			this.setAttr("menu_on", "live");//当前选中菜单
			
			Custinfo custinfo = getSessionAttr(WeiXinConst.CUSTINFO);
			this.setAttr("custinfo", custinfo);
			ParamsParser pp = new ParamsParser(this);
			//查询直播信息
			String sqlu = "select pl.*,pla.`status`,steuser.english_name,steuser.job_title from portal_live pl "
					+ "LEFT JOIN portal_live_application pla ON pl.live_id = pla.live_id "
					+ "LEFT JOIN (select csu.steward_id,cu.* from crm_steward_user csu LEFT JOIN console_user cu ON cu.user_id = csu.user_id) as steuser ON steuser.steward_id = pl.live_steward_id where pl.delete_flag = 0 "
					+ "and pl.live_status in ('直播中','已发布') and pla.custinfo_id = "+custinfo.getCustinfoId()+" "
					+ "order by pl.live_start_time desc";
			
			List<Record> liveList = Db.find(sqlu);
			if(liveList.size()>0){
				this.setAttr("liveList", liveList);
			}
				
			this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_live.jsp");	
		}
		
}
