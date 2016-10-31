package com.mbc.server.database.service.login;

import java.util.List;

import com.mbc.server.database.util.DBUtil;
import com.mbc.server.dto.login.LoginDto;
import com.mbc.server.factory.login.LoginFactory;
import com.mbc.server.jpa.beans.login.Role;
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
	
	public User createNewUser(LoginDto loginDto) {
		Role roleAdmin = dbUtil.getEntityByColumnValue(Role.class, "roleName", "admin");
		return (User) dbUtil.insertEntity(LoginFactory.convertLoginDtoToUser(loginDto, roleAdmin));
	}
	
	public User updateUser(LoginDto loginDto) {
		User user = dbUtil.getEntityByColumnValue(User.class, "userName", loginDto.getUserName());
		user = (User) dbUtil.getEntityToUpdateByPrimaryKey(User.class, user.getId());
		user.setPassword(loginDto.getPassword());
		
		return (User) dbUtil.endUpdateEntity(user);
	}
	
	public void deleteUser(LoginDto loginDto) {
		User user = dbUtil.getEntityByColumnValue(User.class, "userName", loginDto.getUserName());
		dbUtil.deleteEntity(User.class, user.getId());
	}
	
	public List<User> getUserList() {
		return dbUtil.getAllEntityList(User.class);
	}
	
}
