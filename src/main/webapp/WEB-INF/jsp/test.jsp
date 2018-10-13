<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<html>
<head>
<title>红包活动验证</title>
<script type="text/javascript" src="../plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/echarts/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/chart_test.js"></script>
<script type="text/javascript">
	$(function() {

		var myChart;
		var option;
		//配置数据源
		var dataset = {
			source : [ [ '时间', '2015.6.23 11:23', '2015.6.23 11:25', '2015.6.23 11:39' ], [ '负重', 24, 16, 24 ], [ '次数', 12, 10, 8 ] ]
		};

		// 获取echarts绘图区
		myChart = echarts.init($("#dialog_chart").get(0));
		// 设置echarts参数
		option = dialogBaseData(dataset, 200);
		//绘制统计图
		myChart.setOption(option);
		
		$("#btn").on("click",function(){
			addData(myChart, option, '2015.9.13 20:25', 6, 6);
		});
		
	});
</script>
</head>
<body>
	<div id="dialog_chart" style="height: 220px;"></div>
	<button id="btn">click me</button>
</body>
</html>



