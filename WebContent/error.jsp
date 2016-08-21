<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<center>
<%String error_id=request.getParameter("id"); 
if(error_id.equals("1")){
	out.println("抱歉，登入錯誤!'");
%>
<form name="form1" method="post" action="index.jsp">
    <input type="submit" name="back" value="返回">
  </form>
<%}else if(error_id.equals("2")){
	out.println("帳號錯誤!可能已經存在'");
%>
<form name="form1" method="post" action="supervisor.jsp">
    <input type="submit" name="back" value="返回">
  </form>
<%}%>
  </center>
</body>
</html>