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
		int accept = Integer.valueOf(request.getParameter("accept"));
	
		OrderService orderservice = new OrderService();
		
		out.println(accept);
		
		orderservice.update(id, accept);
		orderservice.addTime(id, 1);
		
		response.sendRedirect("order.jsp");
	
	%>

</body>
</html>