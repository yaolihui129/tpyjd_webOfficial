package cn.pacificimmi.common.routes;

import cn.pacificimmi.professional.controller.ProfessionalController;

import com.jfinal.config.Routes;

public class ProfessionalRoute extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/professional",ProfessionalController.class);
	}

}
