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
	List<ActionLog> actionLogList = (List<ActionLog>) request.getAttribute("actionLogList");
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
<script type="text/javascript" src="<%=basePath%>js/loglist.js"></script>


</head>

<body style="background-color: #f8f8f8;">
	<div id="actionlogchart">
		<div id="action_list" style="width: 90%; margin: auto;">
		<%for(ActionLog actionLog:actionLogList){ %>
			<div class="target_in">
				<div class="target_in_dec"></div>
				<div>
					<span><%=actionLog.getfScoreweight() %> kg/<%=actionLog.getTiActiontype()==0?actionLog.getiScorenum()+" "+actionLog.getvActionunit():actionLog.getiScoretime()+" 秒" %></span><br>
					<font><%=Commons.formatDate(actionLog.getDtCdate(), "YYYY.MM.dd HH:mm:ss") %></font>
					<%if(!"".equals(actionLog.getvComments())&&actionLog.getvComments()!=null){ %>
						<img class="remark" src="<%=basePath%>img/remark.png" width="25px" height="25px" alt="<%=actionLog.getvComments()%>">
					<%} %>
				</div>
			</div>
		<%} %>
		</div>
	</div>

</body>

<div class="information_panel" style="display: none;">
<img alt="" src="<%=basePath%>img/mark.png" width="40px" style="margin:10px 0px 20px 30px "><br>
<p style="margin: 0px 0px 0px 70px;width: 60%;word-wrap:break-word;color: #515151;text-align: center;"></p>
</div>
</html>



