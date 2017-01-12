package fcu.android.backend.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Region;
import fcu.android.backend.db.RegionDBManager;

@Path("region/")
public class RegionService {
	
	private RegionDBManager dbManager = RegionDBManager.getInstance();
	
	@GET
	@Path("regionId/{region}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("region") int region_id) {
		return dbManager.getShopFromRegion(region_id);
	}
	
	@GET
	@Path("shopId/{shop}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Region> getRegions(@PathParam("shop") int shop_id) {
		return dbManager.getRegionFromShop(shop_id);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Region> listRegions() {
		return dbManager.listAllRegions();
	}
	
	@GET
	@Path("gate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> distance_gate() {
		return dbManager.distance_gate();
	}
	
	@GET
	@Path("MOS")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> distance_MOS() {
		return dbManager.distance_MOS();
	}
	
	@GET
	@Path("WenHua")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> distance_WenHua() {
		return dbManager.distance_WenHua();
	}
	
	@GET
	@Path("East")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> distance_EastGate() {
		return dbManager.distance_EastGate();
	}
	
	@GET
	@Path("HanLin")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> distance_HanLin() {
		return dbManager.distance_HanLin();
	}

}
