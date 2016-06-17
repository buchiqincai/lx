package com.lx.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lx.entity.User;
import com.lx.service.AopService;
import com.lx.service.UserService;
import com.lx.util.InitUtils;

@Controller
@RequestMapping("/text/")
public class TestController {
	private final Logger log=LoggerFactory.getLogger(getClass());
	@Value("#{int['name']}") 
	private String a;
	@Resource
	private UserService userService;
	@Resource
	private AopService aopService;

	/**
	 * 数据库访问
	 * @param request
	 * @param reponse
	 * @param userId
	 * @param logName
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody Map<String, Object> selectUser(HttpServletRequest request,HttpServletResponse reponse
			,Long userId,String logName){
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			User u1=userService.selectById(userId);
			User u2=userService.selectByLogName(logName);
			map.put("u1", u1);
			map.put("u2", u2);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return map;
	}
	
	/**
	 * 读取配置文件
	 * 两种方式
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping("int")
	public @ResponseBody Map<String, Object> findProperties(HttpServletRequest request,HttpServletResponse reponse){
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			map.put("name", InitUtils.getStr("name"));
			map.put("value", a);
		}catch(Exception e){
			log.error(e.getMessage(), e);   
		}
		return map;
	}
	/**
	 * aop测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("aop")
	public @ResponseBody Object textAop(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();
		try{
            aopService.textAop(request, response);
		}catch(Exception e){
			log.error(e.getMessage(), e);   
		}
		return map;
	}
}
