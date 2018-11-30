<%@page import="com.flowermake.habit.domain.BodyData"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.flowermake.habit.domain.User"%>
<%@page import="com.flowermake.habit.domain.Target"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<html>
<head>
<title>habit</title>
<link rel="icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />

<!-- 引入主样式 -->
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>plugs/layer/skin/dialog_2button/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>plugs/layui/css/layui.css" type="text/css" />

<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/newplan.js"></script>


</head>

<body style="background-color: #f8f8f8;">

	<div class="newplan_main_div">

		<div class="newplan_main_div_div1">
			<div class="newplan_main_div_div1_div1">训练名称</div>
			<div class="newplan_main_div_div1_div2">
				<input id="action_name_input" type="text">
			</div>
		</div>

		<div id="selected_action_list"></div>

		<div class="new_button">
			<div class="new_button_div1"></div>
			<div class="new_button_div2">
				<span style="">添加动作</span>
				<img src="../img/new.png" width="25px;">
			</div>
		</div>

	</div>


	<div class="bottom_black_button">
		<button class="black_button_01" style="width: 100%;" type="button" id="id_button_ok">确 定</button>
	</div>
</body>

<!-- 添加动作面板 -->
<div class="list_panel_dialog" style="display: none; background-color: #f8f8f8">

	<div class="panel_dialog_loding" style="display: none;">
		<img alt="" src="/habit/img/big_loding.gif" width="40px;">
	</div>
	<div class="panel_dialog_nulltip" style="display: none;">
		<img alt="" src="/habit/img/nulltip.png" width="60px;">
		<p style="margin-top: 10px;">
			动作列表空了<br>请先去动作管理中添加
		</p>
		<p style="margin-left: auto; margin-right: auto; margin-top: 20px; width: 70%; font-size: 13px; color: #999">我知道这里不能直接点到动作管理里面是一件体验很不好的事，但主要是写起来有点麻烦，所以求求你们理解理解我这个头发快掉光的年轻人吧。你可以返回主页，然后点击左上角头像，就能管理动作了，快快去吧。多一点理解，少一点脱发，愿人间充满温暖与爱。</p>
	</div>

	<ul loaded="0" id="actionlistul" class="layui-nav layui-nav-tree" style="width: 100%; display: none;"></ul>
</div>
</html>





