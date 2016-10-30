package com.mbc.server.factory.login;

import com.mbc.server.dto.login.LoginDto;
import com.mbc.server.jpa.beans.login.User;
import com.mbc.server.util.ObjectUtil;

public class LoginFactory {
	
	public static LoginDto convertUserToLoginDto(User user) {
		LoginDto loginDto = new LoginDto();
		
		if (ObjectUtil.isNotNull(user)) {
			loginDto.setUserName(user.getUserName());
			loginDto.setMaskedPassword(user.getPassword());
		}
		
		return loginDto;
	}

}
