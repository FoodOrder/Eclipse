package fcu.android.backend.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	private String shopImgPath = null;

	/*
	 * private String dbURL =
	 * "jdbc:mysql://140.134.26.71:53306/mydb?relaxAutoCommit=true&useSSL=false";
	 * private String dbUser = "root"; private String dbPass = "iecsfcu";
	 */

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();

		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		System.out.println("***" + filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
		shopImgPath = this.getServletContext().getAttribute("FILES_DIR") + File.separator;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return;
		}
		File parentDir = new File((String) request.getServletContext().getAttribute("FILES_DIR"));
		File[] childFiles = parentDir.listFiles();
		File targetFile = null;
		for (File file : childFiles) {
			String cFileName = file.getName();
			String fileNamePrefix = StringUtils.substringBeforeLast(cFileName, ".");
			if (fileNamePrefix.equals(id)) {
				targetFile = file;
				break;
			}
		}
		System.out.println("targetFile;" + targetFile.getAbsolutePath());

		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(targetFile);
		String mimeType = ctx.getMimeType(targetFile.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) targetFile.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + targetFile + "\"");
		ServletOutputStream os = response.getOutputStream();

		IOUtils.copy(fis, os);

		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		Connection conn = new fcu.android.backend.db.MySqlDatabase().getConnection();

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		String sql = "select * from SHOP where email=?";
		String query_update = "UPDATE SHOP SET shopName=?, phone=?, email=?, password=?, longitude=?, latitude=?, intro=?, photo=? where ID=?";
		String query_insert = "INSERT INTO SHOP(shopName, email, password, phone, longitude, latitude, intro)  VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		PreparedStatement statementUpdate = null;
		PreparedStatement statementInsert = null;

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<String> shop = new ArrayList<String>();

			int id = -1;
			id = Integer.valueOf(request.getParameter("ShopID"));
			List<FileItem> items = upload.parseRequest(request);

			Iterator<FileItem> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					shop.add(value);
					System.out.println(name + ";" + value);
				} else {
					if(id != 0) { //update
					statement = conn.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("ID");
					}
					System.out.println("id;" + id);
					out.write("id:" + id);
					out.write("<br>");

					InputStream uploadedStream = item.getInputStream();

					String fileName = item.getName();
					System.out.println("fileName;" + fileName);
					out.write("fileName:" + fileName);
					out.write("<br>");
					String ext = StringUtils.substringAfterLast(fileName, "."); // 取檔名不包括副檔名
					String convertFileName = id + "." + ext;
					System.out.println("convertFileName;" + convertFileName);// 更改成id名字
					out.write("coverFileName:" + convertFileName);
					out.write("<br>");
					FileOutputStream outFile = new FileOutputStream(shopImgPath + convertFileName);

					statementUpdate = conn.prepareStatement(query_update);
					statementUpdate.setString(1, shop.get(0)); // name
					statementUpdate.setString(2, shop.get(1)); //phone
					statementUpdate.setString(3, shop.get(2)); //email
					statementUpdate.setString(4, shop.get(3)); //password
					statementUpdate.setDouble(5, Double.parseDouble(shop.get(4))); //lng
					statementUpdate.setDouble(6, Double.parseDouble(shop.get(5))); //lat
					statementUpdate.setString(7, shop.get(6)); //intro
					statementUpdate.setString(8, shopImgPath + convertFileName);
					statementUpdate.setInt(9, id);
					statementUpdate.executeUpdate();

					System.out.println("final file path;" + shopImgPath + convertFileName);
					out.write("final file path:" + shopImgPath + convertFileName);
					out.write("<br>");
//					fileItem.write(file);
					out.write("<br>");
					IOUtils.copy(uploadedStream, outFile);
					/*
					 * out.write("<a href=\"UploadDownloadFileServlet?id=" + id
					 * + "\">Download " + fileItem.getName() + "</a>");
					 */
//					out.write("<br>");
//					out.write("<a href=\"shopInfo.jsp" + "\">Return " + "</a>");
					statement.close();
					statementUpdate.close();
					}
					else { //insert
						InputStream uploadedStream = item.getInputStream();
						
						statementInsert = conn.prepareStatement(query_insert, Statement.RETURN_GENERATED_KEYS);
						statementInsert.setString(1, shop.get(0)); //name
						statementInsert.setString(2, shop.get(1));//email
						statementInsert.setString(3, shop.get(2));//password
						statementInsert.setString(4, shop.get(4));//phone
						statementInsert.setDouble(5, Double.parseDouble(shop.get(5)));//lng
						statementInsert.setDouble(6, Double.parseDouble(shop.get(6)));//lat
						statementInsert.setString(7, shop.get(7));//intro
						statementInsert.executeUpdate();
						
						try (ResultSet generatedKeys = statementInsert.getGeneratedKeys()) {
				            if (generatedKeys.next()) {
				                id = generatedKeys.getInt(1);
				            }
				            else {
				                throw new SQLException("Creating user failed, no ID obtained.");
				            }
				        }
						String fileName = item.getName();
						System.out.println("fileName;" + fileName);
						out.write("fileName:" + fileName);
						out.write("<br>");
						String ext = StringUtils.substringAfterLast(fileName, "."); // 取檔名不包括副檔名
						String convertFileName = id + "." + ext;
						System.out.println("convertFileName;" + convertFileName);// 更改成id名字
						out.write("coverFileName:" + convertFileName);
						out.write("<br>");
						FileOutputStream outFile = new FileOutputStream(shopImgPath + convertFileName);

						statementUpdate = conn.prepareStatement(query_update);
						statementUpdate.setString(1, shop.get(0)); //name
						statementUpdate.setString(2, shop.get(4));//phone
						statementUpdate.setString(3, shop.get(1));//email
						statementUpdate.setString(4, shop.get(2));//password
						statementUpdate.setDouble(5, Double.parseDouble(shop.get(5)));//lng
						statementUpdate.setDouble(6, Double.parseDouble(shop.get(6)));//lat
						statementUpdate.setString(7, shop.get(7));//intro
						statementUpdate.setString(8, shopImgPath + convertFileName);
						statementUpdate.setInt(9, id);
						statementUpdate.executeUpdate();

						System.out.println("final file path;" + shopImgPath + convertFileName);
						out.write("final file path:" + shopImgPath + convertFileName);
						out.write("<br>");
						out.write("<br>");
						IOUtils.copy(uploadedStream, outFile);
						/*
						 * out.write("<a href=\"UploadDownloadFileServlet?id=" + id
						 * + "\">Download " + fileItem.getName() + "</a>");
						 */
						
						statementUpdate.close();
						statementInsert.close();
					}
				}
			}
			out.write("<br>");
			out.write("<a href=\"shopInfo.jsp" + "\">Return " + "</a>");

		} catch (SQLException | FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write("</body></html>");
	}

}
