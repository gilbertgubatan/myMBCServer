package com.mbc.server.factory.login;

import com.mbc.server.dto.login.LoginDto;
import com.mbc.server.jpa.beans.login.Role;
import com.mbc.server.jpa.beans.login.User;
import com.mbc.server.util.ObjectUtil;

public class LoginFactory {
	
	public static LoginDto convertUserToLoginDto(User user) {
		LoginDto loginDto = new LoginDto();
		
		if (ObjectUtil.isNotNull(user)) {
			loginDto.setUserName(user.getUserName());
			loginDto.setPassword(user.getPassword());
			loginDto.setResponseCode(1);
		} else {
			loginDto.setResponseCode(-1);
		}
		
		return loginDto;
	}
	
	public static User convertLoginDtoToUser(LoginDto loginDto, Role role) {
		User user = new User();
		
		if (ObjectUtil.isNotNull(loginDto)) {
			user.setUserName(loginDto.getUserName());
			user.setPassword(loginDto.getPassword());
			user.setRole(role);
//			user.setRoleId(role.getId());
		}
		
		return user;
	}
	
	public static LoginDto login(LoginDto loginDtoInput, User user) {
		LoginDto loginDto = new LoginDto();
		
		if (ObjectUtil.isNotNull(user)) {
			if (loginDtoInput.getPassword().equals(user.getPassword())) {
				loginDto.setUserName(user.getUserName());
				loginDto.setPassword(user.getPassword());
				loginDto.setResponseCode(1);
			} else {
				loginDto.setResponseCode(0);
			}
		}  else {
			loginDto.setResponseCode(-1);
		}
		
		return loginDto;
	}

}
