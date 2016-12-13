
package fcu.android.backend.service;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fcu.android.backend.data.Menu;
import fcu.android.backend.data.Order;
import fcu.android.backend.data.OrderItem;
import fcu.android.backend.db.OrderDBManager;

@Path("order/")
public class OrderService{

	private OrderDBManager dbManager = OrderDBManager.getInstance();
	
	@POST
	@Path("addOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addOrder(Order order) {
		int id = order.getId();
		ArrayList<OrderItem> lsItems = new ArrayList<OrderItem>();
		
		for(int i = 0; i < order.getItems().size() ; i++){
			additem(order.getItems().get(i).getFoodId(),order.getItems().get(i).getAmount(),lsItems);
		}
		System.out.println("Order成功");
		dbManager.addOrderList(order);
		System.out.println(id);
		return true;
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@FormParam("status") String status, @FormParam("id") String id) {
		Order order = new Order();
		int newId = Integer.parseInt(id);
		int newStatus = Integer.parseInt(status);
		order.setId(newId);
		order.setStatus(newStatus);
		System.out.println(id + ", " +  status);
		boolean check = dbManager.updateOrder(order);
		return String.valueOf(check);
	}
	
	@POST
	@Path("addTime")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void addTime(@FormParam("id") int id, @FormParam("type") int type) {
		dbManager.addTime(id, type);
	}
	
	@GET
	@Path("getOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrder() {
		Order order = new Order();
		
		//order.setOrderId(10);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		System.out.println(sdFormat.format(current));
		order.setOrderTime(sdFormat.format(current));
		
		
		
		ArrayList<OrderItem> lsItems = new ArrayList<OrderItem>();
		
		/*
		for(int i = 0; i < order.getItems().size() ; i++){
			additem(order.getItems().get(i).getFoodId(),order.getItems().get(i).getAmount(),lsItems);
		}
		*/
		
		
		OrderItem item1 = new OrderItem();
		item1.setFoodId(1);
		item1.setAmount(10);
		lsItems.add(item1);
		
		OrderItem item2 = new OrderItem();
		item2.setFoodId(2);
		item2.setAmount(5);
		lsItems.add(item2);
		
		order.setItems(lsItems);
		return order;
	}
	
	public boolean additem(int foodId,int amount,ArrayList<OrderItem> lsItems){
		OrderItem item = new OrderItem();
		item.setFoodId(foodId);
		item.setAmount(amount);
		boolean check = lsItems.add(item);
		return check;
	}

	@GET
	@Path("shop/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrder(@PathParam("email") String ShopEmail) {

		return dbManager.getOrder(ShopEmail);
	}
	
	@GET
	@Path("userId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrder(@PathParam("id") int UserId) {

		return dbManager.getOrder(UserId);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> listOrders() {
		return dbManager.listAllOrder();
	}
}
