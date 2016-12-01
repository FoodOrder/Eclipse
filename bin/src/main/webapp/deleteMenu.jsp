<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="fcu.android.backend.service.*"%>
<%@ page import="fcu.android.backend.data.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Delete</title>
</head>
<body>
<%
	int id = Integer.valueOf(request.getParameter("MenuId"));
	
	MenuService menuservice = new MenuService();
	
	Menu Menu = new Menu();
	menuservice.delete(id);
%>	

<%
	response.sendRedirect("menu.jsp");
%>
</body>
</html>