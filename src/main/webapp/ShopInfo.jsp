<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- 最新編譯和最佳化的 JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body{ padding-top: 70px; }>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<ul>
			<li><a href=ShopInfo.jsp>店家資訊</a></li>
			<li><a href=Menu.jsp>菜單</a></li>
			<li><a href=Comment.jsp>評論</a></li>
		</ul>
	</div>
	</nav>

	<div>
		<h3>店家資訊</h3>
		<ul class="list-group">
			<li class="list-group-item">店家名稱：</li>
			<li class="list-group-item">店家帳號：</li>
			<li class="list-group-item">連絡電話：</li>
		</ul>
	</div>
</body>
</html>