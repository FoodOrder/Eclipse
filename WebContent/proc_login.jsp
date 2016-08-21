<%@ page import="java.sql.*" %>
<%@ include file="databasefactory.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%!
ResultSet rs;
String enter_id;
String enter_pw;
//String id,pw;
boolean result1=false;
%>
<%
enter_id=request.getParameter("user_id");
enter_pw=request.getParameter("user_pw");

rs = stmt.executeQuery("SELECT * FROM USER WHERE userName='"+enter_id+"' AND password='"+enter_pw+"'");      
%>


<%

if(!rs.next()){
	response.sendRedirect("error.jsp?id=1");	
}else if(enter_id==null||enter_pw==null){
	response.sendRedirect("error.jsp?id=1");	
}else{%>
<jsp:forward page="shows.jsp">
<jsp:param name="user_id" value="<%=enter_id%>"/>
<jsp:param name="user_pw" value="<%=enter_pw%>"/>
</jsp:forward>
<%
/*response.sendRedirect("shows.jsp");*/	}
rs.close();
stmt.close();
conn.close();
%>

</body>
</html>