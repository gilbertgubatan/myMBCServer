package com.mbc.server.rest.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Crunchify.com
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mbc.server.dto.user.UserDto;
import com.mbc.server.jpa.beans.user.User;
 
@Path("/user")
public class UserRestService {
	
	@GET
	@Produces("application/json")
	public UserDto getUser() {
		UserDto userDto = new UserDto();
		User userResult = new User();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MBCServer");
		System.out.println("Is opened connection :: "+ emf.createEntityManager().isOpen());
		
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<User> tqUser = em.createQuery("SELECT u FROM User u", User.class);
		List<User> userList = tqUser.getResultList();
		
		for (User user : userList) {
			System.out.println("User: " + user.getUserName());
			userResult = user;
		}
		
		userDto.setUserName(userResult.getUserName());
		userDto.setMaskedPassword(userResult.getPassword());
		
		return userDto;
	}
	
	@Path("login/{userName}")
	@GET
	@Produces("application/json")
	public UserDto convertCtoFfromInput(@PathParam("userName") String userName) {
		UserDto userDto = new UserDto();
		User userResult = new User();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MBCServer");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> rootUser = cq.from(User.class);
		cq.select(rootUser);
		
		cq.where(cb.equal(rootUser.get("userName"), userName));
		
		TypedQuery<User> tqUser = em.createQuery(cq);
		
		userResult = tqUser.getResultList().get(0);
		
		userDto.setUserName(userResult.getUserName());
		userDto.setMaskedPassword(userResult.getPassword());
		
		return userDto;
	}
	
}