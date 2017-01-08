package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Style;
import fcu.android.backend.db.StyleDBManager;

@Path("style/")
public class StyleService {

private StyleDBManager dbManager = StyleDBManager.getInstance();
	
	@GET
	@Path("styleId/{style}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("style") int style_id) {
		return dbManager.getShopFromStyle(style_id);
	}
	
	@GET
	@Path("shopId/{shop}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Style> getTypes(@PathParam("shop") int shop_id) {
		return dbManager.getStyleFromShop(shop_id);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Style> listStyles() {
		return dbManager.listAllStyles();
	}
}
