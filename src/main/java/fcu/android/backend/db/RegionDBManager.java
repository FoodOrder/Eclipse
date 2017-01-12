package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Region;
import fcu.android.backend.data.Shop;
import fcu.android.backend.service.ShopService;

public class RegionDBManager {
	ShopService shopService = new ShopService();
	
	private static RegionDBManager DB_MANAGER = new RegionDBManager();

	public static RegionDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private RegionDBManager() {

	}
	
	public List<Shop> getShopFromRegion(int region_id) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from REGION where region = ?";
		
		List<Shop> lsShop = new ArrayList<Shop>();
		ShopService shopService = new ShopService();
		int sid = -1;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, region_id);
			ResultSet rs = stmt.executeQuery();
			Shop shop = new Shop();
			
			while (rs.next()) {
				sid = rs.getInt("shopId");
				shop = shopService.getShop(sid);
				lsShop.add(shop);
			}
			stmt.close();
			conn.commit();
			return lsShop;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Shop>();
	}
	
	public List<Region> getRegionFromShop(int shopId) {
		List<Region> lsRegions = new ArrayList<Region>();
		
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from REGION where shopId = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shopId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int region_id = rs.getInt("region");
				
				Region region = new Region();
				region.setRegion(region_id);
				lsRegions.add(region);
			}
			stmt.close();
			conn.commit();
			return lsRegions;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Region>();
	}

	public List<Region> listAllRegions() {
		List<Region> lsRegions = new ArrayList<Region>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM REGION";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int region_id = rs.getInt("region");

				Region region = new Region();
				region.setId(id);
				region.setShopId(shopId);
				region.setRegion(region_id);
				lsRegions.add(region);
			}
			stmt.close();
			conn.commit();
			return lsRegions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsRegions;
	}
	
	public List<Shop> distance_gate() {
		double gate_lng = 120.646675;
		double gate_lat = 24.178815;
		double distance = 0.0;
		
		List<Shop> shops = shopService.listShops();
		List<Shop> lsRegions = new ArrayList<Shop>();
		
		for(Shop shop : shops) {
			Region region = new Region();
			double shopLat = shop.getLatitude();
			double shopLng = shop.getLongitude();
			
			distance = region.GetDistance(shopLat, shopLng, gate_lat, gate_lng);
			
			System.out.println(shopLat + ", " + shopLng);
			System.out.println(region.GetDistance(shopLat, shopLng, gate_lat, gate_lng));
			if(distance <= 150.0){
				lsRegions.add(shop);
			}
		}
		return lsRegions;
	}
	
	public List<Shop> distance_MOS() {
		double gate_lng = 120.646425;
		double gate_lat = 24.181152;
		double distance = 0.0;
		
		List<Shop> shops = shopService.listShops();
		List<Shop> lsRegions = new ArrayList<Shop>();
		
		for(Shop shop : shops) {
			Region region = new Region();
			double shopLat = shop.getLatitude();
			double shopLng = shop.getLongitude();
			
			distance = region.GetDistance(shopLat, shopLng, gate_lat, gate_lng);
			
			System.out.println(shopLat + ", " + shopLng);
			System.out.println(region.GetDistance(shopLat, shopLng, gate_lat, gate_lng));
			if(distance <= 150.0){
				lsRegions.add(shop);
			}
		}
		return lsRegions;
	}
	
	public List<Shop> distance_WenHua() {
		double gate_lng = 120.646291;
		double gate_lat = 24.183134;
		double distance = 0.0;
		
		List<Shop> shops = shopService.listShops();
		List<Shop> lsRegions = new ArrayList<Shop>();
		
		for(Shop shop : shops) {
			Region region = new Region();
			double shopLat = shop.getLatitude();
			double shopLng = shop.getLongitude();
			
			distance = region.GetDistance(shopLat, shopLng, gate_lat, gate_lng);
			
			System.out.println(shopLat + ", " + shopLng);
			System.out.println(region.GetDistance(shopLat, shopLng, gate_lat, gate_lng));
			if(distance <= 150.0){
				lsRegions.add(shop);
			}
		}
		return lsRegions;
	}
	
	public List<Shop> distance_EastGate() {
		double gate_lng = 120.650257;
		double gate_lat = 24.178514;
		double distance = 0.0;
		
		List<Shop> shops = shopService.listShops();
		List<Shop> lsRegions = new ArrayList<Shop>();
		
		for(Shop shop : shops) {
			Region region = new Region();
			double shopLat = shop.getLatitude();
			double shopLng = shop.getLongitude();
			
			distance = region.GetDistance(shopLat, shopLng, gate_lat, gate_lng);
			
			System.out.println(shopLat + ", " + shopLng);
			System.out.println(region.GetDistance(shopLat, shopLng, gate_lat, gate_lng));
			if(distance <= 400.0){
				lsRegions.add(shop);
			}
		}
		return lsRegions;
	}
	
	public List<Shop> distance_HanLin() {
		double gate_lng = 120.644979;
		double gate_lat = 24.175470;
		double distance = 0.0;
		
		List<Shop> shops = shopService.listShops();
		List<Shop> lsRegions = new ArrayList<Shop>();
		
		for(Shop shop : shops) {
			Region region = new Region();
			double shopLat = shop.getLatitude();
			double shopLng = shop.getLongitude();
			
			distance = region.GetDistance(shopLat, shopLng, gate_lat, gate_lng);
			
			System.out.println(shop.getShopName());
			System.out.println(shopLat + ", " + shopLng);
			System.out.println(region.GetDistance(shopLat, shopLng, gate_lat, gate_lng));
			if(distance <= 150.0){
				lsRegions.add(shop);
			}
		}
		return lsRegions;
	}

}
