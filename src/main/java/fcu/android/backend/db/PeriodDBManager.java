package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Period;
import fcu.android.backend.data.Shop;
import fcu.android.backend.service.ShopService;

public class PeriodDBManager {
	
	private static PeriodDBManager DB_MANAGER = new PeriodDBManager();

	public static PeriodDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private PeriodDBManager() {

	}
	
	public List<Shop> getShopFromPeriod(int Period_id) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from PRIDAY_OR_NIGHTCE where DayNight = ?";
		
		List<Shop> lsShop = new ArrayList<Shop>();
		ShopService shopService = new ShopService();
		int sid = -1;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Period_id);
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
	
	public Period getPeriodFromShop(int shopId) {		
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from DAY_OR_NIGHT where shopId = ?";
		
		Period period = new Period();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shopId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				period.setPeriod(rs.getInt("DAY_OR_NIGHT"));
			}
			stmt.close();
			conn.commit();
			return period;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Period();
	}

	public List<Period> listAllPeriods() {
		List<Period> lsPeriods = new ArrayList<Period>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM DAY_OR_NIGHT";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int DayNight_id = rs.getInt("price");

				Period period = new Period();
				period.setId(id);
				period.setShopId(shopId);
				period.setPeriod(DayNight_id);
				lsPeriods.add(period);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsPeriods;
	}
}
