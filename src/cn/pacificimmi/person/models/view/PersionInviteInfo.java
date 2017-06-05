package cn.pacificimmi.person.models.view;

import cn.pacificimmi.common.ComplexModel;

public class PersionInviteInfo extends ComplexModel<PersionInviteInfo> {

	/**
	 * 客户标识
	 */
	private Integer custinfo_id;

	/**
	 * 客户姓名
	 */
	private String name;

	/**
	 * 手机号
	 */
	private String phone_num;

	/**
	 * 头像
	 */
	private String head_img;

	/**
	 * 直属客户人数
	 */
	private Long sub_custinfo_count;

	public Integer getCustinfo_id() {
		return custinfo_id;
	}

	public void setCustinfo_id(Integer custinfo_id) {
		this.custinfo_id = custinfo_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public Long getSub_custinfo_count() {
		return sub_custinfo_count;
	}

	public void setSub_custinfo_count(Long sub_custinfo_count) {
		this.sub_custinfo_count = sub_custinfo_count;
	}

}
