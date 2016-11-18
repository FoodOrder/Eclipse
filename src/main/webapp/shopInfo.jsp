<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*"%>

<%
	String email = (String) session.getAttribute("email");
	if (email == null || email.equals("")) {
		response.sendRedirect("index.jsp");
	}

	ShopService shopservice = new ShopService();

	//session.setAttribute("email", null);

	String path = "\"" + "http://140.134.26.71:58080/android-backend/UploadDownloadFileServlet?id="
			+ shopservice.getShop(email).getID() + "\"";
	log(path);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.js'></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdhLKOdsw9wjoBV4sCF3IuZGZtJuEO0HI&signed_in=true&callback=initMap"></script>

<meta http-equiv="Content-Type" content="text/html; charset=BIG5"
	name="viewport" content="initial-scale=1.0, user-scalable=no" />

<!-- <meta http-equiv="Content-Type" content="text/html; charset=BIG5"> -->

<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- 最新編譯和最佳化的 JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<!-- Bootstrap Core CSS -->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<title>SuperMenu</title>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">SuperMenu</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>Read
								All Messages</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-messages --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<li><a href="#">
							<div>
								<p>
									<strong>Task 1</strong> <span class="pull-right text-muted">40%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: 40%">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 2</strong> <span class="pull-right text-muted">20%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-info" role="progressbar"
										aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
										style="width: 20%">
										<span class="sr-only">20% Complete</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 3</strong> <span class="pull-right text-muted">60%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%">
										<span class="sr-only">60% Complete (warning)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 4</strong> <span class="pull-right text-muted">80%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="80" aria-valuemin="0"
										aria-valuemax="100" style="width: 80%">
										<span class="sr-only">80% Complete (danger)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-tasks --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-alerts --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="dashboard.jsp"><i
							class="fa fa-dashboard fa-fw"></i> 首頁</a></li>

					<li><a href="order.jsp"><i class="fa fa-table fa-fw"></i>
							訂單查詢</a></li>
					<li><a href="shopInfo.jsp"><i class="fa fa-edit fa-fw"></i>
							編輯店家資訊</a></li>
					<li><a href="menu.jsp"><i class="fa fa-edit fa-fw"></i>菜單</a></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">店家資訊</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<table class=table>
										<tbody>
											<tr>
												<td>
													<form method="post" action="editShop.jsp">
														<button type="button" class="btn btn-default"
															data-toggle="modal" data-target="#exampleModal"
															data-whatever="@mdo">編輯資料</button>
														<div class="modal fade" id="exampleModal" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalLabel"
															aria-hidden="true">
															<div class="modal-dialog">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal">
																			<span aria-hidden="true">&times;</span>
																			<span class="sr-only">Close</span>
																		</button>
																		<h4 class="modal-title" id="exampleModalLabel">編輯資料</h4>
																	</div>

																	<div class="modal-body">
																		<div class="form-group">
																			<label for="ShopName" class="control-label">店家名稱:</label>
																			<input type="text" class="form-control" name="ShopName"
																				value=<%=shopservice.getShop(email).getShopName()%>>
																		</div>
																		<div class="form-group">
																			<label for="ShopPhone" class="control-label">聯絡電話:</label>
																			<input type="text" class="form-control" name="ShopPhone"
																				value=<%=shopservice.getShop(email).getPhone()%>>
																		</div>
																		<div class="form-group">
																			<label for="ShopEmail" class="control-label">信箱:</label>
																			<input type="email" class="form-control" name="ShopEmail"
																				value=<%=shopservice.getShop(email).getEmail()%>>
																		</div>
																		<div class="form-group">
																			<label for="ShopPwd" class="control-label">密碼:</label>
																			<input type="text" class="form-control" name="ShopPwd">
																		</div>
																		<div class="form-group">
																			<label for="ShopEmail" class="control-label">經度:</label>
																			<input type="text" class="form-control" name="ShopLongitude"
																				value=<%=shopservice.getShop(email).getLongitude()%>>
																		</div>
																		<div class="form-group">
																			<label for="ShopEmail" class="control-label">緯度:</label>
																			<input type="text" class="form-control" name="ShopLatitude"
																				value=<%=shopservice.getShop(email).getLatitude()%>>
																		</div>
																		<div class="form-group">
																			<label for="message-text" class="control-label">店家簡介</label>
																			<textarea class="form-control" id="message-text" name="ShopIntro" rows="3"></textarea>
																		</div>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
																		<button type="submit" class="btn btn-primary">送出</button>
																	</div>
																</div>
															</div>
														</div>
													</form>
												</td>
											</tr>
											<tr>
												<td><h4>店家名稱：<%=shopservice.getShop(email).getShopName()%></h4></td>
											</tr>
											<tr>
												<td><h4>聯絡電話： <%=shopservice.getShop(email).getPhone()%></h4></td>
											</tr>
											<tr>
												<td><h4>信箱：<%=shopservice.getShop(email).getEmail()%></h4></td>
											</tr>
											<tr>
												<td><h4>經緯度：(<%=shopservice.getShop(email).getLongitude()%>, <%=shopservice.getShop(email).getLatitude()%>)</h4></td>
											</tr>
											<tr>
												<td><h4>店家簡介：<%=shopservice.getShop(email).getIntro()%></h4></td>
											</tr>

											<tr>
												<td>
													<form method="post" action="UploadDownloadFileServlet" enctype="multipart/form-data">
														<table border="0">
															<tr><td><h4><strong>上傳店家圖片</strong></h4></td></tr>
															<tr><td><img src=<%=path%> class="img-responsive" alt="Responsive image"></td></tr>
															<tr><td><h4>上傳檔案：</h4></td></tr>
															<tr><td><input type="file" name="fileName" size="50" /></td></tr>
															<tr><td colspan="2"><input type="submit" value="上傳"></td></tr>
														</table>
													</form>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- /.col-lg-6 (nested) -->
								<div class="row">
									<div class="col-lg-6">
										<h5><strong>店家位址：</strong></h5>
										<input type="hidden" id="a"
											value="<%=shopservice.getShop(email).getLongitude()%>">
										<input type="hidden" id="b"
											value="<%=shopservice.getShop(email).getLatitude()%>">
										<script>
											$(function() {
												var a = (document
														.getElementById("a").value) * 1;
												var b = (document
														.getElementById("b").value) * 1;
												var latlng = new google.maps.LatLng(
														a, b);
												//設定地圖參數
												var mapOptions = {
													zoom : 19, //初始放大倍數
													center : latlng, //中心點所在位置
													mapTypeId : google.maps.MapTypeId.ROADMAP
												//正常2D道路模式
												};
												//在指定DOM元素中嵌入地圖
												var map = new google.maps.Map(
														document
																.getElementById("map_canvas"),
														mapOptions);
												//加入標示點(Marker)
												var marker = new google.maps.Marker(
														{
															position : latlng, //經緯度
															map : map
														//指定要放置的地圖對象
														});
											});
										</script>
										<div id="map_canvas" style="width: 550px; height: 550px"></div>
									</div>
								</div>
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="./vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="./vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="./dist/js/sb-admin-2.js"></script>

</body>
</html>