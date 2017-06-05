
package cn.pacificimmi.common.controllers;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.CustinfoSecurity;
import cn.pacificimmi.common.utils.CommonHelper;
import cn.pacificimmi.common.utils.CrmCustinfoUtils;
import cn.pacificimmi.common.utils.MD5Util;
import cn.pacificimmi.common.utils.MajorKeyFactory;
import cn.pacificimmi.common.utils.WeiXinConst;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年11月8日 下午1:25:40
 **/

public class RegisterController  extends Controller{
	private static Logger log = LoggerFactory.getLogger(RegisterController.class);
	/**
	 * 注册
	 */
	public void index(){  
		String phone = (String)this.getPara("phone"); 
		String password = (String)this.getPara("password");
		String security = (String)this.getPara("security");
		//判断是否为开发状态
		String product = PropKit.use("debug_config.txt").get("product");
		if("1".equals(product)){
			String securitySql = "select * from crm_custinfo_security where security_no = "+security+" and phone_no = "+phone;
			CustinfoSecurity cs = CustinfoSecurity.dao.findFirst(securitySql);
			if(CommonHelper.isNullOrEmpty(cs)){
				this.setAttr("status", 1);
				this.setAttr("msg", "验证码不正确！");
				this.renderJson();
				return;
			}
		}
		//测试状态直接保存用户信息
		String searchClientSqlByPhone="SELECT * FROM crm_custinfo WHERE phone_num='"+phone+"' and delete_flag='0'";
		Custinfo findFirst = Custinfo.dao.findFirst(searchClientSqlByPhone); 
		//目前先不在客户信息表存客户信息
		if(CommonHelper.isNullOrEmpty(findFirst)){
			findFirst = new Custinfo();
			findFirst.setPhoneNum(phone);
			findFirst.setPassword(MD5Util.MD5(password));
			findFirst.setCreateTime(new Date());
			findFirst.setCustSource(PropKit.use("constants.txt").get("cust_source_mhzc"));//客户来源-门户注册
			findFirst.setLoginName("ym_"+MajorKeyFactory.getInstance().getMajorKey());
			findFirst.save();
			//TODO 目前没有好的解决方案应对同步crm出错的问题。。。
			CrmCustinfoUtils.saveCustinfoToCrm(findFirst);
			//保存登陆状态
			this.setSessionAttr(WeiXinConst.CUSTINFO,findFirst);
			this.setAttr("status", 0);
			this.setAttr("custinfo", findFirst);
			this.renderJson();
		}else{
			this.setAttr("status", 1);
			this.setAttr("msg", "该手机号已被占用！");
			this.renderJson();
		}
	}
	/**
	 * 登录
	 */
	public void login(){
		String phone = (String)this.getPara("phone"); 
		String password = (String)this.getPara("password");
		String searchClientSqlByPhone="SELECT * FROM crm_custinfo WHERE phone_num='"+phone+"' and password = '"+MD5Util.MD5(password)+"' and delete_flag='0'";
		Custinfo findFirst = Custinfo.dao.findFirst(searchClientSqlByPhone); 
		//目前先不在客户信息表存客户信息
		if(CommonHelper.isNullOrEmpty(findFirst)){
			this.setAttr("status", 1);
			this.setAttr("msg", "密码不正确");
			this.renderJson();
		}else{
			this.setAttr("status", 0);
			this.setAttr("custinfo",findFirst);
			this.setSessionAttr(WeiXinConst.CUSTINFO, findFirst);
			this.renderJson();
		}
	}
	/**
	 * 重置密码
	 */
	public void reset(){
		String phone = (String)this.getPara("phone"); 
		String password = (String)this.getPara("password");
		String security = (String)this.getPara("security");
		//判断是否为开发状态
		String product = PropKit.use("debug_config.txt").get("product");
		if("1".equals(product)){
			String securitySql = "select * from crm_custinfo_security where security_no = "+security+" and phone_no = "+phone;
			CustinfoSecurity cs = CustinfoSecurity.dao.findFirst(securitySql);
			if(CommonHelper.isNullOrEmpty(cs)){
				this.setAttr("status", 1);
				this.setAttr("msg", "验证码不正确！");
				this.renderJson();
				return;
			}
		}
		//测试状态直接保存用户信息
		String searchClientSqlByPhone="SELECT * FROM crm_custinfo WHERE phone_num='"+phone+"' and delete_flag='0'";
		Custinfo findFirst = Custinfo.dao.findFirst(searchClientSqlByPhone); 
		if(CommonHelper.isNullOrEmpty(findFirst)){
			this.setAttr("status", 1);
			this.setAttr("msg", "该手机号不存在！");
			this.renderJson();
		}else{
			findFirst.setPassword(MD5Util.MD5(password));
			findFirst.setUpdateTime(new Date());
			findFirst.update();
			//保存登陆状态
			this.setSessionAttr(WeiXinConst.CUSTINFO,findFirst);
			this.setAttr("custinfo",findFirst);
			this.setAttr("status", 0);
			this.renderJson();
		}
	}
	/**
	 * 修改密码
	 */
	public void changePassword(){
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null != custinfo){
			String password = (String)this.getPara("password"); 
			String newPassword = (String)this.getPara("newPassword");
			String operation = (String)this.getPara("operation");
			custinfo = Custinfo.dao.findById(custinfo.getCustinfoId());
			this.setAttr("menu_on", "password");
			if(StringUtils.isEmpty(operation)){
				this.setAttr("custinfo", custinfo);
				this.setAttr("operation", "保存");
				this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"person/person_password.jsp");
				return;
			}
			if(StringUtils.isEmpty(custinfo.getPassword()) || !(custinfo.getPassword().equals(MD5Util.MD5(password)))){
				this.setAttr("status", 1);
				this.setAttr("msg", "原密码错误！");
				this.renderJson();
				return;
			}
			custinfo.setPassword(MD5Util.MD5(newPassword));
			custinfo.setUpdateTime(new Date());
			custinfo.update();
			this.setAttr("status", 0);
			this.setAttr("custinfo",custinfo);
			this.renderJson();
		}else{
			/**
			 * 未登录
			 */
			this.renderJsp(PropKit.use("debug_config.txt").get("TPYJD_PORTALS_JSP_BASE_PATH")+"index.jsp");
		}
	}
	
}
