package cn.pacificimmi.pcTopic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.CustinfoMessage;
import cn.pacificimmi.common.utils.StringUtils;

/**
 * pc专题
 * @author yang
 * @date 2017年6月1日 上午11:33:53
 */
@Before(MenuInterCeptor.class)
public class PcTopicController extends Controller {
	
	public void index() {
		String jsp = this.getPara(0);
		if(StringUtils.notEmpty(jsp)) {
			this.setAttr("url", "/pcTopic/"+jsp);
			this.renderJsp("/views/pcTopic/"+jsp+".jsp");
		} else {
			this.renderJsp("/404.html");
		}
	}
	
	/**
	 * 保存提交信息
	 */
	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = this.getPara("name");
		String phone = this.getPara("phone");
		String content = this.getPara("content");
		String source = this.getPara("source");
		String url = this.getPara("url");
		
		if(StringUtils.notEmpty(name) && StringUtils.notEmpty(phone)) {
			CustinfoMessage cm = new CustinfoMessage();
			cm.set("name", name);
			cm.set("phone", phone);
			cm.set("content", content);
			cm.set("source", source);
			cm.set("url", url);
			cm.set("delete_flag", 0);
			cm.set("create_time", new Date());
			cm.save();
			
			map.put("status", 0);
			map.put("msg", "提交成功");
		} else {
			map.put("status", 1);
			map.put("msg", "参数不正确");
		}
		this.renderJson(map);
	}
	
}
