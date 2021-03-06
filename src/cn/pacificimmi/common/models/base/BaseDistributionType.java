package cn.pacificimmi.common.models.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDistributionType<M extends BaseDistributionType<M>> extends Model<M> implements IBean {

	public void setDistributionTypeId(java.lang.Integer distributionTypeId) {
		set("distribution_type_id", distributionTypeId);
	}

	public java.lang.Integer getDistributionTypeId() {
		return get("distribution_type_id");
	}

	public void setTypeName(java.lang.String typeName) {
		set("type_name", typeName);
	}

	public java.lang.String getTypeName() {
		return get("type_name");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}

	public java.lang.Integer getSort() {
		return get("sort");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
