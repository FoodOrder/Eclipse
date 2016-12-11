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
import fcu.android.backend.data.Order;

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
		//PreparedStatement statement_findUser = null; // find userId

		Statement stmt = null; // select order
		//String findUserId = "select * from USER where email=?";
		String insertOrder = "insert into ORDER_LIST(shopId, userId, orderTime, status, longitude, latitude, address, remark) values(?, ?, ?, ?, ?, ?, ?, ?)";
		String selectOrder = "SELECT * FROM ORDER_LIST";

		try {

			// find userId
			/*statement_findUser = conn.prepareStatement(findUserId);
			statement_findUser.setString(1, order.getUserEmail());
			ResultSet rs_uid = statement_findUser.executeQuery();
			
			int uid = -1;
			while (rs_uid.next()) {
				uid = rs_uid.getInt("ID");
			}
			System.out.println(uid);
			statement_findUser.close();*/

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date current = new Date();

			System.out.println("shopId: " + order.getShopId());
			System.out.println("uid: " + order.getUserId());
			System.out.println("OrderTime: " + sdFormat.format(current));

			// insert order
			preStmt = conn.prepareStatement(insertOrder);
			preStmt.setInt(1, order.getShopId());
			preStmt.setInt(2, order.getUserId());
			preStmt.setString(3, sdFormat.format(current));
			preStmt.setInt(4, 0);
			preStmt.setDouble(5, order.getLongitude());
			preStmt.setDouble(6, order.getLatitude());
			preStmt.setString(7, order.getAddress());
			preStmt.setString(8, order.getRemark());
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

			addOrderItem(order, order.getUserId());

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
			
			if(order.getStatus() == 3) {
				addTime(order.getId(), 3);
			}

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

	public boolean addTime(int id, int timeType) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		Statement stmt = null;
		String sql = "";
		String query = "SELECT * FROM ORDER_LIST";
		try {
			switch(timeType) {
			case 1:  //接受
				sql = "UPDATE ORDER_LIST SET confirmTime=? WHERE id=?";
				break;
			case 2:  //外送
				sql = "UPDATE ORDER_LIST SET outsetTime=? WHERE id=?";
				break;
			case 3:  //送達
				sql = "UPDATE ORDER_LIST SET arriveTime=? WHERE id=?";
				break;
			default:
				break;	
			}
			
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date current = new Date();
			
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, sdFormat.format(current));
			preStmt.setInt(2, id);
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Menus");
			while (rs.next()) {
				switch(timeType) {
				case 1:
					System.out.println("OrderId: " + rs.getInt("id") + ", userId: " + rs.getInt("userId") + ", confirmTime: " + rs.getString("confirmTime"));
					break;
				case 2:
					System.out.println("OrderId: " + rs.getInt("id") + ", userId: " + rs.getInt("userId") + ", outsetTime: " + rs.getString("outsetTime"));
					break;
				case 3:
					System.out.println("OrderId: " + rs.getInt("id") + ", userId: " + rs.getInt("userId") + ", arriveTime: " + rs.getString("arriveTime"));
					break;
				default:
					break;	
				}
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
				order.setAddress(rs.getString("address"));
				order.setLongitude(rs.getDouble("longitude"));
				order.setLatitude(rs.getDouble("latitude"));
				order.setRemark(rs.getString("remark"));
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
		String query = "select * from ORDER_LIST where userId = ?";
		try {

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
				order.setAddress(rs.getString("address"));
				order.setLongitude(rs.getDouble("longitude"));
				order.setLatitude(rs.getDouble("latitude"));
				order.setRemark(rs.getString("remark"));
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
				Double longitude = rs.getDouble("longitude");
				Double latitude = rs.getDouble("latitude");

				Order order = new Order();
				order.setId(id);
				order.setUserId(userId);
				order.setStatus(status);
				order.setShopId(shopId);
				order.setOrderTime(orderTime);
				order.setConfirmTime(confirmTime);
				order.setOutsetTime(outsetTime);
				order.setArriveTime(arriveTime);
				order.setLongitude(longitude);
				order.setLatitude(latitude);
				lsOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsOrder;
	}

}
