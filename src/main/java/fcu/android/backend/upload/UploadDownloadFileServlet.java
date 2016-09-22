package fcu.android.backend.upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	
	/*
	 * private String dbURL = "jdbc:mysql://140.134.26.71:53306/mydb?relaxAutoCommit=true&useSSL=false";
	private String dbUser = "root";
	private String dbPass = "iecsfcu";
	*/

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return ;
		}
		File parentDir = new File((String)request.getServletContext().getAttribute("FILES_DIR"));
		File[] childFiles = parentDir.listFiles();
		File targetFile = null;
		for(File file: childFiles) {
			String cFileName = file.getName();
			String fileNamePrefix = StringUtils.substringBeforeLast(cFileName, ".");
			if(fileNamePrefix.equals(id)) {
				targetFile = file;
				break;
			}
		}
		System.out.println("targetFile;"+targetFile.getAbsolutePath());
		
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
		
		/*File file = new File(request.getServletContext().getAttribute("FILES_DIR") + "\\" +fileName);
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
		System.out.println("File downloaded at client successfully");*/
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
		String query ="UPDATE SHOP SET photo=? where ID=?"; 
		PreparedStatement statement = null;
		PreparedStatement statementUpdate = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, email);
	        ResultSet rs = statement.executeQuery();
	        
	        int id = -1;
	        while(rs.next()) {
	        	id = rs.getInt("ID");
	        }
	        System.out.println("id;"+id);
	        out.write("id:" + id);
	        out.write("<br>");
	        
	        List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			
			FileItem fileItem = null;
			while (fileItemsIterator.hasNext()) {
				fileItem = fileItemsIterator.next();
			}
			
			
			String fileName = fileItem.getName();
			System.out.println("fileName;"+fileName);
	        out.write("fileName:" + fileName);
	        out.write("<br>");
			String ext = StringUtils.substringAfterLast(fileName, "."); //取檔名不包括副檔名
			String convertFileName = id + "." + ext;
			System.out.println("convertFileName;"+convertFileName);//更改成id名字
	        out.write("coverFileName:" + convertFileName);
	        out.write("<br>");
			File file = new File(
					request.getServletContext().getAttribute("FILES_DIR") + File.separator + convertFileName);
			
			statement.close();
			statementUpdate = conn.prepareStatement(query);
		    statementUpdate.setString(1, file.getAbsolutePath());
			statementUpdate.setInt(2, id);
		    statementUpdate.executeUpdate();
			
		    System.out.println("final file path;"+file.getAbsolutePath());
	        out.write("final file path:" + file.getAbsolutePath());
	        out.write("<br>");
			fileItem.write(file);
			out.write("<br>");
			/*out.write("<a href=\"UploadDownloadFileServlet?id=" + id + "\">Download "
					+ fileItem.getName() + "</a>");*/
			out.write("<br>");
			out.write("<a href=\"ShopInfo.jsp" + "\">Return " + "</a>");
			statement.close();
			statementUpdate.close();
	        
		} catch (SQLException | FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		/*
		try {           
            String sql ="UPDATE SHOP SET photo=? WHERE email=?";                 							
			
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
	            int rt = statement.executeUpdate();
	            statement.close();	              	            
	          	             	            
	            
				
				System.out.println("Absolute Path at server=" + file.getAbsolutePath() + ";return;" + rt);
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
		}*/
		out.write("</body></html>");
	}

}
