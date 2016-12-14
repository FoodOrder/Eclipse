<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="fcu.android.backend.service.*, fcu.android.backend.data.*, java.util.*, java.text.*"%>

<%
	String email = (String) session.getAttribute("email");
	if (email == null || email.equals("")) {
		response.sendRedirect("index.jsp");
	}

	OrderItemService orderitemservice = new OrderItemService();
	ShopService shopservice = new ShopService();

	OrderService orderservice = new OrderService();
	List<Order> lsOrder = orderservice.getOrder(email);
	Collections.reverse(lsOrder);

	UserService userservice = new UserService();

	MenuService menuservice = new MenuService();
	//session.setAttribute("email", null);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="60; url=order.jsp; charset=utf-8">

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
			<!-- <a class="navbar-brand" href="order.jsp">SuperMenu</a> -->
			<div>
				<a href="order.jsp">
				<img src="./logo/clipboard.png" alt="Super Menu" height="4%" width="4%">
				<img src="./logo/9.png" alt="Super Menu" height="15%" width="15%">
				</a>
			</div>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">

			<!-- /.dropdown -->

			<!-- /.dropdown -->

			<!-- /.dropdown -->
			<li class="dropdown">
			<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
		</ul>
		<!-- /.dropdown-user --> <!-- /.dropdown --> <!-- /.navbar-top-links -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="order.jsp"><i class="fa fa-table fa-fw"></i>
							訂單查詢</a></li>
					<li><a href="shopInfo.jsp"><i class="fa fa-edit fa-fw"></i>
							編輯店家資訊</a></li>
					<li><a href="menu.jsp"><i class="fa fa-edit fa-fw"></i>菜單</a></li>
					<li><a href="dashboard.jsp"><i
							class="fa fa-dashboard fa-fw"></i> 統計報表</a></li>
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
											<td class="col-md-1"><h4 class="text-center">
													<strong>編號</strong>
												</h4></td>
											<td class="col-md-1"><h4 class="text-center">
													<strong>訂購人</strong>
												</h4></td>
											<td class="col-md-2"><h4 class="text-center">
													<strong>電話</strong>
												</h4></td>
											<td class="col-md-2"><h4 class="text-center">
													<strong>距離及位置</strong>
												</h4></td>
											<td class="col-md-2"><h4 class="text-center">
													<strong>訂單內容</strong>
												</h4></td>
											<td class="col-md-2"><h4 class="text-center">
													<strong>訂餐時間</strong>
												</h4></td>
											<td class="col-md-1"><h4 class="text-center">
													<strong>是否接受</strong>
												</h4></td>
											<td class="col-md-1"><h4 class="text-center">
													<strong>目前狀態</strong>
												</h4></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<table
										class="col-lg-12 table table-striped table-bordered table-hover dataTable no-footer dtr-inline collapsed">
										<%
											int i = 1;
											for (Order order : lsOrder) {
												List<OrderItem> lsOrderItem = orderitemservice.getOrderItem(order.getId());
										%>
										<tr>
											<td class="col-md-1"><h4 class="text-center"><%=i%></h4></td>
											<td class="col-md-1"><h4 class="text-center"><%=userservice.getUser(order.getUserId()).getUserName()%></h4></td>
											<td class="col-md-2"><h4 class="text-center"><%=userservice.getUser(order.getUserId()).getPhone()%></h4></td>
											<td class="col-md-2">
												<%
													Double shopLa = shopservice.getShop(email).getLatitude();
														Double shopLng = shopservice.getShop(email).getLongitude();

														Double userLa = order.getLatitude();
														Double userLng = order.getLongitude();

														Double distance = 0.0;
														distance = order.GetDistance(shopLa, shopLng, userLa, userLng);
												%>
												<h4 class="text-center"><%=distance%>公尺</h4>
												<form>
													<button type="button" class="btn btn-link btn-block"
														data-toggle="modal"
														data-target="#showAddressModal-<%=i - 1%>"
														data-whatever="@mdo">查看位置</button>
													<div class="modal fade" id="showAddressModal-<%=i - 1%>"
														tabindex="-1" role="dialog"
														aria-labelledby="exampleModalLabel" aria-hidden="true">
														<div class="modal-dialog" id={{id}}>
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal">
																		<span aria-hidden="true">&times;</span> <span
																			class="sr-only">Close</span>
																	</button>
																	<h3 class="modal-title" id="exampleModalLabel">訂購人位置及備註</h3>
																</div>

																<div class="modal-body">
																	<h4>
																		位置：<%=order.getAddress()%></h4>
																	<h4>
																		備註：<%=order.getRemark()%></h4>
																</div>
															</div>
														</div>
													</div>
												</form>
											</td>
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
																	<button type="button" class="close"
																		data-dismiss="modal">
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
																				<li><h4>
																						x<%=orderItem.getAmount()%></h4></li>
																			</ul>
																		</li>
																		<%
																			price = price + menuservice.getMenu(orderItem.getFoodId()).getMenuPrice() * orderItem.getAmount();
																				}
																		%>
																	</ul>
																</div>
																<div class="modal-footer">
																	<h3>
																		<strong>總金額：<%=price%>元
																		</strong>
																	</h3>
																</div>
															</div>
														</div>
													</div>
												</form>
											</td>
											<%
												String date = order.getOrderTime();
													SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

													Date orderTime = sdFormat.parse(date);

													DateFormat dateformat;
													dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
											%>
											<td class="col-md-2"><h4 class="text-center"><%=dateformat.format(orderTime)%></h4></td>
											<%
												int check = order.getStatus();
													if (check == 1) {
											%>
											<td class="col-md-1 text-center">
												<form method="post"
													action="changeStatusToOutset.jsp?OrderId=<%=order.getId()%>">
													<button type="submit" class="btn btn-info" name="outset"
														value=2>外送</button>
												</form>
											</td>
											<%
												} else if (check == 2 || check == 3) {
											%>
											<td class="col-md-1 text-center"><button type="submit"
													class="btn btn-default" disabled="disabled">已外送</button></td>
											<%
												} else if (check == 4) {
											%>
											<td class="col-md-1 text-center"><button type="submit"
													class="btn btn-danger" disabled="disabled">已拒絕</button></td>
											<%
												} else {
											%>
											<td class="col-md-1 text-center">
												<form method="post"
													action="changeStatusToAccept.jsp?OrderId=<%=order.getId()%>">
													<button type="submit" class="btn btn-success" name="accept"
														value=1>接受</button>
												</form>
												<form method="post"
													action="changeStatusToReject.jsp?OrderId=<%=order.getId()%>">
													<button type="submit" class="btn btn-danger" name="reject"
														value=4>拒絕</button>
												</form>
											</td>

											<%
												}
											%>
											<td class="col-md-2"><h4 class="text-center"
													style="color: rgb(172, 115, 57)">
													<strong><%=order.getChineseStatus(order.getStatus())%></strong>
												</h4></td>
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