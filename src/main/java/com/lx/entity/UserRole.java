package com.lx.entity;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//用户id
	private Long userId;
	//角色id
	private Long roleId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
	

}
