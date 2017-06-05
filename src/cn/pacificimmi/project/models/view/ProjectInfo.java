package cn.pacificimmi.project.models.view;

import cn.pacificimmi.common.ComplexModel;

public class ProjectInfo extends ComplexModel<ProjectInfo> {

	/**
	 * 项目标识
	 */
	private Integer project_id;
	
	/**
	 * 项目名称
	 */
	private String project_name;
	
	/**
	 * 项目图片
	 */
	private String web_project_img;
	
	/**
	 * 签证类型
	 */
	private String visa_type;
	
	/**
	 * 语言要求
	 */
	private String language;
	
	/**
	 * 投资金额
	 */
	private String investments;
	
	/**
	 * 居住条件
	 */
	private String live_requirement;
	
	/**
	 * 移民预算
	 */
	private Integer immigration_budget;
	

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getVisa_type() {
		return visa_type;
	}

	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getInvestments() {
		return investments;
	}

	public void setInvestments(String investments) {
		this.investments = investments;
	}

	public String getLive_requirement() {
		return live_requirement;
	}

	public void setLive_requirement(String live_requirement) {
		this.live_requirement = live_requirement;
	}

	public Integer getImmigration_budget() {
		return immigration_budget;
	}

	public void setImmigration_budget(Integer immigration_budget) {
		this.immigration_budget = immigration_budget;
	}

	public String getWeb_project_img() {
		return web_project_img;
	}

	public void setWeb_project_img(String web_project_img) {
		this.web_project_img = web_project_img;
	}
}
