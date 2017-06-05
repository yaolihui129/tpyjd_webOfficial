package cn.pacificimmi.estimate.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.EstimateQuestion;
import cn.pacificimmi.common.models.EstimateSelect;
import cn.pacificimmi.common.models.EstimateSelectAdditional;

public class EstimatetDetailRecordInfo extends ComplexModel<EstimatetDetailRecordInfo>{
	
	private Integer estimate_detail_record_id;
	
	private Integer estimate_record_id;
	
	private Integer estimate_question_id;
	
	private Integer estimate_select_id;
	
	private Integer int_value;
	
	private EstimateQuestion estimateQuestion;
	
	private List<EstimateSelectAdditional> esaList;

	public Integer getEstimate_detail_record_id() {
		return estimate_detail_record_id;
	}

	public void setEstimate_detail_record_id(Integer estimate_detail_record_id) {
		this.estimate_detail_record_id = estimate_detail_record_id;
	}

	public Integer getEstimate_record_id() {
		return estimate_record_id;
	}

	public void setEstimate_record_id(Integer estimate_record_id) {
		this.estimate_record_id = estimate_record_id;
	}

	public Integer getEstimate_question_id() {
		return estimate_question_id;
	}

	public void setEstimate_question_id(Integer estimate_question_id) {
		this.estimate_question_id = estimate_question_id;
	}

	public Integer getEstimate_select_id() {
		return estimate_select_id;
	}

	public void setEstimate_select_id(Integer estimate_select_id) {
		this.estimate_select_id = estimate_select_id;
	}

	public Integer getInt_value() {
		return int_value;
	}

	public void setInt_value(Integer int_value) {
		this.int_value = int_value;
	}

	public EstimateQuestion getEstimateQuestion() {
		return estimateQuestion;
	}

	public void setEstimateQuestion(EstimateQuestion estimateQuestion) {
		this.estimateQuestion = estimateQuestion;
	}

	public List<EstimateSelectAdditional> getEsaList() {
		return esaList;
	}

	public void setEsaList(List<EstimateSelectAdditional> esaList) {
		this.esaList = esaList;
	}
	
}
