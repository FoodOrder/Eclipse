package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fcu.android.backend.data.User;
import fcu.android.backend.db.UserDBManager;

@Path("user/")
public class UserService {

	private UserDBManager dbManager = UserDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String register(@FormParam("userName") String userName, @FormParam("password") String password,
			@FormParam("email") String email, @FormParam("phone") String phone) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		boolean check = dbManager.addUser(user);
		
		return String.valueOf(check);
	}

	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello";
	}

	@GET
	@Path("ok/{email}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ok(@PathParam("email") String email, @PathParam("password") String pass) {
		if (email.equals("a") && pass.equals("b")) {
			return "true";
		}

		return "false";
	}

	@POST
	@Path("validate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String isValidUser(@FormParam("email") String email, @FormParam("password") String password) {
		boolean valid = dbManager.validateUser(email, password);
		return String.valueOf(valid);
	}

	@GET
	@Path("email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("email") String email) {

		return dbManager.getUser(email);
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int id) {

		return dbManager.getUser(id);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		return dbManager.listAllUsers();
	}

}
