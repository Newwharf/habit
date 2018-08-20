<%@page import="com.flowermake.habit.tools.Commons"%>
<%@page import="com.flowermake.habit.domain.BodyData"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.flowermake.habit.domain.User"%>
<%@page import="com.flowermake.habit.domain.Target"%>
<%@page import="com.flowermake.habit.domain.Plan"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");

	//得到用户名称以及头像链接
	String name = user.getvName();
	String vImgUrl = user.getvImgurl();

	//得到体重以及体脂数据
	BodyData bodyData = (BodyData) session.getAttribute("bodyData");
	String weightMsg = "";
	if (bodyData.getfLastweight() != null) {
		weightMsg = bodyData.getfLastweight() + "kg";
		if (bodyData.getfLastbodyfat() != null) {
			weightMsg = weightMsg + "（" + bodyData.getfLastbodyfat() + "%）";
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

	//得到训练数据
	int planListSize = 0;
	List<Plan> planList = (List<Plan>) request.getAttribute("planlist");
	if (planList != null) {
		planListSize = planList.size();
	} else {
		planListSize = 0;
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

<!-- 引入相关JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/home.js"></script>
</head>

<body class="background_img">

	<!-- 顶部用户基本信息栏 -->
	<div style="width: 90%; height: 50px; margin: 10px 5% 10px 5%; position: relative;">
		<div class="head_img headclick" style="background-image: url(<%=vImgUrl%>);float:left;"></div>
		<div class="headclick" style="float: left; margin-left: 5px;">
			<span style="font-size: 23px; color: white;"><%=name%></span><br /> <span style="font-size: 14px; color: white;"><%=weightMsg%></span>
		</div>
		<!--  <div id="record_body" style="border: 1px solid #ffffff;width: 100px;height:30px;position: absolute;right: 0px;top:10px;color: #ffffff;text-align: center;font-size: 15px;line-height: 30px;">身体记录</div>-->
		<!-- <div class="ico_img1"></div> -->
	</div>


	<!-- 目标面板 -->
	<div class="list_panel">
		<div class="list_panel_title">
			<div style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/cup.png" height="50px" style="margin: 5px 0 10px 0;">
			</div>
			<div style="width: 60%; line-height: 70px;">
				<span style="font-size: 26px; color: black;">目标</span>
			</div>
			<div id=target_add style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/add.png" height="25px" style="margin: 23px 0 23px 0;">
			</div>
		</div>
		<%
			if (targetListSize > 0) {
		%>
		<!-- 如果目标集合中有数据 -->
		<div class="target_list_nonull" id="target_msg_list">
			<%
				for (Target target : targetList) {
			%>
			<div class="target_item">
				<div class="line_space">
					<div class="line_space_line"></div>
				</div>
				<p>
					<span><%=target.getvMsg()%></span><br>
					<font><%=target.getTargetScore(bodyData)%></font>
				</p>
			</div>
			<%
				}
			%>
			<div class="line_space">
				<div class="line_space_line" style="border-top: 1px solid #f8f8f8;"></div>
			</div>
			<p id="target_more" style="height: 20px; text-align: right; margin-right: 15px;">
				<font>...更多</font>
			</p>
		</div>
		<%
			} else {
		%>
		<div class="list_null" id="target_msg_list">
			<label>没有目标的你，和咸鱼有什么区别</label>
			<div class="line_space" style="display: none;">
				<div class="line_space_line" style="border-top: 1px solid #f8f8f8;"></div>
			</div>
			<p id="target_more" style="height: 20px; text-align: right; margin-right: 15px; display: none;">
				<font>...更多</font>
			</p>
		</div>
		<%
			}
		%>
	</div>


	<!-- 训练面板 -->
	<div class="list_panel">
		<div class="list_panel_title">
			<div style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/plan.png" height="40px" style="margin: 15px 0 15px 0;">
			</div>
			<div style="width: 60%; line-height: 70px;">
				<span style="font-size: 26px; color: black;">训练</span>
			</div>
			<div id="plan_add" style="width: 20%; text-align: center;">
				<img alt="" src="<%=basePath%>img/add.png" height="25px" style="margin: 23px 0 23px 0;">
			</div>
		</div>
		<div class="line_space">
			<div class="line_space_img"></div>
			<div class="line_space_line"></div>
		</div>
		<%
			if (planListSize > 0) {
				for (int i = 0; i < planList.size(); i++) {
					Plan plan = planList.get(i);
		%>
		<!-- 有计划时显示计划列表 -->
		<div class="plan_list_nonull" planid="<%=plan.getiId()%>">
			<div class="plan_list_nonull_item" style="<%=i == planList.size() - 1 ? "border-bottom:0px;" : ""%>">
				<span><%=plan.getvName()%></span><br />
				<font><%=plan.gethPlancol()%></font>
			</div>
		</div>
		<%
			}
			} else {
		%>
		<!-- 没计划时显示如下提示 -->
		<div class="list_null">尴尬...没有计划&gt;_&lt;</div>
		<%
			}
		%>
	</div>
</body>

<!-- 主页更多面板 -->
<div id="more_panel_animation"></div>
<div id="more_panel_body">
	<p class="more_panel_button" id="button_userinfo">
		<img alt="" src="img/userinfo.png" width="60px">
		<span>身体指标</span>
	</p>
	<p class="more_panel_button" id="button_action">
		<img alt="" src="img/action.png" width="60px">
		<span>动作管理</span>
	</p>
	<!-- <p class="more_panel_button" id="button_bodydatalog">
		<img alt="" src="img/bodydata.png" width="60px">
		<span>身体记录</span>
	</p> -->
	<p class="more_panel_button" id="button_logchart">
		<img alt="" src="img/logchart.png" width="60px">
		<span>历史记录</span>
	</p>
	<p class="more_panel_close">
		<img id="more_panel_close" alt="" src="img/close.png" width="30px">
	</p>
</div>

<!-- 新增目标弹窗 -->
<div class="information_panel" style="display: none;">
	<div class="information_panel_input">
		<div class="information_panel_label">
			<font>指标</font>
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
				<option selected="selected" value="sbxw">请选择</option>
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



