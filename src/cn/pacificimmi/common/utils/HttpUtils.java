package cn.pacificimmi.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class HttpUtils {
	
	public static void doGet(){
	}
	
	public static String doGet(String url){
    	HttpClient client = null;
		GetMethod method = null;
		String response = "";
		try {
			System.out.println(url);
			client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			client.getHttpConnectionManager().getParams()
            .setConnectionTimeout(50000);// 设置连接时间
			method = new GetMethod(url);
			method.addRequestHeader("Content-Type","text/html;charset=UTF-8");
			int status = client.executeMethod(method);
			if(status == HttpStatus.SC_OK){
				InputStream inputStream = method.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
    	return response;
    }
    
    public static String doPost(String url){
    	if(StringUtils.isEmpty(url))
            return null;
        String response = "";
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");  
        try {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream ,"utf-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        return response;
    }

}
