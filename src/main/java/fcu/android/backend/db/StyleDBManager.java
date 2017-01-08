package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Shop;
import fcu.android.backend.data.Style;
import fcu.android.backend.service.ShopService;

public class StyleDBManager {
	
	private static StyleDBManager DB_MANAGER = new StyleDBManager();

	public static StyleDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private StyleDBManager() {

	}
	
	public List<Shop> getShopFromStyle(int style_id) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from STYLE where style = ?";
		
		List<Shop> lsShop = new ArrayList<Shop>();
		ShopService shopService = new ShopService();
		int id = -1;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, style_id);
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
	
	public List<Style> getStyleFromShop(int shopId) {
		List<Style> lsStyles = new ArrayList<Style>();
		
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from TYPE where shopId = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shopId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int style_id = rs.getInt("type");
				
				Style style = new Style();
				style.setStyle(style_id);
				
				lsStyles.add(style);
			}
			stmt.close();
			conn.commit();
			return lsStyles;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Style>();
	}

	public List<Style> listAllStyles() {
		List<Style> lsStyles = new ArrayList<Style>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM STYLE";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int style_id = rs.getInt("style");

				Style style = new Style();
				style.setId(id);
				style.setShopId(shopId);
				style.setStyle(style_id);
				lsStyles.add(style);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsStyles;
	}

}
