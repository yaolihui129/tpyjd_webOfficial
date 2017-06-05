package cn.pacificimmi.estimate.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.MenuInfo;
import cn.pacificimmi.common.models.Copyright;
import cn.pacificimmi.common.models.FriendlyLink;
import cn.pacificimmi.common.models.NavMenu;
import cn.pacificimmi.common.models.PageFooters;
import cn.pacificimmi.common.models.PageHeaders;
import cn.pacificimmi.common.models.Topics;
import cn.pacificimmi.common.utils.StringUtils;

public class EstimateInterCeptor implements Interceptor {
	
	@Override
	public void intercept(Invocation inv) {
		Controller ctr = inv.getController();
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布' and t.send_position like '%官网-移民评估%' ORDER BY t.topic_sort desc");
		
		ctr.setAttr("bannerImgs", bannerImgs);
		inv.invoke();
	}
	
}
