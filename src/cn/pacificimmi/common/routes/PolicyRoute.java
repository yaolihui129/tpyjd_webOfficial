package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;
import cn.pacificimmi.policy.controller.PolicyController;

public class PolicyRoute extends Routes {

	@Override
	public void config() { 
		
		add("/policy",PolicyController.class);
	} 
}
