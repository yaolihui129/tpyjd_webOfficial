package cn.pacificimmi.estimate.models.view;

import java.util.Date;

import cn.pacificimmi.common.ComplexModel;

public class EstimateInfo extends ComplexModel<EstimateInfo>{
		
	/**
	 * 移民国家
	 */
	private String country;
	
	/**
	 * 移民目的
	 */
	private String hotspots;
	
	/**
	 * 资产要求
	 */
	private String asset;
	
	/**
	 * 移民预算
	 */
	private String immigration_budget;
	
	/**
	 * 学历要求
	 */
	private String qualifications;
	
	/**
	 * 外语能力
	 */
	private String language;
	
	/**
	 * 管理经验
	 */
	private String manage;
	
	/**
	 * 居住条件
	 */
	private String live_requirement;
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHotspots() {
		return hotspots;
	}

	public void setHotspots(String hotspots) {
		this.hotspots = hotspots;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getImmigration_budget() {
		return immigration_budget;
	}

	public void setImmigration_budget(String immigration_budget) {
		this.immigration_budget = immigration_budget;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

	public String getLive_requirement() {
		return live_requirement;
	}

	public void setLive_requirement(String live_requirement) {
		this.live_requirement = live_requirement;
	}
}
