package cn.pacificimmi.common;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 548463495038003000L;
	private String icon="";
	private String href="";
	private String title="";
	private String id="";
	private String resType="";
	
	private ArrayList<Menu> subMenu = new ArrayList<Menu>();

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(ArrayList<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	/**
	 * 返回menu页面html
	 * @return
	 */
	public String getMenuHtml(String path){
		StringBuffer bf = new StringBuffer();
		if(href.equals("/")){
			if(path.equals("/"))
				bf.append("<li class=\"active\" data-code=\""+id+"\">");
			else
				bf.append("<li data-code=\""+id+"\">");
		}
		else{
			if(path.startsWith(href)){
				bf.append("<li class=\"active\" data-code=\""+id+"\">");
			}else
				bf.append("<li data-code=\""+id+"\">");
		}
		bf.append("<a href=\""+href+"\">");
		bf.append("<i class=\""+icon+"\"></i>");
		bf.append("<span class=\"title\">"+title+"</span>");
		bf.append("<span class=\"arrow \"></span>");
		bf.append("</a>");
		if(subMenu.size()>0){
			bf.append("<ul class=\"sub-menu\">");
			for(Menu menu:subMenu){
				bf.append(menu.getMenuHtml(path));
			}
			bf.append("</ul>");
		}
		
		bf.append("</li>");
		return bf.toString();
	}
}
