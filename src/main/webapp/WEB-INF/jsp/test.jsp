<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<html>
<head>
<title>红包活动验证</title>
<script type="text/javascript" src="../plugs/jquery/jquery-3.3.1.min.js"></script>
<style type="text/css">
body {
	background-image: url('../img/bmg.png');
	background-position: top left;
	background-repeat: no-repeat;
	background-size: 80%;
	background-color: #da2134;
	font-weight: 300;
}

.redbagbody {
	width: auto;
	height: auto;
	position: absolute;
	top: 2rem;
	left: 2rem;
	right: 2rem;
	bottom: 2rem;
	background-image: linear-gradient(-30deg, #da2134, #fb585a);
	box-shadow: 0 0 0.5rem #6a1e21;
	overflow: hidden;
}

.redbagtop {
	width: inherit;
	height: 12rem;
	border-radius: 50% 50% 50% 50%;
	background-image: linear-gradient(90deg, #f44b54, #e22942);
	box-shadow: 0 0.1rem 0.5rem #6a1e21;
	position: relative;
	bottom: 6rem;
	text-align: center;
}

.redbagtop img {
	position: relative;
	top: 9rem;
	width: 5.5rem;
}

.input_area {
	width: auto;
	height: 8rem;
	position: absolute;
	left: 1.5rem;
	right: 1.5rem;
	overflow: hidden;
}

.input_area input {
	background-color: transparent;
	border-style: none none solid none;
	border-bottom: 0.01rem solid #ebebeb;
	width: 100%;
	height: 2rem;
	font-size: 1.2rem;
	color: white;
	position: relative;
	left: 2.2rem;
}

.input_area div {
	position: relative;
}

.input_area img {
	width: 1.5rem;
	position: absolute;
	top: 0.2rem;
}

.sendcode {
	width: 5.5rem;
	height: 1.5rem;
	background-color: #ffd200;
	color: da2134;
	font-size: 14px;
	text-align: center;
	top: 0rem;
	right: 0rem;
	line-height: 1.5rem;
	border-radius: 5px 5px 5px 5px;
}

.submit {
	width: auto;
	height: 2.5rem;
	background-color: #ffd200;
	color: da2134;
	position: absolute;
	line-height: 2.5rem;
	text-align: center;
	left: 1.5rem;
	right: 1.5rem;
	top: 23rem;
	left: 1.5rem;
	border-radius: 20px 20px 20px 20px;
}
</style>
<script type="text/javascript">
	$(function() {

		//发送验证码代码部分开始
		var sec = 121;
		var isTimeRun = false;
		$('.sendcode').on('click', function() {

			if (!isTimeRun) {
				var interval = setInterval(function() {

					isTimeRun = true;
					sec--;
					if (sec <= 0) {
						clearInterval(interval);
						sec = 120;
						isTimeRun = false;
						$('.sendcode').html('发送验证码');
						return;
					}
					$('.sendcode').html(sec);
				}, 1000);
				//执行调用发送验证码的操作......
			}
		});
		//发送验证码代码部分结束
	});
</script>
</head>
<body>

	<div class="redbagbody">
		<div class="redbagtop">
			<img src="../img/money.png">
		</div>
		<div class="input_area">
			<div>
				<img src="../img/phone.png">
				<input type="text" name="phone" value="">
			</div>
			<div style="top: 2rem;">
				<img src="../img/code.png">
				<input type="text" name="code" value="">
				<div class="sendcode" style="position: absolute;">发送验证码</div>
			</div>
		</div>

		<div class="submit">提交</div>

	</div>

</body>
</html>



