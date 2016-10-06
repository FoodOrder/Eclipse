
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

import fcu.android.backend.data.Order;
import fcu.android.backend.db.OrderDBManager;

@Path("order/")
public class OrderService {

	private OrderDBManager dbManager = OrderDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Order addOrder(@FormParam("ShopEmail") String shopEmail, @FormParam("UserEmail") String userEmail, 
			@FormParam("orderTime") String orderTime) {
		Order order = new Order();
		order.setShopEmail(shopEmail);
		order.setUserEmail(userEmail);
		order.setOrderTime(orderTime);
		dbManager.addOrder(order);
		return order;
	}

//	@POST

//	@Path("update")
//
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//
//	@Produces(MediaType.APPLICATION_JSON)
//	public Menu update(@FormParam("id") int id, @FormParam("MenuName") String menuName, @FormParam("MenuPrice") int menuPrice) {
//		Menu menu = new Menu();
//		menu.setId(id);
//		menu.setMenuName(menuName);
//		menu.setMenuPrice(menuPrice);
//		//menu.setEmail(email);
//		dbManager.updateMenu(menu);
//		return menu;
//	}
//
//	@GET
//	@Path("hello")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String hello() {
//		return "hello";
//	}

//	@GET
//	@Path("ok/{email}/{password}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String ok(@PathParam("email") String email, @PathParam("password") String pass) {
//		if (email.equals("a") && pass.equals("b")) {
//			return "true";
//		}
//
//		return "false";
//	}

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
	public List<Order> getOrder(@PathParam("ShopEmail") String ShopEmail) {

		return dbManager.getOrder(ShopEmail);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> listOrders() {
		return dbManager.listAllOrder();
	}
}
