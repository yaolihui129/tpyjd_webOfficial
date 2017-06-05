package cn.pacificimmi.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.MenuInfo;
import cn.pacificimmi.common.models.Copyright;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.FriendlyLink;
import cn.pacificimmi.common.models.NavMenu;
import cn.pacificimmi.common.models.PageFooters;
import cn.pacificimmi.common.models.PageHeaders;
import cn.pacificimmi.common.models.TopicSetting;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.common.utils.WeiXinConst;

public class MenuInterCeptor implements Interceptor {
	
	private static String SQL = "SELECT "+
			"nm.nav_menu_id, "+
			"nm.menu_name, "+
			"nm.menu_url, "+
			"nm.open_mode, "+
			"nm.dict_code, "+
			"nm.menu_type "+
		"FROM "+
			"official_nav_menu nm "+
		"WHERE "+
			"nm.delete_flag = 0";
	
	@Override
	public void intercept(Invocation inv) {
		Controller ctr = inv.getController();
		/**
		 * 显示菜单选项
		*/
		List<Record> list = Db.find("SELECT "+
				"nm.nav_menu_id, "+
				"nm.menu_name, "+
				"nm.menu_url, "+
				"nm.open_mode, "+
				"nm.menu_type "+
			"FROM "+
				"official_nav_menu nm "+
			"WHERE "+
				"nm.delete_flag = 0 "+
			"AND nm.menu_status="+PropKit.use("constants.txt").get("menu_status_yfb")+
			" AND nm.menu_level = 1 "+
			"ORDER BY "+
				"nm.priority DESC");
		List<MenuInfo> result = new ArrayList<MenuInfo>();
		for(Record rd:list) {
			MenuInfo mi = new MenuInfo();
			mi.bindingData(mi, rd);
			List<Record> list2 = Db.find(SQL+" AND nm.menu_pid="+mi.getNav_menu_id()+" ORDER BY nm.priority DESC");
			if(null != list2 && list2.size()>0) {
				List<MenuInfo> two = new ArrayList<MenuInfo>();
				for(Record r:list2) {
					MenuInfo t = new MenuInfo();
					t.bindingData(t, r);
					List<Record> list3 = Db.find(SQL+" AND nm.menu_pid="+t.getNav_menu_id()+" ORDER BY nm.priority DESC");
					if(null != list3 && list3.size()>0) {
						List<MenuInfo> three = new ArrayList<MenuInfo>();
						for(Record d:list3) {
							MenuInfo i = new MenuInfo();
							i.bindingData(i, d);
							three.add(i);
						}
						t.setSubMenuList(three);
					}
					two.add(t);
				}
				mi.setSubMenuList(two);
			}
			result.add(mi);
		}
		
		ctr.setAttr("menu", result);
		ctr.setAttr("menu_type_cdl", PropKit.use("constants.txt").get("menu_type_cdl"));
		ctr.setAttr("menu_type_gj", PropKit.use("constants.txt").get("menu_type_gj"));
		ctr.setAttr("menu_type_xm", PropKit.use("constants.txt").get("menu_type_xm"));
		ctr.setAttr("open_mode_blank", PropKit.use("constants.txt").get("open_mode_blank"));
		
		//顶部信息
		PageHeaders ph = PageHeaders.dao.findFirst("select * from official_page_headers");
		List<FriendlyLink> flList = FriendlyLink.dao.find("select * from official_friendly_link where delete_flag=0 and link_type="+PropKit.use("constants.txt").get("link_type_branch"));
		TopicSetting topicSetting = TopicSetting.dao.findFirst("select ts.official_number, ts.official_frequency from crm_topic_setting ts");
		
		ctr.setAttr("topicSetting", topicSetting);
		ctr.setAttr("branch", flList);
		ctr.setAttr("headers", ph);
		
		//底部信息
		PageFooters pf = PageFooters.dao.findFirst("select * from official_page_footers");
		List<FriendlyLink> fList = FriendlyLink.dao.find("select * from official_friendly_link where delete_flag=0 and  link_type="+PropKit.use("constants.txt").get("link_type_friendly"));
		Copyright cp = Copyright.dao.findFirst("select * from official_copyright");
		
		ctr.setAttr("copy", cp);
		ctr.setAttr("friendly", fList);
		ctr.setAttr("footers", pf);
		
		String currentPath = ctr.getRequest().getServletPath();
		if(StringUtils.notEmpty(currentPath)) {
			int ind = currentPath.indexOf("?");
			if(ind>0) {
				currentPath = currentPath.trim().substring(0, ind);
			}
			List<NavMenu> banner = new ArrayList<NavMenu>();
			
			if(currentPath.startsWith(PropKit.use("debug_config.txt").get("PROJECT_URL"))){ //项目
				NavMenu nm = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url FROM official_nav_menu nm "+
								"WHERE nm.delete_flag = 0 AND nm.menu_level = 1 AND nm.menu_type = "+PropKit.use("constants.txt").get("menu_type_xm"));
				NavMenu sy = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url "+
								"FROM official_nav_menu nm WHERE nm.delete_flag = 0 AND nm.menu_level = 1 AND nm.menu_url = '/' AND nm.menu_type = "+PropKit.use("constants.txt").get("menu_type_cdl"));
			
				if(null != nm) {
					if(null != sy) {
						banner.add(sy);
					}
					banner.add(nm);
				}
			} else if(currentPath.startsWith(PropKit.use("debug_config.txt").get("COUNTRY_URL"))) { //国家
				NavMenu nm = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url FROM official_nav_menu nm "+
						"WHERE nm.delete_flag = 0 AND nm.menu_level = 1 AND nm.menu_type = "+PropKit.use("constants.txt").get("menu_type_gj"));
				NavMenu sy = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url "+
								"FROM official_nav_menu nm WHERE nm.delete_flag = 0 AND nm.menu_level = 1 AND nm.menu_url = '/' AND nm.menu_type = "+PropKit.use("constants.txt").get("menu_type_cdl"));
				if(null != nm) {
					if(null != sy) {
						banner.add(sy);
					}
					banner.add(nm);
				}
			} else if(!currentPath.equals("/")) { //菜单
				NavMenu nm = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url, nm.menu_pid FROM official_nav_menu nm "+
						" WHERE nm.delete_flag = 0 and nm.menu_url = '"+currentPath+"'");
				if(null == nm) {
					int pos = currentPath.lastIndexOf("/");
					if(pos>0) {
						currentPath = currentPath.substring(0, pos); 
						nm = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url, nm.menu_pid FROM official_nav_menu nm "+
								" WHERE nm.delete_flag = 0 and nm.menu_url = '"+currentPath+"'");
					}
				}
				//首页
				NavMenu sy = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url "+
						"FROM official_nav_menu nm WHERE nm.delete_flag = 0 AND nm.menu_level = 1 AND nm.menu_url = '/' AND nm.menu_type = "+PropKit.use("constants.txt").get("menu_type_cdl"));
				
				if(null != nm) {
					if(null != sy) {
						banner.add(sy);
					}
					if(null != nm.getMenuPid()) {
						NavMenu pm = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url, nm.menu_pid FROM official_nav_menu nm "+
								" WHERE nm.delete_flag = 0 AND nm.nav_menu_id = "+nm.getMenuPid());
						if(null != pm) {
							if(null != pm.getMenuPid()) {
								NavMenu p = NavMenu.dao.findFirst("SELECT nm.nav_menu_id, nm.menu_name, nm.menu_url, nm.menu_pid FROM official_nav_menu nm "+
										" WHERE nm.delete_flag = 0 AND nm.nav_menu_id = "+pm.getMenuPid());
								if(null != p) {
									banner.add(p);
								}
							}
							banner.add(pm);
						}
					}
					banner.add(nm);
				}
			}
			Custinfo custinfo = ctr.getSessionAttr(WeiXinConst.CUSTINFO);
			if(null!= custinfo){
				ctr.setAttr("custinfo", custinfo);
			}
			ctr.setAttr("banner", banner);
		}
		inv.invoke();
	}
	
}
