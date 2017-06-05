package cn.pacificimmi.steward.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.BindingInterceptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Orgnization;
import cn.pacificimmi.common.models.StewardEvaluate;
import cn.pacificimmi.common.models.StewardLike;
import cn.pacificimmi.common.models.StewardSign;
import cn.pacificimmi.common.models.StewardUser;
import cn.pacificimmi.common.tree.Node;
import cn.pacificimmi.common.tree.NodeTree;
import cn.pacificimmi.common.utils.ConstantUtil;
import cn.pacificimmi.common.utils.CookieUtil;
import cn.pacificimmi.common.utils.StringUtil;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.common.utils.WeiXinConst; 
import cn.pacificimmi.steward.models.view.CitySteward;
import cn.pacificimmi.steward.models.view.StewardUserInfo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * @version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年11月8日 上午11:38:49
 * @category 移民管家
 **/
@Before(BindingInterceptor.class)
public class StewardsController extends Controller {
	private static Logger log = LoggerFactory.getLogger(StewardsController.class);
	public void index() {  
		/**
		 * 获取客户id
		 */
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			StewardSign stewardSign = StewardSign.dao.findFirst("SELECT * FROM crm_steward_sign AS ss WHERE ss.custinfo_id='"+custinfo.getCustinfoId()+"' AND ss.sign_status='聘用'  ORDER BY ss.sign_time DESC");
			/**
			 * 我的管家
			 */
			if(null!=stewardSign){
				Integer stewardId = stewardSign.getStewardId(); 
				//StewardUser mySteward = StewardUser.dao.findFirst("SELECT * FROM crm_steward_user AS su WHERE su.steward_id='"+stewardId+"'");
				StewardUserInfo changeStewardUserInfo = changeStewardUserInfo(stewardId.toString());
				//TODO changeStewardUserInfo为空时,即管家下线或者删除时的情况
				if(null == changeStewardUserInfo || StringUtils.isEmpty(changeStewardUserInfo.getCity_id())){
					StewardSign ss = StewardSign.dao.findFirst("select * from crm_steward_sign where steward_id ="+stewardId+" and custinfo_id = "+custinfo.getCustinfoId());
					ss.setSignStatus("解聘");
					ss.setUnsignTime(new Date());
					ss.setUnsignReason("管家已下线");
					ss.update();
					this.redirect("/steward");
				}else{
					int isZan = isLike(stewardId.toString(),custinfo.getCustinfoId());
	  				this.setAttr("isZan", isZan);
	  				String wechat = changeStewardUserInfo.getWechat();
	  				if(!(wechat.toLowerCase().contains("image")||wechat.toLowerCase().contains("bmp")||wechat.toLowerCase().contains("png")||wechat.toLowerCase().contains("jpg")||wechat.toLowerCase().contains("gif"))){
	  					changeStewardUserInfo.setWechat(null);
	  				}
					this.setAttr("steward",changeStewardUserInfo);
					this.setAttr("dynamicScore", changeStewardUserInfo.getDynamicScore());
					this.setAttr("custinfoId", custinfo.getCustinfoId()); 
					/**
					 * 渲染视图
					 */ 
					this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/mySteward.jsp");
				}
 				
			}else{
				
				/**
				 * 管家列表
				 */
				List<CitySteward> cityStewards = null;
				ParamsParser pp = new ParamsParser(this);
				//TODO 可以改为用cityId传值
				String cityId = pp.getAllStr("cityId");
				String cityName = pp.getAllStr("cityName");
				if(StringUtils.notEmpty(cityId)){
					if("0".equals(cityId)){
						cityStewards = getCitySteward(null);
					}else{
						cityStewards = getCitySteward(cityId);
					}
					this.setAttr("cityId", cityId);
				}else{
					cityStewards = getCitySteward(null);
				}
				boolean isContains = false;
				//第一次访问，即cityId为空的情况，判断用户是否填写了城市，根据客户所在城市定位公司，如客户在北京，则此处展示的是北京公司下的所有顾问，右上角为北京
				//在此基础上改为获取用户地理位置信息，从前台传cityName;
				if(StringUtils.isEmpty(cityId)&&StringUtils.notEmpty(cityName)){
					
//					Custinfo custinfoCity  = Custinfo.dao.findById(custinfo.getCustinfoId());
					String city = null;
					//= custinfoCity.getCity();
					for(CitySteward citySteward : cityStewards){
						if(cityName.contains(citySteward.getCityName())){
							city = citySteward.getCityId();
							isContains = true;
						}
					}
					if(isContains){
						for(CitySteward citySteward : cityStewards){
							if(!cityName.contains(citySteward.getCityName())){
								citySteward.setStewards(null);
							}
						}
						this.setAttr("cityId", city);
					}else{
						this.setAttr("cityId", "0");
					}
					this.setAttr("cityStewards", cityStewards);
				}else{
					this.setAttr("cityStewards", cityStewards);
				}
				if(StringUtils.notEmpty(cityId)){
					for(CitySteward cs:cityStewards){
						if(cs.getCityId().equals(cityId)){
							this.setAttr("cityName",cs.getCityName());
						}
					}
				}else{
					if(isContains){
						this.setAttr("cityName",cityStewards.get(0).getCityName());
					}
				}
				this.setAttr("appId", PropKit.use("debug_config.txt").get("appId"));
				this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/stewardList.jsp");
			} 
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");  
		}
  
	}
	/**
	 * 点赞
	 */
	public void like(){
		ParamsParser pp = new ParamsParser(this);
		String custinfo_id = pp.getNormStr("custinfo_id");
		String steward_id = pp.getNormStr("steward_id");
		StewardUser su = StewardUser.dao.findById(steward_id);
		if(StringUtils.notEmpty(custinfo_id)&&StringUtils.notEmpty(steward_id) && null!=su){
			String sql = "select * from crm_steward_like where steward_id = "+steward_id+" and custinfo_id = "+custinfo_id;
			StewardLike sl = StewardLike.dao.findFirst(sql);
			if(null == sl){
				sl = new StewardLike();
				sl.set("custinfo_id", custinfo_id);
				sl.set("steward_id", steward_id);
				sl.setCreateTime(new Date());
				sl.save();
				
				su.setLikeCount(su.getLikeCount()+1);
				su.update();
				this.setAttr("code", "0");
			}else{
				this.setAttr("code", "1");	
			}
		}else{
			this.setAttr("code", "1");
		}
		this.renderJson();
	}
	 /**
	  * 清楚cookie仅用于开发测试用clearcookie
	  */
	public void clearcookie(){ 
		ParamsParser pp = new ParamsParser(this);
		HttpServletResponse response = this.getResponse();
		HttpServletRequest request = this.getRequest();
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();  
		session.removeAttribute(WeiXinConst.PRE_URL);
		session.setAttribute(WeiXinConst.PRE_URL, requestURI);
		CookieUtil.delete(WeiXinConst.COOKIE_PHONE, request, response);
		CookieUtil.delete(WeiXinConst.COOKIE_CUSTINFO_ID, request, response);   
		this.redirect(ConstantUtil.TPYJD_WX_BASE_URL+"/index"); 
		 /*
		this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/stewardDetail.jsp"); */
	}
	
	/***
	 * 管家详情s
	 */ 
	public void detail(){ 
		ParamsParser pp = new ParamsParser(this);
		String stewardId = pp.getNormStr("steward_id"); 
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null == custinfo || StringUtils.isEmpty(stewardId)){
			/**
			 * 未登录
			 */
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");
		}else{
			StewardUserInfo changeStewardUserInfo = changeStewardUserInfo(stewardId);
			//微信存的不是图片时前台不能点击微信咨询
			String wechat = changeStewardUserInfo.getWechat();
				if(!(wechat.toLowerCase().contains("image")||wechat.toLowerCase().contains("bmp")||wechat.toLowerCase().contains("png")||wechat.toLowerCase().contains("jpg")||wechat.toLowerCase().contains("gif"))){
					changeStewardUserInfo.setWechat(null);
				}
			this.setAttr("custinfoId",custinfo.getCustinfoId());	
			this.setAttr("steward",changeStewardUserInfo);  
			this.setAttr("dynamicScore", changeStewardUserInfo.getDynamicScore());//星级评分
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/stewardDetail.jsp"); 	
		}
	}
	 

	/***
	 * 客户--雇佣管家
	 */ 
	public void hire(){
		ParamsParser pp = new ParamsParser(this);
		String steward_id = pp.getNormStr("steward_id");
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo && steward_id!=null && !"".equals(steward_id)){
			StewardSign stewardSign = null;
			List<StewardSign> stewardSigns = StewardSign.dao.find("select * from crm_steward_sign where steward_id = "+steward_id+" and custinfo_id = "+custinfo.getCustinfoId());
			//默认一个用户对应一个管家只有一条记录
			if(stewardSigns.size() > 0){
				stewardSign = stewardSigns.get(0);
				stewardSign.setStewardId(Integer.parseInt(steward_id));
				stewardSign.setCustinfoId(custinfo.getCustinfoId());
				stewardSign.setSignStatus("聘用");
				stewardSign.setUnsignReason("");
				stewardSign.setUnsignTime(null);
				stewardSign.setSignTime(new Date());
				stewardSign.update();
			}else{
				stewardSign = new StewardSign();
				stewardSign.setStewardId(Integer.parseInt(steward_id));
				stewardSign.setCustinfoId(custinfo.getCustinfoId());
				stewardSign.setSignStatus("聘用");
				stewardSign.setSignTime(new Date());
				stewardSign.save();
			}
			this.setAttr("code", 0);
			this.renderJson();
	}else{
		/**
		 * 未登录
		 */
		this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");	
		}
	}
	 
	/**
	 * 解聘  firesteward
	 */
	public void firesteward(){
		ParamsParser pp = new ParamsParser(this);
		String stewardId = pp.getNormStr("steward_id");
		String fireReason = pp.getAllStr("fireReason");
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			List<StewardSign> stewardSigns = StewardSign.dao.find("select * from crm_steward_sign where steward_id = "+stewardId+" and custinfo_id = "+ custinfo.getCustinfoId()+" and sign_status='聘用'");
			if(stewardSigns.size() > 0){
				//默认为只聘任一个人，所以取第一个
				StewardSign stewardSign = stewardSigns.get(0);
				stewardSign.setSignStatus("解聘");
				stewardSign.setUnsignReason(fireReason);
				stewardSign.setUnsignTime(new Date());
				boolean isOk = stewardSign.update();
				if(isOk){
					this.setAttr("code", 0);
				}else{
					this.setAttr("code", 1);
				}
				this.renderJson();
			}
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");	
		}
	}
	/**
	 * 跳转解聘页面  toFire
	 */
	public void toFire(){
		ParamsParser pp = new ParamsParser(this);
		String stewardId = pp.getNormStr("steward_id");
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			StewardUserInfo changeStewardUserInfo = changeStewardUserInfo(stewardId);
			this.setAttr("steward", changeStewardUserInfo);  
			this.setAttr("dynamicScore", changeStewardUserInfo.getDynamicScore());//星级评分
			this.setAttr("custinfo_id", custinfo.getCustinfoId());
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/fireSteward.jsp");
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");	
		}
	}
	/**
	 * 跳转评价 evaluate页面
	 */
	public void evaluate(){
		ParamsParser pp = new ParamsParser(this);
		String stewardId = pp.getNormStr("steward_id");
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			List<StewardEvaluate> ses = StewardEvaluate.dao.find("select * from crm_steward_evaluate where delete_flag=0 and steward_id="+stewardId+" and custinfo_id="+custinfo.getCustinfoId()+" order by create_time desc");
			if(ses.size()>0 ){
				this.setAttr("stewardEvaluate", ses.get(0));
			}
			StewardUserInfo changeStewardUserInfo = changeStewardUserInfo(stewardId);
			this.setAttr("steward", changeStewardUserInfo);  
			int isZan = isLike(stewardId.toString(),custinfo.getCustinfoId());
				this.setAttr("isZan", isZan);
			this.setAttr("dynamicScore", changeStewardUserInfo.getDynamicScore());
			this.setAttr("custinfo_id", custinfo.getCustinfoId()); 
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "steward/evaluate.jsp");  
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login.jsp");	
		}
	}
	/**
	 * 客户对  管家进行 评价 
	 */
	public void myEvaluate(){
		ParamsParser pp = new ParamsParser(this);
		String steward_id = pp.getNormStr("steward_id");
		String custinfo_id = pp.getNormStr("custinfo_id");
		String service_attitude = pp.getNormStr("service_attitude");
		String specialized_knowledge = pp.getNormStr("specialized_knowledge");
		String feedback_efficiency = pp.getNormStr("feedback_efficiency");
		String star_level = pp.getNormStr("star_level");
		String content = pp.getNormStr("content");
		// TODO 改为不删除评价，按最近一次评价的显示
//		List<StewardEvaluate> ses = StewardEvaluate.dao.find("select * from crm_steward_evaluate where delete_flag=0 and steward_id="+steward_id+" and custinfo_id="+custinfo_id);
//		if(null != ses){
//			for(StewardEvaluate se : ses){
//				se.set("delete_flag", 1);
//				se.update();
//			}
//		}
		StewardEvaluate stewardEvaluate=new StewardEvaluate();
		if(content!=null&&!"".equals(content)){
			stewardEvaluate.setContent(content);
		}
		if(steward_id!=null&&!"".equals(steward_id)){
			stewardEvaluate.setStewardId(Integer.parseInt(steward_id));
		}
		stewardEvaluate.setCreateTime(new Date());
		
		if(custinfo_id!=null&&!"".equals(custinfo_id)){
			stewardEvaluate.setCustinfoId(Integer.parseInt(custinfo_id));
		}
		if(service_attitude!=null&&!"".equals(service_attitude)){
			stewardEvaluate.setServiceAttitude(Integer.valueOf(service_attitude));
		}
		if(specialized_knowledge!=null&&!"".equals(specialized_knowledge)){
			stewardEvaluate.setSpecializedKnowledge(Integer.valueOf(specialized_knowledge));
		}
		if(feedback_efficiency!=null&&!"".equals(feedback_efficiency)){
			stewardEvaluate.setFeedbackEfficiency(Integer.valueOf(feedback_efficiency));
		}
		if(star_level!=null&&!"".equals(star_level)){
			stewardEvaluate.setStarLevel(Float.parseFloat(star_level));
		}
		stewardEvaluate.save();
//		this.redirect(ConstantUtil.TPYJD_WX_BASE_URL+"/steward");
		this.setAttr("code", 0);
		this.renderJson();
	}
	
	
	
	
	/***
	 * 管家详情s
	 */ 
	public StewardUserInfo changeStewardUserInfo(String stewardId){ 
		List<Record> list = null;
		StewardUserInfo stewardUserInfo = new StewardUserInfo();
		if(stewardId!=null&&!"".equals(stewardId)){
			//StewardUser steward = StewardUser.dao.findFirst("SELECT * FROM crm_steward_user AS su WHERE su.steward_id='"+stewardId+"'");
			list = Db.find("select su.steward_id,su.user_id,su.delete_flag,su.create_user,su.create_time,"+
								"su.update_user,su.update_time,su.release_mark,su.employ_count,su.card_status,su.like_count,cu.*,cd.name AS province_name "+
								"FROM crm_steward_user AS su "+ 
								"LEFT JOIN console_user AS cu ON  su.user_id = cu.user_id "+
								"LEFT JOIN console_dictionary AS cd ON cu.province_id =  cd.dict_code "+
								"WHERE su.steward_id = '"+stewardId+"' and su.delete_flag = '0' and su.release_mark = '已发布'");
			List<StewardUserInfo> result = new ArrayList<StewardUserInfo>();
			for(Record rd : list) {
				StewardUserInfo sui = new StewardUserInfo();
				sui.bindingData(sui, rd);
				result.add(sui);
			}
			if(result.size()>0){
				stewardUserInfo = result.get(0);
				Integer stewardId2 = stewardUserInfo.getSteward_id();
				if(null!=stewardId2){ 
					stewardUserInfo.setSteward_id(stewardId2);
					/**
					 * 动态评分  默认 5分
					 */
					String dynamicScore = getDynamicScores(stewardId2);
					if(StringUtils.notEmpty(dynamicScore) && Double.parseDouble(dynamicScore)>0 && Double.parseDouble(dynamicScore)<5){
						stewardUserInfo.setDynamicScore(dynamicScore);
					}else{
						stewardUserInfo.setDynamicScore("5.00");
					} 
				} 
			}  
		} 
		return	stewardUserInfo;
	}
	 
	
	
	
	
	
	
	/**
	 * 获取城市列表下的管家列表
	 * @param steward_id
	 * @return
	 */
 
	private List<CitySteward> getCitySteward(String cityId){
		
		/***
		 * 获取数据列表
		 */
		List<Record> list = null;
		List<Record> listCity = null;
		List<CitySteward> cityStewards=new ArrayList<CitySteward>();
			listCity = Db.find("SELECT "+
					"cd.dict_code cityId,"+
					"cd.`name` cityName "+
				" FROM "+
					" crm_steward_user AS csu "+
				" LEFT JOIN console_user AS cu ON csu.user_id = cu.user_id "+
				" LEFT JOIN console_dictionary AS cd ON cu.city_id = cd.dict_code "+
				" WHERE "+
					" cd.dict_code IS NOT NULL "+
					" and csu.release_mark = '已发布' and csu.delete_flag=0 "+
				" GROUP BY "+
					" cd.dict_code "+
				" ORDER BY "+
					" COUNT(cd.dict_code) desc ");
		for(Record rdCity : listCity) {
			CitySteward cs = new CitySteward();
			cs.bindingData(cs, rdCity);
			list = Db.find("SELECT su.steward_id,su.user_id,su.delete_flag,su.create_user,su.create_time,"+
				"su.update_user,su.update_time,su.release_mark,su.employ_count,su.card_status,su.like_count,cu.*"+
				" FROM "+
					" crm_steward_user AS su "+
				" LEFT JOIN console_user AS cu ON su.user_id = cu.user_id "+
				" WHERE "+
					" cu.city_id = "+cs.getCityId()+
					" and su.release_mark = '已发布' and su.delete_flag=0 ");
			List<StewardUserInfo> suis = new ArrayList<StewardUserInfo>();
			for(Record rd : list){
				StewardUserInfo sui = new StewardUserInfo();
				sui.bindingData(sui, rd);
				/**
				 * 动态评分  默认 5分
				 */
				String dynamicScore = getDynamicScores(sui.getSteward_id());
				if(StringUtils.notEmpty(dynamicScore) && Double.parseDouble(dynamicScore)>0 && Double.parseDouble(dynamicScore)<5){
					sui.setDynamicScore(dynamicScore);
				}else{
					sui.setDynamicScore("5.00");
				}
				suis.add(sui);
			}
			if(StringUtils.notEmpty(cityId)){
				if(cs.getCityId().equals(cityId)){
					cs.setStewards(suis);
				}
			}else{
				//判断用户是否填写所在城市，只显示所在的城市
				
				cs.setStewards(suis);
			}
			cityStewards.add(cs);
		}
		return cityStewards;
	}
	
	
	
	
	

	 //获取每个管家评分
	private String getDynamicScores(Integer steward_id){
		String score = "";
		List<Record> list = Db.find("select avg(star_level) as star_level FROM crm_steward_evaluate AS se WHERE  se.delete_flag = '"+0+"' AND  steward_id='"+steward_id+"' order by se.create_time  desc");
		if(null != list){
			Record r = list.get(0);
			if(null != r.getDouble("star_level")){
				score = r.getDouble("star_level").toString();
			}
			
		}
		return score;
	}
	
	
	
	
	
	
	
	/**
	 * 查询参数解析类
	 * @author lijinglun
	 *
	 */
	private class StewardUserParams extends ParamsParser{


		public StewardUserParams(Controller ctr) {
			super(ctr);
			/***
			 * 处理传入参数
			 */
			this.setSelectStr("select * ");
			this.setFromStr("from crm_steward_user ");
			String user_name = this.getNormStr("user_name"); 
			if(user_name!=null){
				this.addWhereSegmentByAnd(" user_name like '%"+user_name+"%' or english_name  like '%"+user_name+"%'  or phone  like '%"+user_name+"%' ");
			}
			//默认排序
			this.setDefaultOrderStr("order by create_time desc");
		}
	}
	
	
	
	
	
 
	
	private String loadTree(String code){
		List<Record> records = Db.find("select * from console_orgnization where delete_flag='DELETE_NO' order by org_code asc");
		NodeTree ct = new NodeTree("orgTree","所属部门",10);
		
		/***
		 * 初始化时将选中的节点，描红显示
		 */
		if(!code.isEmpty()){
			ct.getSelectedCodes().add(code);
		}
		/**
		 * 显示选择框
		 */
		ct.setSelectMode(true);
		
		/**
		 * 不显示编辑按钮
		 */
		ct.setAllownEdit(false);
		
		for(Record r:records){
			Node node = new Node();
			node.setName(r.getStr("name"));
			node.setCode(r.getStr("org_code"));
			node.setId(r.getStr("org_id"));
			
			node.setExtInfo(r.getStr("org_type"));
			ct.addNode(node);
		}
		
		return ct.buildTree();
	}
	
	
	private String getComId(String org_id){
		Orgnization mo = Orgnization.dao.findById(org_id);
		String code = mo.getOrgCode();
		String codes = StringUtil.getParentCodes(code);
		mo = Orgnization.dao.findFirst("select * from console_orgnization where delete_flag='DELETE_NO' and org_code in ("+codes+") and org_type='ORG_TYPE_COMPANY' order by org_code desc");
		if(mo!=null){
			return mo.getOrgId().toString();
		}else{
			return "0";
		}
	}
	/**
	 * 是否已经点赞，在跳转我的管家页面时查询
	 * @param stewardId
	 * @param custinfoId
	 * @return
	 */
	private int isLike(String stewardId,Integer custinfoId){
		int isLike = 0;
		if(StringUtils.notEmpty(stewardId)&&"" != custinfoId+""){
			StewardLike sl = StewardLike.dao.findFirst("select * from crm_steward_like where steward_id = "+Integer.valueOf(stewardId)+" and custinfo_id = "+custinfoId);
 			if(null != sl){ isLike = 1;}
		}
 		return isLike;
	}
	
	
	
	
	
	//新增移民管家  并且该管家不属于 公司员工///////////////////////////////////////////////////////////////////////
	
	

	}
 
	
	
	
	
	
	
	
	

