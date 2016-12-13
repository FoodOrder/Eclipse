package fcu.android.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fcu.android.backend.data.Comment;

public class CommentDBManager {
	
	private static CommentDBManager DB_MANAGER = new CommentDBManager();
	
	public static CommentDBManager getInstance() {
		return DB_MANAGER;
	}

	private IDatabase database = DatabaseFactory.getDatabase(DatabaseFactory.DatabaseType.MySql);

	private CommentDBManager() {

	}

	public boolean addComment(Comment comment) {
		Connection conn = database.getConnection();
		PreparedStatement preStmt = null;
		Statement stmt = null;
		String sql = "INSERT INTO COMMENT(userId, type, content)  VALUES(?, ?, ?)";
		String query = "SELECT * FROM COMMENT";
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, comment.getUserId());
			preStmt.setString(2, comment.getType());
			preStmt.setString(3, comment.getContent());
			preStmt.executeUpdate();
			preStmt.close();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("List All Comments");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt("ID") + ", userId: " + rs.getInt("userId") + ", Type: " + rs.getString("type") + ", Content: " + rs.getString("content"));
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
	
	public Comment getComment(int userId) {
		Connection conn = database.getConnection();
		PreparedStatement stmt = null;
		String query = "select * from COMMENT where userId = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			Comment comment = new Comment();
			if (rs.next()) {
				comment.setID(rs.getInt("ID"));
				comment.setType(rs.getString("type"));
				comment.setContent(rs.getString("content"));
			}
			stmt.close();
			conn.commit();
			return comment;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Comment();
	}

	public List<Comment> listAllComments() {
		List<Comment> lsComments = new ArrayList<Comment>();

		Connection conn = database.getConnection();
		String sql = "SELECT * FROM COMMENT";
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				int userId = rs.getInt("userId");
				String type = rs.getString("type");
				String content = rs.getString("content");

				Comment comment = new Comment();
				comment.setID(id);
				comment.setUserId(userId);
				comment.setType(type);
				comment.setContent(content);
				lsComments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsComments;
	}

}
