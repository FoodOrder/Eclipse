package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Type;
import fcu.android.backend.data.Shop;
import fcu.android.backend.service.ShopService;

public class TypeDBManager {
	
	private static TypeDBManager DB_MANAGER = new TypeDBManager();

	public static TypeDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private TypeDBManager() {

	}
	
	public List<Shop> getShopFromType(int type_id) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from TYPE where type = ?";
		
		List<Shop> lsShop = new ArrayList<Shop>();
		ShopService shopService = new ShopService();
		int sid = -1;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, type_id);
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
	
	public List<Type> getTypeFromShop(int shopId) {
		List<Type> lsTypes = new ArrayList<Type>();
		
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from TYPE where shopId = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shopId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int type_id = rs.getInt("type");
				
				Type type = new Type();
				type.setType(type_id);
				
				lsTypes.add(type);
			}
			stmt.close();
			conn.commit();
			return lsTypes;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Type>();
	}

	public List<Type> listAllTypes() {
		List<Type> lsTypes = new ArrayList<Type>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM TYPE";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int type_id = rs.getInt("type");

				Type type = new Type();
				type.setId(id);
				type.setShopId(shopId);
				type.setType(type_id);
				lsTypes.add(type);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsTypes;
	}

}
