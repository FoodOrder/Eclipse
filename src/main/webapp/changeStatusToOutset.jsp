<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="fcu.android.backend.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>SuperMenu</title>
</head>
<body>

	<%
		int id = Integer.valueOf(request.getParameter("OrderId"));
		int outset = Integer.valueOf(request.getParameter("outset"));
	
		OrderService orderservice = new OrderService();
		
		out.println(outset);
		
		orderservice.update(id, outset);
		orderservice.addTime(id, 2);
		
		response.sendRedirect("order.jsp");
	
	%>

</body>
</html>