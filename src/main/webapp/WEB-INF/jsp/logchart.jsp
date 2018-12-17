<%@page import="com.flowermake.habit.domain.Action_NewPlanJSPTemp"%>
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
<link rel="stylesheet" href="<%=basePath%>plugs/swiper/css/swiper.min.css">

<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>plugs/swiper/js/swiper.min.js"></script>
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

	<!-- 导航 -->
	<div class="tab_h50">
		<div class="tab_h50_selected" id="leftnav">身体记录</div>
		<div class="tab_h50_unselected" id="rightnav">动作记录</div>
		<div class="tab_h50_line"></div>
	</div>

	<div id="mainswiper" class="swiper-container" style="margin-top: 50px;" >
		<div class="swiper-wrapper">
			<!-- 内容页：身体指标 -->
			<div id="bodylogchart" style="width: 100%;" class="swiper-slide">
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

			<!-- 内容页：动作记录 -->
			<div id="actionlogchart" class="swiper-slide" init="0">
				<div class="loding_panel" style="display: none;">
					<img src="<%=basePath%>/img/big_loding.gif" width="50px">
					<br> <br> <span>...正在加载..</span>
				</div>
				<div class="nodata_panel" style="display: none;">
					<img src="<%=basePath%>/img/nodata.png" width="100px">
					<br> <span>还未添加过任何动作</span>
				</div>
				<div class="error_panel" style="display: none;">
					<img src="<%=basePath%>/img/error.png" width="100px">
					<br> <span>系统懵逼了，要不再试一次</span>
					<div class="error_panel_button">再试一次</div>
				</div>
			</div>

		</div>
	</div>
</body>
<!-- 日志详情页 -->
	<div id="loglist_panel" style="position: fixed; display: none; width: 100%; height: 100%; background: #f8f8f8;">
		<!-- 顶部导航 -->
		<div id="loglist_panel_nav">
			<img id="loglist_panel_nav_close" src="<%=basePath%>img/close.png">
		</div>
		<div id="loglist_panel_nav_blank"></div>
		<div id="loglist_panel_loding_panel" style="display: none;">
			<img src="<%=basePath%>/img/big_loding.gif" width="50px">
			<br> <br> <span>...正在加载...</span>
		</div>
		<div id="loglist_panel_nodata_panel" style="display: none;">
			<img src="<%=basePath%>/img/nodata.png" width="100px">
			<br> <span>还未添加过任何动作</span>
		</div>
		<div id="loglist_panel_error_panel" style="display: none;">
			<img src="<%=basePath%>/img/error.png" width="100px">
			<br> <span>系统懵逼了，要不再试一次</span>
			<div class="error_panel_button" id="loglist_panel_error_panel_button">再试一次</div>
		</div>
	</div>
</html>



