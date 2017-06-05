package cn.pacificimmi.common.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**资讯
 * @author Gorge
 * 2017年5月19日
 */
@Before(MenuInterCeptor.class)
public class InformationController extends Controller {
	private static Logger log = LoggerFactory.getLogger(InformationController.class);
	/**
	 * 
	 */
	public void index(){
		log.debug("page to index.jsp");
	}
	
	/**
	 * 预览
	 */
	public void preview(){
		ParamsParser pp = new ParamsParser(this);
		Record r = Db.findById("crm_information_article", "info_id", pp.getId());
		this.setAttr("article", r);
		this.setAttr("pos_nav_name", r.getStr("section_name"));
		this.renderJsp("/views/information/preview.jsp");
	}
	
}
