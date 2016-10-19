
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

import fcu.android.backend.data.OrderItem;
import fcu.android.backend.db.OrderItemDBManager;

@Path("orderItem/")
public class OrderItemService {

	private OrderItemDBManager dbManager = OrderItemDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderItem addOrderItem(@FormParam("orderId") int orderId, @FormParam("foodId") int foodId, @FormParam("amount") int amount) {
		OrderItem orderItem = new OrderItem();
//		orderItem.setOrderid(orderId);
		orderItem.setFoodId(foodId);
		orderItem.setAmount(amount);
		dbManager.addOrderItem(orderItem);
		return orderItem;
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
	public List<OrderItem> getOrderItem(@PathParam("OrderId") int orderId) {

		return dbManager.getOrderItem(orderId);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderItem> listOrders() {
		return dbManager.listAllOrderItem();
	}
}