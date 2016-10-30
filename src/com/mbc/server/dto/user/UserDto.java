package com.mbc.server.dto.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDto {

	private String userName;
	private String maskedPassword;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMaskedPassword() {
		return maskedPassword;
	}
	public void setMaskedPassword(String maskedPassword) {
		this.maskedPassword = maskedPassword;
	}
	
}
