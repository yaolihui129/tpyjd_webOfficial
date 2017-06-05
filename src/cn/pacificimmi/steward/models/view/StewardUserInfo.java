
package cn.pacificimmi.steward.models.view;

import java.io.Serializable;

import cn.pacificimmi.common.ComplexModel;

import com.jfinal.plugin.activerecord.Record;

/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年10月27日 下午3:10:31
 **/

public class StewardUserInfo extends ComplexModel<StewardUserInfo> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer steward_id;
	private Integer user_id;
	private String login_name;
	private String user_name;
	private String nick_name;
	private String gender;
	private String phone;
	private String qq;
	private String job_title;
	private String release_mark;
	private String province_id;
	private String province_name;
	private String city_id;
	private String city_name;
	//公司ID
	private Integer com_id;
	//部门id
	private Integer dep_id;
	//部门名称
	private String dep_name;
	private String dynamicScore;
	private String wechat;
	private String head_img;
	private String introduce;
	
	private String english_name;
	private Integer like_count;
	private String email;
	
	 
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getDynamicScore() {
		return dynamicScore;
	}

	public void setDynamicScore(String dynamicScore) {
		this.dynamicScore = dynamicScore;
	}


	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getRelease_mark() {
		return release_mark;
	}

	public void setRelease_mark(String release_mark) {
		this.release_mark = release_mark;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSteward_id() {
		return steward_id;
	}

	public void setSteward_id(Integer steward_id) {
		this.steward_id = steward_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getEnglish_name() {
		return english_name;
	}

	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}

	public Integer getLike_count() {
		return like_count;
	}

	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCom_id() {
		return com_id;
	}

	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}

	public Integer getDep_id() {
		return dep_id;
	}

	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	
}
