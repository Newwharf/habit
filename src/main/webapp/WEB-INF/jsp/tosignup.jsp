<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
<!-- 引入输入框样式 -->
<link rel="stylesheet" href="<%=basePath%>css/input_skin/component.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/input_skin/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/input_skin/normalize.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />

<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/tosignup.js"></script>

</head>

<body class="signup" style="background-color: #f8f8f8">

	<form action="/habit/user/signup" method="get">
		<div class="title2 signup_title">
			欢迎您，XXX<br> 请告诉我您的基本信息
		</div>

		<div class="signup_text">您的性别是</div>

		<div>
			<img id="male-h" alt="" src="<%=basePath%>img/male-h.png" width="30%" style="padding-right: 10px;">
			<img id="male" alt="" src="<%=basePath%>img/male.png" width="30%" style="padding-right: 10px; display: none;">
			<img id="famale" alt="" src="<%=basePath%>img/famale.png" width="30%" style="padding-left: 10px;">
			<img id="famale-h" alt="" src="<%=basePath%>img/famale-h.png" width="30%" style="padding-left: 10px; display: none;">
		</div>

		<div class="signup_text">您的年龄是</div>

		<div style="margin-top: 10px">
			<input value="0" name="age" class="input__field input__field--isao" type="number" id="input-39" value="0" style="font-size: 200%; text-align: center; color: #212122;" />
			<label class="input__label input__label--isao" for="input-38" data-content="单位：岁" id="temp"> <span class="input__label-content input__label-content--isao">&nbsp;</span>
			</label>
		</div>
		<input type="hidden" name="sex" id="sex" value="0" />
		<button class="black_button_03" style="display: block; outline: none; border-color: transparent; box-shadow: none;" type="submit" id="id_button_ok">确 定</button>
	</form>
</body>
</html>



