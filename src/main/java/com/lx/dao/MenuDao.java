package com.lx.dao;

import java.util.List;
import java.util.Map;

import com.lx.entity.Menu;

public interface MenuDao {

	/**
	 * 单表条件查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Menu> selectMenu (Map<String, Object> map)throws Exception;
}
