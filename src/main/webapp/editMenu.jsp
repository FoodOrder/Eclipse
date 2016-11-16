<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="fcu.android.backend.service.*"%>
<%@ page import="fcu.android.backend.data.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Edit</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	int id = Integer.valueOf(request.getParameter("MenuId"));
	String name = request.getParameter("MenuName");
	int price = Integer.valueOf(request.getParameter("MenuPrice"));
	
	MenuService menuservice = new MenuService();
	
	menuservice.update(id, name, price);
%>	

<%
	response.sendRedirect("Menu.jsp");
%>
</body>
</html>