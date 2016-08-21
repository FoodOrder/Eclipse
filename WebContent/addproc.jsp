<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ include file="databasefactory.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%!
ResultSet rs;
String enter_id;
String enter_pw;
String enter_rname;
String enter_email;
String enter_dep;
String enter_lvl;
//String id,pw;

%>
<%
enter_id=request.getParameter("id");
enter_pw=request.getParameter("pw");
enter_rname=request.getParameter("rname");
enter_email=request.getParameter("email");
enter_dep=request.getParameter("dep");
enter_lvl=request.getParameter("lvl");
rs=stmt.executeQuery("select * from users where LOGINNAME='"+enter_id+"'");
if(!enter_id.equals("")&&!enter_pw.equals("")&&!enter_rname.equals("")&&!enter_dep.equals("")&&!enter_lvl.equals("")){
if(!rs.next()){
	stmt.executeUpdate("INSERT INTO users (LOGINNAME,PASSWORD,REALNAME,EMAIL,DEPARTMENT,LEVEL) VALUES('"+enter_id+"','"+enter_pw+"','"+enter_rname+"','"+enter_email+"','"+enter_dep+"','"+enter_lvl+"')");      
	response.sendRedirect("supervisor.jsp");
}else{
	response.sendRedirect("error.jsp?id=2");
}
}else{
	response.sendRedirect("error.jsp?id=2");
}
%>

<% 
	

stmt.close();
conn.close();
%>
</body>
</html>