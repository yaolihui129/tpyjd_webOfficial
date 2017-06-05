package cn.pacificimmi.common.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.BindingInterceptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.User;
import cn.pacificimmi.common.utils.HttpUtils;
/**
 * 呼叫中心
 * @author Gorge
 * @date : 20170227
 **/
@Before(BindingInterceptor.class)
public class CallCenterController extends Controller {
	private static Logger log = LoggerFactory.getLogger(CallCenterController.class);

	//默认webcall
	@Clear
	public void index(){
		ParamsParser pp = new ParamsParser(this);
		boolean canCall = true;
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			String url = "http://121.40.153.25/command?Action=Webcall&Account=N00000011728&PBX=bj.ali.8.4&Timeout=20";
			Custinfo caller = Custinfo.dao.findById(pp.getAllStr("Caller"));//主叫-webcall接口的被叫
			if(null == caller || caller.getPhoneNum().isEmpty()){
				canCall = false;
				result.put("code", 100);
				result.put("msg", "主叫号码错误");
			}else{
				url += "&Exten=" + caller.getPhoneNum();
			}
			
			String calledId = pp.getAllStr("Called");
			log.debug("被叫用户的ID："+calledId);
			User called = User.dao.findById(calledId);//被叫
			if(null != called && !called.getPhone().isEmpty()){
				url += "&ServiceNo=01011113335&Variable=phoneNum:"+called.getPhone();//指定被叫
			}else{
				url += "&ServiceNo=01011113334";//不指定被叫，有callcenter系统分配
			}
			if(canCall){
				String res = HttpUtils.doGet(url);
				JSONObject json = JSONObject.parseObject(res);
				if(json.get("Message").equals(4)){
					result.put("code", 0);
					result.put("msg", "已接听");
				}else{
					result.put("code", 1);
					result.put("msg", "为正常接听");
				}
				this.renderJson(result);
			}else{
				this.renderJson(result);
			}
		}catch(Exception e){
			log.error("拨打电话错误"+e.getMessage(), e);
			result.put("code", 100);
			result.put("msg", "服务器异常");
			this.renderJson(result);
		}
		
	} 
	
	
}
