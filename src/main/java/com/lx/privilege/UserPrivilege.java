package com.lx.privilege;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.lx.dao.UserDao;
import com.lx.entity.User;
import com.lx.util.StringUtils;

/**
 * 用户权限
 * @author lx
 *
 */
public class UserPrivilege {
	
	private  final Logger log=LoggerFactory.getLogger(getClass());
	
	private static UserDao userDao;
	static{
		/**
		 * 初始化bean
		 */
		String[] path={"classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml"};
		ApplicationContext context=new FileSystemXmlApplicationContext(path);
		userDao =(UserDao)context.getBean("userDao");
	}
	
	/**
	 * 校验用户是否有该uri的访问权限
	 * @param cookieId
	 * @param uri
	 * @return
	 */
	public boolean checkUserPrivilege(String cookieId,String uri){
		if (StringUtils.isBlank(cookieId)) {
			return false;
		}
		try {
			User user = userDao.selectByCookieId(cookieId);
			if(user == null){
				return false;
			}
			//管理员用户
			if(user.getType() == 1){
				return true;
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("usuerId", user.getId());
			map.put("url", uri);
			Integer resId=userDao.selectUserAuthority(map);
			if(resId != null && resId >-1){
				return true;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}		
		return false;
	}

}
