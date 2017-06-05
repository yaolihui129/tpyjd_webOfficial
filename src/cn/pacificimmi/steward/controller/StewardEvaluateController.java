package cn.pacificimmi.steward.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.PagesBar;
import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.controllers.MsgController;
import cn.pacificimmi.common.interceptor.BindingInterceptor; 
import cn.pacificimmi.common.models.AddressCountry;
import cn.pacificimmi.common.models.AddressProvince;
import cn.pacificimmi.common.models.Dictionary; 
import cn.pacificimmi.common.models.Orgnization;
import cn.pacificimmi.common.models.StewardEvaluate;
import cn.pacificimmi.common.models.StewardUser;
import cn.pacificimmi.common.tree.Node;
import cn.pacificimmi.common.tree.NodeTree;
import cn.pacificimmi.common.utils.MD5Util;
import cn.pacificimmi.common.utils.StringUtil;
import cn.pacificimmi.steward.models.view.DictionaryInfo;
import cn.pacificimmi.steward.models.view.StewardEvaluateInfo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.ContentType;
/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年10月31日 下午3:25:05
 * @category 管家评价等..
 **/
@Before(BindingInterceptor.class)
public class StewardEvaluateController extends Controller {
	private static Logger log = LoggerFactory.getLogger(StewardEvaluateController.class);
	
	public void index() { 
		ParamsParser ppg = new ParamsParser(this);
		Object sessionAttr = this.getSessionAttr("LoginUserInfo");
		if(null!=sessionAttr){
			BindingInterceptor login =(BindingInterceptor)sessionAttr; 
			//String uid = login.getUid();
			if("admin".equals(login)){ 
				
				StewardEvaluateParams sp = new StewardEvaluateParams(this,Integer.parseInt(ppg.getId()));
				ParamsParser pp = new ParamsParser(this);
				//way
				String way= pp.getNormStr("way");
				String para = getPara("field"); 
				DecimalFormat df = new DecimalFormat("#.##"); 
				/***
				 * 获取数据列表
				 */
				Page<Record> page = Db.paginate(sp.getPageNum(), sp.getPageSize(), sp.getSelectStr(), sp.getFromStr());
				List<Record> list = page.getList();
				ArrayList<StewardEvaluateInfo> result = new ArrayList<StewardEvaluateInfo>(); 
				int countStar=0;
				Double sumStar=0.0;   
				for(Record rd:list){
					StewardEvaluateInfo ui =new StewardEvaluateInfo();
					Object object = rd.get("star_level");
					if(null!=object){
						Double star_level=(Double)object;
						countStar++;
						sumStar=sumStar+star_level;
					}
					ui.bindingData(ui, rd);
					result.add(ui);
				} 
				if(countStar!=0){
					this.setAttr("dynamicScore", df.format(sumStar/countStar)); 
				}
				
				StewardUser stewardUser = getStewardUser(Integer.parseInt(ppg.getId()));
				if(null!=stewardUser){
					this.setAttr("stewardUser", stewardUser);
				}
				// 获取 管家评价数据字典列表 以及数据库相关数据
				List<DictionaryInfo> dictionaryInfos = getDictionaryInfo(list);
				
				this.setAttr("dictionaryInfos", dictionaryInfos);
 
				/***
				 * 保存数据列表
				 */
				this.setAttr("list", result); 
				/***
				 * 保存翻页
				 */
				
				String pagesView = PagesBar.getShortPageBar(sp.getPageNum(), page.getTotalPage(), page.getPageSize(), page.getTotalRow(), 5);
				setAttr("pageBar",pagesView); 
				setAttr("loginName","admin");
				
				
			} 
		} 
		/**
		 * 渲染视图
		 */
		this.renderJsp("/views/steward_evaluate.jsp");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/***
	 * 
	 */ 
	public void remove(){ 
		StewardEvaluateParams sp = new StewardEvaluateParams(this);
		Map<String,Object> rst = new HashMap<String,Object>(); 
		if(!sp.getIds().isEmpty()){
			String ids = sp.getIds();
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				StewardEvaluate findFirst = StewardEvaluate.dao.findFirst("SELECT * FROM crm_steward_evaluate AS se WHERE se.evaluate_id='"+split[i]+"'");
				findFirst.setDeleteFlag(0);
				findFirst.update(); 
			}
	 		rst.put("status", 0);
			rst.put("msg", "删除成功");
			this.renderJson(rst);		
		}
		else{
			rst.put("status", 100);
			rst.put("msg", "删除失败");
			this.renderJson(rst);
		}
	}
	 
	
	
