
package fcu.android.backend.service;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.text.DateFormat;
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
		
		dbManager.addOrderList(order);
		System.out.println(id);
		return true;
	}
	
	@GET
	@Path("getOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrder() {
		Order order = new Order();
		Date nowTime = new Date();
		System.out.println(String.valueOf(nowTime.getHours() + ":" + nowTime.getMinutes() +":" + nowTime.getSeconds()));
		order.setOrderTime(String.valueOf(nowTime.getHours() + ":" + nowTime.getMinutes() +":" + nowTime.getSeconds()));;
		//order.setOrderId(10);
		
		ArrayList<OrderItem> lsItems = new ArrayList<OrderItem>();
		/*
		for(int i = 0; i < 3 ; i++)
		{
			OrderItem item = new OrderItem();
			item.setFoodId(1);
			item.setAmount(10);
			lsItems.add(item);
		}
		*/
		
	
		for(int i = 0; i < lsItems.size() ; i++){
			//additem(,lsItems);
		}
		
		
		/*
		OrderItem item1 = new OrderItem();
		item1.setFoodId(1);
		item1.setAmount(10);
		lsItems.add(item1);
		
		OrderItem item2 = new OrderItem();
		item2.setFoodId(2);
		item2.setAmount(5);
		lsItems.add(item2);
		*/
		
		order.setItems(lsItems);
		return order;
	}
	
	public void additem(int foodId,int amount,ArrayList<OrderItem> lsItems){
		OrderItem item = new OrderItem();
		item.setFoodId(foodId);
		item.setAmount(amount);
		lsItems.add(item);
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
