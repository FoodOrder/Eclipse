package fcu.android.backend.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
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

public class Menu_UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	private String foodImgPath = null;

	/*
	 * private String dbURL =
	 * "jdbc:mysql://140.134.26.71:53306/mydb?relaxAutoCommit=true&useSSL=false";
	 * private String dbUser = "root"; private String dbPass = "iecsfcu";
	 */

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE_MENU");
		fileFactory.setRepository(filesDir);
		System.out.println("***" + filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
		// foodImgPath = this.getServletContext().getRealPath("food-img") +
		// File.separator;
		foodImgPath = this.getServletContext().getAttribute("FILES_DIR_MENU") + File.separator;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return;
		}

		File parentDir = new File((String) request.getServletContext().getAttribute("FILES_DIR_MENU"));
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

		PreparedStatement statementUpdate = null;
		PreparedStatement statementFindId = null;
		String query_update = "UPDATE MENU SET photo=?, MenuName=?, MenuPrice=? where id=?";

		String findShopId = "select * from SHOP where email=?";
		String query_insert = "INSERT INTO MENU(MenuName, MenuPrice, ShopID)  VALUES(?, ?, ?)";

		PreparedStatement statementInsert = null;

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		Connection conn = new fcu.android.backend.db.MySqlDatabase().getConnection();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		try {
			List<String> menu = new ArrayList<String>();

			int id = -1;
			id = Integer.valueOf(request.getParameter("MenuId"));
			List<FileItem> items = upload.parseRequest(request);

			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					menu.add(value);
					System.out.println(name + ";" + value);
				} else {
					if (id != 0) {// update
						InputStream uploadedStream = item.getInputStream();

						String fileName = item.getName();
						System.out.println("fileName;" + fileName);
						out.write("fileName:" + fileName);
						out.write("<br>");
//						String ext = StringUtils.substringAfterLast(fileName, "."); // 取副檔名不包括檔名
//						String convertFileName = id + "." + ext;
						String convertFileName = id + ".jpg";
						System.out.println("convertFileName;" + convertFileName);// 更改成id名字
						out.write("coverFileName:" + convertFileName);
						out.write("<br>");
						// File file = new
						// File(request.getServletContext().getAttribute("FILES_DIR_MENU")
						// + File.separator
						// + convertFileName);
						FileOutputStream outFile = new FileOutputStream(foodImgPath + convertFileName);
						statementUpdate = conn.prepareStatement(query_update);
						statementUpdate.setString(1, foodImgPath + convertFileName);
						statementUpdate.setString(2, menu.get(0));
						statementUpdate.setInt(3, Integer.parseInt(menu.get(1)));
						statementUpdate.setInt(4, id);
						statementUpdate.executeUpdate();
						
						System.out.println("final file path;" + foodImgPath + convertFileName);
						out.write("final file path:" + foodImgPath + convertFileName);
						out.write("<br>");
						// item.write(file);
						out.write("<br>");

						IOUtils.copy(uploadedStream, outFile);
						/*
						 * out.write("<a href=\"UploadDownloadFileServlet?id=" +
						 * id + "\">Download " + fileItem.getName() + "</a>");
						 */
						out.write("<br>");
						out.write("<a href=\"menu.jsp" + "\">Return " + "</a>");
						// statement.close();
						statementUpdate.close();
					} 
					
					else { // insert
						InputStream uploadedStream = item.getInputStream();					

						statementFindId = conn.prepareStatement(findShopId);
						statementFindId.setString(1, email);
						ResultSet rs_id = statementFindId.executeQuery();

						int sid = -1;
						while (rs_id.next()) {
							sid = rs_id.getInt("ID");
						}

						statementInsert = conn.prepareStatement(query_insert, Statement.RETURN_GENERATED_KEYS);
						statementInsert.setString(1, menu.get(0));
						statementInsert.setInt(2, Integer.parseInt(menu.get(1)));
						statementInsert.setInt(3, sid);
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
						String ext = StringUtils.substringAfterLast(fileName, "."); // 取副檔名不包括檔名
//						String convertFileName = id + "." + ext;
						String convertFileName = id + ".jpg";
						System.out.println("convertFileName;" + convertFileName);// 更改成id名字
						out.write("coverFileName:" + convertFileName);
						out.write("<br>");
						
						FileOutputStream outFile = new FileOutputStream(foodImgPath + convertFileName);
						
						statementUpdate = conn.prepareStatement(query_update);
						statementUpdate.setString(1, foodImgPath + convertFileName);
						statementUpdate.setString(2, menu.get(0));
						statementUpdate.setInt(3, Integer.parseInt(menu.get(1)));
						statementUpdate.setInt(4, id);
						statementUpdate.executeUpdate();

						System.out.println("final file path;" + foodImgPath + convertFileName);
						out.write("final file path:" + foodImgPath + convertFileName);
//						out.write("<br>");
						// item.write(file);
//						out.write("<br>");
						
						InputStream input = getClass().getResourceAsStream("menuDefault.jpg");
						System.out.println(input.getClass().getClassLoader().getResource("").getPath());
						System.out.println(input.getClass().getName());
						
						if (ext.equals(null)) IOUtils.copy(input, outFile);
						else IOUtils.copy(uploadedStream, outFile);
						/*
						 * out.write("<a href=\"UploadDownloadFileServlet?id=" +
						 * id + "\">Download " + fileItem.getName() + "</a>");
						 */
						out.write("<br>");
						out.write("<a href=\"menu.jsp" + "\">Return " + "</a>");
						// statement.close();

						statementInsert.close();
						statementUpdate.close();
					}

				}
			}

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
