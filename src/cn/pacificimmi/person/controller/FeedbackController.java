package cn.pacificimmi.person.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import cn.pacificimmi.common.interceptor.BindingInterceptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.CustinfoFeedback;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.utils.WeiXinConst;

@Before(BindingInterceptor.class)
public class FeedbackController extends Controller {
	private static Logger log = LoggerFactory.getLogger(FeedbackController.class);
	
	/**
	 * 客户反馈
	 */
	public void index() {
		List<Dictionary> selList = Dictionary.dao.find("select dict_code, name from console_dictionary where "+
					"delete_flag=0 and dict_code like '"+PropKit.use("constants.txt").get("custinfo_feedback")+"%' and  length(dict_code)=12 order by sort asc");
		this.setAttr("selList", selList);
		this.renderJsp("/WEB-INF/jsp/person/person-feedback.jsp");
	}
	
	/**
	 * 保存
	 */
	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		Integer custinfoId = custinfo.getCustinfoId();
		
		CustinfoFeedback cf = new CustinfoFeedback();
		cf.setCustinfoId(custinfoId);
		cf.setCreateTime(new Date());
		cf.setVersion(PropKit.use("constants.txt").get("v_version"));
		String contact_way = this.getPara("contact_way");
		if(null != contact_way && !"".equals(contact_way.trim())) {
			cf.set("contact_way", contact_way.trim());
		}
		String feedback = this.getPara("feedback");
		if(null != feedback && !"".equals("feedback")) {
			cf.set("feedback", feedback);
		}
		String profession_degree = this.getPara("profession_degree");
		if(null != profession_degree && !"".equals(profession_degree)) {
			cf.set("profession_degree", profession_degree);
		}
		String call_back = this.getPara("call_back");
		if(null != call_back && !"".equals(call_back)) {
			cf.set("call_back", call_back);
		}
		String message = this.getPara("message");
		if(null != message && !"".equals(message)) {
			cf.set("message", message);
		}
		
		cf.save();
		
		map.put("status", "0");
		map.put("msg", "保存成功");
		this.renderJson(map);
	}
	
}
