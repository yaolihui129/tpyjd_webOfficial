package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;
import cn.pacificimmi.live.controllers.LiveController;

public class LiveRoute extends Routes {

	@Override
	public void config() { 
		
		add("/live",LiveController.class);
	} 
}
