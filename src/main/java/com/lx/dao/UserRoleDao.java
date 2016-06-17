package com.lx.dao;

import java.util.List;

import com.lx.entity.UserRole;

public interface UserRoleDao {

	/**
	 * 单表条件查询
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	List<UserRole> selectUserRole(UserRole userRole)throws Exception;
}
