package cn.pacificimmi.common.models.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCustinfoInvoice<M extends BaseCustinfoInvoice<M>> extends Model<M> implements IBean {

	public void setCustinfoInvoiceId(java.lang.Integer custinfoInvoiceId) {
		set("custinfo_invoice_id", custinfoInvoiceId);
	}

	public java.lang.Integer getCustinfoInvoiceId() {
		return get("custinfo_invoice_id");
	}

	public void setCustinfoId(java.lang.Integer custinfoId) {
		set("custinfo_id", custinfoId);
	}

	public java.lang.Integer getCustinfoId() {
		return get("custinfo_id");
	}

	public void setInvoiceTitle(java.lang.String invoiceTitle) {
		set("invoice_title", invoiceTitle);
	}

	public java.lang.String getInvoiceTitle() {
		return get("invoice_title");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setIsUse(java.lang.Integer isUse) {
		set("is_use", isUse);
	}

	public java.lang.Integer getIsUse() {
		return get("is_use");
	}

}