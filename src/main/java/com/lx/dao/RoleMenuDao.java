package com.lx.dao;

import java.util.List;

import com.lx.entity.RoleMenu;

public interface RoleMenuDao {

	/**
	 * 单表条件查询
	 * @param roleMenu
	 * @return
	 * @throws Exception
	 */
	List<RoleMenu> selectRoleMenu(RoleMenu roleMenu)throws Exception;
}
