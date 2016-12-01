<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SuperMenu</title>
</head>
<body>
	<%
	session.setAttribute("email", null);
	
	response.sendRedirect("index.jsp");
	%>

</body>
</html>