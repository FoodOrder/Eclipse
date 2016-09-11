package fcu.android.backend.upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

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

public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	
	private String dbURL = "jdbc:mysql://140.134.26.71:53306/mydb?relaxAutoCommit=true&useSSL=false";
	private String dbUser = "root";
	private String dbPass = "iecsfcu";

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName = request.getParameter("fileName");
		if (fileName == null || fileName.equals("")) {
			return ;
		}
		File file = new File(request.getServletContext().getAttribute("FILES_DIR") + "\\" +fileName);
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
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
		
		try {           
            String sql = "UPDATE SHOP SET photo=? WHERE email=?";                 							
			
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				out.write("FileName=" + fileItem.getName());
				out.write("<br>");
				out.write("ContentType=" + fileItem.getContentType());
				out.write("<br>");
				out.write("Size in bytes=" + fileItem.getSize());
				out.write("<br>");
				
				
				File file = new File(
						request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
				
				HttpSession session = request.getSession();
				String email = (String) session.getAttribute("email");
				
				PreparedStatement statement = null;
				statement = conn.prepareStatement(sql);
				statement.setString(1, file.getAbsolutePath());
	            statement.setString(2, email);
	            statement.executeUpdate();
	            statement.close();	              	            
	          	             	            
	            
				
				System.out.println("Absolute Path at server=" + file.getAbsolutePath());
				fileItem.write(file);
				out.write("Absolute Path at server=" + file.getAbsolutePath());
				out.write("<br>");
				out.write("File " + fileItem.getName() + " uploaded successfully");
				out.write("<br>");
				out.write("<a href=\"UploadDownloadFileServlet?fileName=" + fileItem.getName() + "\">Download "
						+ fileItem.getName() + "</a>");
			}
		} catch (FileUploadException e) {
			out.write("Exception in uploading file.");
		} catch (Exception e) {
			out.write("Exception in uploading file.");
		}
		out.write("</body></html>");
	}

}
