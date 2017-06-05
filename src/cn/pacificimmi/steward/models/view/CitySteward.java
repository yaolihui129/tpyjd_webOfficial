package cn.pacificimmi.steward.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.StewardUser;

public class CitySteward extends ComplexModel<CitySteward>{
	
	private String cityId;
	
	private String firstCode;
	
	private String cityName;
	
	private List<StewardUserInfo> stewards;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<StewardUserInfo> getStewards() {
		return stewards;
	}

	public void setStewards(List<StewardUserInfo> stewards) {
		this.stewards = stewards;
	}

	

	
}
