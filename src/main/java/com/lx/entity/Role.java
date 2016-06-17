package com.lx.entity;

import java.io.Serializable;

/**
 * 角色
 * @author lx
 *
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//角色名
	private String name;
	//描述
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
