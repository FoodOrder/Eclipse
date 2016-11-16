<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*, fcu.android.backend.data.*, java.util.*"%>

<%
	String email = (String) session.getAttribute("email");
	if (email == null || email.equals("")) {
		response.sendRedirect("Home.jsp");
	}

	OrderItemService orderitemservice = new OrderItemService();

	OrderService orderservice = new OrderService();
	List<Order> lsOrder = orderservice.getOrder(email);

	UserService userservice = new UserService();
	
	MenuService menuservice = new MenuService();
	//session.setAttribute("email", null);
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
					<li><a href="index.jsp"><i
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
					<h1 class="page-header">訂單列表</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-lg-12">
								<table class="col-lg-12">
									<tr>
										<td class="col-md-2"><h4 class="text-center"><strong>編號</strong></h4></td>
										<td class="col-md-2"><h4 class="text-center"><strong>訂購人</strong></h4></td>
										<td class="col-md-2"><h4 class="text-center"><strong>訂單內容</strong></h4></td>
										<td class="col-md-2"><h4 class="text-center"><strong>訂餐時間</strong></h4></td>
										<td class="col-md-2"><h4 class="text-center"><strong>是否接受</strong></h4></td>
										<td class="col-md-2"><h4 class="text-center"><strong>目前狀態</strong></h4></td>
									</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
							  <div class="col-lg-12">
								<table class="col-lg-12 table table-striped table-bordered table-hover dataTable no-footer dtr-inline collapsed">
									<%
										int i = 1;
										for (Order order : lsOrder) {
											List<OrderItem> lsOrderItem = orderitemservice.getOrderItem(order.getId());
									%>
									<tr>
										<td class="col-md-2"><h4 class="text-center"><%=order.getId()%></h4></td>
										<td class="col-md-2"><h4 class="text-center"><%=userservice.getUser(order.getUserId()).getUserName()%></h4></td>
										<td class="col-md-2">
											<form>
												<button type="button" class="btn btn-link btn-block"
													data-toggle="modal" data-target="#showModal-<%=i - 1%>"
													data-whatever="@mdo">查看訂單內容</button>
												<div class="modal fade" id="showModal-<%=i - 1%>"
													tabindex="-1" role="dialog"
													aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" id={{id}}>
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">&times;</span> <span
																		class="sr-only">Close</span>
																</button>
																<h3 class="modal-title" id="exampleModalLabel">訂單內容</h3>
															</div>

															<div class="modal-body">
																<ul class="list-style">
																	<%
																		int price = 0;
																		for (OrderItem orderItem : lsOrderItem) {
																	%>
																	<li>
																		<ul class="list-inline">
																			<li><h4><%=menuservice.getMenu(orderItem.getFoodId()).getMenuName()%></h4></li>
																			<li><h4>x<%=orderItem.getAmount()%></h4></li>
																		</ul>
																	</li>
																	<%
																		price = price + menuservice.getMenu(orderItem.getFoodId()).getMenuPrice() * orderItem.getAmount();
																		}
																	%>
																</ul>
															</div>
															<div class="modal-footer">
																<h3><strong>總金額：<%=price %>元</strong></h3>
															</div>
														</div>
													</div>
												</div>
											</form>
										</td>
										<td class="col-md-2"><h4 class="text-center"><%=order.getOrderTime()%></h4></td>
										<%
											int check = order.getStatus();
											if(check == 1) {
										%>
											<form method="post" action="changeOrderStatus.jsp?OrderId=<%=order.getId()%>">
												<td class="col-md-2 text-center">
														<button type="submit" class="btn btn-info" name="outset" value=2>外送</button>
												</td>
										<%
											}
											else if(check == 2 || check == 3) {
										%>
												<td class="col-md-2 text-center"><button type="submit" class="btn btn-default" disabled="disabled">已外送</button></td>
										<%
												
											}
											else if(check == 4) {
										%>
												<td class="col-md-2 text-center"><button type="submit" class="btn btn-danger" disabled="disabled">已拒絕</button></td>
										<%
											}else {
										%>
												<td class="col-md-2 text-center">
														<button type="submit" class="btn btn-success" name="accept" value=1>接受</button>
														<button type="submit" class="btn btn-danger" name="reject" value=4>拒絕</button>
												</td>
											</form>
										<%
											}
										%>
										<td class="col-md-2"><h4 class="text-center" style="color:rgb(172, 115, 57)"><strong><%=order.getChineseStatus(order.getStatus())%></strong></h4></td>
										</tr>
									<%
										i++;
										}
									%>
									</table>
								</div>
								<!-- /.col-lg-12 (nested) -->
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