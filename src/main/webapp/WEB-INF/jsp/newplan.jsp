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

<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
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
<div class="list_panel_dialog" style="display: none;">
	<p class="panel_dialog_loding">
		<img alt="" src="/habit/img/big_loding.gif" width="25px;">
		<span>正在加载动作列表...</span>
	</p>

	<p class="panel_dialog_nodata" style="display: none;">
		<span>还没有添加过任何动作<br>试试下面的操作
		</span><br> <br>
		<button class="little_button_01" id="to_newaction">去添加动作</button>
	</p>

	<p class="panel_dialog_nodata" style="display: none;" id="action_nodata">
		<span>已经没有更多动作了<br>没有了，被选完了
		</span>
	</p>
</div>
</html>



