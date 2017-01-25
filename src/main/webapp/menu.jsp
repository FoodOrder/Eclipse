<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page
	import="fcu.android.backend.service.*, fcu.android.backend.data.*, java.util.*"%>

<%
	String email = (String) session.getAttribute("email");
	if (email == null || email.equals("")) {
		response.sendRedirect("index.jsp");
	}

	MenuService menuservice = new MenuService();

	//session.setAttribute("email", null);

	//out.println(email);

	List<Menu> lsMenu = menuservice.getMenu(email);
	int i = 0;
	/*String path = "\"" + "http://140.134.26.71:58080/android-backend/Menu_UploadDownloadFileServlet?id="
			+ lsMenu.get(i).getId() + "\"";*/

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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

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
				<a href="order.jsp"> <img src="./logo/clipboard.png"
					alt="Super Menu" height="4%" width="4%"> <img
					src="./logo/9.png" alt="Super Menu" height="15%" width="15%">
				</a>
			</div>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">

			<!-- /.dropdown -->

			<!-- /.dropdown -->

			<!-- /.dropdown -->
			<li class="dropdown">
			<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i>
					Logout</a></li>
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
					<div class="page-header">
						<h1>菜單</h1>
						<form method="post"
							action="Menu_UploadDownloadFileServlet?MenuId=0"
							enctype="multipart/form-data">
							<button type="button" class="btn" data-toggle="modal"
								data-target="#exampleModal" data-whatever="@mdo">新增菜單</button>
							<div class="modal fade" id="exampleModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" id={{id}}>
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span> <span
													class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="exampleModalLabel">新增餐點</h4>
										</div>

										<div class="modal-body">
											<div class="form-group">
												<label for="MenuName" class="control-label">餐點名稱:</label> <input
													type="text" class="form-control" name="MenuName">
											</div>
											<div class="form-group">
												<label for="MenuPrice" class="control-label">餐點價格:</label> <input
													type="text" class="form-control" name="MenuPrice">
											</div>
											<div class="form-group">
												<table border="0">
													<tr>
														<td><label>餐點圖片:</label></td>
													</tr>
													<tr>
														<td></td>
														<td><input type="file" name="fileName" size="50" /></td>
													</tr>
												</table>
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
					</div>
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
											src="http://140.134.26.71:58080/android-backend/Menu_UploadDownloadFileServlet?id=<%=menu.getId()%>"
											class="img-responsive" alt="Responsive image">
										<div class="col-md-9">
											<h4 style="height: 19px;"><%=menu.getMenuName()%></h4>
										</div>

										<div class="col-md-3">
											<form method="post"
												action="deleteMenu.jsp?MenuId=<%=menu.getId()%>">
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
																	<span aria-hidden="true">&times;</span> <span
																		class="sr-only">Close</span>
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
													<td class="col-md-6">NT：<span class="starting-price"><%=menu.getMenuPrice()%></span>
													</td>
													<td class="col-md-3">
														<form method="post"
															action="Menu_UploadDownloadFileServlet?MenuId=<%=menu.getId()%>"
															enctype="multipart/form-data">
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
																				<span aria-hidden="true">&times;</span> <span
																					class="sr-only">Close</span>
																			</button>
																			<h4 class="modal-title" id="exampleModalLabel">編輯</h4>
																		</div>

																		<div class="modal-body">
																			<div class="form-group">
																				<label for="MenuName" class="control-label">餐點名稱:</label>
																				<input type="text" class="form-control"
																					name="MenuName" value=<%=menu.getMenuName()%>>
																			</div>
																			<div class="form-group">
																				<label for="MenuPrice" class="control-label">餐點價格:</label>
																				<input type="text" class="form-control"
																					name="MenuPrice" value=<%=menu.getMenuPrice()%>>
																			</div>
																			<div class="form-group">
																				<table border="0">
																					<tr>
																						<td><label>餐點圖片:</label></td>
																					</tr>
																					<tr>
																						<td></td>
																						<td><input type="file" name="fileName"
																							size="50" /></td>
																					</tr>
																				</table>
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
						</div>
						<!-- /.row (nested) -->
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