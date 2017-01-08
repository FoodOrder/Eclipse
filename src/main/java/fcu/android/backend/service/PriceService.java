package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Price;
import fcu.android.backend.db.PriceDBManager;

@Path("price/")
public class PriceService {

	private PriceDBManager dbManager = PriceDBManager.getInstance();

	@GET
	@Path("priceId/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("price") int price_id) {
		return dbManager.getShopFromPrice(price_id);
	}

	@GET
	@Path("shopId/{shop}")
	@Produces(MediaType.APPLICATION_JSON)
	public Price getTypes(@PathParam("shop") int shop_id) {
		return dbManager.getPriceFromShop(shop_id);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Price> listTypes() {
		return dbManager.listAllPrices();
	}

}
