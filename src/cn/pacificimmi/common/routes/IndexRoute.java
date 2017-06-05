package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.common.controllers.CallCenterController;
import cn.pacificimmi.common.controllers.IndexController;
import cn.pacificimmi.common.controllers.InformationController;
import cn.pacificimmi.common.controllers.MsgController;
import cn.pacificimmi.common.controllers.RegisterController;

public class IndexRoute extends Routes {

	@Override
	public void config() { 
		
		add("/",IndexController.class);//首页Controller
		add("/register",RegisterController.class);//注册和登陆
		add("/sendMsg",MsgController.class);//发送验证码
		add("/callcenter",CallCenterController.class);//呼叫中心
		add("/information",InformationController.class);//资讯
	} 
}
