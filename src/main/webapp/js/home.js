$(function() {

	preUrl = "/habit/home";
	isCtrlBack = true;
	// 点击头像区域触发更多面板
	$(".headclick").on("touchstart", function(e) {

		// 禁止滚动
		$("body").css("overflow", "hidden");
		// 以动画形式显示更多面板动画层
		var _touch = e.originalEvent.targetTouches[0];
		var _x = _touch.pageX;
		var _y = _touch.pageY;
		$("#more_panel_animation").css({
		"top" : _y ,
		"left" : _x ,
		"width" : "0px" ,
		"height" : "0px"
		}).show();
		$("#more_panel_animation").animate({
		width : "2000px" ,
		height : "2000px" ,
		top : _y - 1000 ,
		left : _x - 1000
		}, 500);
		// 动画结束后展示更多面板内容层以及逐步淡入显示按钮
		setTimeout(function() {

			$("#more_panel_body").show();
			$("#button_userinfo").fadeIn(300);
			setTimeout(function() {

				$("#button_action").fadeIn(300);
				setTimeout(function() {

//					$("#button_bodydatalog").fadeIn(300);
//					setTimeout(function() {

						$("#button_logchart").fadeIn(300);
				}, 100);
			}, 100);
		}, 500);
	});
	// 关闭更多面板相应处理
	$("#more_panel_close").on("click", function() {

		// 开启滚动
		$("body").css("overflow", "visible");
		// 隐藏相关div
		$("#more_panel_body").fadeOut(300);
		$("#more_panel_animation").fadeOut(300);
		setTimeout(function() {

			$("#button_userinfo").hide();
			$("#button_action").hide();
			$("#button_logchart").hide();
		}, 300);
	});
	// 用户基本信息按钮事件
	$("#button_userinfo").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'user/userinformation');
	});
	// 用户动作管理按钮事件
	$("#button_action").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'action/actionlist');
	});
	// 分析报表按钮事件
	$("#button_logchart").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'chart/logchart');
	});
	$("#plan_add").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'plan/tonewplan');
	});
	// 训练面板每个训练点击详情
	$(".plan_list_nonull").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'plan/toplandetails?planid=' + $(this).attr("planid"));
	});
	// 目标面板更多按钮事件
	$("#target_more").on("click", function() {

		layer.load(1, {
			shade : [0.5 , '#000']
		});
		$(location).attr('href', 'target/targetlist');
	});
	// 目标面板添加事件
	$('#target_add').on("click", function() {

		var content = $(".information_panel");
		var newTargetDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['80%' , '300px'] ,
		title : false ,
		btn : ['确定'] ,
		offset : '100px' ,
		yes : function() {

			var ti_index = $("#ti_index").val();
			var ti_nexus = $("#ti_nexus").val();
			var f_value = $("#f_value").val();
			if (ti_index == "sbxw" || ti_nexus == "sbxw" || f_value == "") {
				layer.msg("请先好好填完目标信息", {
				time : "2000" ,
				offset : "200px" ,
				anim : "6"
				});
				return;
			}
			layer.close(newTargetDialog);
			var loding_shade = layer.load(1, {
				shade : [0.5 , '#000']
			});
			$.ajax({
			type : "post" ,
			url : "/habit/target/newtarget" ,
			data : {
			"ti_index" : $("#ti_index").val() ,
			"ti_nexus" : $("#ti_nexus").val() ,
			"f_value" : $("#f_value").val()
			} ,
			dataType : "json" ,
			error : function(data) {

				var msg = "失败了！系统懵逼中...";
				if (data.responseText.length <= 20) {
					msg = data.responseText;
				}
				layer.close(loding_shade);
				layer.msg(msg, {
				time : "3000" ,
				offset : "200px" ,
				anim : "6"
				});
			} ,
			success : function(data) {

				layer.close(loding_shade);
				layer.msg("添加目标成功", {
				time : "2000" ,
				offset : "200px"
				});
				var target_item = "<div style='display:none;' class='target_item'><div class='line_space'>" + "<div class='line_space_line'></div>" + "</div>" + "<p>" + "<span>" + data.msg + "</span><br>" + "<font>" + data.score + "</font>" + "</p></div>";
				$("#target_msg_list label").remove();
				$(".line_space").show();
				$("#target_more").show();
				$("#target_msg_list").attr("class", "target_list_nonull");
				$("#target_msg_list").prepend(target_item);
				$("#target_msg_list .target_item:first").slideDown(200);
				if ($(".target_item").length > 3) {
					$("#target_msg_list .target_item:last").slideUp(200).remove();
				}
				$("#ti_index").val("sbxw");
				$("#ti_nexus").val("sbxw");
				$("#f_value").val("");
			}
			});
		}
		});
	});
	$("#ti_index").change(function() {

		var ti_nexus_val = $("#ti_index").val();
		switch (ti_nexus_val) {
			case "-1" :
				$("#target_unit").text("");
				break;
			case "1" :
				$("#target_unit").text("kg");
				break;
			case "2" :
				$("#target_unit").text("%");
				break;
			default :
				$("#target_unit").text("cm");
				break;
		}
	});
	ctrlBack("home");
});