	/**
	 * 根据 管家id  查询出管家信息
	 * @param steward_id
	 * @return
	 */  
	private StewardUser getStewardUser(Integer steward_id){
		StewardUser stewardUser = StewardUser.dao.findFirst("SELECT  * FROM crm_steward_user  WHERE steward_id = '"+steward_id+"'");
		if(null!=stewardUser){ 
			return stewardUser;
		} 
		return null;
	}
	/**
	 * 管家评价项
	 * @param steward_id
	 * @return
	 */  
	private List<Dictionary> getDictionary(String stewarditem){ 
		List<Dictionary> find = Dictionary.dao.find("SELECT  * FROM console_dictionary WHERE dict_pcode = '"+stewarditem+"'"); 
		return find;
	}
	
	
	 //获取中国的省份
	private List<AddressProvince> getProvinceInChina(){
		List<AddressCountry> listCountrys = AddressCountry.dao.find("SELECT  * FROM crm_address_country WHERE CountryName = '中国'");
		if(listCountrys.size()>0){
			//中国的CountryId
			String countryId = listCountrys.get(0).getCountryId();
			String sql="SELECT  * FROM crm_address_province WHERE CountryId = "+"'"+countryId+"'";
			List<AddressProvince> provinces = AddressProvince.dao.find(sql); 
			return provinces;
		} 
		return null;
	}
	
	
	/**
	 * 获取当前管家 的部门结构列 例如:北京>顾问中心>顾问一部
	 * @param code
	 * @param table
	 * @param code_field
	 * @return
	 */
	private String getOrgnizationLineName(String dep_id){
		String orgnizName="";
		String sqlOne="SELECT * FROM  console_orgnization WHERE org_id='"+dep_id+"'";
		Orgnization orgnization = Orgnization.dao.findFirst(sqlOne);
		if(null!=orgnization)
		if(null!=orgnization.getOrgPcode()){
			String parentOrgnizationCode = getParentOrgnizationCode(orgnization);
			orgnizName=parentOrgnizationCode;
		}
		return orgnizName;
	}
	/**
	 * 已经父code查出orgName
	 * @param orgnization
	 * @return
	 */
	private String getParentOrgnizationCode(Orgnization orgnization){
		String parentName="";
		if(null!=orgnization){
			if((null!=orgnization.getOrgPcode())&&(!orgnization.getOrgPcode().equals("PARENT"))){
				String orgPcode = orgnization.getOrgPcode();
				if(null!=orgPcode){
					String sqlParent="SELECT * FROM  console_orgnization WHERE org_code='"+orgPcode+"'";
					Orgnization findFirst = Orgnization.dao.findFirst(sqlParent);
					//parentName=findFirst.getName();
					parentName=	orgnization.getName();
					String parentOrgnizationCode = getParentOrgnizationCode(findFirst);
					return	parentName=parentOrgnizationCode+">"+parentName;
				}
			}else{
				return parentName=parentName+orgnization.getName();
			}
		}
		return "";
	}
	
	/**
	 * 查询参数解析类
	 * @author lijinglun
	 *
	 */
	private class StewardEvaluateParams extends ParamsParser{


