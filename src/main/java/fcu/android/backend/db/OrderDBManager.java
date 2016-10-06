package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Order;

public class OrderDBManager {

	private static OrderDBManager DB_MANAGER = new OrderDBManager();

	public static OrderDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private OrderDBManager() {

	}

	public boolean addOrder(Order order) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		Statement stmt = null;
		String findShopId = "select * from SHOP where email=?";
		String findUserId = "select * from USER where email=?";
		String sql = "INSERT INTO ORDER(shopId, userId, orderTime)  VALUES(?, ?, ?)";
		String query = "SELECT * FROM ORDER";
		try {
			statement = conn.prepareStatement(findShopId);
			statement.setString(1, order.getShopEmail());
			ResultSet rs_sid = statement.executeQuery();

			int sid = -1;
			while (rs_sid.next()) {
				sid = rs_sid.getInt("ID");
			}
			System.out.println(sid);
			
			statement1 = conn.prepareStatement(findUserId);
			statement1.setString(1, order.getUserEmail());
			ResultSet rs_uid = statement1.executeQuery();

			int uid = -1;
			while (rs_uid.next()) {
				uid = rs_uid.getInt("ID");
			}

			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, sid);
			preStmt.setInt(2, uid);
			preStmt.setString(3, order.getOrderTime());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Orders");
			while (rs.next()) {
				System.out.println("ShopID: " + rs.getInt("shopId") + ", UserID: " + rs.getInt("userID") 
					+ ", Order Time:" + rs.getString("orderTime"));
			}
			stmt.close();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

/*	public boolean updateMenu(Menu menu) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		Statement stmt = null;
		String sql = "UPDATE MENU SET MenuName=?, MenuPrice=? WHERE id=?";
		String query = "SELECT * FROM MENU";
		try {
		
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, menu.getMenuName());
			preStmt.setInt(2, menu.getMenuPrice());
			preStmt.setInt(3, menu.getId());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Menus");
			while (rs.next()) {
				System.out.println("MenuId: " + rs.getInt("id") + ", Menu Name: " + rs.getString("MenuName") + ", MenuPrice: " + rs.getString("MenuPrice"));
			}
			stmt.close();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	*/

	/*
	 * public boolean validateShop(String email, String password) { Connection
	 * conn = database.getConnection(); PreparedStatement stmt = null; String
	 * query = "SELECT * FROM SHOP WHERE email = ? and password = ?"; try { stmt
	 * = conn.prepareStatement(query); stmt.setString(1, email);
	 * stmt.setString(2, password); ResultSet rs = stmt.executeQuery(); boolean
	 * valid = rs.first(); stmt.close(); conn.commit(); return valid; } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try { conn.close(); }
	 * catch (SQLException e) { e.printStackTrace(); } } return false; }
	 */

	public List<Order> getOrder(String ShopEmail) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement statement = null;
		String findShopId = "select * from SHOP where email=?";
		String query = "select * from ORDER where shopId = ?";
		try {

			statement = conn.prepareStatement(findShopId);
			statement.setString(1, ShopEmail);
			ResultSet rs_id = statement.executeQuery();

			int sid = -1;
			while (rs_id.next()) {
				sid = rs_id.getInt("ID");
			}

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, sid);
			ResultSet rs = stmt.executeQuery();

			List<Order> lsOrder = new ArrayList<Order>();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderTime(rs.getString("orderTime"));;
				order.setShopId(rs.getInt("shopId"));;
				order.setUserId(rs.getInt("userId"));;
				order.setId(rs.getInt("id"));
				lsOrder.add(order);
			}

			stmt.close();
			conn.commit();
			return lsOrder;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Order>();
	}

	public List<Order> listAllOrder() {
		List<Order> lsOrder = new ArrayList<Order>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM ORDER";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int shopId = rs.getInt("shopId");
				int userId = rs.getInt("userId");
				String orderTime = rs.getString("orderTime");

				Order order = new Order();
				order.setId(id);
				order.setShopId(shopId);;
				order.setUserId(userId);;
				order.setOrderTime(orderTime);;
				lsOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsOrder;
	}

}
