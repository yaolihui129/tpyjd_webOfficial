
package cn.pacificimmi.steward.models.view;

import java.math.BigDecimal;
import java.util.Date;

import cn.pacificimmi.common.ComplexModel; 

import com.jfinal.plugin.activerecord.Record;

/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年10月27日 下午3:10:31
 **/

public class StewardEvaluateInfo  extends ComplexModel<StewardEvaluateInfo>  {
	private static final long serialVersionUID = 1L;
	private Integer steward_id;
	private Integer evaluate_id;
	private Integer custinfo_id;
	private String name;
	private Double star_level;
	private String content;
	private String service_attitude;
	private String specialized_knowledge;
	private String feedback_efficiency;
	private Date create_time;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSteward_id() {
		return steward_id;
	}
	public void setSteward_id(Integer steward_id) {
		this.steward_id = steward_id;
	}
	public Integer getEvaluate_id() {
		return evaluate_id;
	}
	public void setEvaluate_id(Integer evaluate_id) {
		this.evaluate_id = evaluate_id;
	}
	public Integer getCustinfo_id() {
		return custinfo_id;
	}
	public void setCustinfo_id(Integer custinfo_id) {
		this.custinfo_id = custinfo_id;
	}
 
	public Double getStar_level() {
		return star_level;
	}
	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	 
	public String getService_attitude() {
		return service_attitude;
	}
	public void setService_attitude(String service_attitude) {
		this.service_attitude = service_attitude;
	}
	public String getSpecialized_knowledge() {
		return specialized_knowledge;
	}
	public void setSpecialized_knowledge(String specialized_knowledge) {
		this.specialized_knowledge = specialized_knowledge;
	}
	public String getFeedback_efficiency() {
		return feedback_efficiency;
	}
	public void setFeedback_efficiency(String feedback_efficiency) {
		this.feedback_efficiency = feedback_efficiency;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "StewardEvaluateInfo [steward_id=" + steward_id
				+ ", evaluate_id=" + evaluate_id + ", custinfo_id=" + custinfo_id
				+ ", star_level=" + star_level + ", content=" + content
				+ ", service_attitude=" + service_attitude
				+ ", specialized_knowledge=" + specialized_knowledge
				+ ", feedback_efficiency=" + feedback_efficiency
				+ ", create_time=" + create_time + "]";
	}
	 
	
	
	
	
	
}
