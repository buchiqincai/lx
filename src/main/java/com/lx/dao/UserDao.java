package com.lx.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.lx.entity.User;

public interface UserDao {

	/**
	 * 通过登录名查询用户
	 * @param LogName
	 * @return
	 * @throws Exception
	 */
	User selectUserByLogName(String logName)throws Exception;
	/**
	 * 通过id查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	User selectUserById(Long userId)throws Exception;
	/**
	 * 通过时间查询
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	List<User> selectUserByTime(Map<String, Timestamp> map)throws Exception;
	/**
	 * 通过cookieid查询用户
	 * @param cookieId
	 * @return
	 * @throws Exception
	 */
	User selectByCookieId(String cookieId)throws Exception;
	/**
	 * 查询用户是否具有该地址的访问权限
	 * @param map ：userId,url
	 * @return
	 * @throws Exception
	 */
	Integer selectUserAuthority(Map<String, Object> map)throws Exception;
	/**
	 * 根据userId修改
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int updateUserByUserId(User user)throws Exception;
}
