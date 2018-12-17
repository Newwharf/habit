var page_index = 0;
var page_offset = 20;
var noPageData = false;
var nextPageLoding = false;
var logListIsHasData = false;
var mainSwiperScrollTop;
var swiperIndex = 0;
var ajaxObj;
var mainSwiper;

$(function() {
	// 注册swiper
	mainSwiper = new Swiper('#mainswiper', {
		autoplay : false,
		direction : "horizontal",
		autoHeight : true,
		on : {
			slideChange : function() {
				if (swiperIndex == 0) {
					swiperIndex = 1;
					showRightNav();
					initActionList();
				} else {
					swiperIndex = 0;
					showLeftNav();
				}
			}
		}
	});

	// 再试一次按钮点击处理事件
	$(".error_panel_button").on("click", function() {
		initActionList();
	});
	
	//历史记录详情中再试一次按钮点击处理事件
	$("#loglist_panel_error_panel_button").on("click",function(){
		if ($("#selectbodydata").length > 0) {
			loadBodyDataLogData();
		} else if ($("#selectaction").length > 0) {
			loadActionLogData();
		}
	});

	// 历史记录详情列表点击关闭按钮处理事件
	$("#loglist_panel_nav_close").on("click", function() {
		closeLogListPanel();
	});

	// 历史记录详情列表滚动条触底处理事件
	$("#loglist_panel").on("touchend", function(e) {
		// TODO 判断当前是何种类型的历史记录
		if (!logListIsHasData) {
			return;
		}
		let panel_h = document.getElementById("loglist_panel").scrollHeight;
		let client_h = $(window).height();
		let scrollTop_h = $("body").scrollTop();
		let scrollBottom_h = panel_h - client_h - scrollTop_h;

		if (scrollBottom_h < 200) {
			if ($("#selectaction").length > 0) {
				nextActionLogData();
			} else if ($("#selectbodydata").length > 0) {
				nextBodyDataLogData();
			}

		}
	});

	// 动作点击处理事件
	$("#actionlogchart").on("click", ".list_panel_noimg_item", function() {
		$(this).attr("id", "selectaction");
		showLogListPanel();
	});

	// 身体指标点击处理事件
	$(".logochart_bodydata_item").on("click", function() {
		if ($(this).attr("isJump") == "true") {
			$(this).attr("id", "selectbodydata");
			showLogListPanel();
		} else {
			showToastMsg("在个人信息中更新对应指标，才会产生记录");
		}
	});

	// 点击导航栏左边按钮处理事件
	$("#leftnav").on("click", function() {
		if (swiperIndex == 1) {
			mainSwiper.slidePrev(300, true);
		}
	});
	// 点击导航栏右边按钮处理事件
	$("#rightnav").on("click", function() {
		if (swiperIndex == 0) {
			mainSwiper.slideNext(300, true);
		}
	});

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("/habit/home");
	});

	ctrlBack("/habit/home");
});

// 将一级页面左侧导航设为被选状态，包含过渡动画
function showLeftNav() {
	$(".tab_h50_line").animate({
		left : "0"
	}, "fast");
	$("#leftnav").attr("class", "tab_h50_selected");
	$("#rightnav").attr("class", "tab_h50_unselected");

}

// 将一级页面右侧导航设为被选状态，包含过渡动画
function showRightNav() {
	$(".tab_h50_line").animate({
		left : "50%"
	}, "fast");
	$("#rightnav").attr("class", "tab_h50_selected");
	$("#leftnav").attr("class", "tab_h50_unselected");
}

// 初始化一级页面动作列表，一次性加载多有动作
function initActionList() {
	if (getInit() == 1) {
		return;
	} else {
		setInited();
		showLodingPanel();
		hideNodataPanel();
		hideErrorPanel();
		$.ajax({
			url : "../action/ajaxactionlist?deviceid=" + $("#deviceid").val(),
			type : "get",
			dataType : "json",
			error : function(data) {
				setUnInit();
				hideLodingPanel();
				showErrorPanel();
			},
			success : function(data) {
				setInited();
				if (data.length > 0) {
					for (let i = 0; i < data.length; i++) {
						addActionToPanel(data[i]);
					}
				} else {
					hideLodingPanel();
					showNodataPanel();
				}
			}
		});
	}
	$(".swiper-wrapper").css({"height":"auto"});
	
}

