<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page
	import="fcu.android.backend.service.*, fcu.android.backend.data.*, java.util.*"%>

<%
	String email = (String) session.getAttribute("email");
	if (email == null || email.equals("")) {
		response.sendRedirect("Home.jsp");
	}

	MenuService menuservice = new MenuService();

	//session.setAttribute("email", null);

	//out.println(email);

	List<Menu> lsMenu = menuservice.getMenu(email);
	int i = 0;
	String path = "\"" + "http://140.134.26.71:58080/android-backend/Menu_UploadDownloadFileServlet?id="
			+ lsMenu.get(i).getId() + "\"";

	/*for (int i = 0; i < lsMenu.size(); i++) {
		out.println(lsMenu.get(i).getMenuName());
		out.println(lsMenu.get(i).getMenuPrice());
		out.println(lsMenu.get(i).getId());
		out.println(lsMenu.get(i).getShopID());
		out.println("<br>");
	}*/

	//out.println(lsMenu.size());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

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

<title>Menu</title>
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
			<a class="navbar-brand" href="Dashboard.jsp">ShopManager</a>
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
					<li><a href="Logout.jsp"><i class="fa fa-sign-out fa-fw"></i>
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
					<li><a href="Dashboard.jsp"><i
							class="fa fa-dashboard fa-fw"></i> Dashboard(首頁)</a></li>
					<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
							Charts<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="FlotCharts.jsp">Flot Charts(圖表)</a></li>
							<li><a href="morris.html">Morris.js Charts</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="Order.jsp"><i class="fa fa-table fa-fw"></i>Order(訂單查詢)</a>
					</li>
					<li><a href="ShopInfo.jsp"><i class="fa fa-edit fa-fw"></i>
							Shop Information(編輯店家資訊)</a></li>
					<li><a href="Menu.jsp"><i class="fa fa-edit fa-fw"></i>
							Menu菜單</a></li>
					<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI
							Elements<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="panels-wells.html">Panels and Wells</a></li>
							<li><a href="buttons.html">Buttons</a></li>
							<li><a href="notifications.html">Notifications</a></li>
							<li><a href="typography.html">Typography</a></li>
							<li><a href="icons.html"> Icons</a></li>
							<li><a href="grid.html">Grid</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
							Multi-Level Dropdown<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Third Level <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
								</ul> <!-- /.nav-third-level --></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-files-o fa-fw"></i> Sample
							Pages<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="blank.html">Blank Page</a></li>
							<li><a href="login.html">Login Page</a></li>
						</ul> <!-- /.nav-second-level --></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Menu</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="row">



							<%
								for (Menu menu : lsMenu) {
							%>

							<div class="col-lg-3">
								<div class="panel panel-default panel-product">
									<div class="panel-body">

										<img
											src="http://140.134.26.71:58080/android-backend/Menu_UploadDownloadFileServlet?id=<%=lsMenu.get(i).getId()%>"
											class="img-responsive" alt="Responsive image">
										<div class="col-md-9">
											<h4 style="height: 19px;"><%=lsMenu.get(i).getMenuName()%></h4>
										</div>

										<div class="col-md-3">
											<form method="post"
												action="DeleteMenu.jsp?MenuId=<%=lsMenu.get(i).getId()%>">
												<button type="button" class="btn btn-link"
													data-toggle="modal" data-target="#deleteModal-<%=i%>"
													data-whatever="@mdo">刪除</button>
												<div class="modal fade" id="deleteModal-<%=i%>"
													tabindex="-1" role="dialog"
													aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" id={{id}}>
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">&times;</span>
																	<spanclass="sr-only">Close</span>
																</button>
																<h4 class="modal-title" id="exampleModalLabel">刪除</h4>
															</div>

															<div class="modal-body">
																<h3 class="text-center">是否刪除這項餐點？</h3>
															</div>
															<div class="modal-footer">
																<button type="submit" class="btn btn-primary">確定</button>
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">取消</button>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>

									</div>
									<div class="panel-footer">
										<div class="row row-narrow">
											<table>
												<tr>
													<td class="col-md-6">NT：<span class="starting-price"><%=lsMenu.get(i).getMenuPrice()%></span>
													</td>
													<td class="col-md-3">
														<form method="post"
															action="Menu_UploadDownloadFileServlet?MenuId=<%=lsMenu.get(i).getId()%>"
															enctype="multipart/form-data">
															<button type="button" class="btn btn-default"
																data-toggle="modal" data-target="#exampleModal-<%=i%>"
																data-whatever="@mdo">圖片</button>
															<div class="modal fade" id="exampleModal-<%=i%>"
																tabindex="-1" role="dialog"
																aria-labelledby="exampleModalLabel" aria-hidden="true">
																<div class="modal-dialog" id={{id}}>
																	<div class="modal-content">
																		<div class="modal-header">
																			<button type="button" class="close"
																				data-dismiss="modal">
																				<span aria-hidden="true">&times;</span>
																				<spanclass="sr-only">Close</span>
																			</button>
																			<h4 class="modal-title" id="exampleModalLabel">上傳圖片</h4>
																		</div>

																		<table border="0">
																			<tr>
																				<td><h4>File Name:</h4></td>
																			</tr>
																			<tr>
																				<td></td>
																				<td><input type="file" name="fileName"
																					size="50" /></td>
																			</tr>
																		</table>

																		<div class="modal-footer">
																			<button type="button" class="btn btn-default"
																				data-dismiss="modal">關閉</button>
																			<button type="submit" class="btn btn-primary">送出</button>
																		</div>

																	</div>
																</div>
															</div>
														</form>
													</td>
													<td class="col-md-3">
														<form method="post"
															action="EditMenu.jsp?MenuId=<%=lsMenu.get(i).getId()%>">
															<button type="button" class="btn btn-default"
																data-toggle="modal" data-target="#Modal-<%=i%>"
																data-whatever="@mdo">編輯</button>
															<div class="modal fade" id="Modal-<%=i%>" tabindex="-1"
																role="dialog" aria-labelledby="exampleModalLabel"
																aria-hidden="true">
																<div class="modal-dialog" id={{id}}>
																	<div class="modal-content">
																		<div class="modal-header">
																			<button type="button" class="close"
																				data-dismiss="modal">
																				<span aria-hidden="true">&times;</span>
																				<spanclass="sr-only">Close</span>
																			</button>
																			<h4 class="modal-title" id="exampleModalLabel">編輯</h4>
																		</div>

																		<div class="modal-body">
																			<div class="form-group">
																				<label for="MenuName" class="control-label">餐點名稱:</label>
																				<input type="text" class="form-control"
																					name="MenuName"
																					value=<%=lsMenu.get(i).getMenuName()%>>
																			</div>
																			<div class="form-group">
																				<label for="MenuPrice" class="control-label">餐點價格:</label>
																				<input type="text" class="form-control"
																					name="MenuPrice"
																					value=<%=lsMenu.get(i).getMenuPrice()%>>
																			</div>
																		</div>

																		<div class="modal-footer">
																			<button type="submit" class="btn btn-primary">送出</button>
																		</div>
																	</div>
																</div>
															</div>
														</form>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>



							<%
								i++;
								}
							%>

							<tr>
								<td>
									<form method="post" action="AddMenu.jsp">
										<button type="button" class="btn btn-default"
											data-toggle="modal" data-target="#exampleModal"
											data-whatever="@mdo">+</button>
										<div class="modal fade" id="exampleModal" tabindex="-1"
											role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" id={{id}}>
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span>
															<spanclass="sr-only">Close</span>
														</button>
														<h4 class="modal-title" id="exampleModalLabel">新增餐點</h4>
													</div>

													<div class="modal-body">
														<div class="form-group">
															<label for="MenuName" class="control-label">餐點名稱:</label>
															<input type="text" class="form-control" name="MenuName">
														</div>
														<div class="form-group">
															<label for="MenuPrice" class="control-label">餐點價格:</label>
															<input type="text" class="form-control" name="MenuPrice">
														</div>
													</div>

													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">關閉</button>
														<button type="submit" class="btn btn-primary">送出</button>
													</div>
												</div>
											</div>
										</div>
									</form>
								</td>
							</tr>
						</div>
						<!-- /.col-lg-6 (nested) -->
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