$(function() {

	var listDialog;
	var dialog_height = 0;

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("/habit/home");
	});

	// 点击确定按钮的处理事件
	$("#id_button_ok").on("click", function() {

		var name = $("#action_name_input").val();
		var actionList = $(".selected_action_list_item_option_value");
		if (name == "") {
			layer.msg("请输入训练名称", {
				time : "2000",
				offset : "200px",
				anim : "6"
			});
			return;
		} else if (name.length > 10) {
			showErrorMsg("训练名称最多10个字");
			return;
		} else if (actionList.length == 0) {
			layer.msg("训练中至少得有一个动作吧", {
				time : "2000",
				offset : "200px",
				anim : "6"
			});
			return;
		} else {
			// 验证通过，开始组装数据
			layer.load(2, {
				shade : [ 0.5, '#000' ]
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
				url : "newplan",
				type : "post",
				data : {
					"planname" : name,
					"actionlist" : JSON.stringify(actionJsonList),
					"deviceid" : $("#deviceid").val()
				},
				dataType : "json",
				error : function(data) {

					layer.msg("失败了！系统懵逼中！", {
						time : "2000",
						offset : "200px",
						anim : "6"
					});
				},
				success : function(data) {
					toUrl('toplandetails?planid=' + data.pid);
				}
			});
		}
	});
	// 删除动作按钮时的处理事件
	$("#selected_action_list").on("click", "img", function() {
		let actionId = $(this).attr("actionid");
		deleteActionFromPage(actionId);
		showAction(actionId);
	});

	// 选择动作时的处理事件
	$("#actionlistul").on("click", "dd", function() {
		let action = {};
		action.actionTypeid = $(this).attr("actionTypeid");
		action.actionTypeName = $(this).attr("actionTypeName");
		action.actionId = $(this).attr("actionId");
		action.actionName = $(this).attr("actionName");
		action.actionType = $(this).attr("actionType");
		addActionToPage(action);
		hideAction(action.actionId);
		closeActionDialog();
	});

	// 添加动作按钮点击事件
	$(".new_button").on("click", function() {
		initActionList();
		openActionDialog();
	});
	ctrlBack("/habit/home");
});

// 打开动作选择弹窗
function openActionDialog() {
	let content = $(".list_panel_dialog");
	listDialog = layer.open({
		type : 1,
		skin : 'dialog_2button',
		closeBtn : 2,
		content : content,
		area : [ '90%', '300px' ],
		title : false,
		offset : '80px'
	});
}

// 关闭动作选择弹窗
function closeActionDialog() {
	layer.close(listDialog);
}

// 初始化带分类的动作列表
function initActionList() {
	if (isLoad()) {
		return;
	}
	showDialogLoding();
	$.ajax({
		url : "../action/ajaxactionlist?deviceid=" + $("#deviceid").val(),
		type : "get",
		dataType : "json",
		error : function(data) {
			layer.close(loadLyer);
			layer.msg("出错了！系统懵逼中！", {
				time : "2000",
				offset : "200px",
				anim : "6"
			});
			closeActionDialog();
		},
		success : function(data) {
			hideDialogLoding();
			if (data.length > 0) {
				for (let i = 0; i < data.length; i++) {
					addActionToDialog(data[i]);
				}
				showDialogActionList();
				layui.use('element', function() {
					var element = layui.element;
				});
				setLoaded();
			} else {
				showDialogNulltip();
				setLoaded();
			}
		}
	});
}

// 添加一个动作到页面
function addActionToPage(action) {
	var selectedActionHtml = "<div id='action_item_" + action.actionId + "' class='selected_action_list_item' style='display:none;'>"
			+ "<div class='selected_action_list_item_name' >" + "<span>" + action.actionName + "</span>" + "<img src='/habit/img/delete.png' width='20px' actionid='"
			+ action.actionId + "' >" + "</div>" + "<div class='line_01'></div>" + "<div class='selected_action_list_item_option' >" + "<span>动作组数</span>"
			+ "<input id='action_item_value_" + action.actionId + "' class='selected_action_list_item_option_value' type='number' actionid='" + action.actionId + "' value='4'>"
			+ "</div>" + "</div>"
	$("#selected_action_list").append(selectedActionHtml);
	$("#action_item_" + action.actionId).slideDown(200);
}

// 从页面删除一个动作
function deleteActionFromPage(actionId) {
	$("#action_item_" + actionId).slideUp(200);
	setTimeout(function() {
		$("#action_item_" + actionId).remove();
	}, 200);
}

// 显示动作列表中的动作
function showAction(actionId) {
	let dd = $("#actiondd_" + actionId);
	let li = $("#actionlistli_" + actionId);
	li.show();
	dd.show();
}

// 隐藏动作列表中的动作
function hideAction(actionId) {
	let dd = $("#actiondd_" + actionId);
	let li = $("#actionlistli_" + actionId);
	let ul = $("#actionlistul");
	dd.hide();
	if (li.find("dd:visible").length == 0) {
		li.hide();
	}
}

// 添加一个动作到动作弹窗列表
function addActionToDialog(action) {
	let ul = $("#actionlistul");
	let li = $("#actionlistli_" + action.actionTypeId);
	if ($("#actionlistli_" + action.actionTypeId).length == 0) {
		ul.append("<li class='layui-nav-item' id='actionlistli_" + action.actionTypeId + "'><a>" + action.actionTypeName + "</a><dl class='layui-nav-child'><dd id='actiondd_"
				+ action.actionId + "' actionId='" + action.actionId + "' actionTypeId='" + action.actionTypeId + "' actionName='" + action.actionName + "' actionTypeName='"
				+ action.actionTypeName + "' actionType='" + action.actionType + "'><a>" + action.actionName + "</a></dd></dl></li>");
	} else {
		$("#actionlistli_" + action.actionTypeId + " dl").append(
				"<dd id='actiondd_" + action.actionId + "' actionId='" + action.actionId + "' actionTypeId='" + action.actionTypeId + "' actionName='" + action.actionName
						+ "' actionTypeName='" + action.actionTypeName + "' actionType='" + action.actionType + "'><a>" + action.actionName + "</a></dd>");
	}
}
// 隐藏动作弹窗加载动画是否显示
function hideDialogLoding() {
	$(".panel_dialog_loding").hide();
}

// 显示动作弹窗加载动画是否显示
function showDialogLoding() {
	$(".panel_dialog_loding").show();
}

// 隐藏动作弹窗中的动作列表
function hideDialogActionList() {
	$("#actionlistul").hide();
}
// 显示动作弹窗中的动作列表
function showDialogActionList() {
	$("#actionlistul").show();
}

// 隐藏动作弹窗中的无数据提示
function hideDialogNulltip() {
	$(".panel_dialog_nulltip").hide();
}

// 显示动作弹窗中的无数据提示
function showDialogNulltip() {
	$(".panel_dialog_nulltip").show();
}

// 设置动作列表为已加载
function setLoaded() {
	$("#actionlistul").attr("loaded", "1");
}

// 设置动作列表为未加载,并清空已加载的动作列表
function setUnload() {
	$("#actionlistul").attr("loaded", "0");
	$("#actionlistul").text("");
}

// 判断动作列表是否已加载，已加载返回true，未加载返回false；
function isLoad() {
	if ($("#actionlistul").attr("loaded") == 0) {
		return false;
	} else {
		return true;
	}
}
