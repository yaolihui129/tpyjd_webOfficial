package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.activity.controller.ActivityController;

public class ActivityRoute extends Routes {

	@Override
	public void config() { 
		
		add("/activity",ActivityController.class);
	} 
}
