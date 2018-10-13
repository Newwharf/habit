<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.flowermake.habit.domain.*"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");

	Plan plan = (Plan) request.getAttribute("plan");
	List<Action_PlanDetailsJSPTemp> actionList = (List<Action_PlanDetailsJSPTemp>) request
			.getAttribute("tempactionlist");

	//获取训练日志及动作日志
	PlanLog planLog = (PlanLog) request.getAttribute("planlog");
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
<link rel="stylesheet" href="<%=basePath%>css/loding01.css" type="text/css" />

<!-- 引入三方库 -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/plandetails.js"></script>


</head>

<body class="background_img" style="background-size: 100% 300px;">

	<p class="plandetails_topbar">
		<span><%=plan.getvName()%></span>


		<img id="deleteplan" alt="" src="/habit/img/details_delete.png" width="25px" planid="<%=plan.getiId()%>">
		&nbsp;&nbsp;
		<!--<img id="moreplan" alt="" src="/habit/img/details_more.png" width="25px">
		&nbsp;&nbsp;-->
		<img id="editplan" alt="" src="/habit/img/details_edit.png" width="25px" planid="<%=plan.getiId()%>">
	</p>
	<div style="width: 90%; margin: 5% 5% 100px 5%">

		<%
			for (Action_PlanDetailsJSPTemp action : actionList) {
		%>
		<div class='target_in' actionid="<%=action.getAid()%>">
			<div class='target_in_dec action_rm'></div>
			<div>
				<span><%=action.getAname()%></span><br>
				<font>
					总共<%=action.getNum()%>组，训练未开始
				</font>
			</div>
		</div>
		<%
			}
		%>
	</div>

	<div class="bottom_black_button">
		<button class="black_button_01" style="width: 100%;" type="button" id="button_es" state="0" planid="<%=plan.getiId()%>">开始训练</button>
	</div>
</body>
</html>



