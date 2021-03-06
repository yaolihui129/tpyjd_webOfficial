package cn.pacificimmi.common.models.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseEstimateSelect<M extends BaseEstimateSelect<M>> extends Model<M> implements IBean {

	public void setEstimateSelectId(java.lang.Integer estimateSelectId) {
		set("estimate_select_id", estimateSelectId);
	}

	public java.lang.Integer getEstimateSelectId() {
		return get("estimate_select_id");
	}

	public void setEstimateQuestionId(java.lang.Integer estimateQuestionId) {
		set("estimate_question_id", estimateQuestionId);
	}

	public java.lang.Integer getEstimateQuestionId() {
		return get("estimate_question_id");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
	}

	public java.lang.Integer getDeleteFlag() {
		return get("delete_flag");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setCreateUser(java.lang.Integer createUser) {
		set("create_user", createUser);
	}

	public java.lang.Integer getCreateUser() {
		return get("create_user");
	}

}