// 将每条动作append到页面上
function addActionToPanel(action) {
	let list_panel = $("#list_panel_" + action.actionTypeId);
	if (list_panel.length == 0) {
		let actionHtml = '<div class="list_panel_noimg" id="list_panel_' + action.actionTypeId + '"><div class="list_panel_noimg_title">' + action.actionTypeName
				+ '</div><div class="line_dashed"></div><div class="list_panel_noimg_item" actionId="' + action.actionId + '">' + action.actionName + '</div></div>'
		$("#actionlogchart").append(actionHtml);
	} else {
		let actionHtml = '<div class="line_dashed"></div><div class="list_panel_noimg_item" actionId="' + action.actionId + '">' + action.actionName + '</div>';
		list_panel.append(actionHtml);
	}
	hideLodingPanel();
	hideNodataPanel();
	hideErrorPanel();
}

// 显示一级页面加载动画面板
function showLodingPanel() {
	$(".loding_panel").show();
}
// 隐藏一级页面加载动画面板
function hideLodingPanel() {
	$(".loding_panel").hide();
}
// 显示一级页面无数据提示面板
function showNodataPanel() {
	$(".nodata_panel").show();
}
// 隐藏一级页面无数据提示面板
function hideNodataPanel() {
	$(".nodata_panel").hide();
}
// 显示一级页面错误提示面板
function showErrorPanel() {
	$(".error_panel").show();
}
// 隐藏一级页面错误提示面板
function hideErrorPanel() {
	$(".error_panel").hide();
}
// 设置一级页面动作加载状态为已加载
function setInited() {
	$("#actionlogchart").attr("init", "1");
}
// 设置一级页面动作加载状态为未加载
function setUnInit() {
	$("#actionlogchart").attr("init", "0");
}
// 获得一级页面动作加载状态，0未加载，1已加载
function getInit() {
	return $("#actionlogchart").attr("init");
}
// 显示二级页面加载动画面板
function showLogListLodingPanel() {
	$("#loglist_panel_loding_panel").show();
}
// 隐藏二级页面加载动画面板
function hideLogListLodingPanel() {
	$("#loglist_panel_loding_panel").hide();
}
// 显示二级页面无数据提示面板
function showLogListNodataPanel() {
	$("#loglist_panel_nodata_panel").show();
}
// 隐藏二级页面无数据提示面板
function hideLogListNodataPanel() {
	$("#loglist_panel_nodata_panel").hide();
}
// 显示二级页面错误提示面板
function showLogListErrorPanel() {
	$("#loglist_panel_error_panel").show();
}
// 隐藏二级页面错误提示面板
function hideLogListErrorPanel() {
	$("#loglist_panel_error_panel").hide();
}
// 显示二级页面，有过渡动画
function showLogListPanel() {
	mainSwiperScrollTop = $("body").scrollTop();
	let logListPanel = $("#loglist_panel");
	logListPanel.css({
		"top" : "100%",
		"z-index" : "99999"
	});
	$("#loglist_panel_nav_close").hide();
	$("#loglist_panel_nav").css({
		"position" : "absolute"
	});
	logListPanel.show();
	logListPanel.animate({
		top : "0"
	}, 300, function() {
		logListPanel.css({
			"position" : "absolute"
		});
		$("#loglist_panel_nav").css({
			"position" : "fixed"
		});
		logListPanel.css({
			"height" : "auto"
		});
		$("#loglist_panel_nav_close").fadeIn("normal");
		$("#mainswiper").height("0");
		if ($("#selectbodydata").length > 0) {
			loadBodyDataLogData();
		} else if ($("#selectaction").length > 0) {
			loadActionLogData();
		}

	});
}

// 关闭二级页面，有过渡动画
function closeLogListPanel() {
	$("#mainswiper").height("auto");
	let logListPanel = $("#loglist_panel");
	logListPanel.css({
		"position" : "fixed"
	});
	$("#loglist_panel_nav").css({
		"position" : "absolute"
	});
	logListPanel.css({
		"height" : "100%"
	});
	$("body").scrollTop(mainSwiperScrollTop);
	logListPanel.animate({
		top : "100%"
	}, 300, function() {
		logListPanel.hide();
		clearLogListPanel();
		hideLogListErrorPanel();
		hideLogListNodataPanel();
		showLogListLodingPanel();
	});
}

