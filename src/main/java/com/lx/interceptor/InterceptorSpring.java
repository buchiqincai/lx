package com.lx.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lx.cookieutil.CookieUtils;
import com.lx.privilege.UserPrivilege;

/**
 * 拦截器,通过spring配置
 * @author lx
 *
 */
public class InterceptorSpring extends HandlerInterceptorAdapter{
	//日志
	private Logger log=LoggerFactory.getLogger(getClass());
	
	/**
	 * 配置文件中需要拦截的url
	 */
	private List<String> uriList;
	

	public List<String> getUriList() {
		return uriList;
	}
	public void setUriList(List<String> uriList) {
		this.uriList = uriList;
	}
	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		System.out.println("spring配置的拦截器preHandle方法-------------------");
		String uri=request.getRequestURI();	
//		UserPrivilege userPrivilege=new UserPrivilege();
//		 if(uriList.contains(uri)){
//			 //校验用户是否能够够访问路径
//			 String cookieId=CookieUtils.getCookie(request, "cookieId");
//			 if(!userPrivilege.checkUserPrivilege(cookieId, uri)){
//				 response.sendRedirect("/user/toLogin.htm");	
//				return false; 
//			 }
//		 }
		return true;
		}     
	/**
	 * 有机会修改 ModelAndView
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("spring配置的拦截器postHandle方法-------------------");
	}

	/**
	 * 最后
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("spring配置的拦截器afterCompletion方法-------------------");
	}
}
