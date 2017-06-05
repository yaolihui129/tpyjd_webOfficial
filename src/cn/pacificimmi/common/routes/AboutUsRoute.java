package cn.pacificimmi.common.routes;

import com.jfinal.config.Routes;

import cn.pacificimmi.about_us.controllers.AboutController;
import cn.pacificimmi.about_us.controllers.CompanyNewsController;
import cn.pacificimmi.about_us.controllers.ContactUsController;
import cn.pacificimmi.about_us.controllers.JobController;
import cn.pacificimmi.about_us.controllers.ProfessionalTeamController;
import cn.pacificimmi.about_us.controllers.SuccessCasesController;

public class AboutUsRoute extends Routes {

	@Override
	public void config() {
		
		add("/about-us/contact-us",ContactUsController.class);
		add("/about-us",AboutController.class);//关于我们：公司简介、诚邀合作、投诉建议
		add("/about-us/jobs",JobController.class);//诚聘英才
		add("/about-us/professional-team",ProfessionalTeamController.class);
		
		add("/about-us/news",CompanyNewsController.class);//公司动态
		add("/about-us/success-cases",SuccessCasesController.class);//成功案例
	}

}
