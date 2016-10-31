package com.mbc.server.rest.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mbc.server.database.service.login.LoginDBService;
import com.mbc.server.dto.login.LoginDto;
import com.mbc.server.factory.login.LoginFactory;
 
@Path("/login")
public class LoginRestService {
	
	private LoginDBService loginDBService = new LoginDBService();
	
	@Path("{userName}")
	@GET
	@Produces("application/json")
	public LoginDto getUserByUserName(@PathParam("userName") String userName) {
		return LoginFactory.convertUserToLoginDto(loginDBService.getUserByUserName(userName));
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public LoginDto login(LoginDto loginDto) {
		return LoginFactory.login(loginDto, loginDBService.login(loginDto));
	}
	
	@Path("add")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public LoginDto createNewUser(LoginDto loginDto) {
		return LoginFactory.convertUserToLoginDto(loginDBService.createNewUser(loginDto));
	}
	
	@Path("update")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public LoginDto updateUserPassword(LoginDto loginDto) {
		return LoginFactory.convertUserToLoginDto(loginDBService.updateUser(loginDto));
	}
	
	@Path("delete")
	@DELETE
	@Consumes("application/json")
	@Produces("application/json")
	public Response deleteUser(LoginDto loginDto) {
		loginDBService.deleteUser(loginDto);
		return Response.ok().build();
	}
	
}