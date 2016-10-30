package com.mbc.server.database.service.login;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mbc.server.database.util.DBUtil;
import com.mbc.server.jpa.beans.login.User;

public class LoginDBService {
	
	private DBUtil dbUtil = new DBUtil();
	
	public User getUserByUserName(String userName) {
		CriteriaBuilder cb = dbUtil.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> rootUser = cq.from(User.class);
		cq.select(rootUser);
		
		Path<String> pathUserName = rootUser.get("userName");
		Predicate prUserName = cb.equal(pathUserName, userName);
		
		cq.where(prUserName);
		
		TypedQuery<User> tqUser = dbUtil.getEntityManager().createQuery(cq);
		
		return dbUtil.getOneEntity(tqUser);
	}
	
	public List<User> getUserList() {
		return dbUtil.getAllEntityList(User.class);
	}
	
}
