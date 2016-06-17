package com.lx.dao;

import java.util.List;

import com.lx.entity.Role;

public interface RoleDao {
	/**
	 * 单表条件查询
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Role> selectRole(Role role)throws Exception;

}
