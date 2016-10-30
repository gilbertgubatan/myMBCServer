package com.mbc.server.dto.login;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginDto {

	private String userName;
	private String password;
	private Integer responseCode;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	
}
