package cn.pacificimmi.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
public class WeiXinUtils extends WeiXinConst{
	private static Logger logger = Logger.getLogger(WeiXinUtils.class);
	
    public static String getCodeUrl(String redirectUrl,String state) {
		redirectUrl = URLEncoder.encode(redirectUrl);
		logger.info("redirectUrl"+redirectUrl);
    	String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                WeiXinConst.testappid, redirectUrl, "snsapi_base", state);
    	logger.info("url"+url);
        return url;
    }
    
    public static String getUserInfoUrl(String redirectUrl,String state) {
		redirectUrl = URLEncoder.encode(redirectUrl);
		logger.info("redirectUrl"+redirectUrl);
    	String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                WeiXinConst.testappid, redirectUrl, "snsapi_userinfo", state);
    	logger.info("url"+url);
        return url;
    }
	
	public static String getOpenid(String code) {
		logger.info("code值为"+code);
		String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		tokenUrl = String.format(tokenUrl,WeiXinConst.testappid,WeiXinConst.testsecret,code);
		String tokenStr = HttpUtils.doGet(tokenUrl);
		JSONObject tokenJson = JSONObject.parseObject(tokenStr);
		String access_token = tokenJson.getString("access_token");
		String openId = tokenJson.getString("openid");
		logger.info("access_token值为"+access_token);
		logger.info("openId值为"+openId);
		return openId;
	}
	
	public static String getUserInfo(String code) {
		logger.info("code值为"+code);
		String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		tokenUrl = String.format(tokenUrl,WeiXinConst.testappid,WeiXinConst.testsecret,code);
		String tokenStr = HttpUtils.doGet(tokenUrl);
		JSONObject tokenJson = JSONObject.parseObject(tokenStr);
		String access_token = tokenJson.getString("access_token");
		String openId = tokenJson.getString("openid");
		logger.info("access_token值为"+access_token);
		logger.info("openId值为"+openId);
		String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
    	String userInfo = HttpUtils.doPost(userInfoUrl);
		return userInfo;
	}
	
	public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
	private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
