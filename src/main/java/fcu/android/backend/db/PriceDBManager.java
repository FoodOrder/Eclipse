package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Price;
import fcu.android.backend.service.ShopService;

public class PriceDBManager {
	
	private static PriceDBManager DB_MANAGER = new PriceDBManager();

	public static PriceDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private PriceDBManager() {

	}
	
	public List<Shop> getShopFromPrice(int Price_id) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from PRICE where price = ?";
		
		List<Shop> lsShop = new ArrayList<Shop>();
		ShopService shopService = new ShopService();
		int id = -1;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Price_id);
			ResultSet rs = stmt.executeQuery();
			Shop shop = new Shop();
			
			while (rs.next()) {
				id = rs.getInt("id");
				shop = shopService.getShop(id);
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
	
	public Price getPriceFromShop(int shopId) {		
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from PRICE where shopId = ?";
		
		Price price = new Price();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shopId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				price.setPrice(rs.getInt("price"));
			}
			stmt.close();
			conn.commit();
			return price;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Price();
	}

	public List<Price> listAllPrices() {
		List<Price> lsPrices = new ArrayList<Price>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM PRICE";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int price_id = rs.getInt("price");

				Price price = new Price();
				price.setId(id);
				price.setShopId(shopId);
				price.setPrice(price_id);
				lsPrices.add(price);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsPrices;
	}

}
