package cn.pacificimmi.common.utils;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by Fernflower decompiler)
//

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

public class CookieUtil {
private static final Logger LOG = LoggerFactory.getLogger(CookieUtil.class);
private static final int DEFAULT_MAX_AGE = 604800;

public CookieUtil() {
 }

public static String getValueByName(String name, HttpServletRequest request) {
     String value = "";
Cookie cookie = WebUtils.getCookie(request, name);
if(cookie != null) {
         value = cookie.getValue();
}

return value;
}

public static void add(String name, String value, int expiry, HttpServletRequest request, HttpServletResponse response) {
     Cookie cookie = new Cookie(name, value);
cookie.setDomain(request.getServerName());
cookie.setPath("/");
cookie.setMaxAge(expiry);
response.addCookie(cookie);
}

public static void add(String name, String value, HttpServletRequest request, HttpServletResponse response) {
     add(name, value, DEFAULT_MAX_AGE, request, response);
}

public static void delete(String name, HttpServletRequest request, HttpServletResponse response) {
     add(name, "", 0, request, response);
	}
}

