<%@page import="com.flowermake.habit.tools.Commons"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.flowermake.habit.domain.ActionType"%>
<%@page import="com.flowermake.habit.domain.User"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");
	List<ActionType> actionTypeList = (List<ActionType>) request.getAttribute("actionTypeList");
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
<!-- 引入相关JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/actiontype.js"></script>
</head>

<body style="background-color: #f8f8f8;">
<input type="hidden" id="deviceid" value="<%=user.getvDeviceId()%>">
	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="<%=basePath%>img/back.png">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>


	<!-- 目标面板 -->
	<div class="list_panel">
		<div class="list_panel_title">
			<div style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/actiontype.png" height="50px" style="margin: 10px 0 10px 20;">
			</div>
			<div style="width: 60%; line-height: 70px;">
				<span style="font-size: 26px; color: black; margin-left: 15px;">动作分类</span>
			</div>
			<div id=target_add style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/add.png" height="25px" style="margin: 23px 0 23px 0;">
			</div>
		</div>

		<%
			if (actionTypeList.size() > 0) {
		%>

		<!-- 如果目标集合中有数据 -->
		<div class="target_list_nonull" id="target_msg_list">
			<%
				for (ActionType actionType : actionTypeList) {
			%>
			<div class="target_item" id="action_type_<%=actionType.getiId()%>">
				<div class="line_space">
					<div class="line_space_line"></div>
				</div>
				<p style="position: relative;">
					<span class="hotarea" style="position: absolute; height: 100%; width: 70%; z-index: 999;" typeid=<%=actionType.getiId()%>></span> <span><%=actionType.getvName()%></span><br>
					<font><%=actionType.getvRemarks()%></font>
					<img class="actiontype_edit" alt="" src="/habit/img/edit.png" width="25px" style="position: absolute; right: 5%; bottom: 8px;" type_id="<%=actionType.getiId()%>" type_name="<%=actionType.getvName()%>" type_remarks="<%=actionType.getvRemarks()%>">
				</p>
			</div>
			<%
				}
			%>

		</div>

		<%
			} else {
		%>

		<div class="list_null" id="target_msg_list">
			<label>点击右上角+号添加一个动作分类</label>
		</div>

		<%
			}
		%>
	</div>
</body>



<!-- 添加动作分类面板 -->
<div id="actiontype_add_panel" class="information_panel" style="display: none;">

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>名称</font>
		</div>
		<div>
			<input type="text" id="type_name">
		</div>
	</div>

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>备注</font>
		</div>
		<div>
			<input type="text" id="type_remarks">
		</div>
	</div>

</div>

<!-- 修改动作分类面板 -->
<div id="actiontype_edit_panel" class="information_panel" style="display: none;">

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>名称</font>
		</div>
		<div>
			<input type="text" id="update_type_name">
			<input type="hidden" id="update_type_id">
		</div>
	</div>

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>备注</font>
		</div>
		<div>
			<input type="text" id="update_type_remarks">
		</div>
	</div>

	<p style="margin-top: 15px; text-align: center;">
		<img id="actiontype_delete" alt="" src="/habit/img/delete.png" width="30px" type_id="">
	</p>

</div>

</html>



