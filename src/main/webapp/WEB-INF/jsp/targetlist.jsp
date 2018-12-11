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

	//得到体重以及体脂数据
	BodyData bodyData = (BodyData) session.getAttribute("bodyData");
	String weightMsg = "";
	if (bodyData.getfLastweight() != null) {
		weightMsg = bodyData.getfLastweight() + "kg";
		if (bodyData.getfLastbodyfat() != null) {
			weightMsg = weightMsg + "（" + bodyData.getfLastbodyfat() * 100 + "%）";
		}
	} else {
		weightMsg = "暂无体重数据";
	}

	//得到目标数据
	int targetListSize = 0;
	List<Target> targetList = (List<Target>) request.getAttribute("targetList");
	if (targetList != null) {
		targetListSize = targetList.size();
	} else {
		targetListSize = 0;
	}
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
<script type="text/javascript" src="<%=basePath%>js/targetlist.js"></script>

<script type="text/javascript">
	
</script>
</head>

<body style="background-color: #f8f8f8;">
<input type="hidden" id="deviceid" value="<%=user.getvDeviceId()%>">
	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="<%=basePath%>img/back.png">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>
	<div class="add_button">+</div>
	<div id="target_list" style="width: 90%; margin: auto;">
		<%
			if (targetListSize > 0) {
		%>
		<%
			for (Target target : targetList) {
		%>
		<%
			if (target.getTiState() == 0) {
		%>
		<div id="<%="target_" + target.getiId()%>" class="target_in">
			<div class="target_in_dec"></div>
			<div>
				<span><%=target.getvMsg()%></span><br>
				<font><%=target.getTargetScore(bodyData)%></font>
			</div>
			<img class="delete_button" src="../img/delete.png" width="25px;" t_id="<%=target.getiId()%>">
		</div>
		<%
			} else {
		%>
		<div id="<%="target_" + target.getiId()%>" class="target_complate">
			<div class="target_complate_dec"></div>
			<div>
				<span><%=target.getvMsg()%></span><br>
				<font>目标已完成</font>
			</div>
			<img src="../img/complate.png" width="25px;">
		</div>
		<%
			}
		%>
		<%
			}
		%>
		<%
			}
		%>
	</div>

</body>

<!-- 新增目标弹窗 -->
<div class="information_panel" style="display: none;">
	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>项目</font>
		</div>
		<div>
			<select id="ti_index">
				<option selected="selected" value="sbxw">请选择</option>
				<option <%=bodyData.getfLastheight() == null ? "disabled='disabled'" : ""%> value="0">身高(<%=bodyData.getfLastheight() == null ? "未设置" : bodyData.getfLastheight() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastweight() == null ? "disabled='disabled'" : ""%> value="1">体重(<%=bodyData.getfLastweight() == null ? "未设置" : bodyData.getfLastweight() + "kg"%>)
				</option>
				<option <%=bodyData.getfLastbodyfat() == null ? "disabled='disabled'" : ""%> value="2">体脂(<%=bodyData.getfLastbodyfat() == null ? "未设置" : bodyData.getfLastbodyfat() + "%"%>)
				</option>
				<option <%=bodyData.getfLastshouldersize() == null ? "disabled='disabled'" : ""%> value="3">肩围(<%=bodyData.getfLastshouldersize() == null ? "未设置" : bodyData.getfLastshouldersize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastbust() == null ? "disabled='disabled'" : ""%> value="4">胸围(<%=bodyData.getfLastbust() == null ? "未设置" : bodyData.getfLastbust() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastwaistline() == null ? "disabled='disabled'" : ""%> value="5">腰围(<%=bodyData.getfLastwaistline() == null ? "未设置" : bodyData.getfLastwaistline() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastabdominalsize() == null ? "disabled='disabled'" : ""%> value="6">腹围(<%=bodyData.getfLastabdominalsize() == null ? "未设置" : bodyData.getfLastabdominalsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLasthipline() == null ? "disabled='disabled'" : ""%> value="7">臀围(<%=bodyData.getfLasthipline() == null ? "未设置" : bodyData.getfLasthipline() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastlarmsize() == null ? "disabled='disabled'" : ""%> value="8">左大臂围(<%=bodyData.getfLastlarmsize() == null ? "未设置" : bodyData.getfLastlarmsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastlforearmsize() == null ? "disabled='disabled'" : ""%> value="10">左小臂围(<%=bodyData.getfLastlforearmsize() == null ? "未设置" : bodyData.getfLastlforearmsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastrarmsize() == null ? "disabled='disabled'" : ""%> value="9">右大臂围(<%=bodyData.getfLastrarmsize() == null ? "未设置" : bodyData.getfLastrarmsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastrforearmsize() == null ? "disabled='disabled'" : ""%> value="11">右小臂围(<%=bodyData.getfLastrforearmsize() == null ? "未设置" : bodyData.getfLastrforearmsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastlthighsize() == null ? "disabled='disabled'" : ""%> value="12">左大腿围(<%=bodyData.getfLastlthighsize() == null ? "未设置" : bodyData.getfLastlthighsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastlcrussize() == null ? "disabled='disabled'" : ""%> value="14">左小腿围(<%=bodyData.getfLastlcrussize() == null ? "未设置" : bodyData.getfLastlcrussize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastrthighsize() == null ? "disabled='disabled'" : ""%> value="13">右大腿围(<%=bodyData.getfLastrthighsize() == null ? "未设置" : bodyData.getfLastrthighsize() + "cm"%>)
				</option>
				<option <%=bodyData.getfLastrcrussize() == null ? "disabled='disabled'" : ""%> value="15">右小腿围(<%=bodyData.getfLastrcrussize() == null ? "未设置" : bodyData.getfLastrcrussize() + "cm"%>)
				</option>
			</select>
		</div>
	</div>

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>关系</font>
		</div>
		<div>
			<select id="ti_nexus">
				<option selected="selected" value="0">请选择</option>
				<option value="0">大于等于</option>
				<option value="1">小于等于</option>
			</select>
		</div>
	</div>

	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>目标</font>
		</div>
		<div>
			<input step="0.01" type="number" id="f_value">
		</div>
		<font id="target_unit"></font>
	</div>

</div>


</html>



