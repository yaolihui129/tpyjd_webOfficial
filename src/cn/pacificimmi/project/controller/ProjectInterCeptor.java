package cn.pacificimmi.project.controller;

import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import cn.pacificimmi.common.models.Topics;

public class ProjectInterCeptor implements Interceptor {
	
	@Override
	public void intercept(Invocation inv) {
		Controller ctr = inv.getController();
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position  like '%官网-移民项目%'  	ORDER BY t.topic_sort desc");
		
		ctr.setAttr("bannerImgs", bannerImgs);
		inv.invoke();
	}
	
}
