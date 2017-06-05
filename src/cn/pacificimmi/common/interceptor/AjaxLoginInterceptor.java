package cn.pacificimmi.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.utils.WeiXinConst;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * @author Gorge 
 * 校验用户是否已登录
 * 使用场景:
 * 门户直播的报名申请等操作
 */
public class AjaxLoginInterceptor implements Interceptor {
	private static Logger log = LoggerFactory.getLogger(AjaxLoginInterceptor.class);
	@Override
	public void intercept(Invocation inv) {
		log.debug("进入拦截器");
		Controller ctr = inv.getController();
		Custinfo custinfo = ctr.getSessionAttr(WeiXinConst.CUSTINFO);
		if(null!= custinfo){
			ctr.removeSessionAttr(WeiXinConst.PRE_URL);
			inv.invoke(); 
		}else{
			ctr.removeSessionAttr(WeiXinConst.PRE_URL); 
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("status", 2);
			ctr.renderJson(rlt);
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
	public AjaxLoginInterceptor() {
		// TODO Auto-generated constructor stub
	}
}