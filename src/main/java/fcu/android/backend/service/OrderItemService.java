
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
		orderItem.setFoodId(foodId);
		orderItem.setAmount(amount);
		dbManager.addOrderItem(orderItem);
		return orderItem;
	}

	@GET
	@Path("{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderItem> getOrderItem(@PathParam("orderId") int orderId) {

		return dbManager.getOrderItem(orderId);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderItem> listOrders() {
		return dbManager.listAllOrderItem();
	}
}