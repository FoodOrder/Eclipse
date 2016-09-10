<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
	<%!
		String Email, Password, StringTrue;
		boolean valid;
	%>
	
	<%
		ShopService shopservice = new ShopService();
		StringTrue = "true";
	
		Email = request.getParameter("InputEmail");
		Password = request.getParameter("InputPassword");
		
		
		valid = shopservice.isValidShop(Email, Password);	
		
		if(valid){
			session.setAttribute("email", Email);
			response.sendRedirect("Dashboard.jsp");
		}
		else{%>
			<h3>帳號或密碼錯誤!</h3>
			<br>
			<a href=Home.jsp>重新登入</a>
		<%}
		
	%>

</body>
</html>