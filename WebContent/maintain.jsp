<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="databasefactory.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%!
ResultSet rs;
String sql;
int countpage=2;
%>
<%
sql="SELECT * FROM users WHERE LOGINNAME='"+request.getParameter("user_id")+"'";
rs=stmt.executeQuery(sql);
rs.next();
%>
<form name="form2" method="post" action="updateprofile.jsp">
<TABLE  border=1 width=80%>
 <TR><TD width=30%><B>帳號:</B><TD><input type="text" name="id" value="<%=rs.getString("LOGINNAME")%>">
 <TR><TD width=30%><B>密碼:</B><TD><input type="text" name="pw" value="<%=rs.getString("PASSWORD")%>">
 <TR><TD width=30%><B>真實姓名:</B><TD><input type="text" name="rname" value="<%=rs.getString("REALNAME")%>"> 
 <TR><TD width=30%><B>E-MAIL:</B><TD><input type="text" name="email" value="<%=rs.getString("EMAIL")%>">  
 <TR><TD width=30%><B>部門:</B><TD><input type="text" name="dep" value="<%=rs.getString("DEPARTMENT")%>"> 
 <TR><TD width=30%><B>職位:</B><TD><input type="text" name="lvl" value="<%=rs.getString("LEVEL")%>"> 
 </table>     
     <input type="submit" name="udate" value="修改">  
    </form>
    <form name="form3" method="post" action=cancelprofile.jsp?id=<%=request.getParameter("user_id")%>>
 <input type="submit" name="delete" value="刪除">
  </form>
  <form name="form5" method="post" action="index.jsp">
    <input type="submit" name="logout" value="登出"> 
 </form>
<hr>

<% 
rs.close();
stmt.close();
conn.close();
%>
</body>
</html>