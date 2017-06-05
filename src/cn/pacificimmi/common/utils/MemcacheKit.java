package cn.pacificimmi.common.utils;


import java.util.Date;

import com.jfinal.kit.PropKit;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcacheKit {
	/* 单例模式 */  
    protected static MemCachedClient mcc = new MemCachedClient();  
      
    private MemcacheKit() {  
    }  
      
    /* 配置服务器组 */  
    static {  
//    	PropKit.use("a_little_config.txt");
    	
    	String memcachedURL = PropKit.get("memcachedURL", "");
        String[] servers = memcachedURL.split(",");
    	
//    	
//          
//        /* 定义IP地址和端口 */  
//        String[] servers = { "192.168.1.114:22228" };
//        String[] servers = { "115.159.67.148:22228" };
        /* 设置缓存大小 */  
        Integer[] weights = { 2 };  
          
        /* 拿到一个连接池的实例 */  
        SockIOPool pool = SockIOPool.getInstance();  
          
        /* 注入服务器组信息 */  
        pool.setServers(servers);  
        pool.setWeights(weights);  
          
        /* 配置缓冲池的一些基础信息 */  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(250);  
        pool.setMaxIdle(1000 * 60 * 60 * 6);  
        
        /* 设置线程休眠时间 */  
        pool.setMaintSleep(30);  
          
        /* 设置关于TCP连接 */  
        pool.setNagle(false);// 禁用nagle算法  
        pool.setSocketConnectTO(0);  
        pool.setSocketTO(3000);// 3秒超时  
          
        /* 初始化 */  
        pool.initialize();  
    }  
      
    public static boolean set(String arg0, Object arg1,long expire) {
    		expire = System.currentTimeMillis()+expire*1000;
        return mcc.set(arg0, arg1,new Date(expire));  
    }  
      
    public static Object get(String arg0) {  
        return mcc.get(arg0);  
    }
    
    public static boolean delete(String arg0) {  
        return mcc.delete(arg0);  
    }
    
    public static void main(String[] args){
    		MemcacheKit.set("test", "test expire!",60*10);
    		System.out.println(MemcacheKit.get("test"));
    }
}
