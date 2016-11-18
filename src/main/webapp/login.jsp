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
	<%!String Email, Password, StringTrue;
	boolean valid;%>

	<%
		ShopService shopservice = new ShopService();
		StringTrue = "true";

		Email = request.getParameter("InputEmail");
		Password = request.getParameter("InputPassword");

		valid = shopservice.isValidShop(Email, Password);

		if (valid) {
			session.setAttribute("email", Email);
			response.sendRedirect("order.jsp");
		} else {
	%>
	<div class="container">
		<form class="form-signin" method="post" action="login.jsp">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3>
							<strong>店家管理平台</strong>
						</h3>
					</div>

					<div class="panel-body">
						<h4>帳號或密碼錯誤!</h4>
						<br>
						<a href=index.jsp>重新登入</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%
		}
	%>

</body>
</html>