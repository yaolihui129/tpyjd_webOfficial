package cn.pacificimmi.common;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;

public class MenuInfo extends ComplexModel<MenuInfo> {
	
	/**
	 * 导航菜单标识
	 */
	private Integer nav_menu_id;
	
	/**
	 * 菜单名称
	 */
	private String menu_name;
	
	/**
	 * 链接地址
	 */
	private String menu_url;
	
	/**
	 * 打开方式
	 */
	private String open_mode;
	
	/**
	 * 绑定选择
	 */
	private String dict_code;
	
	/**
	 * 导航类型
	 */
	private String menu_type;
	
	/**
	 * 子菜单
	 */
	private List<MenuInfo> subMenuList;

	
	
	public Integer getNav_menu_id() {
		return nav_menu_id;
	}

	public void setNav_menu_id(Integer nav_menu_id) {
		this.nav_menu_id = nav_menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getOpen_mode() {
		return open_mode;
	}

	public void setOpen_mode(String open_mode) {
		this.open_mode = open_mode;
	}

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public String getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}

	public List<MenuInfo> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<MenuInfo> subMenuList) {
		this.subMenuList = subMenuList;
	}

}
