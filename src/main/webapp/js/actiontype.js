$(function() {

	preUrl = "/habit/home?deviceid="+$("#deviceid").val();
	isCtrlBack = true;
	var editActionTypeDialog;
	
	//返回按钮处理事件
	$(".nvabar_back").on("click",function(){
		keyback = true;
		window.history.back();
	});

	// 动作分类添加事件
	$('#target_add').on(
			"click",
			function() {

				let content = $("#actiontype_add_panel");
				let newActionTypeDialog = layer.open({
					type : 1,
					skin : 'dialog_2button',
					closeBtn : 2,
					content : content,
					area : [ '80%', '300px' ],
					title : false,
					btn : [ '确定' ],
					offset : '100px',
					yes : function() {

						let type_name = $("#type_name").val();
						let type_remarks = $("#type_remarks").val();
						if (type_name == "") {
							layer.msg("分类名称还是要有的", {
								time : "2000",
								offset : "200px",
								anim : "6"
							});
							return;
						}
						layer.close(newActionTypeDialog);
						let loding_shade = layer.load(2, {
							shade : [ 0.5, '#000' ]
						});
						$.ajax({
							type : "post",
							url : "/habit/action/newactiontype",
							data : {
								"type_name" : type_name,
								"type_remarks" : type_remarks,
								"deviceid":$("#deviceid").val()
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

								layer.close(loding_shade);
								layer.msg("动作分类添加成功", {
									time : "2000",
									offset : "200px"
								});
								var target_item = "<div style='display:none;' class='target_item' id='action_type_" + data.id + "'><div class='line_space'>"
										+ "<div class='line_space_line'></div>" + "</div>" + "<p style='position: relative;'>"
										+ "<span class='hotarea' style='position: absolute;height: 100%;width: 70%;z-index: 999;' typeid=" + data.id + "></span><span>" + data.name
										+ "</span><br>" + "<font>" + data.remarks + "</font>"
										+ "<img class='actiontype_edit' src='/habit/img/edit.png' width='25px' style='position: absolute; right: 5%; bottom: 8px;' type_id='"
										+ data.id + "' type_name='" + data.name + "' type_remarks='" + data.remarks + "'></p></div>";
								$("#target_msg_list label").hide();
								$(".line_space").show();
								$("#target_msg_list").attr("class", "target_list_nonull");
								$("#target_msg_list").prepend(target_item);
								$("#target_msg_list .target_item:first").slideDown(200);
								$("#type_name").val("");
								$("#type_remarks").val("");
							}
						});
					}
				});
			});

	// 修改分类事件
	$("#target_msg_list").on("click", ".actiontype_edit", function() {

		$("#update_type_name").val($(this).attr("type_name"));
		$("#update_type_remarks").val($(this).attr("type_name"));
		let type_id = $(this).attr("type_id");
		$("#actiontype_delete").attr("type_id", type_id);

		let content = $("#actiontype_edit_panel");
		editActionTypeDialog = layer.open({
			type : 1,
			skin : 'dialog_2button',
			closeBtn : 2,
			content : content,
			area : [ '80%', '300px' ],
			title : false,
			btn : [ '确定' ],
			offset : '100px',
			yes : function() {

				let type_name = $("#update_type_name").val();
				let type_remarks = $("#update_type_remarks").val();

				if (type_name == "") {
					layer.msg("分类名称还是要有的", {
						time : "2000",
						offset : "200px",
						anim : "6"
					});
					return;
				}
				layer.close(editActionTypeDialog);
				let loding_shade = layer.load(2, {
					shade : [ 0.5, '#000' ]
				});
				$.ajax({
					type : "post",
					url : "/habit/action/editactiontype",
					data : {
						"type_name" : type_name,
						"type_remarks" : type_remarks,
						"type_id" : type_id,
						"deviceid":$("#deviceid").val()
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

						layer.close(loding_shade);
						layer.msg("分类已修改", {
							time : "2000",
							offset : "200px"
						});
						$("#action_type_" + type_id + " p span").text(data.name);
						$("#action_type_" + type_id + " p font").text(data.remarks);
						$("#action_type_" + type_id + " p img").attr("type_name", data.name);
						$("#action_type_" + type_id + " p img").attr("type_remarks", data.remarks);
					}
				});
			}
		});
	});

	// 删除事件
	$("#actiontype_delete").on("click", function() {
		if (!confirm("删除分类会同时删除其下所有动作以及动作产生的训练记录！！你确定这么做！！！！！？？？")) {
			return;
		}
		let type_id = $(this).attr("type_id");
		let loding_shade = layer.load(2, {
			shade : [ 0.5, '#000' ]
		});
		$.ajax({
			type : "post",
			url : "/habit/action/deleteactiontype",
			data : {
				"type_id" : type_id,
				"deviceid":$("#deviceid").val()
			},
			dataType : "html",
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

				layer.close(loding_shade);
				layer.close(editActionTypeDialog);
				layer.msg("分类已删除", {
					time : "2000",
					offset : "200px"
				});
				$("#action_type_" + type_id).slideUp(200);
				$("#action_type_" + type_id).remove();
				if ($(".target_item").length == 0) {
					$("#target_msg_list label").show();
					$(".line_space").hide();
					$("#target_msg_list").attr("class", "list_null");
				}

			}
		});

	});

	// 分类点击事件
	$("#target_msg_list").on("click",".hotarea", function() {
		
		$(location).attr('href', 'actionlist?typeid=' + $(this).attr("typeid")+'&deviceid='+$("#deviceid").val());
	});
});
