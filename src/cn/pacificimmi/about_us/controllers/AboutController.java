package cn.pacificimmi.about_us.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.interceptor.AboutUsInterceptor;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;

/**关于我们
 * @author Gorge
 * 2017年5月17日
 */
@Before({MenuInterCeptor.class,AboutUsInterceptor.class})
public class AboutController extends Controller{
	private static Logger log = LoggerFactory.getLogger(AboutController.class);
	
	public void index(){
		log.debug("forward to page index.jsp");
	}
	
	/**
	 * 公司简介
	 */
	public void profile(){
		this.setAttr("profile", Db.findFirst("select value from official_configs where code = '公司简介' and status = '已发布'"));
		this.renderJsp("/views/aboutus/company-profile.jsp");
	}
	
	/**
	 * 公司简介预览
	 */
	public void profilePreview(){
		this.setAttr("profile", Db.findFirst("select value from official_configs where code = '公司简介'"));
		this.renderJsp("/views/aboutus/company-profile.jsp");
	}
	
	/**
	 * 诚邀合作
	 */
	public void cooperation(){
		this.setAttr("cooperation", Db.findFirst("select value from official_configs where code = '诚邀合作' and status = '已发布'"));
		this.renderJsp("/views/aboutus/Invite-cooperation.jsp");
	}
	
	/**
	 * 诚邀合作预览
	 */
	public void cooperationPreview(){
		this.setAttr("cooperation", Db.findFirst("select value from official_configs where code = '诚邀合作'"));
		this.renderJsp("/views/aboutus/Invite-cooperation.jsp");
	}
	
	/**
	 * 投诉建议
	 */
	public void suggestions(){
		this.setAttr("suggestions", Db.findFirst("select value from official_configs where code = '投诉建议' and status = '已发布'"));
		this.renderJsp("/views/aboutus/suggestions.jsp");
	}
	
	/**
	 * 投诉建议预览
	 */
	public void suggestionsPreview(){
		this.setAttr("suggestions", Db.findFirst("select value from official_configs where code = '投诉建议'"));
		this.renderJsp("/views/aboutus/suggestions.jsp");
	}
	
	/**
	 * 荣誉资质
	 */
	public void honors(){
		List<Record> honorList = Db.find("select title,oh_id from official_honor where delete_flag = 0 and status = '已发布' order by sort asc");
		for(Record honor : honorList){
			List<Record> imgs = Db.find("select ohi_id,oh_id,name,img_url from official_honor_imgs where delete_flag = 0 and oh_id = "+honor.getInt("oh_id")+" order by sort desc");
			honor.set("imgs", imgs);
		}
		this.setAttr("honorList", honorList);
		this.renderJsp("/views/aboutus/honors.jsp");
	}
	
	/**
	 * 荣誉资质
	 */
	public void honorsPreview(){
		List<Record> honorList = Db.find("select title,oh_id from official_honor where delete_flag = 0  order by sort asc");
		for(Record honor : honorList){
			List<Record> imgs = Db.find("select ohi_id,oh_id,name,img_url from official_honor_imgs where delete_flag = 0 and oh_id = "+honor.getInt("oh_id")+" order by sort desc");
			honor.set("imgs", imgs);
		}
		this.setAttr("honorList", honorList);
		this.renderJsp("/views/aboutus/honors.jsp");
	}

}
