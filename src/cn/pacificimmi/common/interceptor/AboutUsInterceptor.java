package cn.pacificimmi.common.interceptor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import cn.pacificimmi.common.models.Topics;

public class AboutUsInterceptor implements Interceptor {
	private static Logger log = LoggerFactory.getLogger(AboutUsInterceptor.class);
	@Override
	public void intercept(Invocation inv) {
		log.debug("进入拦截器");
		Controller ctr = inv.getController();
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-关于我们%' 	ORDER BY t.topic_sort desc");
		if(bannerImgs.size()>0)
			ctr.setAttr("bannerImgs", bannerImgs);
		
		inv.invoke();
	} 
	
}