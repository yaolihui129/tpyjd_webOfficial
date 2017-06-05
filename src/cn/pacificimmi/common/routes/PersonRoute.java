package cn.pacificimmi.common.routes;

import cn.pacificimmi.person.controller.PersonController;

import com.jfinal.config.Routes;


public class PersonRoute extends Routes {

	@Override
	public void config() {
		add("/person",PersonController.class);//个人信息
		
	}

}
