<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>

<div class="footer">
    &copy; 2016  
    伺服器:
    <%= 
        InetAddress.getLocalHost().getHostAddress() + 
            ":" + request.getLocalPort() 
    %>
    <a href="http://www.iecs.fcu.edu.tw/wSite/mp?mp=370201" target='_blank'>
        逢甲大學資訊工程學系
    </a> 
    <a href="">下載點餐系統APP</a> 
    <a href="">下載行動逢甲APP</a>
</div>