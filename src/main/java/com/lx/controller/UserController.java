package com.lx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.entity.response.ResponseMessage;
import com.lx.service.UserService;

/**
 * 用户操作
 * @author lx
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	private final Logger log=LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin(){
		return "jsp/login";
	}

	/**
	 * 登录
	 * @param request
	 * @param reponse
	 * @param pwd
	 * @param logName
	 * @param model
	 * @return
	 */
	@RequestMapping("log")
	public @ResponseBody ResponseMessage login(HttpServletRequest request,HttpServletResponse response,String pwd,String logName,Model model){
		try{
			int res=userService.userLogin(logName, pwd, request, response);
			switch (res) {
			case 0:
				return ResponseMessage.success();
			case 1:
				return ResponseMessage.error("用户名错误");
			case 2:
				return ResponseMessage.error("密码错误");
			default :
				return ResponseMessage.error("系统错误");
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return ResponseMessage.error();
		}
	}

}
