<%@page import="com.flowermake.habit.domain.Action"%>
<%@page import="com.flowermake.habit.tools.Commons"%>
<%@page import="com.flowermake.habit.domain.BodyData"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.flowermake.habit.domain.User"%>
<%@page import="com.flowermake.habit.domain.Target"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");

	List<Action> actionList = (List<Action>) request.getAttribute("actionList");
	long actiontypeid = (Long) request.getAttribute("actiontypeid");
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
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/actionlist.js"></script>


</head>

<body style="background-color: #f8f8f8;">

	<input type="hidden" id="deviceid" value="<%=user.getvDeviceId()%>">

	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="<%=basePath%>img/back.png">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>


	<div class="add_button" actiontypeid="<%=actiontypeid%>" style=""></div>
	<div id="action_list" style="width: 90%; margin: auto;">

		<%
			if (actionList.size() > 0) {
		%>
		<%
			for (Action action : actionList) {
		%>
		<div class="target_in" id="action_<%=action.getiId()%>">
			<%
				if (action.getTiType() == 0) {
			%>
			<div class="target_in_dec action_rm"></div>
			<div>
				<span id="action_name_<%=action.getiId()%>"><%=action.getvName()%></span><br>
				<font id="action_type_<%=action.getiId()%>">计次动作</font>
				<img class="action_edit" alt="" src="/habit/img/edit.png" width="25px" actionid='<%=action.getiId()%>' actionname='<%=action.getvName()%>' actiontype='<%=action.getTiType()%>' actionunit='<%=action.getvUnit()%>' actiontypeid='<%=action.getiActionTypeid()%>'>
			</div>
			<%
				} else {
			%>
			<div class="target_in_dec action_time"></div>
			<div>
				<span id="action_name_<%=action.getiId()%>"><%=action.getvName()%></span><br>
				<font id="action_type_<%=action.getiId()%>">计时动作</font>
				<img class="action_edit" alt="" src="/habit/img/edit.png" width="25px" actionid='<%=action.getiId()%>' actionname='<%=action.getvName()%>' actiontype='<%=action.getTiType()%>' actionunit='<%=action.getvUnit()%>' actiontypeid='<%=action.getiActionTypeid()%>'>
			</div>
			<%
				}
			%>

		</div>

		<%
			}
		%>
		<%
			} else {
		%>

		<div class="nulltip" style="width: 100%; text-align: center; font-size: 20px; margin-top: 70%;">点击右下角按钮来添加动作</div>

		<%
			}
		%>
	</div>

	<!-- 新增动作面板 -->
	<div id="action_add_panel" class="information_panel" style="display: none;">

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>名称</font>
			</div>
			<div style="top: 17px;">
				<input type="text" id="action_name">
			</div>
		</div>

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>类型</font>
			</div>
			<div>
				<select id="action_type">
					<option selected="selected" value="0">计次动作</option>
					<option value="1">计时动作</option>
				</select>
			</div>
		</div>

		<div class="information_panel_input" id="action_unit">
			<div class="information_panel_label">
				<font>单位</font>
			</div>
			<div style="top: 17px;">
				<input type="text" id="action_unit_value">
			</div>
		</div>

	</div>

	<!-- 修改动作面板 -->
	<div id="action_edit_panel" class="information_panel" style="display: none;">

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>名称</font>
			</div>
			<div style="top: 17px;">
				<input type="text" id="action_name_edit">
			</div>
		</div>

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>类型</font>
			</div>
			<div>
				<select id="action_type_edit" disabled="ture">
					<option value="0">计次动作</option>
					<option value="1">计时动作</option>
				</select>
			</div>
		</div>

		<div class="information_panel_input" id="action_unit_edit">
			<div class="information_panel_label">
				<font>单位</font>
			</div>
			<div style="top: 17px;">
				<input type="text" id="action_unit_value_edit" value="">
			</div>
		</div>

		<p style="margin-top: 15px; text-align: center;">
			<img id="action_delete" alt="" src="/habit/img/delete.png" width="30px">
		</p>

	</div>

</body>
</html>