// 清除二级页面相关数据
function clearLogListPanel() {
	if(ajaxObj!=null){
		ajaxObj.abort();
	}
	logListIsHasData = false;
	$("#selectaction").removeAttr("id");
	$("#selectbodydata").removeAttr("id");
	$(".loglist_panel_noimg").remove();
	noPageData = false;
	nextPageLoding = false;
}

// 加载动作训练历史记录，本方法为初次加载，加载条数为page_offset
function loadActionLogData() {
	page_index = 0;
	hideLogListErrorPanel();
	hideLogListNodataPanel();
	showLogListLodingPanel();

	ajaxObj = $.ajax({
		url : "../chart/actionloglistbyaid",
		type : "post",
		dataType : "json",
		data : {
			"aid" : $("#selectaction").attr("actionId"),
			"deviceid" : $("#deviceid").val(),
			"page_index" : page_index,
			"page_offset" : page_offset
		},
		error : function(data) {
			hideLogListLodingPanel();
			showLogListErrorPanel();
		},
		success : function(data) {
			page_index += data.length;
			if (data.length > 0) {
				for (let i = 0; i < data.length; i++) {
					addActionLogToPanel(data[i]);
				}
				logListIsHasData = true;
				if (data.length < page_offset) {
					noPageData = true;
				}
			} else {
				showLogListNodataPanel();
				hideLogListLodingPanel();
			}
		}
	});
}

// 加载下一页动作训练历史记录，加载条数为page_offset
function nextActionLogData() {
	if (noPageData || nextPageLoding) {
		return;
	}
	nextPageLoding = true;
	ajaxObj = $.ajax({
		url : "../chart/actionloglistbyaid",
		type : "post",
		dataType : "json",
		data : {
			"aid" : $("#selectaction").attr("actionId"),
			"deviceid" : $("#deviceid").val(),
			"page_index" : page_index,
			"page_offset" : page_offset
		},
		error : function(data) {
			showErrorMsg("加载下一页时发生错误");
		},
		success : function(data) {
			page_index += data.length;
			if (data.length > 0) {
				for (let i = 0; i < data.length; i++) {
					addActionLogToPanel(data[i]);
				}
				$("body").scrollTop($("body").scrollTop() + 50);
				if (data.length < page_offset) {
					noPageData = true;
				}
			} else {
				showToastMsg("没有更多数据了");
				noPageData = true;
			}
		}
	});
}

// 将一条动作训练历史记录append到二级页面中
function addActionLogToPanel(log) {
	hideLogListLodingPanel();
	hideLogListErrorPanel();
	hideLogListNodataPanel();

	let log_panel = $("#loglist_panel_noimg_" + log.planLogId);
	if (log_panel.length == 0) {
		let logHtml;
		if (log.actionType == 0) {
			logHtml = '<div class="loglist_panel_noimg" id="loglist_panel_noimg_' + log.planLogId
					+ '"><div class="loglist_panel_noimg_title"><span class="loglist_panel_noimg_title_main">' + log.planLogCdate
					+ '</span><br><span class="loglist_panel_noimg_title_sub">' + log.planName
					+ '</span></div><div class="line_dashed"></div><div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.actionLogId
					+ '"><span class="loglist_panel_noimg_item_main">' + log.actionLogScoreweight + 'kg/' + log.actionLogScorenum + log.actionUnit
					+ '</span><br><span class="loglist_panel_noimg_item_sub">' + (log.actionLogComments == '' ? '——' : log.actionLogComments) + '</span></div></div>';
		} else {
			logHtml = '<div class="loglist_panel_noimg" id="loglist_panel_noimg_' + log.planLogId
					+ '"><div class="loglist_panel_noimg_title"><span class="loglist_panel_noimg_title_main">' + log.planLogCdate
					+ '</span><br><span class="loglist_panel_noimg_title_sub">' + log.planName
					+ '</span></div><div class="line_dashed"></div><div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.actionLogId
					+ '"><span class="loglist_panel_noimg_item_main">' + log.actionLogScoreweight + 'kg/' + log.actionLogScoretime
					+ '秒</span><br><span class="loglist_panel_noimg_item_sub">' + (log.actionLogComments == '' ? '——' : log.actionLogComments) + '</span></div></div>';
		}
		$("#loglist_panel").append(logHtml);
	} else {
		let logHtml;
		if (log.actionType == 0) {
			logHtml = '<div class="line_dashed"></div><div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.actionLogId
					+ '"><span class="loglist_panel_noimg_item_main">' + log.actionLogScoreweight + 'kg/' + log.actionLogScorenum + log.actionUnit
					+ '</span><br><span class="loglist_panel_noimg_item_sub">' + (log.actionLogComments == '' ? '——' : log.actionLogComments) + '</span></div>';
		} else {
			logHtml = '<div class="line_dashed"></div><div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.actionLogId
					+ '"><span class="loglist_panel_noimg_item_main">' + log.actionLogScoreweight + 'kg/' + actionLogScoretime
					+ '秒</span><br><span class="loglist_panel_noimg_item_sub">' + (log.actionLogComments == '' ? '——' : log.actionLogComments) + '</span></div>';
		}
		log_panel.append(logHtml);
	}
}

