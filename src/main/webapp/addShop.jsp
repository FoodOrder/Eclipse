<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- 最新編譯和最佳化的 JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>SuperMenu</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("ShopName");
		String phone = request.getParameter("ShopPhone");
		String email = request.getParameter("ShopEmail");
		String pwd = request.getParameter("ShopPwd");
		String intro = request.getParameter("ShopIntro");
		Double longitude = Double.valueOf(request.getParameter("ShopLongitude"));
		Double latitude = Double.valueOf(request.getParameter("ShopLatitude"));

		ShopService shopservice = new ShopService();

		shopservice.register(name, pwd, email, phone, intro, longitude, latitude);
	%>
	
	<div class="container">
		<form class="form-signin" method="post" action="login.jsp">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3><strong>店家管理平台</strong></h3>
					</div>

					<div class="panel-body">
						<h4>註冊成功！</h4>
						<br>
						<h4><a href="index.jsp">回登入畫面</a></h4>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>