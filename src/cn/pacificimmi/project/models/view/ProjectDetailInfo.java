package cn.pacificimmi.project.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.Project;
import cn.pacificimmi.common.models.ProjectApplyProcess;

public class ProjectDetailInfo extends ComplexModel<ProjectDetailInfo> {
	
	/**
	 * 项目标识
	 */
	private Integer project_id;
	
	/**
	 * 项目名称
	 */
	private String project_name;
	
	/**
	 * 项目状态
	 */
	private String project_status;
	
	/**
	 * 项目简介
	 */
	private String introduce;
	
	/**
	 * 官网图片
	 */
	private String web_project_img;
	
	/**
	 * 项目官网介绍
	 */
	private String web_introduce;
	
	/**
	 * 项目优势
	 */
	private String[] advantage;
	
	/**
	 * 项目类型
	 */
	private String project_type;
	
	/**
	 * 签证类型
	 */
	private String visa_type;
	
	/**
	 * 居住要求
	 */
	private String live_requirement;
	
	/**
	 * 语言要求
	 */
	private String language;
	
	/**
	 * 学历要求
	 */
	private String qualifications;
	
	/**
	 * 管理经验
	 */
	private String manage;
	
	/**
	 * 推荐指数
	 */
	private String recommended;
	
	/**
	 * 申请条件
	 */
	private String apply_condition;
	
	/**
	 * 子项目
	 */
	private List<Project> subProjectList;
	
	/**
	 * 办理流程
	 */
	private List<ProjectApplyProcess> projectApList;

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

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getWeb_project_img() {
		return web_project_img;
	}

	public void setWeb_project_img(String web_project_img) {
		this.web_project_img = web_project_img;
	}

	public String getWeb_introduce() {
		return web_introduce;
	}

	public void setWeb_introduce(String web_introduce) {
		this.web_introduce = web_introduce;
	}

	public String[] getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String[] advantage) {
		this.advantage = advantage;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getVisa_type() {
		return visa_type;
	}

	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
	}

	public String getLive_requirement() {
		return live_requirement;
	}

	public void setLive_requirement(String live_requirement) {
		this.live_requirement = live_requirement;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getApply_condition() {
		return apply_condition;
	}

	public void setApply_condition(String apply_condition) {
		this.apply_condition = apply_condition;
	}

	public List<Project> getSubProjectList() {
		return subProjectList;
	}

	public void setSubProjectList(List<Project> subProjectList) {
		this.subProjectList = subProjectList;
	}

	public List<ProjectApplyProcess> getProjectApList() {
		return projectApList;
	}

	public void setProjectApList(List<ProjectApplyProcess> projectApList) {
		this.projectApList = projectApList;
	}
	
}
