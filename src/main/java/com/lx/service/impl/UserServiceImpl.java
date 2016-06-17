package com.lx.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.lx.cookieutil.CookieUtils;
import com.lx.dao.UserDao;
import com.lx.entity.User;
import com.lx.service.UserService;
import com.lx.util.IPUtils;
import com.lx.util.NumberUtil;
import com.lx.util.StringUtils;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	@Override
	public User selectById(Long userId) throws Exception {
		return userDao.selectUserById(userId);
	}

	@Override
	public User selectByLogName(String logName) throws Exception {
		return userDao.selectUserByLogName(logName);
	}

	/**
	 * 登录
	 * @param logName
	 * @param pwd
	 * @return 0：成功，1：用户名错误，2：密码错误，3：参数错误
	 * @throws Exception
	 */
	@Override
	public int userLogin(String logName, String pwd,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(StringUtils.isBlank(logName) || StringUtils.isBlank(pwd)){
			return 3;
		}
		User user=userDao.selectUserByLogName(logName);
		if(user == null){
			return 1;
		}
		if(!pwd.equals(user.getPassword())){
			return 2;
		}
		//获取ip
		String lastIp=IPUtils.getIPAddress(request);
		if(StringUtils.isBlank(lastIp)){
			return 3;
		}
		String cookieId=NumberUtil.getUUID();
		Map<String, String> map=new HashMap<String, String>();
		map.put("cookieId", cookieId);
		CookieUtils.setCookie(map, response, CookieUtils.ONE_HOUR);
		
		//修改用户信息
		User resUser=new User();
		resUser.setId(user.getId());
		resUser.setLastTime(new Timestamp(System.currentTimeMillis()));
		resUser.setCookieId(cookieId);
		resUser.setLastIp(lastIp);
		userDao.updateUserByUserId(resUser);
		return 0;
	}
}
