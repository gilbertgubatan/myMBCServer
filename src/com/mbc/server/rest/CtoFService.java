package com.mbc.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Crunchify.com
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mbc.server.jpa.beans.User;
 
@Path("/ctofservice")
public class CtoFService {
	@GET
	@Produces("application/json")
	public String convertCtoF() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MBCServer");
		System.out.println("Is opened connection :: "+ emf.createEntityManager().isOpen());
		
		EntityManager em = emf.createEntityManager();
		
		List<User> userList = (List<User>) em.createQuery("SELECT u FROM User u").getResultList();
		
		for (User user : userList) {
			System.out.println("User: " + user.getUserName());
		}

 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MBCServer");
		System.out.println("Is opened connection :: "+ emf.createEntityManager().isOpen());
		
		EntityManager em = emf.createEntityManager();
		
		List<User> userList = (List<User>) em.createQuery("SELECT u FROM User u").getResultList();
		
		for (User user : userList) {
			System.out.println("User: " + user.getUserName());
		}
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
}