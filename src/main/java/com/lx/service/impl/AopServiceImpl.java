package com.lx.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lx.service.AopService;
@Service("aopService")
public class AopServiceImpl implements AopService{

	/**
	 * 测试aop
	 */
	@Override
	public void textAop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("方法体-------------");
	}

}
