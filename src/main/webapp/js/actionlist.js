$(function() {

	var editActionDialog;
	var newActionDialog;

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("actiontypelist");
	});

	// 修改动作相应处理
	$("#action_list").on("click", ".action_edit", function() {

		$(".add_button").hide();
		let action_edit = $(this);
		let content = $("#action_edit_panel");
		let i_id = action_edit.attr("actionid");
		let v_name = action_edit.attr("actionname");
		let ti_type = action_edit.attr("actiontype");
		let v_unit = action_edit.attr("actionunit");
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
			type : 1,
			skin : 'dialog_2button',
			closeBtn : 2,
			content : content,
			area : [ '80%', '300px' ],
			title : false,
			btn : [ '确定' ],
			offset : '100px',
			cancel : function(index, layero) {
				$(".add_button").show();
			},
			yes : function() {

				layer.close(editActionDialog);
				var loding_shade = layer.load(2, {
					shade : [ 0.5, '#000' ]
				});
				v_name = $("#action_name_edit").val();
				v_unit = $("#action_unit_value_edit").val();

				if (v_name != "") {
					if (v_name.length > 10) {
						showErrorMsg("动作名称最多10个字");
						return;
					}
					if (ti_type == 0) {

						if (v_unit.length > 3) {
							showErrorMsg("动作单位最多3个字");
							return;
						}
						if (v_unit == "") {
							layer.msg("请填写以什么单位来计算次数", {
								time : "3000",
								offset : "200px",
								anim : "6"
							});
							return;
						}
					}
				} else {
					layer.msg("请填写动作名称", {
						time : "2000",
						offset : "200px",
						anim : "6"
					});
					return;
				}
				$.ajax({
					type : "post",
					url : "editaction",
					data : {
						"v_name" : v_name,
						"v_unit" : v_unit,
						"i_id" : i_id,
						"deviceid" : $("#deviceid").val()
					},
					dataType : "json",
					error : function(data) {

						var msg = "失败了！系统懵逼中...";
						if (data.responseText.length <= 20) {
							msg = data.responseText;
						}
						layer.close(loding_shade);
						layer.msg(msg, {
							time : "3000",
							offset : "200px",
							anim : "6"
						});
					},
					success : function(data) {

						$(".add_button").show();
						layer.close(loding_shade);
						layer.msg("动作修改成功", {
							time : "2000",
							offset : "200px"
						});
						// TODO 修改成功后如何处理
						$("#action_name_" + i_id).text(data.v_name);
						action_edit.attr({
							"actionname" : data.v_name,
							"actionunit" : data.v_unit
						});
					}
				});
			}
		});
	});
	// 新增动作相应处理
	$('.add_button').on(
			"click",
			function() {
				$(".add_button").hide();
				let content = $("#action_add_panel");
				let actiontypeid = $(this).attr("actiontypeid");
				newActionDialog = layer.open({
					type : 1,
					skin : 'dialog_2button',
					closeBtn : 2,
					content : content,
					area : [ '80%', '300px' ],
					title : false,
					btn : [ '确定' ],
					offset : '100px',
					cancel : function(index, layero) {
						$(".add_button").show();
					},
					yes : function() {

						let v_name = $("#action_name").val();
						let ti_type = $("#action_type").val();
						let v_unit = $("#action_unit_value").val();
						if (v_name != "") {
							if (v_name.length > 10) {
								showErrorMsg("动作名称最多10个字");
								return;
							}
							if (ti_type == 0) {
								if (v_unit == "") {
									layer.msg("请填写以什么单位来计算次数", {
										time : "3000",
										offset : "200px",
										anim : "6"
									});
									return;
								}
							}

						} else {
							layer.msg("请填写动作名称", {
								time : "2000",
								offset : "200px",
								anim : "6"
							});
							return;
						}

						let loding_shade = layer.load(2, {
							shade : [ 0.5, '#000' ]
						});
						$.ajax({
							type : "post",
							url : "newaction",
							data : {
								"v_name" : v_name,
								"ti_type" : ti_type,
								"v_unit" : v_unit,
								"actiontypeid" : actiontypeid,
								"deviceid" : $("#deviceid").val()
							},
							dataType : "json",
							error : function(data) {

								let msg = "失败了！系统懵逼中...";
								if (data.responseText.length <= 20) {
									msg = data.responseText;
								}
								layer.close(loding_shade);
								layer.msg(msg, {
									time : "3000",
									offset : "200px",
									anim : "6"
								});
							},
							success : function(data) {
								$(".add_button").show();
								layer.close(newActionDialog);
								layer.close(loding_shade);
								layer.msg("动作添加成功", {
									time : "2000",
									offset : "200px"
								});
								let action_item;
								if (data.ti_type == 0) {
									action_item = "<div style='display:none;' class='target_in' id='action_" + data.i_id
											+ "'><div class='target_in_dec action_rm'></div><div><span id='action_name_" + data.i_id + "'>" + data.v_name
											+ "</span><br><font id='action_type_" + data.i_id
											+ "'>计次动作</font></div><img class='action_edit' src='/habit/img/edit.png' width='25px' actionid='" + data.i_id + "' actionname='"
											+ data.v_name + "' actiontype='" + data.ti_type + "' actionunit='" + data.v_unit + "' actiontypeid='" + data.actiontypeid + "'></div>";
								} else {
									action_item = "<div style='display:none;' class='target_in' id='action_" + data.i_id
											+ "'><div class='target_in_dec action_time'></div><div><span id='action_name_" + data.i_id + "'>" + data.v_name
											+ "</span><br><font id='action_type_" + data.i_id
											+ "'>计时动作</font></div><img class='action_edit' src='/habit/img/edit.png' width='25px' actionid='" + data.i_id + "' actionname='"
											+ data.v_name + "' actiontype='" + data.ti_type + "' actionunit='" + data.v_unit + "' actiontypeid='" + data.actiontypeid + "'></div>";
								}
								$("#action_list").prepend(action_item);
								$("#action_list div:first").slideDown(200);
								$("#action_name").val("");
								$("#action_type").val("0");
								$("#action_unit_value").val("");
								$("#action_unit").show()
								$(".nulltip").hide();
							}
						});
					}
				});
			});

	// 删除动作相应处理
	$("#action_delete").on("click", function() {

		if (!confirm("删除动作后，该动作下的记录数据也会被删除，你确定要这么做？？！！")) {
			return;
		}
		var a_id = $(this).attr("actionid");
		var loding_shade = layer.load(2, {
			shade : [ 0.5, '#000' ]
		});
		$.ajax({
			type : "post",
			url : "deleteaction",
			data : {
				"a_id" : a_id,
				"deviceid" : $("#deviceid").val()
			},
			dataType : "html",
			error : function(data) {

				layer.close(loding_shade);
				var msg = "失败了！系统懵逼中...";
				if (data.responseText.length <= 20) {
					msg = data.responseText;
				}
				layer.msg(msg, {
					time : "2000",
					offset : "200px",
					anim : "6"
				});
			},
			success : function(data) {
				$(".add_button").show();
				layer.close(loding_shade);
				layer.msg("动作已删除", {
					time : "2000",
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

	ctrlBack("actiontypelist");
});
