package cn.pacificimmi.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateTemplate {
	
	private Map<String,Object> map;

	public CreateTemplate(LinkedHashMap<String,Object> data, 
			String openId, String template_id, String topcolor) {
		  map = new LinkedHashMap<String,Object>();
		  
		  map.put("touser", openId);
		  map.put("template_id", template_id);
		  map.put("topcolor", topcolor);
		  map.put("data",data);
	}
		
	public Map<String,Object> getMap() {
		return map;
	}

	public void setMap(Map<String,Object> map){
		this.map =map;
	}
}
