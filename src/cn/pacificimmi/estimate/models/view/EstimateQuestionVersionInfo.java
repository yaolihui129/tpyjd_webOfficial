package cn.pacificimmi.estimate.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;

public class EstimateQuestionVersionInfo extends ComplexModel<EstimateQuestionVersionInfo> {
	
	private Integer estimate_record_id;
	
	private Integer version_id;
	
	private String status;
	
	private List<EstimateQuestionInfo> eqList;

	public Integer getVersion_id() {
		return version_id;
	}

	public void setVersion_id(Integer version_id) {
		this.version_id = version_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EstimateQuestionInfo> getEqList() {
		return eqList;
	}

	public void setEqList(List<EstimateQuestionInfo> eqList) {
		this.eqList = eqList;
	}

	public Integer getEstimate_record_id() {
		return estimate_record_id;
	}

	public void setEstimate_record_id(Integer estimate_record_id) {
		this.estimate_record_id = estimate_record_id;
	}
}
