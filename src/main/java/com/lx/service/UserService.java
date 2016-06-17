package com.lx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lx.entity.User;

public interface UserService {
	/**
	 * 通过userid查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	User selectById(Long userId)throws Exception;
	/**
	 * 通过logName查询
	 * @param logName
	 * @return
	 * @throws Exception
	 */
	User selectByLogName(String logName)throws Exception;
	/**
	 * 登录
	 * @param logName
	 * @param pwd
	 * @return 0：成功，1：用户名错误，2：密码错误，3：参数错误
	 * @throws Exception
	 */
	int userLogin(String logName,String pwd,HttpServletRequest request,HttpServletResponse response)throws Exception;
}
