<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<html>
<head>
<title>habit</title>


<style type="text/css">
.nvabar {
	position: fixed;
	z-index: 9999;
	width: 100%;
	height: 50px;
	top: 0;
	background-color: #fff;
	text-align: center;
	line-height: 50px;
	font-size: 18px;
}

.nvabar_back {
	width: 25px;
	position: absolute;
	top: 13px;
	left: 10px;
}

.nvabar_blank {
	width: 100%;
	height: 50px
}
</style>

</head>

<body style="background-color: #f8f8f8;">
	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="img/back.png" onClick="history.back();">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>

	<div style="width: 100%; height: 100%; text-align: center;">
		<img alt="" src="img/error.png" width="100px" style="top: 100px; position: relative;">
		<br> <span style="position: relative; top: 120px; color: #999; font-size: 20px;">服务器懵逼中<br>（返回上一页或者重启APP试试）
		</span>
	</div>

</body>
</html>



