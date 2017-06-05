package cn.pacificimmi.about_us.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.interceptor.AboutUsInterceptor;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.utils.MemcacheKit;

@Before({MenuInterCeptor.class,AboutUsInterceptor.class})
public class ContactUsController extends Controller {
	
	public void index(){
		String area = this.getPara(0);
		if(area==null)
			area="beijing";
		setAttr("area",area);
		setAttr("beijing",getContent("北京",true));
		setAttr("domestic",getContent("国内分公司",true));
		setAttr("foreign",getContent("国外分公司",true));
		this.renderJsp("/views/aboutus/Contact-us.jsp");
	}
	
	public void preview(){
		setAttr("beijing",getContent("北京",false));
		setAttr("domestic",getContent("国内分公司",false));
		setAttr("foreign",getContent("国外分公司",false));
		this.renderJsp("/views/aboutus/Contact-us.jsp");
	}
	
	private String getContent(String code,boolean flag){
		if(MemcacheKit.get(code)!=null){
			return (String)MemcacheKit.get(code);
		}
		else{
			Record r ;
			if(flag)
				r = Db.findFirst("select * from official_configs where code='"+code+"'  and status = '已发布'");
			else
				r = Db.findFirst("select * from official_configs where code='"+code+"'");
			if(r!=null){
				String content = r.getStr("value");
				if(content!=null && !content.isEmpty()){
					MemcacheKit.set(code,content,3600);
					return content;
				}
				else
					return "";
			}
			else
				return "";
		}
	}
}