		public StewardEvaluateParams(Controller ctr,Integer steward_id) {
			super(ctr);
			/***
			 * 处理传入参数   se.*,cc.`name` AS `name` LEFT JOIN crm_custinfo AS cc ON se.custinfo_id=cc.custinfo_id
			 */
			String delete_sign = PropKit.use("constants.txt").get("delete_sign");
			this.setSelectStr("select se.evaluate_id AS evaluate_id , se.custinfo_id AS custinfo_id "
					+ ",se.content AS content, se.create_time AS create_time, se.feedback_efficiency AS feedback_efficiency,"
					+ " se.service_attitude AS service_attitude ,se.specialized_knowledge AS specialized_knowledge "
					+ ",se.star_level AS star_level,se.steward_id AS steward_id ,cc.`name` AS `name`  ");
			this.setFromStr(" from crm_steward_evaluate AS se  LEFT JOIN crm_custinfo AS cc ON se.custinfo_id=cc.custinfo_id WHERE se.delete_sign  >= '"+delete_sign+"' AND se.steward_id="+steward_id);
			//this.setWhereStr("se.steward_id="+steward_id);
			 
			//默认排序
			this.setDefaultOrderStr("order by se.create_time  desc");
		}
		public StewardEvaluateParams(Controller ctr) {
			super(ctr);
			/***
			 * 处理传入参数   se.*,cc.`name` AS `name` LEFT JOIN crm_custinfo AS cc ON se.custinfo_id=cc.custinfo_id
			 */
			String delete_sign = PropKit.use("constants.txt").get("delete_sign");
			this.setSelectStr("select se.evaluate_id AS evaluate_id , se.custinfo_id AS custinfo_id "
					+ ",se.content AS content, se.create_time AS create_time, se.feedback_efficiency AS feedback_efficiency,"
					+ " se.service_attitude AS service_attitude ,se.specialized_knowledge AS specialized_knowledge "
					+ ",se.star_level AS star_level,se.steward_id AS steward_id ,cc.`name` AS `name`  ");
			this.setFromStr(" from crm_steward_evaluate AS se  LEFT JOIN crm_custinfo AS cc ON se.custinfo_id=cc.custinfo_id WHERE se.delete_sign LIKE  >= '"+delete_sign+"' ");
			//this.setWhereStr("se.steward_id="+steward_id);
			 
			//默认排序
			this.setDefaultOrderStr("order by se.create_time  desc");
		}
		
	}
	
	
	
	
	
	
	
	
	
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void update(){
		/**
		 * 创建参数解析类
		 */
		ParamsParser pp = new ParamsParser(this);
		if(pp.getNormStr("operation")!=null){
			if(pp.getId()!=null){ 
			//	StewardUser su=new StewardUser(); 
				StewardUser su = StewardUser.dao.findById(pp.getId()); 
				//登录名
				/*String login_name= pp.getNormStr("login_name");
				if(login_name!=null)
					su.set("login_name", login_name);*/
				//密码
			/*	String password = pp.getNormStr("password");*/
			/*	if(password!=null && !password.equals("888888"))*/
				/*if(password!=null )
					su.set("password", MD5Util.MD5(password));*/
				//姓名
				String user_name = pp.getNormStr("user_name");
				if(user_name!=null)
					su.set("user_name", user_name);
				//头衔
				String job_title = pp.getNormStr("job_title");
				if(job_title!=null)
					su.set("job_title", job_title);
				//性别
				String gender = pp.getNormStr("gender");
				if(gender!=null)
					su.set("gender", gender);
				//电话
				String phone = pp.getNormStr("phone");
				if(phone!=null)
					su.set("phone", phone);
				//头像
				String head_img = this.getPara("head_img");
				if(head_img!=null)
					su.set("head_img", head_img);  
				//英文名
				String english_name = pp.getNormStr("english_name");
				if(english_name!=null)
					su.set("english_name", english_name);
				
				//qq
				String qq = pp.getNormStr("qq");
				if(qq!=null)
					su.set("qq", qq);
				
				//微信
				String wechat = pp.getNormStr("wechat");
				if(wechat!=null)
					su.set("wechat", wechat);
				 
				//电子邮箱
				String email =getPara("email");
				if(email!=null)
					su.set("email", email);
				
				//所在地区
				String province_id =getPara("province_id");
				if(province_id!=null)
					su.set("province_id", province_id);
				
				//个人简介
				String introduce = pp.getNormStr("introduce");
				if(introduce!=null)
					su.set("introduce", introduce);
				
				//修改创建时间
			/*	LoginUserInfo lui = this.getSessionAttr("LoginUserInfo");
				if(lui!=null){
					su.set("update_user",lui.getUserName());
				}*/
				su.set("update_time",StringUtil.yyyymmddhmsTime(new Date()));
				
				String release_mark = pp.getNormStr("release_mark");
				if(release_mark!=null)
					su.set("release_mark", release_mark);
				try {
					su.update();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				
				/***
				 * 跳转到跳出时列表页地址
				 */
				
				String fromurl = getPara("fromurl");
				this.redirect(fromurl);
			}
			else
			{
				StewardUser mu = StewardUser.dao.findById(pp.getId());
				if(mu!=null){
					//加载对象数据
					this.setAttr("stewardUser", mu);
					this.setAttr("operation", "编辑");
					 
					StringBuffer bf = new StringBuffer(); 
		            //性别 
		            bf.append("<select class=\"m-wrap span6\" name=\"gender\">");	
		            if(mu.getGender().equals("MALE"))
		            		bf.append("<option value=\"MALE\" selected>男</option>");	
		            else
		            		bf.append("<option value=\"MALE\">男</option>");	
		            if(mu.getGender().equals("FEMALE"))
		            		bf.append("<option value=\"FEMALE\" selected>女</option>");	
		            else
		            	bf.append("<option value=\"FEMALE\">女</option>");
		            bf.append("</select>");
		            this.setAttr("gender", bf.toString());
		            //获取中国的省份
		            List<AddressProvince> provinces = getProvinceInChina();
					this.setAttr("provinces",provinces); 
					
					 
					this.renderJsp("/views/stewards_opt.jsp");
				}
				else{
					this.renderText("无此数据!<a href='javascript:window.history.back()'>返回</a>", ContentType.HTML);
				}
			} 
	}
 
	
	///////////////////////树状图加载//////////////////////////////////////////////////////////////
	private String loadTree(){
		return loadTree("");
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
		if(mo!=null)
			return mo.getOrgId().toString();
		else
			return "0";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//新增移民管家  并且该管家不属于 公司员工///////////////////////////////////////////////////////////////////////
	
	
	
	
	
	public void add(){
		/**
		 * 创建参数解析类
		 */
		ParamsParser pp = new ParamsParser(this);
		 
			if(pp.getNormStr("operation")!=null){
				StewardUser mu =new  StewardUser();
				//登录名
				String login_name= pp.getNormStr("login_name");
				if(login_name!=null)
					mu.set("login_name", login_name);
				//密码
				String password = pp.getNormStr("password");
				if(password!=null && !password.equals("888888"))
					mu.set("password", MD5Util.MD5(password));
				//姓名
				String user_name = pp.getNormStr("user_name");
				if(user_name!=null)
					mu.set("user_name", user_name);
				//职称
				String job_title = pp.getNormStr("job_title");
				if(job_title!=null)
					mu.set("job_title", job_title);
				//性别
				String gender = pp.getNormStr("gender");
				if(gender!=null)
					mu.set("gender", gender);
				//电话
				String phone = pp.getNormStr("phone");
				if(phone!=null)
					mu.set("phone", phone);
				//头像
				String head_img = this.getPara("head_img");
				if(head_img!=null)
					mu.set("head_img", head_img);
				//部门
				String org_id = pp.getNormStr("org_id");
				if(org_id!=null){
					mu.set("dep_id", org_id);
					//获取所在公司
					String com_id = getComId(org_id);
					mu.set("com_id", com_id);
				}
				 
				
				//英文名
				String english_name = pp.getNormStr("english_name");
				if(english_name!=null)
					mu.set("english_name", english_name);
				
				//qq
				String qq = pp.getNormStr("qq");
				if(qq!=null)
					mu.set("qq", qq);
				
				//微信
				String wechat = pp.getNormStr("wechat");
				if(wechat!=null)
					mu.set("wechat", wechat);
				
				
				//电子邮箱
				String email =getPara("email");
				if(email!=null)
					mu.set("email", email);
				
				//所在地区
				String province_id =getPara("province_id");
				if(province_id!=null)
					mu.set("province_id", province_id);
				
				//个人简介
				String introduce = pp.getNormStr("introduce");
				if(introduce!=null)
					mu.set("introduce", introduce);
				
				//修改创建时间
				/*LoginUserInfo lui = this.getSessionAttr("LoginUserInfo");
				if(lui!=null){
					mu.set("update_user",lui.getUserName());
				}*/
				mu.set("update_time",StringUtil.yyyymmddhmsTime(new Date()));
				
				mu.set("release_mark", "0");
				mu.save();
				 
				/***
				 * 跳转到跳出时列表页地址
				 */
				
				String fromurl = getPara("fromurl");
				this.redirect(fromurl);
			}
			else
			{ 
				//加载对象数据 
				this.setAttr("operation", "新增"); 
				StringBuffer bf = new StringBuffer(); 
	            //性别 
	            bf.append("<select class=\"m-wrap span6\" name=\"gender\">");	 	 
        		bf.append("<option value=\"MALE\">男</option>");	 
        		bf.append("<option value=\"FEMALE\" selected>女</option>");	 
	            bf.append("</select>");
	            this.setAttr("gender", bf.toString());
	            //获取中国的省份
	            List<AddressProvince> provinces = getProvinceInChina();
				this.setAttr("provinces",provinces);  
				this.renderJsp("/views/stewards_opt.jsp"); 
			}
		}
	 

 
	
	
	
	
	
	
	
	/**
	 * 获取 管家评价数据字典列表 以及数据库相关数据
	 * @param list
	 * @return
	 */
	 public List<DictionaryInfo>  getDictionaryInfo(List<Record> list){
		 	DecimalFormat df = new DecimalFormat("#.##"); 
			String stewarditem = PropKit.use("constants.txt").get("stewarditem");
			String stewarditemitem = PropKit.use("constants.txt").get("stewarditemitem"); 
			 
			String service_attitude = PropKit.use("constants.txt").get("service_attitude"); 
			String specialized_knowledge = PropKit.use("constants.txt").get("specialized_knowledge"); 
			String feedback_efficiency = PropKit.use("constants.txt").get("feedback_efficiency"); 
		 
			String satisfied = PropKit.use("constants.txt").get("satisfied"); 
			String general = PropKit.use("constants.txt").get("general"); 
			String dissatisfied = PropKit.use("constants.txt").get("dissatisfied"); 
			 
			List<Dictionary> stewarditems = getDictionary(stewarditem); 
			List<DictionaryInfo> dictionaryInfos=new ArrayList<DictionaryInfo>(); 
			
			if(stewarditems.size()>0){
				for (int i = 0; i < stewarditems.size(); i++) {
					Dictionary dictionary = stewarditems.get(i);
					DictionaryInfo dictionaryInfo=new DictionaryInfo();
					dictionaryInfo.setName(dictionary.getName());
					dictionaryInfo.setDict_code(dictionary.getDictCode());
					dictionaryInfo.setDict_pcode(dictionary.getDictPcode());
					 
					List<Dictionary> stewarditemitems = getDictionary(stewarditemitem); 
					if(dictionary.getDictCode().equals(service_attitude)){ 
						int countTemp=0;double countTemp_1=0.000, countTemp_2=0.000, countTemp_3=0.000;
						for(Record rd:list){ 
							String serviceAttitude=(String) rd.get("service_attitude");
							if(satisfied.equals(serviceAttitude))
								countTemp_1++;
							if(general.equals(serviceAttitude))
								countTemp_2++;
							if(dissatisfied.equals(serviceAttitude))
								countTemp_3++; 
							countTemp++;
						}
						List<DictionaryInfo> dictionaryInfosItems=new ArrayList<DictionaryInfo>(); 
						for (int j = 0; j < stewarditemitems.size(); j++) {
							Dictionary stewarditemitemSS = stewarditemitems.get(j);
							DictionaryInfo dictionaryInfoItem=new DictionaryInfo();
							if(satisfied.equals(stewarditemitemSS.getDictCode())){
								
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_1/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes)); 
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(general.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_2/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes)); 
									
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(dissatisfied.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_3/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));   
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							dictionaryInfosItems.add(dictionaryInfoItem);
						} 
						dictionaryInfo.setDictionaryInfos(dictionaryInfosItems);
					}
					
					 
					if(dictionary.getDictCode().equals(specialized_knowledge)){ 
						int countTemp=0;double countTemp_1=0.000, countTemp_2=0.000, countTemp_3=0.000;
						for(Record rd:list){ 
							String specializedKnowledge=(String) rd.get("specialized_knowledge");
							if(satisfied.equals(specializedKnowledge))
								countTemp_1++;
							if(general.equals(specializedKnowledge))
								countTemp_2++;
							if(dissatisfied.equals(specializedKnowledge))
								countTemp_3++; 
							countTemp++;
						}
						List<DictionaryInfo> dictionaryInfosItems=new ArrayList<DictionaryInfo>(); 
						for (int j = 0; j < stewarditemitems.size(); j++) {
							Dictionary stewarditemitemSS = stewarditemitems.get(j);
							DictionaryInfo dictionaryInfoItem=new DictionaryInfo();
							if(satisfied.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_1/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));   
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(general.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_2/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));   

								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(dissatisfied.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_3/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));  
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							dictionaryInfosItems.add(dictionaryInfoItem);
						} 
						dictionaryInfo.setDictionaryInfos(dictionaryInfosItems);
					}
					
					 
					if(dictionary.getDictCode().equals(feedback_efficiency)){ 
						int countTemp=0;double countTemp_1=0.000, countTemp_2=0.000, countTemp_3=0.000;
						for(Record rd:list){ 
							String feedbackEfficiency=(String) rd.get("feedback_efficiency");
							if(satisfied.equals(feedbackEfficiency))
								countTemp_1++;
							if(general.equals(feedbackEfficiency))
								countTemp_2++;
							if(dissatisfied.equals(feedbackEfficiency))
								countTemp_3++; 
							countTemp++;
						}
						
						List<DictionaryInfo> dictionaryInfosItems=new ArrayList<DictionaryInfo>(); 
						for (int j = 0; j < stewarditemitems.size(); j++) {
							Dictionary stewarditemitemSS = stewarditemitems.get(j);
							DictionaryInfo dictionaryInfoItem=new DictionaryInfo();
							if(satisfied.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_1/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));  
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(general.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_2/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));  
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							if(dissatisfied.equals(stewarditemitemSS.getDictCode())){ 
								dictionaryInfoItem.setName(stewarditemitemSS.getName());
								if(countTemp!=0){
									Double te=0.0;
									te=countTemp_3/countTemp*100; 
									String tes=df.format(te);
									dictionaryInfoItem.setPercentage(Double.parseDouble(tes));  
								}else{
									dictionaryInfoItem.setPercentage(0.00); 
								} 
							}
							dictionaryInfosItems.add(dictionaryInfoItem);
						} 
						dictionaryInfo.setDictionaryInfos(dictionaryInfosItems);
					}
					
					dictionaryInfos.add(dictionaryInfo);
				}
			
				
			}
			 
			return dictionaryInfos; 
	 }	 
	 
	
	 
 }

