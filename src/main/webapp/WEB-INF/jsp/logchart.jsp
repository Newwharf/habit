<%@page import="com.flowermake.habit.domain.ActionLog"%>
<%@page import="com.flowermake.habit.domain.Action"%>
<%@page import="com.flowermake.habit.tools.Commons"%>
<%@page import="com.flowermake.habit.domain.BodyData"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.flowermake.habit.domain.User"%>
<%@page import="com.flowermake.habit.domain.Target"%>
<%@page import="com.flowermake.habit.domain.BodyDataLog"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");
	Map<Byte, BodyDataLog> bdlMap = (Map<Byte, BodyDataLog>) request.getAttribute("bdlMap");
	Map<Long, ActionLog> actionLogMap = (Map<Long, ActionLog>) request.getAttribute("actionLogMap");
	List<Action> actionList = (List<Action>) request.getAttribute("actionList");
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
<script type="text/javascript" src="<%=basePath%>js/logchart.js"></script>


</head>

<body style="background-color: #f8f8f8;">
<input type="hidden" id="deviceid" value="<%=user.getvDeviceId()%>">
	<!-- 顶部导航栏 -->
	<div class="nvabar">
		<img class="nvabar_back" src="<%=basePath%>img/back.png">
		<span class="nvabar_title"></span>
	</div>
	<div class="nvabar_blank"></div>
	<div id="bodylogchart" style="width: 100%;">
		<div class="tab_h50">
			<div class="tab_h50_selected">
				身体记录
				<div class="tab_h50_line"></div>
			</div>
			<div class="tab_h50_unselected">动作记录</div>
		</div>

		<div class="logochart_bodydata_base">
			<span style="color: #727272;">基本身体数据</span>
			<div style="clear: both;"></div>
			<div class="logochart_bodydata_item" data="1" isJump="<%=bdlMap.get((byte) 1) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">体重</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 1) != null ? "最近数据，" + bdlMap.get((byte) 1).getfScore() + "kg" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="2" isJump="<%=bdlMap.get((byte) 2) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">体脂</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 2) != null ? "最近数据，" + bdlMap.get((byte) 2).getfScore() + "%" : "暂无数据"%></div>
			</div>
		</div>

		<div class="logochart_bodydata_base">
			<span style="color: #727272;">整体纬度</span>
			<div style="clear: both;"></div>
			<div class="logochart_bodydata_item" data="3" isJump="<%=bdlMap.get((byte) 3) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">肩宽</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 3) != null ? "最近数据，" + bdlMap.get((byte) 3).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="4" isJump="<%=bdlMap.get((byte) 4) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">胸围</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 4) != null ? "最近数据，" + bdlMap.get((byte) 4).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_item" data="6" isJump="<%=bdlMap.get((byte) 6) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">腰围</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 6) != null ? "最近数据，" + bdlMap.get((byte) 6).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="7" isJump="<%=bdlMap.get((byte) 7) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">臀围</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 7) != null ? "最近数据，" + bdlMap.get((byte) 7).getfScore() + "cm" : "暂无数据"%></div>
			</div>
		</div>

		<div class="logochart_bodydata_base">
			<span style="color: #727272;">上肢纬度</span>
			<div style="clear: both;"></div>
			<div class="logochart_bodydata_item" data="8" isJump="<%=bdlMap.get((byte) 8) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">左上臂</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 8) != null ? "最近数据，" + bdlMap.get((byte) 8).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="9" isJump="<%=bdlMap.get((byte) 9) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">右上臂</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 9) != null ? "最近数据，" + bdlMap.get((byte) 9).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_item" data="10" isJump="<%=bdlMap.get((byte) 10) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">左前臂</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 10) != null ? "最近数据，" + bdlMap.get((byte) 10).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="11" isJump="<%=bdlMap.get((byte) 11) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">右前臂</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 11) != null ? "最近数据，" + bdlMap.get((byte) 11).getfScore() + "cm" : "暂无数据"%></div>
			</div>
		</div>

		<div class="logochart_bodydata_base">
			<span style="color: #727272;">下肢围度</span>
			<div style="clear: both;"></div>
			<div class="logochart_bodydata_item" data="12" isJump="<%=bdlMap.get((byte) 12) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">左大腿</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 12) != null ? "最近数据，" + bdlMap.get((byte) 12).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="13" isJump="<%=bdlMap.get((byte) 13) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">右大腿</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 13) != null ? "最近数据，" + bdlMap.get((byte) 13).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_item" data="14" isJump="<%=bdlMap.get((byte) 14) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">左小腿</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 14) != null ? "最近数据，" + bdlMap.get((byte) 14).getfScore() + "cm" : "暂无数据"%></div>
			</div>
			<div class="logochart_bodydata_block"></div>
			<div class="logochart_bodydata_item" data="15" isJump="<%=bdlMap.get((byte) 15) != null ? true : false%>">
				<div class="logochart_bodydata_item_line"></div>
				<div class="logochart_bodydata_item_title">右小腿</div>
				<div class="logochart_bodydata_item_msg"><%=bdlMap.get((byte) 15) != null ? "最近数据，" + bdlMap.get((byte) 15).getfScore() + "cm" : "暂无数据"%></div>
			</div>
		</div>

	</div>
	<div id="actionlogchart" style="display: none;">
		<div class="tab_h50">
			<div class="tab_h50_unselected">身体记录</div>
			<div class="tab_h50_selected">
				动作记录
				<div class="tab_h50_line"></div>
			</div>
		</div>

		<div id="action_list" style="width: 90%; margin: auto;">

			<%
				if (actionList.size() > 0) {
			%>
			<%
				for (Action action : actionList) {
						ActionLog actionLog = actionLogMap.get(action.getiId());
			%>
			<%
				if (action.getTiType() == 0) {
			%>
			<div class="target_in" data="<%=action.getiId()%>" isJump="<%=actionLog != null ? true : false%>">
				<div class="target_in_dec action_rm"></div>
				<div>
					<span><%=action.getvName()%></span><br>
					<font><%=actionLog != null
										? "最近数据，" + actionLog.getfScoreweight() + "kg/" + actionLog.getiScorenum()
												+ action.getvUnit()
										: "暂无数据"%></font>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="target_in" data="<%=action.getiId()%>" isJump="<%=actionLog != null ? true : false%>">
				<div class="target_in_dec action_time"></div>
				<div>
					<span><%=action.getvName()%></span><br>
					<font><%=actionLog != null
								? "最近数据，" + actionLog.getfScoreweight() + "kg/" + actionLog.getiScoretime() + "秒"
								: "暂无数据"%></font>
				</div>
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
	</div>

</body>
</html>



