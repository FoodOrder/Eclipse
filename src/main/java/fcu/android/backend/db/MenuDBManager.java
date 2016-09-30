package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Menu;

public class MenuDBManager {

	private static MenuDBManager DB_MANAGER = new MenuDBManager();

	public static MenuDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private MenuDBManager() {

	}

	public boolean addMenu(Menu menu) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		PreparedStatement statement = null;
		Statement stmt = null;
		String findShopId = "select * from SHOP where email=?";
		String sql = "INSERT INTO MENU(MenuName, MenuPrice, ShopID)  VALUES(?, ?, ?)";
		String query = "SELECT * FROM MENU";
		try {
			statement = conn.prepareStatement(findShopId);
			statement.setString(1, menu.getShopEmail());
			ResultSet rs_id = statement.executeQuery();

			int sid = -1;
			while (rs_id.next()) {
				sid = rs_id.getInt("ID");
			}

			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, menu.getMenuName());
			preStmt.setInt(2, menu.getMenuPrice());
			preStmt.setInt(3, sid);
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Menus");
			while (rs.next()) {
				System.out.println("Menu Name: " + rs.getString("MenuName") + ", Price: " + rs.getInt("MenuPrice"));
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

	public boolean updateMenu(Menu menu) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		PreparedStatement statement = null;
		Statement stmt = null;
		String findShopId = "select * from SHOP where email=?";
		String sql = "UPDATE MENU SET MenuName=?, MenuPrice=?, WHERE ShopID=?";
		String query = "SELECT * FROM MENU";
		try {
			statement = conn.prepareStatement(findShopId);
			statement.setString(1, menu.getShopEmail());
			ResultSet rs_id = statement.executeQuery();

			int sid = -1;
			while (rs_id.next()) {
				sid = rs_id.getInt("ID");
			}
			
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, menu.getMenuName());
			preStmt.setInt(2, menu.getMenuPrice());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Shops");
			while (rs.next()) {
				System.out.println("Shop Name: " + rs.getString("shopName") + ", Email: " + rs.getString("email"));
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

	public List<Menu> getMenu(String ShopEmail) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement statement = null;
		String findShopId = "select * from SHOP where email=?";
		String query = "select * from MENU where ShopID = ?";
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

			List<Menu> lsMenu = new ArrayList<Menu>();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuName(rs.getString("MenuName"));
				menu.setMenuPrice(rs.getInt("MenuPrice"));
				lsMenu.add(menu);
			}

			stmt.close();
			conn.commit();
			return lsMenu;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Menu>();
	}

	public List<Menu> listAllMenu() {
		List<Menu> lsMenu = new ArrayList<Menu>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM MENU";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("MenuName");
				int price = rs.getInt("MenuPrice");

				Menu menu = new Menu();
				menu.setId(id);
				menu.setMenuName(name);
				menu.setMenuPrice(price);
				lsMenu.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsMenu;
	}

}
