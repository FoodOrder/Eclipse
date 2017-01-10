package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fcu.android.backend.data.Period;
import fcu.android.backend.data.Shop;
import fcu.android.backend.db.PeriodDBManager;

@Path("period/")
public class PeriodService {
	private PeriodDBManager dbManager = PeriodDBManager.getInstance();

	@GET
	@Path("periodId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("id") int period_id) {
		return dbManager.getShopFromPeriod(period_id);
	}

	@GET
	@Path("shopId/{shop}")
	@Produces(MediaType.APPLICATION_JSON)
	public Period getTypes(@PathParam("shop") int shop_id) {
		return dbManager.getPeriodFromShop(shop_id);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Period> listTypes() {
		return dbManager.listAllPeriods();
	}

}
