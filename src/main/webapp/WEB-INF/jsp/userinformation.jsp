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

	//得到用户名称以及头像链接
	String name = user.getvName();
	String vImgUrl = user.getvImgurl();

	//得到体重以及体脂数据
	BodyData bodyData = (BodyData) request.getAttribute("bodyData");
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

<!-- 引入JS -->
<script type="text/javascript" src="<%=basePath%>plugs/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugs/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/userinformation.js"></script>

</head>

<body class="background_img">
	<div id="maindiv">
		<!-- 基本信息 -->
		<div class="information_panel information_panel_headimg">
			<img src="<%=vImgUrl%>" width="85px;" style="border-radius: 50%; position: absolute; left: 0; right: 0; margin: auto; top: -43px;">

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>昵称</font>
				</div>
				<div>
					<input id="username" name="username" type="text" value="<%=user.getvName()%>">
				</div>
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>性别</font>
				</div>
				<div>
					<select id="sex" name="sex">
						<%
							if (user.getTiSex() == 0) {
						%>
						<option selected="selected" value="0">男</option>
						<option value="1">女</option>
						<%
							} else {
						%>
						<option value="0">男</option>
						<option selected="selected" value="1">女</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<div class="information_panel_input" style="border: none;">
				<div class="information_panel_label">
					<font>生日</font>
				</div>
				<div>
					<input type="date" id="birthday" name="birthday" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(user.getdBirthday())%>">
				</div>
			</div>

		</div>

		<!-- 基本身体数据 -->
		<div class="information_panel">
			<div style="width: 100%; line-height: 60px; height: 50px; padding-left: 20px;">
				<span style="font-size: 20px; color: black;">基本身体数据</span>
			</div>
			<div class="line_space">
				<div class="line_space_line"></div>
			</div>

			<div class="information_panel_input" style="">
				<div class="information_panel_label">
					<font>身高</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastheight() == null ? "" : bodyData.getfLastheight()%>" id="height" name="height">
					<input type="hidden" name="height_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>体重</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastweight() == null ? "" : bodyData.getfLastweight()%>" id="weight" name="weight">
					<input type="hidden" name="weight_isUpdate" value="0">
				</div>
				kg
			</div>

			<div class="information_panel_input" style="border: none;">
				<div class="information_panel_label">
					<font>体脂</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastbodyfat() == null ? "" : bodyData.getfLastbodyfat()%>" id="bodyfat" name="bodyfat">
					<input type="hidden" name="bodyfat_isUpdate" value="0">
				</div>
				%
			</div>

		</div>

		<!-- 整体围度 -->
		<div class="information_panel">
			<div style="width: 100%; line-height: 60px; height: 50px; padding-left: 20px;">
				<span style="font-size: 20px; color: black;">整体围度</span>
			</div>
			<div class="line_space">
				<div class="line_space_line"></div>
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>肩宽</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastshouldersize() == null ? "" : bodyData.getfLastshouldersize()%>" id="shouldersize" name="shouldersize">
					<input type="hidden" name="shouldersize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>胸围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastbust() == null ? "" : bodyData.getfLastbust()%>" id="bust" name="bust">
					<input type="hidden" name="bust_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input" style="display: none;">
				<div class="information_panel_label">
					<font>腹围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastabdominalsize() == null ? "" : bodyData.getfLastabdominalsize()%>" id="abdominalsize" name="abdominalsize">
					<input type="hidden" name="abdominalsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>腰围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastwaistline() == null ? "" : bodyData.getfLastwaistline()%>" id="waistline" name="waistline">
					<input type="hidden" name="waistline_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input" style="border: none;">
				<div class="information_panel_label">
					<font>臀围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLasthipline() == null ? "" : bodyData.getfLasthipline()%>" id="hipline" name="hipline">
					<input type="hidden" name="hipline_isUpdate" value="0">
				</div>
				cm
			</div>

		</div>

		<!-- 上肢围度 -->
		<div class="information_panel">
			<div style="width: 100%; line-height: 60px; height: 50px; padding-left: 20px;">
				<span style="font-size: 20px; color: black;">上肢围度</span>
			</div>
			<div class="line_space">
				<div class="line_space_line"></div>
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>左上臂围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastlarmsize() == null ? "" : bodyData.getfLastlarmsize()%>" id="larmsize" name="larmsize">
					<input type="hidden" name="larmsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>左前臂围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastlforearmsize() == null ? "" : bodyData.getfLastlforearmsize()%>" id="lforearmsize" name="lforearmsize">
					<input type="hidden" name="lforearmsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>右上臂围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastrarmsize() == null ? "" : bodyData.getfLastrarmsize()%>" id="rarmsize" name="rarmsize">
					<input type="hidden" name="rarmsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input" style="border: none;">
				<div class="information_panel_label">
					<font>右前臂围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastrforearmsize() == null ? "" : bodyData.getfLastrforearmsize()%>" id="rforearmsize" name="rforearmsize">
					<input type="hidden" name="rforearmsize_isUpdate" value="0">
				</div>
				cm
			</div>

		</div>

		<!-- 下肢围度 -->
		<div class="information_panel">
			<div style="width: 100%; line-height: 60px; height: 50px; padding-left: 20px;">
				<span style="font-size: 20px; color: black;">下肢围度</span>
			</div>
			<div class="line_space">
				<div class="line_space_line"></div>
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>左大腿围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastlthighsize() == null ? "" : bodyData.getfLastlthighsize()%>" id="lthighsize" name="lthighsize">
					<input type="hidden" name="lthighsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>左小腿围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastlcrussize() == null ? "" : bodyData.getfLastlcrussize()%>" id="lcrussize" name="lcrussize">
					<input type="hidden" name="lcrussize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input">
				<div class="information_panel_label">
					<font>右大腿围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastrthighsize() == null ? "" : bodyData.getfLastrthighsize()%>" id="rthighsize" name="rthighsize">
					<input type="hidden" name="rthighsize_isUpdate" value="0">
				</div>
				cm
			</div>

			<div class="information_panel_input" style="border: none;">
				<div class="information_panel_label">
					<font>右小臂围</font>
				</div>
				<div>
					<input step="0.01" type="number" value="<%=bodyData.getfLastrcrussize() == null ? "" : bodyData.getfLastrcrussize()%>" id="rcrussize" name="rcrussize">
					<input type="hidden" name="rcrussize_isUpdate" value="0">
				</div>
				cm
			</div>

		</div>

		<button class="black_button_01" style="margin-top: 60px; margin-bottom: 30px;" type="button" id="id_button_ok">
			<span>确 定</span>
			<img id="loding_tip" src='<%=basePath%>img/loding.gif' width='30px' style="display: none;" />
			<img id="success_tip" src='<%=basePath%>img/success.png' width='30px' style="display: none;" />
			<img id="error_tip" src='<%=basePath%>img/error.png' style="display: none;" height="30px;" />
		</button>
	</div>

	<!-- 	
	<div id="loading" style="display: none">
		<div id="loading-center">
			<div id="loading-center-absolute">
				<div class="object" id="object_four"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_one"></div>
			</div>
		</div>
	</div>
 -->
</body>
</html>



