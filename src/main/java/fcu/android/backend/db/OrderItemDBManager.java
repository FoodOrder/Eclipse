package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;

import fcu.android.backend.data.Menu;
import fcu.android.backend.data.OrderItem;
import fcu.android.backend.data.Shop;

public class OrderItemDBManager {

	private static OrderItemDBManager DB_MANAGER = new OrderItemDBManager();

	public static OrderItemDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private OrderItemDBManager() {

	}

	public boolean addOrderItem(OrderItem orderitem) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		Statement stmt = null;
		String sql = "INSERT INTO ORDER_ITEM(orderId, foodId, amount)  VALUES(?, ?, ?)";
		String query = "SELECT * FROM ORDER_ITEM";
		try {
			preStmt = conn.prepareStatement(sql);
		//	preStmt.setInt(1, orderitem.getOrderid());
			preStmt.setInt(2, orderitem.getFoodId());
			preStmt.setInt(3, orderitem.getAmount());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All OrderItem");
			while (rs.next()) {
				System.out.println("FoodId: " + rs.getInt("foodId") + ", Amount: " + rs.getInt("amount"));
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

	/*
	 * public boolean updateMenu(Menu menu) { Connection conn =
	 * database.getConnection(); PreparedStatement preStmt = null; Statement
	 * stmt = null; String sql =
	 * "UPDATE MENU SET MenuName=?, MenuPrice=? WHERE id=?"; String query =
	 * "SELECT * FROM MENU"; try {
	 * 
	 * preStmt = conn.prepareStatement(sql); preStmt.setString(1,
	 * menu.getMenuName()); preStmt.setInt(2, menu.getMenuPrice());
	 * preStmt.setInt(3, menu.getId()); preStmt.executeUpdate();
	 * preStmt.close();
	 * 
	 * stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);
	 * System.out.println("List All Menus"); while (rs.next()) {
	 * System.out.println("MenuId: " + rs.getInt("id") + ", Menu Name: " +
	 * rs.getString("MenuName") + ", MenuPrice: " + rs.getString("MenuPrice"));
	 * } stmt.close(); conn.commit(); return true; } catch (SQLException e) {
	 * e.printStackTrace(); } finally { try { conn.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } return false; }
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

	public List<OrderItem> getOrderItem(int orderId) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement statement = null;
		String query = "select * from ORDER_ITEM where orderId=?";
		try {

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			List<OrderItem> lsOrderItem = new ArrayList<OrderItem>();

			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
		//		orderItem.setOrderid(orderId);
				orderItem.setFoodId(rs.getInt("foodId"));
				orderItem.setAmount(rs.getInt("amount"));
				lsOrderItem.add(orderItem);
			}

			stmt.close();
			conn.commit();
			return lsOrderItem;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<OrderItem>();
	}

	public List<OrderItem> listAllOrderItem() {
		List<OrderItem> lsOrderItem = new ArrayList<OrderItem>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM ORDER_ITEM";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int orderid = rs.getInt("id");
				int foodId = rs.getInt("foodId");
				int amount = rs.getInt("amount");

				OrderItem orderItem = new OrderItem();
//				orderItem.setOrderid(orderid);
				orderItem.setFoodId(foodId);
				orderItem.setAmount(amount);
				lsOrderItem.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsOrderItem;
	}

}
