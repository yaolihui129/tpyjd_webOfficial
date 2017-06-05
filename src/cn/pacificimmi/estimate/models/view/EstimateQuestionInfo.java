package cn.pacificimmi.estimate.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;

public class EstimateQuestionInfo extends ComplexModel<EstimateQuestionInfo> {
	
	
	private Integer estimate_question_id;
	
	private String title;
	
	private String annotation;
	
	private String answer;
	
	private Integer isrequired;
	
	private Integer weight;
	
	private Integer select_num;
	
	private Integer int_value;
	
	private Integer estimate_select_id;
	
	private Integer estimate_record_id;
	
	private List<EstimateSelectInfo> esList;
	
	private Integer line_num;

	public Integer getEstimate_question_id() {
		return estimate_question_id;
	}

	public void setEstimate_question_id(Integer estimate_question_id) {
		this.estimate_question_id = estimate_question_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIsrequired() {
		return isrequired;
	}

	public void setIsrequired(Integer isrequired) {
		this.isrequired = isrequired;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getSelect_num() {
		return select_num;
	}

	public void setSelect_num(Integer select_num) {
		this.select_num = select_num;
	}

	public List<EstimateSelectInfo> getEsList() {
		return esList;
	}

	public void setEsList(List<EstimateSelectInfo> esList) {
		this.esList = esList;
	}

	public Integer getInt_value() {
		return int_value;
	}

	public void setInt_value(Integer int_value) {
		this.int_value = int_value;
	}

	public Integer getEstimate_record_id() {
		return estimate_record_id;
	}

	public void setEstimate_record_id(Integer estimate_record_id) {
		this.estimate_record_id = estimate_record_id;
	}
	
	public Integer getEstimate_select_id() {
		return estimate_select_id;
	}

	public void setEstimate_select_id(Integer estimate_select_id) {
		this.estimate_select_id = estimate_select_id;
	}

	public Integer getLine_num() {
		return line_num;
	}

	public void setLine_num(Integer line_num) {
		this.line_num = line_num;
	}
}
