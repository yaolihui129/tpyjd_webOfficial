package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.project.controller.ProjectController;

public class ProjectRoute extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/project", ProjectController.class);
	}

}
