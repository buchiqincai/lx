package com.lx.entity;

import java.sql.Timestamp;

public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String loginName;
	private String password;
	private Timestamp crrateTime;
	private Integer type;
	private Integer status;
	private Timestamp lastTime;
	private String cookieId;
	private String lastIp;
	
	
	public Timestamp getCrrateTime() {
		return crrateTime;
	}
	public void setCrrateTime(Timestamp crrateTime) {
		this.crrateTime = crrateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public String getCookieId() {
		return cookieId;
	}
	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", crrateTime=" + crrateTime
				+ ", type=" + type + ", status=" + status + ", lastTime=" + lastTime + ", cookieId=" + cookieId
				+ ", lastIp=" + lastIp + "]";
	}
	
}
