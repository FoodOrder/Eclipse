<%@ page import="java.sql.*"%>

<%!String DRIVER = "org.gjt.mm.mysql.Driver"; //連接資料庫
String USERNAME = "root";
String PASSWORD = "iecsfcu";
String DATABASE="mydb";
String URL = "jdbc:mysql://140.134.26.62:53306/"+DATABASE+"?useUnicode=true&characterEncoding=utf-8";


Connection conn;

 Statement stmt;
%>
<%
try{

     Class.forName("org.gjt.mm.mysql.Driver").newInstance();

     conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

     stmt = conn.createStatement();
     
}catch(SQLException sqle){
 out.println("SQL Exception : " + sqle);
}

%>