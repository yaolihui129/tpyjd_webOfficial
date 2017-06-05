package cn.pacificimmi.common.utils;

import java.util.Collection;
import java.util.Map;

public class CommonHelper {
	
	/** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }
    
    /**返回加密后的手机号
     * @param phoneNo
     * @return
     */
    public static String encryptPhoneNo(String phoneNo){
    		if(null == phoneNo || phoneNo.isEmpty()){
    			return "";
    		}else{
    			if(phoneNo.length() == 11){
    				return phoneNo.substring(0,3) + "****" + phoneNo.substring(7);
    			}else{
    				return phoneNo;
    			}
    		}
    }

}
