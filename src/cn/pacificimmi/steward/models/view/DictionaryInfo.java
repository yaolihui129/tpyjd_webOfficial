
package cn.pacificimmi.steward.models.view;

import java.util.ArrayList;
import java.util.List;

import cn.pacificimmi.common.ComplexModel;

/**
 *	@version pacific 1.0.0
 * @author lijinlun
 * @date : 2016年10月31日 下午8:05:58
 **/

public class DictionaryInfo  extends   ComplexModel<DictionaryInfo> {
	private String dict_code="";
	private String dict_pcode="";
	private String name="";
	private Double percentage=0.00;
	
	private  List<DictionaryInfo> dictionaryInfos=new ArrayList<DictionaryInfo>(); 
	
	
	
	
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	public String getDict_pcode() {
		return dict_pcode;
	}
	public void setDict_pcode(String dict_pcode) {
		this.dict_pcode = dict_pcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public List<DictionaryInfo> getDictionaryInfos() {
		return dictionaryInfos;
	}
	public void setDictionaryInfos(List<DictionaryInfo> dictionaryInfos) {
		this.dictionaryInfos = dictionaryInfos;
	}
	@Override
	public String toString() {
		return "DictionaryInfo [dict_code=" + dict_code + ", dict_pcode="
				+ dict_pcode + ", name=" + name + ", percentage=" + percentage
				+ ", dictionaryInfos=" + dictionaryInfos + "]";
	}
	
	
	
}
