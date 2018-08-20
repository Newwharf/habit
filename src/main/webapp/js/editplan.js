$(function() {

	var listDialog;
	var dialog_height = 0;
	// 点击确定按钮的处理事件
	$("#id_button_ok").on("click", function() {

		var name = $("#action_name_input").val();
		var actionList = $(".selected_action_list_item_option_value");
		if (name == "") {
			layer.msg("请输入训练名称", {
			time : "2000" ,
			offset : "200px" ,
			anim : "6"
			});
			return;
		} else if (actionList.length == 0) {
			layer.msg("训练中至少得有一个动作吧", {
			time : "2000" ,
			offset : "200px" ,
			anim : "6"
			});
			return;
		} else {
			// 验证通过，开始组装数据
			layer.load(1, {
				shade : [0.5 , '#000']
			});
			var actionJsonList = [];
			for (var i = 0; i < actionList.length; i++) {
				var actionObj = actionList.eq(i);
				var actionJson = {};
				actionJson.a_id = actionObj.attr("actionid");
				actionJson.num = actionObj.val();
				actionJsonList.push(actionJson);
			}
			$.ajax({
			url : "editplan" ,
			type : "post" ,
			data : {
			"planname" : name ,
			"planid" : $("#hidder_planid").val() ,
			"actionlist" : JSON.stringify(actionJsonList)
			} ,
			dataType : "json" ,
			error : function(data) {

				layer.msg("失败了！系统懵逼中！", {
				time : "2000" ,
				offset : "200px" ,
				anim : "6"
				});
			} ,
			success : function(data) {

				$(location).attr('href', 'toplandetails?planid=' + data.pid);
			}
			});
		}
	});
	// 删除动作按钮时的处理事件
	$("#selected_action_list").on("click", "img", function() {

		var id = $(this).attr("actionid");
		$("#action_item_" + id).slideUp(200);
		setTimeout(function() {

			$("#action_item_" + id).remove();
		}, 200);
		$("#list_panel_dialog_item_" + id).show();
		$("#list_panel_dialog_item_" + id).addClass("action_show");
		$("#list_panel_dialog_item_" + id + " + div").show();
	});
	// 选择动作时的处理事件
	$(".list_panel_dialog").on("click", ".list_panel_dialog_item", function() {

		var id = $(this).attr("actionid");
		var name = $(this).attr("actionname");
		var type = $(this).attr("actiontype");
		$("#list_panel_dialog_item_" + id).slideUp(200);
		$("#list_panel_dialog_item_" + id).removeClass("action_show");
		$("#list_panel_dialog_item_" + id + " + div").hide();
		layer.close(listDialog);
		var selectedActionHtml = "<div id='action_item_" + id + "' class='selected_action_list_item' style='display:none;'>" + "<div class='selected_action_list_item_name' >" + "<span>" + name + "</span>" + "<img src='/habit/img/delete.png' width='20px' actionid='" + id + "' >" + "</div>" + "<div class='line_01'></div>" + "<div class='selected_action_list_item_option' >" + "<span>动作组数</span>" + "<input id='action_item_value_" + id + "' class='selected_action_list_item_option_value' type='number' actionid='" + id + "' value='4'>" + "</div>" + "</div>"
		$("#selected_action_list").append(selectedActionHtml);
		$("#action_item_" + id).slideDown(200);
	});
	// 添加动作按钮点击事件
	$(".new_button").on("click", function() {

		var content = $(".list_panel_dialog");
		if ($(".action_show").length == 0) {
			// 动作已被选择完了
			$("#action_nodata").show();
		} else {
			$("#action_nodata").hide();
		}
		listDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['90%' , '300px'] ,
		title : false ,
		offset : '80px'
		});
	});
	ctrlBack("/habit/plan/toplandetails?planid="+getQueryString("pid"));
});
