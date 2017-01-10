
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

import fcu.android.backend.data.Shop;
import fcu.android.backend.db.ShopDBManager;

@Path("shop/")
public class ShopService {

	private ShopDBManager dbManager = ShopDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Shop register(@FormParam("shopName") String shopName, @FormParam("password") String password,
			@FormParam("email") String email, @FormParam("phone") String phone, @FormParam("intro") String intro, @FormParam("longitude") Double longitude, @FormParam("latitude") Double latitude) {
		Shop shop = new Shop();
		shop.setShopName(shopName);
		shop.setPassword(password);
		shop.setEmail(email);
		shop.setPhone(phone);
		shop.setIntro(intro);
		shop.setLongitude(longitude);
		shop.setLatitude(latitude);
		dbManager.addShop(shop);
		return shop;
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Shop update(@FormParam("shopName") String shopName, @FormParam("password") String password,
			 @FormParam("phone") String phone, @FormParam("email") String email, @FormParam("intro") String intro, @FormParam("longitude") Double longitude, @FormParam("latitude") Double latitude) {
		Shop shop = new Shop();
		shop.setShopName(shopName);
		shop.setPassword(password);
		shop.setEmail(email);
		shop.setPhone(phone);
		shop.setIntro(intro);
		shop.setLongitude(longitude);
		shop.setLatitude(latitude);
		dbManager.updateShop(shop);
		return shop;
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
	public boolean isValidShop(@FormParam("email") String email, @FormParam("password") String password) {
		boolean valid = dbManager.validateShop(email, password);
		return valid;
	}

	@GET
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shop getShop(@PathParam("email") String email) {

		return dbManager.getShop(email);
	}
	
	public Shop getShop(int shopId){
		return dbManager.getShop(shopId);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> listShops() {
		return dbManager.listAllShops();
	}
	
	@GET
	@Path("list_iOS")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> listShops_IOS() {
		return dbManager.listAllShops_IOS();
	}
}
