package cn.pacificimmi.common.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;  
import com.jfinal.kit.PropKit;

import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.CustinfoSecurity;
import cn.pacificimmi.common.utils.CommonHelper;
import cn.pacificimmi.common.utils.ConstantUtil;
import cn.pacificimmi.common.utils.MsgUtils;
import cn.pacificimmi.steward.models.view.UserInfoJson;
/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年11月8日 下午12:03:23
 **/
public class MsgController extends Controller {
	private static Logger log = LoggerFactory.getLogger(MsgController.class);
	/**
	 * 验证码发送及注册
	 */
	public void index(){
		String phoneNum="";
		phoneNum = (String)this.getPara("phoneNo"); 
//		Map<String,Object> maps = new HashMap<String,Object>(); 
		Integer securityNo = checkCode();   
		try {
			String product = PropKit.use("debug_config.txt").get("product");
			String reStr = "";
			if("1".equals(product)){
				reStr = MsgUtils.send(phoneNum, securityNo);
			}else{
				reStr = "0000000000000000";
			}
			if("0".equals(String.valueOf(reStr.charAt(15)))){
				log.debug("发送短信成功");
				//发送验证码时，不需要默认注册用户，存储和验证码的关系时，id变为手机号，验证码校验时 根据手机号获取验证码即可 by Gorge 20170221 start
				//用手机号查是否有对应客户信息，没有新建一条数据，然后在验证码表中增加一条记录
				//注释掉了，发送验证码的时候不判断手机号重复
//				Custinfo custinfo = Custinfo.dao.findFirst("SELECT * FROM crm_custinfo AS cust WHERE  cust.phone_num='"+phoneNum+"' and delete_flag='0'"); 
				//发送验证码时，不需要默认注册用户，验证码校验时 根据手机号获取验证码即可 by Gorge 20170221 start
				
				CustinfoSecurity cs = new CustinfoSecurity();
//				if(custinfo != null){
//					this.setAttr("status", 1);
//					this.setAttr("msg", "该手机号已被占用！");
//					this.renderJson();
//					return;
//				}else{
//					cs.setCustinfoId(0);
//				}
				cs.setCustinfoId(0);
				cs.setPhoneNo(phoneNum);
				cs.setSecurityNo(securityNo.toString());
				cs.setCreateTime(new Date());
				cs.save();
//				maps.put("status", "success"); 
				this.setAttr("status", 0);
				this.renderJson();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	public int checkCode(){
		Random random = new Random();
		int code = 0;
		do{
			code = random.nextInt(999999);
		}while(code <= 100000);
		return  code;
	}
}
