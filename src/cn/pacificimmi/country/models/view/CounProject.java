package cn.pacificimmi.country.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.Project;

public class CounProject extends ComplexModel<CounProject>{
	
	
	private String projectType;
	
	private String projectName;
	
	private List<ProjectModel> firstLevels;
	
	private List<ProjectModel> secondLevels;

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<ProjectModel> getFirstLevels() {
		return firstLevels;
	}

	public void setFirstLevels(List<ProjectModel> firstLevels) {
		this.firstLevels = firstLevels;
	}

	public List<ProjectModel> getSecondLevels() {
		return secondLevels;
	}

	public void setSecondLevels(List<ProjectModel> secondLevels) {
		this.secondLevels = secondLevels;
	}
	
}
