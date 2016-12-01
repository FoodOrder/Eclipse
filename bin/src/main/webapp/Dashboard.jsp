<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%	
	String email = (String)session.getAttribute("email");
	if(email == null || email.equals("")) {
		response.sendRedirect("index.jsp");
	}
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>SuperMenu</title>
	<!-- Bootstrap Core CSS -->
    <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="./vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- 選擇性佈景主題 -->
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

	<!-- 最新編譯和最佳化的 JavaScript -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', '接單數量', '拒絕數量'],
          ['8月',  18,      2],
          ['9月',  26,      10],
          ['10月',  24,       3],
          ['11月',  30,     14]
        ]);

        var options = {
        
          hAxis: {title: '月份',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Element", "金額", { role: "style" } ],
        ["8月", 9840, "#b87333"],
        ["9月", 14790, "silver"],
        ["10月", 14380, "gold"],
        ["11月", 17840, "color: #e5e4e2"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
      
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
  </script>
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
					
		
					
					
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
                    </ul>
                    <!-- /.dropdown-user -->
             
                <!-- /.dropdown -->
          
            <!-- /.navbar-top-links -->

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
		<!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">統計報表</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3 col-md-6">
                  <div class="panel panel-primary">
                      <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-child fa-5x" aria-hidden="true"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">15</div>
                                    <div></div>
                                </div>
                            </div>
                        </div> 
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">11月訂單觸及人數</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">30</div>
                                    <div></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">11月訂單數量</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <i class="fa fa-usd fa-5x" aria-hidden="true"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">17840</div>
                                    <div></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">11月訂單總金額</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-hand-paper-o fa-5x" aria-hidden="true"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">14</div>
                                    <div></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">11月拒絕訂單</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            
            
             
			<div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-line-chart" aria-hidden="true"></i> 近四月訂單接收狀態
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div id="chart_div" style="width: 100%; height: 700px;"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 近四月訂單總金額
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div id="columnchart_values" style="width: 600px; height: 400px;"></div>
                            <!-- /.row -->
                       
                         </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="material-icons">&#xe01d;</i> 銷售排行榜
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d0;</i> 慕尼黑豬腳
                                    <span class="pull-right text-muted small"><em>17人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d1;</i> 法式早餐
                                    <span class="pull-right text-muted small"><em>14人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                   	<i class="material-icons">&#xe3d2;</i> 凱薩沙拉
                                    <span class="pull-right text-muted small"><em>13人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d4;</i> 巧克力咖啡牛奶
                                    <span class="pull-right text-muted small"><em>12人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d5;</i> 黑醬雞腿排
                                    <span class="pull-right text-muted small"><em>10人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d6;</i> 考里肌三明治
                                    <span class="pull-right text-muted small"><em>8人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d7;</i> 拿鐵咖啡
                                    <span class="pull-right text-muted small"><em>5人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d8;</i> 白咖啡
                                    <span class="pull-right text-muted small"><em>3人點過</em>
                                    </span>
                                </a>
                                <a href="#" class="list-group-item">
                                    <i class="material-icons">&#xe3d9;</i> 小菜
                                    <span class="pull-right text-muted small"><em>1人點過</em>
                                    </span>
                                </a>
                            </div>
                            <!-- /.list-group -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    
                    <!-- /.panel -->
                    
                        <!-- /.panel-heading -->
                        
                        <!-- /.panel-body -->
                  
                        <!-- /.panel-footer -->
                    </div>
                    <!-- /.panel .chat-panel -->
                </div>
                <!-- /.col-lg-4 -->
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

    <!-- Morris Charts JavaScript -->
    <script src="./vendor/raphael/raphael.min.js"></script>
    <script src="./vendor/morrisjs/morris.min.js"></script>
    <script src="./data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="./dist/js/sb-admin-2.js"></script>
</body>
</html>