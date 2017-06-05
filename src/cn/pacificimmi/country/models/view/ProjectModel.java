package cn.pacificimmi.country.models.view;

import java.io.Serializable;

import cn.pacificimmi.common.ComplexModel;

public class ProjectModel extends ComplexModel<ProjectModel> implements Serializable{
		
		private Integer project_id;//项目id
		
		private String project_name;//项目名称
		
		private String introduce;//项目介绍
		
		private String investments;//投资金额
		
		private String advantage;//项目特点（优势）
		
		private String visa_type;//签证类型
		
		private String language;//语言要求
		
		private String live_requirement;//居住要求
		
		private String qualifications;//学历要求
		
		private String project_img;//项目图片
		
		private String web_project_img;//项目图片（首页）

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

		public String getIntroduce() {
			return introduce;
		}

		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}

		public String getAdvantage() {
			return advantage;
		}

		public void setAdvantage(String advantage) {
			this.advantage = advantage;
		}

		public String getVisa_type() {
			return visa_type;
		}

		public void setVisa_type(String visa_type) {
			this.visa_type = visa_type;
		}

		public String getInvestments() {
			return investments;
		}

		public void setInvestments(String investments) {
			this.investments = investments;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getLive_requirement() {
			return live_requirement;
		}

		public void setLive_requirement(String live_requirement) {
			this.live_requirement = live_requirement;
		}

		public String getQualifications() {
			return qualifications;
		}

		public void setQualifications(String qualifications) {
			this.qualifications = qualifications;
		}

		public String getProject_img() {
			return project_img;
		}

		public void setProject_img(String project_img) {
			this.project_img = project_img;
		}

		public String getWeb_project_img() {
			return web_project_img;
		}

		public void setWeb_project_img(String web_project_img) {
			this.web_project_img = web_project_img;
		}
		
}
