package com.mbc.server.database.service.login;

import java.util.List;

import com.mbc.server.database.util.DBUtil;
import com.mbc.server.jpa.beans.login.User;

public class LoginDBService {
	
	private DBUtil dbUtil = new DBUtil();
	
	public User getUserByUserName(String userName) {
		return dbUtil.getEntityByColumnValue(User.class, "userName", userName);
	}
	
	public List<User> getUserList() {
		return dbUtil.getAllEntityList(User.class);
	}
	
}
