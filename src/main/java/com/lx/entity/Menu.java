package com.lx.entity;

import java.io.Serializable;

/**
 * 菜单
 * @author lx
 *
 */
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//父id
	private Long pid;
	//标题
	private String title;
	//类型：0：菜单，1：功能
	private Integer type;
	//状态，0：正常，1：禁用
	private Integer state;
	//地址
	private String url;
	//描述
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", title=" + title + ", type=" + type + ", state=" + state + ", url="
				+ url + ", description=" + description + "]";
	}
	

}
