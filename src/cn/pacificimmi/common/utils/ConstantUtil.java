package cn.pacificimmi.common.utils;

import com.jfinal.kit.PropKit;

/**
 * @author guyic
 * 常量类
 */
public class ConstantUtil {
	/**
	 * 微信-配置文件
	 */
	public static final String WEIXIN_CONFIG_FILE="debug_config.txt";
	/**
	 * 太平洋出国微网站网址
	 */
	public static final String TPYJD_WX_BASE_URL = PropKit.use(WEIXIN_CONFIG_FILE).get("TPYJD_WX_BASE_URL");
	 
	/**
	 * 微网站页面根路径地址
	 */
	public static final String TPYJD_WX_JSP_BASE_PATH =PropKit.use(WEIXIN_CONFIG_FILE).get("TPYJD_WX_JSP_BASE_PATH");
	
	/**
	 * 直播点赞
	 */
	public static final int LIVE_PRAISE = 0;
	
	/**
	 * 直播踩
	 */
	public static final int LIVE_TREAD = 1;
	
	/**
	 * 成功
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * 失败
	 */
	public static final String FAIL = "fail";
}
