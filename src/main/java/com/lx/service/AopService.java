package com.lx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * aop
 * @author lx
 *
 */
public interface AopService {

	/**
	 * 测试aop
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void textAop(HttpServletRequest request,HttpServletResponse response)throws Exception;
}
