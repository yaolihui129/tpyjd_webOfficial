package cn.pacificimmi.estimate.models.view;

import java.util.List;

import cn.pacificimmi.common.ComplexModel;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.EstimateSelectAdditional;

public class EstimateSelectInfo extends ComplexModel<EstimateSelectInfo> {
	
	private Integer estimate_select_id;
	
	private Integer estimate_question_id;
	
	private String  content;
	
	private List<EstimateSelectAdditional> esaList;

	public Integer getEstimate_select_id() {
		return estimate_select_id;
	}

	public void setEstimate_select_id(Integer estimate_select_id) {
		this.estimate_select_id = estimate_select_id;
	}

	public Integer getEstimate_question_id() {
		return estimate_question_id;
	}

	public void setEstimate_question_id(Integer estimate_question_id) {
		this.estimate_question_id = estimate_question_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<EstimateSelectAdditional> getEsaList() {
		return esaList;
	}

	public void setEsaList(List<EstimateSelectAdditional> esaList) {
		this.esaList = esaList;
	}
	
}
