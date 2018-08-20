<%@page import="com.flowermake.habit.domain.BodyData"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.flowermake.habit.domain.User"%>
<%@page import="com.flowermake.habit.domain.Target"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<html>
<head>
<title>habit</title>
<link rel="icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>plugs/layer/skin/dialog_2button/style.css" type="text/css" />


<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/echarts/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/chart.js"></script>
<script src="https://s3.ssl.qhres.com/!fd546749/chimee-player.browser.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		
		/**
		var aggdPlugin = ChimeePlayer.popupFactory({
			name : 'time-ad' ,
			className : 'time-ad' ,
			title : false ,
			body : '' ,
			offset : '0px 10px auto auto' ,
			operable : false
			});
			ChimeePlayer.install(aggdPlugin);
			var player = new ChimeePlayer({
			wrapper : '.chimee-container' ,
			//src : '../video/aaaa.mp4' ,
			src:'http://test.patient.wechat.yaoshi365.com/uploads/video/video_1.mp4',
			isLive : false ,
			box : 'native' ,
			autoplay : false ,
			controls : true ,
			plugin : [aggdPlugin.name]
			});**/

		//***************************************************
		

		$("#abc").blur(function() {

			alert(0.3333333333.toString().match(/^\d+(?:\.\d{0,2})?/));
		});
		var myChart
		var option;
		$("#test_button").on("click", function() {

			var content = $("#recard_dialog");
			var newTargetDialog = layer.open({
			type : 1 ,
			skin : 'dialog_2button' ,
			closeBtn : 2 ,
			content : content ,
			area : ['90%' , '500px'] ,
			title : false ,
			btn : ['确定'] ,
			offset : '40px' ,
			yes : function() {

				//var x_values = ['01.01 22:10' , '01.01 22:20' , '01.01 22:30' , '01.01 22:40' , '2323'];
				var new_data = ['01.01 22:10',40];
				//updateData(myChart, x_values, y_values)
				addData(myChart, option, new_data);
			}
			});
			// 基于准备好的dom，初始化echarts实例
			myChart = echarts.init($("#dialog_chart").get(0));
			$("#chart").width(content.width());
			//var x_value1 = ['01.01 22:10' , '01.01 22:20' , '01.01 22:30' , '01.01 22:30' , '01.01 22:40'];
			//var y_value1 = [40 , 50 , 55 , 60 , 45];
			var data_value = [['1',40],['2',45],['3',35],['4',45],['5',50]];
			option = dialogBaseData(data_value, content.width());
			option.grid.left = "30%";
			myChart.setOption(option);
		});
	});
</script>
</head>

<body>

	<button id="test_button">click me</button>
	<input type="text" id="abc" style="background-color: blue;">

</body>

<div id="recard_dialog" style="height: 1000px; background-color: #ffffff; position: relative; display: none;">
	<div id="dialog_chart" style="height: 220px;"></div>
	<div style="height: 1px; width: 100%; background-color: #ffb5ae; position: absolute; top: 185px; z-index: 100;"></div>
</div>


<!-- 
<div class="chimee-container">
	<video tabindex="-1"></video>
</div>
 -->






</html>



