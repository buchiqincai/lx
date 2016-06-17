package com.lx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.cookieutil.CookieUtils;
import com.lx.util.NumberUtil;

/**
 * cookie处理
 * @author lx
 *
 */
@Controller
@RequestMapping("/cookie/")
public class CookieTestController {
	
	private final Logger log=LoggerFactory.getLogger(getClass());

	/**
	 * 测试添加cookie
	 * @param request
	 * @param response
	 * @param maxAge cookie生存时间
	 * @return
	 */
	@RequestMapping("setCookie")
	public @ResponseBody Object setCookie(HttpServletRequest request,HttpServletResponse response,int maxAge){
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			Map<String, String> cookieMap=new HashMap<String, String>();
			cookieMap.put("id", NumberUtil.getCharAndNumber(7));
			cookieMap.put("name", "lx");
			CookieUtils.setCookie(cookieMap, response,maxAge);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 获取cookie
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("getCookie")
	public @ResponseBody Object getCookie(HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			map.put("name", CookieUtils.getCookie(request, "name"));
			map.put("id", CookieUtils.getCookie(request, "id"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}
}
