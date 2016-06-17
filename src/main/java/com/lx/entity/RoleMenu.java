package com.lx.entity;

import java.io.Serializable;

public class RoleMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	//角色id
	private Long roleId;
	//菜单id
	private Long menuId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}
	
	
}
