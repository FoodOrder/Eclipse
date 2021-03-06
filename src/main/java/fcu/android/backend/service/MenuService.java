
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

import fcu.android.backend.data.Menu;
import fcu.android.backend.db.MenuDBManager;

@Path("menu/")
public class MenuService {

	private MenuDBManager dbManager = MenuDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Menu addMenu(@FormParam("MenuName") String MenuName, @FormParam("MenuPrice") int MenuPrice,
			@FormParam("ShopEmail") String ShopEmail) {
		Menu menu = new Menu();
		menu.setMenuName(MenuName);
		menu.setMenuPrice(MenuPrice);
		menu.setShopEmail(ShopEmail);
		dbManager.addMenu(menu);
		return menu;
	}
	
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Menu delete(@FormParam("id") int id) {
		Menu menu = new Menu();
		menu.setId(id);
		//menu.setEmail(email);
		dbManager.deleteMenu(menu);
		return menu;
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Menu update(@FormParam("id") int id, @FormParam("MenuName") String menuName, @FormParam("MenuPrice") int menuPrice) {
		Menu menu = new Menu();
		menu.setId(id);
		menu.setMenuName(menuName);
		menu.setMenuPrice(menuPrice);
		//menu.setEmail(email);
		dbManager.updateMenu(menu);
		return menu;
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

	/*
	 * @POST
	 * 
	 * @Path("validate")
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public boolean
	 * isValidShop(@FormParam("email") String email, @FormParam("password")
	 * String password) { boolean valid = dbManager.validateShop(email,
	 * password); return valid; }
	 */

	@GET
	@Path("email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> getMenu(@PathParam("email") String ShopEmail) {

		return dbManager.getMenu(ShopEmail);
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Menu getMenu(@PathParam("id") int id) {

		return dbManager.getMenu(id);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> listMenus() {
		return dbManager.listAllMenu();
	}
}
