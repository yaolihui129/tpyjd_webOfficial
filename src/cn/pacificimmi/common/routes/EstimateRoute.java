package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.estimate.controllers.EstimateController;

/**
 * 评估
 * @author yang
 */
public class EstimateRoute extends Routes {

	@Override
	public void config() {
		add("/estimate",EstimateController.class);//移民评估
	}

}
