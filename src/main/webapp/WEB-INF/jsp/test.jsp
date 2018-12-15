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
		var mySwiper = new Swiper('.swiper-container', {
			autoplay : false,
			initialSlide : 0,
			direction : "horizontal",
			autoHeight : true
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
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide" style="background-color: red;">slider1</div>
			<div class="swiper-slide" style="background-color: yellow;">
				<div style="width: 100px; height: auto; background-color: pink; font-size: 30px;">
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
					<p>aaaaa</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