// 加载身体指标历史记录，本方法为初次加载，加载条数为page_offset
function loadBodyDataLogData() {
	page_index = 0;
	hideLogListErrorPanel();
	hideLogListNodataPanel();
	showLogListLodingPanel();

	ajaxObj= $.ajax({
		url : "../chart/bdllist",
		type : "post",
		dataType : "json",
		data : {
			"index" : $("#selectbodydata").attr("data"),
			"deviceid" : $("#deviceid").val(),
			"page_index" : page_index,
			"page_offset" : page_offset
		},
		error : function(data) {
			hideLogListLodingPanel();
			showLogListErrorPanel();
		},
		success : function(data) {
			page_index += data.length;
			if (data.length > 0) {
				for (let i = 0; i < data.length; i++) {
					addBodyDataLogToPanel(data[i]);
				}
				logListIsHasData = true;
				if (data.length < page_offset) {
					noPageData = true;
				}
			} else {
				showLogListNodataPanel();
				hideLogListLodingPanel();
			}
		}
	});
}

// 下一页身体指标历史记录，本方法为初次加载，加载条数为page_offset
function nextBodyDataLogData() {
	if (noPageData || nextPageLoding) {
		return;
	}
	nextPageLoding = true;

	ajaxObj=$.ajax({
		url : "../chart/bdllist",
		type : "post",
		dataType : "json",
		data : {
			"index" : $("#selectbodydata").attr("data"),
			"deviceid" : $("#deviceid").val(),
			"page_index" : page_index,
			"page_offset" : page_offset
		},
		error : function(data) {
			showErrorMsg("加载下一页时发生错误");
		},
		success : function(data) {
			page_index += data.length;
			if (data.length > 0) {
				for (let i = 0; i < data.length; i++) {
					addBodyDataLogToPanel(data[i]);
				}
				$("body").scrollTop($("body").scrollTop() + 50);
				if (data.length < page_offset) {
					noPageData = true;
				}
			} else {
				showToastMsg("没有更多数据了");
				noPageData = true;
			}
		}
	});
}

// 将一条身体指标历史记录append到二级页面中
function addBodyDataLogToPanel(log) {
	hideLogListLodingPanel();
	hideLogListErrorPanel();
	hideLogListNodataPanel();
	let unit = "cm";
	if (log.tiIndex == 1) {
		unit = "kg";
	} else if (log.tiIndex == 2) {
		unit = "%";
	}
	let date = new Date(log.dtCdate);
	let dateStr = date.getMonth()+"月"+date.getDay()+"日 "+date.getHours()+":"+date.getMinutes();
	if ($("#loglist_panel_noimg_" + log.tiIndex).length == 0) {
		let logHtml = '<div class="loglist_panel_noimg" id="loglist_panel_noimg_' + log.tiIndex
				+ '"><div class="line_dashed"></div><div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.iId
				+ '"><span class="loglist_panel_noimg_item_main">' + log.fScore + unit + '</span><br><span class="loglist_panel_noimg_item_sub">' + dateStr
				+ '</span></div></div>';
		$("#loglist_panel").append(logHtml);
	} else {
		let logHtml = '<div class="loglist_panel_noimg_item" id="loglist_panel_noimg_item_' + log.iId + '"><span class="loglist_panel_noimg_item_main">' + log.fScore + unit
				+ '</span><br><span class="loglist_panel_noimg_item_sub">' + dateStr + '</span></div>';
		$("#loglist_panel_noimg_" + log.tiIndex).append(logHtml);
	}
	$(".line_dashed").eq(0).remove();

}