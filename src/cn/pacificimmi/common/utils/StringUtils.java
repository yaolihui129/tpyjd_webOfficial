package cn.pacificimmi.common.utils;

public class StringUtils {

	public static boolean isEmpty(String str){
		return str == null || str.trim().length() == 0;
	}
	
	public static boolean notEmpty(String str){
		return str != null && str.trim().length() > 0;
	}
}
