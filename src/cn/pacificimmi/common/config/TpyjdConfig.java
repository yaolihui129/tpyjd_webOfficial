/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package cn.pacificimmi.common.config;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

import cn.pacificimmi.common.controllers.CommonController;
import cn.pacificimmi.common.controllers.FileUploadController;
import cn.pacificimmi.common.models._MappingKit;
import cn.pacificimmi.common.routes.AboutUsRoute;
import cn.pacificimmi.common.routes.CountryRoute;
import cn.pacificimmi.common.routes.EstimateRoute;
import cn.pacificimmi.common.routes.IndexRoute;
import cn.pacificimmi.common.routes.ActivityRoute;
import cn.pacificimmi.common.routes.LiveRoute;
import cn.pacificimmi.common.routes.PcTopicRoute;
import cn.pacificimmi.common.routes.PersonRoute;
import cn.pacificimmi.common.routes.PolicyRoute;
import cn.pacificimmi.common.routes.ProfessionalRoute;
import cn.pacificimmi.common.routes.ProjectRoute;

public class TpyjdConfig extends JFinalConfig {

	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * @param pro 生产环境配置文件
	 * @param dev 开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
			loadPropertyFile(pro);
		}
		catch (Exception e) {
			PropKit.use(dev);
			loadPropertyFile(dev);
		}
	}

	public void configConstant(Constants me) {
		loadProp("product_config.txt", "debug_config.txt");
		
		// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setViewType(ViewType.JSP);
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
		me.setError404View("/sorry.html");
		me.setError500View("/sorry.html");
		
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}

	public void configRoute(Routes me) {
		me.add(new IndexRoute()); //首页
		me.add(new PersonRoute()); //个人信息
		me.add(new ProfessionalRoute());//我的业务
		me.add(new AboutUsRoute());//关于我们
		me.add("/common", CommonController.class);
		me.add("/fileupload", FileUploadController.class);
		me.add(new LiveRoute());//直播
		me.add(new PolicyRoute());//移民新政
		me.add(new ActivityRoute());//活动
		me.add(new CountryRoute());//国家
		me.add(new EstimateRoute());//移民评估
		me.add(new ProjectRoute());//移民项目
		me.add(new PcTopicRoute());//pc专题
	}

	public void configPlugin(Plugins me) {
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		me.add(dp);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(true);
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	public void configInterceptor(Interceptors me) {
		// ApiInterceptor.setAppIdParser(new AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
		// MsgInterceptor.setAppIdParser(new AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
	}

	public void configHandler(Handlers me) {

	}

	public void afterJFinalStart() {
		
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}
}
