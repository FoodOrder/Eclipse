<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="fcu.android.backend.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>ChangeOrderStatus</title>
</head>
<body>

	<%
		int id = Integer.valueOf(request.getParameter("OrderId"));
		int accept = Integer.valueOf(request.getParameter("accept"));
		int reject = Integer.valueOf(request.getParameter("reject"));
		int outset = Integer.valueOf(request.getParameter("outset"));
		int status = -1;
	
		OrderService orderservice = new OrderService();
		
		out.println(accept + ", " + reject + ", " + outset);
	
	%>

</body>
</html>