package cn.pacificimmi.common.utils;

import com.jfinal.kit.PropKit;

public class WeiXinConst {
	/**
	 * 测试微信的appid
	 */
/*public static String  testappid = "wxe43ed076045e537c";
	
	public static String  testsecret = "8d6af09bcb6980db049d9cad7a7fe509";
	
	public static String  testBaseUrl = "http://tpyjd.tunnel.qydev.com";*/
	
	
//	public static String  testappid = "wx7b9674648ec06fc3";
//	
//	public static String  testsecret = "0412f9c7a0cdd38124e7ec5bc48c9f94";
	
	public static String  testappid = PropKit.use("debug_config.txt").get("appId");
	
	public static String  testsecret = PropKit.use("debug_config.txt").get("appSecret");
	
	public static String  testBaseUrl = PropKit.use("debug_config.txt").get("TPYJD_WX_BASE_URL");
	
	
	public static String  appid = "";
	
	public static String  secret = "";
	
	public static String COOKIE_OPEN_ID = "userOpenId";
	
	public static String COOKIE_CUSTINFO_ID = "custinfoId";
	
	public static String COOKIE_PHONE = "phone";
	
	public static String PRE_URL = "preUrl";
	
	public static String CUSTINFO = "Custinfo";
	
	public WeiXinConst(){
	
	}

}
