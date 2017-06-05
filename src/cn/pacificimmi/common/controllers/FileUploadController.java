package cn.pacificimmi.common.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.render.ContentType;
import com.jfinal.upload.UploadFile;

import cn.pacificimmi.common.QcloudUploader;
import cn.pacificimmi.common.utils.Base64Utils;

public class FileUploadController extends Controller{
	
	private QcloudUploader qcloudUploader = new QcloudUploader();
	
	public void index(){
		String contentStr = this.getPara("content");
		contentStr = contentStr.replace("data:image/jpeg;base64,", "");
		byte[] bytes = Base64Utils.decodeBase64(contentStr);
		
		InputStream ins = new ByteArrayInputStream(bytes);
		
		Map<String, Object> map = new HashMap<String, Object>();
		String imgUrl = this.getPara("imgUrl");
		String picUrl = null;

		picUrl = qcloudUploader.uploadPic(ins);
		
		if(null != imgUrl && !"".equals(imgUrl)) {
			try{
				qcloudUploader.deletePic(imgUrl);
			}catch(Exception e){
				//删除异常，传入的地址不是qcloudUploader生成的地址时报错，不处理。
			}
			//qcloudUploader.deletePic(imgUrl);
		}
		
		
		if(null != picUrl) {
			map.put("status", 0);
			map.put("picUrl", picUrl);
		} else {
			map.put("errorInfo", "上传失败！");
		}
		this.renderJson(map);
	}
	
}
