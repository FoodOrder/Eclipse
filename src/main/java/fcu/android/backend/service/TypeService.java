package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Type;
import fcu.android.backend.db.TypeDBManager;

@Path("type/")
public class TypeService {
	
	private TypeDBManager dbManager = TypeDBManager.getInstance();
	
	@GET
	@Path("typeId/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("type") int type_id) {
		return dbManager.getShopFromType(type_id);
	}
	
	@GET
	@Path("shopId/{shop}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Type> getTypes(@PathParam("shop") int shop_id) {
		return dbManager.getTypeFromShop(shop_id);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Type> listTypes() {
		return dbManager.listAllTypes();
	}

}
