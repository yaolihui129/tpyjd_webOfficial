package cn.pacificimmi.common.models.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCardAccess<M extends BaseCardAccess<M>> extends Model<M> implements IBean {

	public void setCardAccessId(java.lang.Integer cardAccessId) {
		set("card_access_id", cardAccessId);
	}

	public java.lang.Integer getCardAccessId() {
		return get("card_access_id");
	}

	public void setWeixinResourceId(java.lang.Integer weixinResourceId) {
		set("weixin_resource_id", weixinResourceId);
	}

	public java.lang.Integer getWeixinResourceId() {
		return get("weixin_resource_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}

	public java.lang.String getIp() {
		return get("ip");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	public java.lang.Integer getType() {
		return get("type");
	}

}