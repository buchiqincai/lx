package com.lx.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lx.cookieutil.CookieUtils;
import com.lx.privilege.UserPrivilege;
import com.lx.util.StringUtils;

/**
 * 通过web配置拦截器
 * @author lx
 *
 */
public class InterceptorWeb implements Filter{
	
	private static UserPrivilege userPrivilege;
	
	private static final String NO_lOG_URL="/user/toLogin.htm";
	
	private static final int UNPROTECT_MODEL=2;
	private static final int PROTECT_MODEL=1; 
	//1：全保护，2：全不保护
	private static  int model =1;
	//受保护的uri
	private static Map<String, Boolean> protectUrl=new HashMap<String, Boolean>();
	//不受保护的uri
	private static Map<String, Boolean> unprotectUrl=new HashMap<String, Boolean>();
	
	private Logger log=LoggerFactory.getLogger(getClass());
	

	/**
	 * 编译的时间就执行，初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("web配置的拦截器init方法----------------");
		//初始化数据
		String protectUrlStr=filterConfig.getInitParameter("protectUrl");
		String unProtectUrlStr=filterConfig.getInitParameter("unprotectUrl");
		String modelStr=filterConfig.getInitParameter("model");
		if(!StringUtils.isBlank(modelStr)){
			model=Integer.valueOf(modelStr).intValue();
		}
		if(model==PROTECT_MODEL && StringUtils.isBlank(unProtectUrlStr)){
			String message="全保护模式下非保护uri不能为空";
			log.error(message);
			throw new ServletException(message);
		}
		if (model == UNPROTECT_MODEL && StringUtils.isBlank(protectUrlStr)) {
			String message="全不保护模式下保护uri不能为空";
			log.error(message);
			throw new ServletException(message);
		}
		addMap(protectUrlStr, protectUrl);
		addMap(unProtectUrlStr, unprotectUrl);
		
		userPrivilege=new UserPrivilege();
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		System.out.println("web配置的拦截器doFilter方法----------------");
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		/**
		 * 是否允许跨域访问
		 * *：代表允许所有的地址访问
		 */
		response.setHeader("Access-Control-Allow-Gredentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Origin", "http://www.baidu.com");
		
		//获取访问地址格式为：/cookie/getCookie.htm
		String uri=request.getRequestURI();
		System.out.println("uri---------------------"+uri);
		if(checkUriIntercept(uri)){
			//将请求发送给下一个filter
			chain.doFilter(request, response);
			return ;
		}
		
		//判断cookie是否存在
		String cookieId=CookieUtils.getCookie(request, "cookieId");
		if(!StringUtils.isEmpty(cookieId)){
			if(userPrivilege.checkUserPrivilege(cookieId, uri)){
				//将请求发送给下一个filter
				chain.doFilter(request, response);
				return;
			}
		}
		//重定向到登录页
		response.sendRedirect(NO_lOG_URL);		
	}

	@Override
	public void destroy() {
		System.out.println("web配置的拦截器destroy方法----------------");
		// TODO Auto-generated method stub
	}

	/**
	 * {"/cookie/getCookie.htm":true}
	 * {"/cookie/":false}
	 * @param url
	 * @param map
	 */
	private static void addMap(String url,Map<String, Boolean> map){
		if(StringUtils.isBlank(url)){
			return ;
		}
		for(String uri:url.split(",")){
			if(StringUtils.isBlank(uri)){
				continue ;
			}
		    map.put(StringUtils.trim(uri), uri.lastIndexOf(".") > -1);
		}
	}
	/**
	 * 校验uri是否拦截,通过为true
	 * @param uri
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private static boolean checkUriIntercept(String uri){
		uri=StringUtils.trim(uri);
		
		switch (model) {
		/**
		 * 全不拦截模式下，判断uri是否为拦截的
		 */
		case UNPROTECT_MODEL:
			for(String portectKey:protectUrl.keySet()){
				//{"/cookie/getCookie.htm":true}
				if(protectUrl.get(portectKey)){
					if(uri.equals(portectKey)){
						return false;
					}
				}
				//{"/cookie/":false}
				if (! protectUrl.get(portectKey)) {
					String ukey=uri.substring(0, uri.lastIndexOf("/")+1);
					if(ukey.equals(portectKey)){
						return false;
					}
				}
			}
			return true;
		/**
		 * 全拦截模式下判断uri是否为不拦截的
		 */
		case PROTECT_MODEL:
			for(String unPortectKey:unprotectUrl.keySet()){
				//{"/cookie/getCookie.htm":true}
				if(unprotectUrl.get(unPortectKey)){
					if(uri.equals(unPortectKey)){
						return true;
					}
				}
				//{"/cookie/":false}
				if (! unprotectUrl.get(unPortectKey)) {
					String ukey=uri.substring(0, uri.lastIndexOf("/")+1);
					if(ukey.equals(unPortectKey)){
						return true;
					}
				}
			}
			return false;
		default:
			return false;
		}
	}
}
