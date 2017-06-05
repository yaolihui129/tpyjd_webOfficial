package cn.pacificimmi.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;


public class NoticeMsgUtil {
	
	public static StringBuffer send(CreateTemplate ct) {
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		tokenUrl = String.format(tokenUrl, PropKit.use("debug_config.txt").get("appId"), PropKit.use("debug_config.txt").get("appSecret"));
		String tokenStr = HttpUtils.doGet(tokenUrl);
		JSONObject tokenJson = JSONObject.parseObject(tokenStr);
		String access_token = tokenJson.getString("access_token");
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(ct.getMap());
		return sendMsgByOpenId(json.toString(),access_token);
	}
	
	private static StringBuffer sendMsgByOpenId(String params,String accessToken){
		
		StringBuffer bufferRes =new StringBuffer();

        try {
            URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +accessToken);
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 请求方式
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","application/json");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out =new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
            //out.write(URLEncoder.encode(params,"UTF-8"));
            //发送json包

            out.write(params);
            out.flush();
            out.close();
            
            InputStream in =conn.getInputStream();
            BufferedReader read =new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String valueString =null;
            while ((valueString=read.readLine())!=null){
                    bufferRes.append(valueString);
            }

            //输出返回的json
            System.out.println(bufferRes);
            in.close();
            if (conn !=null){
                // 关闭连接
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return bufferRes;
	}
	
}
