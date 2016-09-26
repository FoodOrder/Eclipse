
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
	public Menu addMenu(@FormParam("id") int id, @FormParam("MenuName") String MenuName,
			@FormParam("MenuPrice") int MenuPrice, @FormParam("ShopEmail") String ShopEmail) {
		Menu menu = new Menu();
		menu.setId(id);
		menu.setMenuName(MenuName);
		menu.setMenuPrice(MenuPrice);
		menu.setShopEmail(ShopEmail);
		dbManager.addMenu(menu);
		return menu;
	}

	/*
	 * @POST
	 * 
	 * @Path("update")
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Shop
	 * update(@FormParam("shopName") String shopName, @FormParam("password")
	 * String password,
	 * 
	 * @FormParam("phone") String phone, @FormParam("email") String
	 * email, @FormParam("intro") String intro) { Shop shop = new Shop();
	 * shop.setShopName(shopName); shop.setPassword(password);
	 * shop.setEmail(email); shop.setPhone(phone); shop.setIntro(intro);
	 * dbManager.updateShop(shop); return shop; }
	 */

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
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> getMenu(@PathParam("ShopEmail") String ShopEmail) {

		return dbManager.getMenu(ShopEmail);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> listShops() {
		return dbManager.listAllMenu();
	}
}
