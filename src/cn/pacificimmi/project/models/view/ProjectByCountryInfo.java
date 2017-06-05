package cn.pacificimmi.project.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.Project;

public class ProjectByCountryInfo extends ComplexModel<ProjectByCountryInfo>{
	
	private String dict_code;
	
	private String value;
	
	private String name;
	
	private String fist_value;
	
	private List<Project> projectList;

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFist_value() {
		return fist_value;
	}

	public void setFist_value(String fist_value) {
		this.fist_value = fist_value;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
}
