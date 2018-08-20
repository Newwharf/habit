$(function() {

	var editActionDialog;
	var newActionDialog;
	// 修改动作相应处理
	$("#action_list").on("click", ".action_edit", function() {

		var action_edit = $(this);
		var content = $("#action_edit_panel");
		var i_id = action_edit.attr("actionid");
		var v_name = action_edit.attr("actionname");
		var ti_type = action_edit.attr("actiontype");
		var v_unit = action_edit.attr("actionunit");
		$("#action_delete").attr("actionid", i_id);
		$("#action_name_edit").val(v_name);
		$("#action_type_edit").val(ti_type);
		if (ti_type == 0) {
			$("#action_type_edit opation:first").attr("selected", "true");
			$("#action_unit_value_edit").val(v_unit);
			$("#action_unit_edit").show();
		} else {
			$("#action_type_edit opation:last").attr("selected", "true");
			$("#action_unit_edit").hide();
		}
		editActionDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['80%' , '300px'] ,
		title : false ,
		btn : ['确定'] ,
		offset : '100px' ,
		yes : function() {

			layer.close(editActionDialog);
			var loding_shade = layer.load(1, {
				shade : [0.5 , '#000']
			});
			var v_name = $("#action_name_edit").val();
			var v_unit = $("#action_unit_value_edit").val();
			if (v_name != "") {
				if (ti_type == 0) {
					if (v_unit == "") {
						layer.msg("请填写以什么单位来计算次数", {
						time : "3000" ,
						offset : "200px" ,
						anim : "6"
						});
						return;
					}
				}
			} else {
				layer.msg("请填写动作名称", {
				time : "2000" ,
				offset : "200px" ,
				anim : "6"
				});
				return;
			}
			$.ajax({
			type : "post" ,
			url : "editaction" ,
			data : {
			"v_name" : v_name ,
			"v_unit" : v_unit ,
			"i_id" : i_id
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
				layer.msg("动作修改成功", {
				time : "2000" ,
				offset : "200px"
				});
				// TODO 修改成功后如何处理
				$("#action_name_" + i_id).text(data.v_name);
				action_edit.attr({
				"actionname" : data.v_name ,
				"actionunit" : data.v_unit
				});
			}
			});
		}
		});
	});
	// 新增动作相应处理
	$('.new_button').on("click", function() {

		var content = $("#action_add_panel");
		newActionDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['80%' , '300px'] ,
		title : false ,
		btn : ['确定'] ,
		offset : '100px' ,
		yes : function() {

			layer.close(newActionDialog);
			var loding_shade = layer.load(1, {
				shade : [0.5 , '#000']
			});
			var v_name = $("#action_name").val();
			var ti_type = $("#action_type").val();
			var v_unit = $("#action_unit_value").val();
			if (v_name != "") {
				if (ti_type == 0) {
					if (v_unit == "") {
						layer.msg("请填写以什么单位来计算次数", {
						time : "3000" ,
						offset : "200px" ,
						anim : "6"
						});
						return;
					}
				}
			} else {
				layer.msg("请填写动作名称", {
				time : "2000" ,
				offset : "200px" ,
				anim : "6"
				});
				return;
			}
			$.ajax({
			type : "post" ,
			url : "newaction" ,
			data : {
			"v_name" : v_name ,
			"ti_type" : ti_type ,
			"v_unit" : v_unit
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
				layer.msg("动作添加成功", {
				time : "2000" ,
				offset : "200px"
				});
				if (data.ti_type == 0) {
					var action_item = "<div style='display:none;' class='target_in' id='action_" + data.i_id + "'><div class='target_in_dec action_rm'></div><div><span id='action_name_" + data.i_id + "'>" + data.v_name + "</span><br><font id='action_type_" + data.i_id + "'>计次动作</font></div><img class='action_edit' src='/habit/img/edit.png' width='25px' actionid='" + data.i_id + "' actionname='" + data.v_name + "' actiontype='" + data.ti_type + "' actionunit='" + data.v_unit + "'></div>";
				} else {
					var action_item = "<div style='display:none;' class='target_in' id='action_" + data.i_id + "'><div class='target_in_dec action_time'></div><div><span id='action_name_" + data.i_id + "'>" + data.v_name + "</span><br><font id='action_type_" + data.i_id + "'>计时动作</font></div><img class='action_edit' src='/habit/img/edit.png' width='25px' actionid='" + data.i_id + "' actionname='" + data.v_name + "' actiontype='" + data.ti_type + "' actionunit='" + data.v_unit + "'></div>";
				}
				$("#action_list").prepend(action_item);
				$("#action_list div:first").slideDown(200);
				$("#action_name").val("");
				$("#action_type").val("0");
				$("#action_unit_value").val("");
				$("#action_unit").show()
			}
			});
		}
		});
	});
	$("#action_delete").on("click", function() {

		if (!confirm("删除动作后，该动作下的记录数据也会被删除，你确定要这么做？？！！")) {
			return;
		}
		var a_id = $(this).attr("actionid");
		var loding_shade = layer.load(1, {
			shade : [0.5 , '#000']
		});
		$.ajax({
		type : "post" ,
		url : "deleteaction" ,
		data : {
			"a_id" : a_id
		} ,
		dataType : "html" ,
		error : function(data) {

			layer.close(loding_shade);
			var msg = "失败了！系统懵逼中...";
			if (data.responseText.length <= 20) {
				msg = data.responseText;
			}
			layer.msg(msg, {
			time : "2000" ,
			offset : "200px" ,
			anim : "6"
			});
		} ,
		success : function(data) {

			layer.close(loding_shade);
			layer.msg("动作已删除", {
			time : "2000" ,
			offset : "200px"
			});
			layer.close(editActionDialog);
			$("#action_" + a_id).slideUp(200);
		}
		});
	});
	// 动作类型下拉框改变选项监听器
	$("#action_type").change(function() {

		if ($(this).val() == 0) {
			$("#action_unit").slideDown(200);
		} else {
			$("#action_unit").slideUp(200);
		}
	});
	ctrlBack("../home");
});
