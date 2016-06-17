package com.lx.cookieutil;

import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lx.util.InitUtils;
import com.lx.util.StringUtils;

/**
 * cookie处理
 * @author lx
 *
 */
public class CookieUtils {
	public static final int ONE_HOUR=3600;
	public static final int ONE_DAY=86400;
	private static String doMain=InitUtils.getStr("domain");
	private static String path="/";
	private static int maxAge=Integer.valueOf(InitUtils.getStr("cookie.maxAge.default")).intValue();
	
	/**
	 * 获取kookie
	 * @param request
	 * @param 要获取的cookie的key
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String name){
		String value=null;
		if(StringUtils.isEmpty(name)){
			return value;
		}
		Cookie[] cookies=request.getCookies();
		if(cookies == null || cookies.length <=0){
			return value;
		}
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(name)){
				value = cookie.getValue();
			}
		}
		return value;
	}
	
	/**
	 *设置cookie  ，默认生存时间一次会话
	 * @param map key：cookie的key，value：cookie的value
	 * @param response
	 * @throws Exception 
	 */
	public static void setCookie(Map<String , String> map,HttpServletResponse response) throws Exception{
		setCookie(map, response,maxAge);
	} 
	
	/**
	 *设置cookie  
	 * @param map key：cookie的key，value：cookie的value 
	 * @param maxAge :cookie的生存时间
	 * @param response
	 * @throws Exception 
	 */
	public static void setCookie(Map<String , String> map,HttpServletResponse response,int maxAge) throws Exception{
		if(map == null){
			return ;
		}
		for(String key:map.keySet()){
			if(StringUtils.isEmpty(key)){
				continue;
			}
			String value=map.get(key);
			if(StringUtils.isEmpty(value)){
				continue;
			}
			Cookie cookie=new Cookie(key, URLEncoder.encode(value,"UTF-8"));
			cookie.setDomain(doMain);
			cookie.setPath(path);
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		}	
	} 

}
