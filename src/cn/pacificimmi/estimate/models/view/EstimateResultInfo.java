package cn.pacificimmi.estimate.models.view;

import java.util.Date;

import cn.pacificimmi.common.ComplexModel;

public class EstimateResultInfo extends ComplexModel<EstimateResultInfo>{
	
	/**
	 * 项目id
	 */
	private Integer projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目优势
	 */
	private String[] advantage;
	
	/**
	 * 签证类型
	 */
	private String visa_type_name;
	
	/**
	 * 推荐星级
	 */
	private Integer recommended;
	
	/**
	 * 上线时间
	 */
	private Date online_time;
	
	/**
	 * 匹配度
	 */
	private Integer matching;
	
	/**
	 * 移民国家
	 */
	private Integer country;
	
	/**
	 * 移民目的
	 */
	private Integer hotspots;
	
	/**
	 * 资产要求
	 */
	private Integer asset;
	
	/**
	 * 移民预算
	 */
	private Integer immigration_budget;
	
	/**
	 * 学历要求
	 */
	private Integer qualifications;
	
	/**
	 * 外语能力
	 */
	private Integer language;
	
	/**
	 * 管理经验
	 */
	private Integer manage;
	
	/**
	 * 居住条件
	 */
	private Integer live_requirement;
	
	/**
	 * 年龄要求
	 */
	private Integer age;
	
	/**
	 * 移民国家name
	 */
	private String country_name;
	
	/**
	 * 移民目的name
	 */
	private String hotspots_name;
	
	/**
	 * 资产要求name
	 */
	private String asset_name;
	
	/**
	 * 学历要求name
	 */
	private String qualifications_name;
	
	/**
	 * 外语能力name
	 */
	private String language_name;
	
	/**
	 * 管理经验name
	 */
	private String manage_name;
	
	/**
	 * 居住条件name
	 */
	private String live_requirement_name;
	
	/**
	 * 年龄要求name
	 */
	private String age_name;
	
	/**
	 * 移民预算name
	 */
	private String immigration_budget_name;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String[] getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String[] advantage) {
		this.advantage = advantage;
	}

	public String getVisa_type_name() {
		return visa_type_name;
	}

	public void setVisa_type_name(String visa_type_name) {
		this.visa_type_name = visa_type_name;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public Date getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Date online_time) {
		this.online_time = online_time;
	}

	public Integer getMatching() {
		return matching;
	}

	public void setMatching(Integer matching) {
		this.matching = matching;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getHotspots() {
		return hotspots;
	}

	public void setHotspots(Integer hotspots) {
		this.hotspots = hotspots;
	}

	public Integer getAsset() {
		return asset;
	}

	public void setAsset(Integer asset) {
		this.asset = asset;
	}

	public Integer getImmigration_budget() {
		return immigration_budget;
	}

	public void setImmigration_budget(Integer immigration_budget) {
		this.immigration_budget = immigration_budget;
	}

	public Integer getQualifications() {
		return qualifications;
	}

	public void setQualifications(Integer qualifications) {
		this.qualifications = qualifications;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Integer getManage() {
		return manage;
	}

	public void setManage(Integer manage) {
		this.manage = manage;
	}

	public Integer getLive_requirement() {
		return live_requirement;
	}

	public void setLive_requirement(Integer live_requirement) {
		this.live_requirement = live_requirement;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getHotspots_name() {
		return hotspots_name;
	}

	public void setHotspots_name(String hotspots_name) {
		this.hotspots_name = hotspots_name;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getQualifications_name() {
		return qualifications_name;
	}

	public void setQualifications_name(String qualifications_name) {
		this.qualifications_name = qualifications_name;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public String getManage_name() {
		return manage_name;
	}

	public void setManage_name(String manage_name) {
		this.manage_name = manage_name;
	}

	public String getLive_requirement_name() {
		return live_requirement_name;
	}

	public void setLive_requirement_name(String live_requirement_name) {
		this.live_requirement_name = live_requirement_name;
	}

	public String getAge_name() {
		return age_name;
	}

	public void setAge_name(String age_name) {
		this.age_name = age_name;
	}

	public String getImmigration_budget_name() {
		return immigration_budget_name;
	}

	public void setImmigration_budget_name(String immigration_budget_name) {
		this.immigration_budget_name = immigration_budget_name;
	}
}
