package com.mbc.server.database.service.login;

import java.util.List;

import com.mbc.server.database.util.DBUtil;
import com.mbc.server.dto.login.LoginDto;
import com.mbc.server.jpa.beans.login.User;
import com.mbc.server.util.ObjectUtil;

public class LoginDBService {
	
	private DBUtil dbUtil = new DBUtil();
	
	public User getUserByUserName(String userName) {
		return dbUtil.getEntityByColumnValue(User.class, "userName", userName);
	}
	
	public User login(LoginDto loginDto) {
		if (ObjectUtil.isNotNull(loginDto)) {
			return dbUtil.getEntityByColumnValue(User.class, "userName", loginDto.getUserName());
		} else {
			return null;
		}
	}
	
	public List<User> getUserList() {
		return dbUtil.getAllEntityList(User.class);
	}
	
}
