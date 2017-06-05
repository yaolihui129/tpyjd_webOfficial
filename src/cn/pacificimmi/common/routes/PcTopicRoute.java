package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.pcTopic.controller.PcTopicController;

public class PcTopicRoute extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/pcTopic", PcTopicController.class);
	}

}
