package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.activity.controller.ActivityController;
import cn.pacificimmi.country.controller.CountryController;

public class CountryRoute extends Routes {

	@Override
	public void config() { 
		
		add("/country",CountryController.class);
	} 
}
