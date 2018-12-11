<%@page import="com.flowermake.habit.tools.Commons"%>
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
	Map<Long, List<ActionLog>> actionLogMap = (Map<Long, List<ActionLog>>) request.getAttribute("actionLogMap");
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
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/loding01.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>plugs/layer/skin/dialog_2button/style.css" type="text/css" />

<!-- 引入三方库 -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/echarts/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/chart.js"></script>
<script type="text/javascript" src="<%=basePath%>js/plandetails.js"></script>


</head>

<body class="background_img" style="background-size: 100% 500px;">
<input type="hidden" id="deviceid" value="<%=user.getvDeviceId()%>">
	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="<%=basePath%>img/back.png">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>
	<p class="plandetails_topbar">
		<span><%=plan.getvName()%></span>
	</p>

	<%
		for (Action_PlanDetailsJSPTemp action : actionList) {
			boolean isShowRecord = true;
	%>

	<div class="list_panel" style="padding-bottom: 0px;">
		<div class="title_60h"><%=action.getAname()%></div>
		<div class="line_01"></div>

		<%
			List<ActionLog> actionLogList = actionLogMap.get(action.getAid());
				for (int i = 0; i < action.getNum(); i++) {
					if (i < actionLogList.size()) {
						ActionLog actionLog = actionLogList.get(i);
		%>

		<div class="item_50h">
			<div class="item_50h_d1">
				<span><%=(i + 1) < 10 ? "0" + (i + 1) : i%></span>
			</div>
			<div class="item_50h_d2">
				<span style="font-size: 18px;"><%=actionLog.getfScoreweight()%>kg/<%=actionLog.getTiActiontype() == 0 ? actionLog.getiScorenum() + actionLog.getvActionunit()
										: actionLog.getiScoretime() + "秒"%></span> <br>
				<font style="font-size: 13px;"><%=Commons.formatDate(actionLog.getDtCdate(), "YYYY.MM.dd HH:mm")%></font>
			</div>
			<img alt="" src="/habit/img/record.png" height="30px" isrecord="1" planid="<%=plan.getiId()%>" num="<%=actionLog.getiNumbyplan()%>" actiontype="<%=action.getType()%>" actionid="<%=action.getAid()%>" planlogid="<%=planLog.getId()%>" actionlogid="<%=actionLog.getiId()%>" score="<%=action.getType() == 0 ? actionLog.getiScorenum() : actionLog.getiScoretime()%>" scoreweight="<%=actionLog.getfScoreweight()%>" comments="<%=actionLog.getvComments()%>" actionunit="<%=action.getUnit()%>">
		</div>
		<div class="line_01"></div>

		<%
			} else {
		%>
		<div class="item_50h">
			<div class="item_50h_d1">
				<span><%=(i + 1) < 10 ? "0" + (i + 1) : i%></span>
			</div>
			<div class="item_50h_d2">
				<span style="font-size: 15px;">--kg/--</span> <br>
				<font style="font-size: 13px;">--</font>
			</div>
			<img alt="" src="/habit/img/record.png" height="30px" isrecord="0" planid="<%=plan.getiId()%>" num="<%=i + 1%>" actiontype="<%=action.getType()%>" actionid="<%=action.getAid()%>" planlogid="<%=planLog.getId()%>" actionunit="<%=action.getUnit()%>" style="<%=isShowRecord ? "" : "display:none;"%>">
		</div>
		<div class="line_01"></div>
		<%
			isShowRecord = false;
					}
				}
		%>

	</div>

	<%
		}
	%>

	<div style="height: 100px;"></div>


	<div class="bottom_black_button">
		<button class="black_button_01" style="width: 100%; background-color: #ff0000;" type="button" id="button_es" state="1" planid="<%=plan.getiId()%>" planlogid="<%=planLog.getId()%>">结束训练</button>
	</div>

</body>

<!-- 弹出记录编辑面板 -->
<div id="recard_dialog" style="height: 1000px; background-color: #ffffff; position: relative; display: none;">
	<div id="dialog_chart" style="height: 220px;"></div>
	<div style="height: 1px; width: 100%; background-color: #ffb5ae; position: absolute; top: 185px; z-index: 100;"></div>

	<div class="information_panel">
		<!-- <div style="font-size: 20px;padding-left: 20px;position: relative;bottom: 13px;">动作名称</div> -->
		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>负重</font>
			</div>
			<div style="top: 16px;">
				<input id="scoreweight" step="0.1" type="number">
			</div>
			<font>kg</font>
		</div>

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font id="score_title">次数</font>
			</div>
			<div style="top: 16px;">
				<input id="score" step="0" type="number">
			</div>
			<font id="score_unit">次</font>
		</div>

		<div class="information_panel_input">
			<div class="information_panel_label">
				<font>感悟</font>
			</div>
			<div style="top: 16px;">
				<input id="comments" type="text">
			</div>
			<font></font>
		</div>

		<input type="hidden" id="actiontype" value="">
		<input type="hidden" id="numbyplan" value="">
		<input type="hidden" id="aid" value="">
		<input type="hidden" id="plid" value="">
		<input type="hidden" id="plaisrecord" weight_isrecord=0 score_isrecord=0 value="">


	</div>

</div>

</html>



