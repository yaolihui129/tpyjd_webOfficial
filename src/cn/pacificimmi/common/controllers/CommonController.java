package cn.pacificimmi.common.controllers;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.utils.MemcacheKit;

public class CommonController extends Controller {
	@SuppressWarnings("unchecked")
	public void about(){
		Integer oanid = this.getParaToInt(0);
		String sql = "select * from official_about_nav order by sort asc";
		List<Record> list ;
		
		if(MemcacheKit.get("oan")!=null){
			list = (List<Record>)MemcacheKit.get("oan");
		}
		else{
			list = Db.find(sql);
			MemcacheKit.set("oan",list,3600);
		}
		StringBuffer bf = new StringBuffer();
		
		bf.append("document.write('");
		for(Record r:list){
			String name = r.getStr("nav_name");
			String url = r.getStr("nav_url");
			int oan_id = r.getInt("oan_id");
			if(oanid!=null && oanid==oan_id)
				bf.append("<li class=\"color\"><a href=\""+url+"\">");
			else
				bf.append("<li><a href=\""+url+"\">");
			bf.append(name).append("</a></li>");
		}
		bf.append("');");
		
		this.renderJavascript(bf.toString());
	}
}
