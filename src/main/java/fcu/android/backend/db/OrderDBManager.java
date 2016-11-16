package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Date;

import javax.validation.constraints.Size;

import fcu.android.backend.data.Menu;
import fcu.android.backend.data.Order;
import fcu.android.backend.data.OrderItem;

public class OrderDBManager {

	private static OrderDBManager DB_MANAGER = new OrderDBManager();

	public static OrderDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private OrderDBManager() {

	}

	public boolean addOrderList(Order order) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null; // insert order
		PreparedStatement statement_findUser = null; // find userId

		Statement stmt = null; // select order
		String findUserId = "select * from USER where email=?";
		String insertOrder = "insert into ORDER_LIST(shopId, userId, orderTime) values(?, ?, ?)";
		String selectOrder = "SELECT * FROM ORDER_LIST";

		try {

			// find userId
			statement_findUser = conn.prepareStatement(findUserId);
			statement_findUser.setString(1, order.getUserEmail());
			ResultSet rs_uid = statement_findUser.executeQuery();

			int uid = -1;
			while (rs_uid.next()) {
				uid = rs_uid.getInt("ID");
			}
			statement_findUser.close();

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date current = new Date();

			System.out.println("shopId: " + order.getShopId());
			System.out.println("uid: " + uid);
			System.out.println("OrderTime: " + sdFormat.format(current));

			// insert order
			preStmt = conn.prepareStatement(insertOrder);
			preStmt.setInt(1, order.getShopId());
			preStmt.setInt(2, uid);
			preStmt.setString(3, sdFormat.format(current));
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectOrder);
			System.out.println("List All Orders");
			while (rs.next()) {
				System.out.println(" UserID: " + rs.getInt("userID") + ", Order Time:" + rs.getString("orderTime"));
			}
			stmt.close();
			conn.commit();

			addOrderItem(order, uid);

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

	public boolean addOrderItem(Order order, int uid) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null; // insert order
		PreparedStatement statement_findId = null; // find userId

		Statement stmt = null; // select order
		String findUserId = "select * from ORDER_LIST where userId=?";
		String insertOrder = "insert into ORDER_ITEM(orderId, foodId, amount) values(?, ?, ?)";
		String selectOrder = "SELECT * FROM ORDER_ITEM";

		try {

			// find userId
			statement_findId = conn.prepareStatement(findUserId);
			statement_findId.setInt(1, uid);
			ResultSet rs_id = statement_findId.executeQuery();

			int id = -1;
			while (rs_id.next()) {
				id = rs_id.getInt("ID");
			}
			System.out.println("id: " + id);
			statement_findId.close();

			for (int i = 0; i < order.getItems().size(); i++) {
				preStmt = conn.prepareStatement(insertOrder);

				preStmt.setInt(1, id);
				preStmt.setInt(2, order.getItems().get(i).getFoodId());
				preStmt.setInt(3, order.getItems().get(i).getAmount());
				preStmt.executeUpdate();
				preStmt.close();
			}

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectOrder);
			System.out.println("List All Orders");
			while (rs.next()) {
				System.out.println("OrderId: " + rs.getInt("orderId") + ", foodId: " + rs.getInt("foodId") + ", Amount:"
						+ rs.getString("amount"));
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

	public boolean updateOrder(Order order) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		Statement stmt = null;
		String sql = "UPDATE ORDER_LIST SET status=? WHERE id=?";
		String query = "SELECT * FROM ORDER_LIST";
		try {

			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, order.getStatus());
			preStmt.setInt(2, order.getId());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Menus");
			while (rs.next()) {
				System.out.println("OrderId: " + rs.getInt("id") + ", userId: " + rs.getInt("userId") + ", orderTime: " + rs.getString("orderTime"));
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
		String query = "select * from ORDER_LIST where shopId = ?";
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
				order.setOrderTime(rs.getString("orderTime"));
				order.setConfirmTime(rs.getString("confirmTime"));
				order.setOutsetTime(rs.getString("outsetTime"));
				order.setArriveTime(rs.getString("arriveTime"));
				order.setStatus(rs.getInt("status"));
				order.setShopId(rs.getInt("shopId"));
				order.setUserId(rs.getInt("userId"));
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
	
	public List<Order> getOrder(int UserId) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement statement = null;
//		String findShopId = "select * from USER where email=?";
		String query = "select * from ORDER_LIST where userId = ?";
		try {

//			statement = conn.prepareStatement(findShopId);
//			statement.setString(1, UserEmail);
//			ResultSet rs_id = statement.executeQuery();
//
//			int uid = -1;
//			while (rs_id.next()) {
//				uid = rs_id.getInt("ID");
//			}

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, UserId);
			ResultSet rs = stmt.executeQuery();

			List<Order> lsOrder = new ArrayList<Order>();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderTime(rs.getString("orderTime"));
				order.setConfirmTime(rs.getString("confirmTime"));
				order.setOutsetTime(rs.getString("outsetTime"));
				order.setArriveTime(rs.getString("arriveTime"));
				order.setStatus(rs.getInt("status"));
				order.setShopId(rs.getInt("shopId"));
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
		String sql = "SELECT * FROM ORDER_LIST";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int status = rs.getInt("status");
				int shopId = rs.getInt("shopId");
				String orderTime = rs.getString("orderTime");
				String confirmTime = rs.getString("confirmTime");
				String outsetTime = rs.getString("outsetTime");
				String arriveTime = rs.getString("arriveTime");

				Order order = new Order();
				order.setId(id);
				order.setUserId(userId);
				order.setStatus(status);
				order.setShopId(shopId);
				order.setOrderTime(orderTime);
				order.setConfirmTime(confirmTime);
				order.setOutsetTime(outsetTime);
				order.setArriveTime(arriveTime);
				lsOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsOrder;
	}

}
