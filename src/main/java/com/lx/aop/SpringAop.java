package com.lx.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lx
 * @date 16-01-03
 *
 */
@Service
@Aspect
public class SpringAop {
	
	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* com.lx.service.impl.AopServiceImpl.*(..))")
	private void a(){}
	/**
	 * 前置通知
	 */
	@Before("a()")
	public void before(){
		System.out.println("aop前置通知---------------");
	}
	/**
	 * 后置通知
	 */
	@AfterReturning("a()")
	public void after(){
		System.out.println("aop后置通知-------------------");
	}
	@Around("a()")
	public void around(){
		System.out.println("环绕通知--------");
	}

}
