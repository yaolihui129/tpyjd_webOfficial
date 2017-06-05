package cn.pacificimmi.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.CustinfoSecurity;
import cn.pacificimmi.common.utils.CommonHelper;
import cn.pacificimmi.common.utils.ConstantUtil;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.common.utils.WeiXinConst;
import cn.pacificimmi.common.utils.WeiXinUtils; 

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 * @author lijinglun
 * 校验用户是否进行绑定
 * 使用场景:
 * 1、微网站首页（移民评估、制定方案、我要团购、专属管家）按钮被点击时校验
 */
public class BindingInterceptor implements Interceptor {
	private static Logger log = LoggerFactory.getLogger(BindingInterceptor.class);
	@Override
	public void intercept(Invocation inv) {
		log.debug("进入拦截器");
		Controller ctr = inv.getController();
		//Object attribute = session.getAttribute(WeiXinConst.COOKIE_PRE_URL);
		HttpServletRequest request = ctr.getRequest();
		String requestURI = request.getRequestURI();
		Custinfo custinfo = ctr.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null!= custinfo){
			ctr.removeSessionAttr(WeiXinConst.PRE_URL);
			ctr.setAttr("custinfo", custinfo);
			inv.invoke(); 
		}else{
			ctr.removeSessionAttr(WeiXinConst.PRE_URL);
			ctr.setSessionAttr(WeiXinConst.PRE_URL, requestURI);
			ctr.redirect("/");  
		}
	} 
	public String getRealPath(String path){
		int pos = path.lastIndexOf("/");
		if(pos>0){
			path=path.substring(0, pos);
		}
		else
			path="/";
		return path;
	}
	public BindingInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	
}


 
/*if(null!=phoneNo||"".equals(phoneNo))
phoneNo = CookieUtil.getValueByName("phone", request);*/

/*
String searchClientSqlByPhone="SELECT * FROM crm_custinfo WHERE phone_num='"+phoneNo+"'";
Custinfo findFirst = Custinfo.dao.findFirst(searchClientSqlByPhone); 

if(CommonHelper.isNullOrEmpty(findFirst)){ 
	ctr.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login/register.jsp"); 
}else{
	String getCustSecurityByCustIdSQL="SELECT * FROM crm_custinfo_security AS cs WHERE  cs.custinfo_id='"+findFirst.getCustinfoId()+"'";
	List<CustinfoSecurity> securityList  = CustinfoSecurity.dao.find(getCustSecurityByCustIdSQL);
	if(CommonHelper.isNullOrEmpty(securityList)){
		ctr.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login/register.jsp"); 
	}
	Date createTime = securityList.get(0).getCreateTime();
	Date nowTime = new Date();
	long t1 = nowTime.getTime();
	long t2 =createTime.getTime();
	long diffTime = Math.abs(t1-t2)/(1000*60);
	System.out.println((securityList.get(0).getSecurityNo()).toString().equals(securityNo));
//	if((securityList.get(0).getSecurityNo()).toString().equals(securityNo) && diffTime<=5){
	if("0000".equals(securityNo)){
		log.debug("开始获取用户OpenID");
		System.out.println("用户OpenId");
		CookieUtil.delete(WeiXinConst.COOKIE_PHONE, request, response); 
		CookieUtil.delete(WeiXinConst.COOKIE_PC_ID, request, response);
		CookieUtil.add(WeiXinConst.COOKIE_PHONE, phoneNo, request, response);
		CookieUtil.add(WeiXinConst.COOKIE_PC_ID, findFirst.getCustinfoId().toString(), request, response);
		if(StringUtils.isEmpty(findFirst.getWxName())){
			codeUrl = WeiXinUtils.getUserInfoUrl(WeiXinConst.testBaseUrl+"/oauth2/userInfo",phoneNo);
		}else{
			codeUrl = WeiXinUtils.getUserInfoUrl(WeiXinConst.testBaseUrl+"/oauth2/openId",phoneNo);
		}
		ctr.redirect(ConstantUtil.TPYJD_WX_BASE_URL+""+codeUrl);	 
	}else{
		ctr.renderJsp(ConstantUtil.TPYJD_WX_JSP_BASE_PATH + "login/register.jsp"); 
	}
	
} */
