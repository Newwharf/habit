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
<link rel="stylesheet" href="<%=basePath%>plugs/layer/skin/dialog_2button/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>plugs/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>plugs/swiper/css/swiper.min.css">
<script type="text/javascript" src="../plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/echarts/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/chart_test.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script src="<%=basePath%>plugs/swiper/js/swiper.min.js"></script>
<script src="<%=basePath%>plugs/layui/layui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#clickme").on("click", function() {
			$("#dialog").css({
				"top" : "100%"
			});
			$("#dialog").show();
			$("#dialog").animate({
				top : "0"
			}, 300);
		});
	});
</script>
<style type="text/css">
.swiper-slide {
	width: 100%;
	height: auto;
}
</style>
</head>
<body>
	<div id="dialog" style="width: 100%; height: 100%; overflow: hidden; background-color: white;">
		<div style="width: 100%; height: 50px; background-color: yellow;">top nav</div>
		<div style="width: 100%; height: 200%; overflow: visible;">
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			<p>aaaaaa</p>
			
		</div>
	</div>
</body>
</html>



