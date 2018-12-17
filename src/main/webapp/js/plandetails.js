var ajaxObj;
var myChart;
var option;
var scoreLogNum=0;
var weightLogNum=0;
var page_index = 0;
var page_offset = 30;
var noPageData = false;
var nextPageLoding = false;
var logListIsHasData = false;

$(function() {
	
	// 返回按钮处理事件
	$(".nvabar_back").on("click",function(){
		keyback = true;
		toUrl("/habit/home");
	});
	
	// 历史记录详情中再试一次按钮点击处理事件
	$("#loglist_panel_error_panel_button").on("click",function(){
			loadActionLogData();
	});
	
	// 历史记录详情列表点击关闭按钮处理事件
	$("#loglist_panel_nav_close").on("click", function() {
		closeLogListPanel();
	});
	
	// 历史记录详情列表滚动条触底处理事件
	$("#loglist_panel").on("touchend", function(e) {
		if (!logListIsHasData) {
			return;
		}
		let panel_h = document.getElementById("loglist_panel").scrollHeight;
		let client_h = $(window).height();
		let scrollTop_h = $("body").scrollTop();
		let scrollBottom_h = panel_h - client_h - scrollTop_h;

		if (scrollBottom_h < 200) {
				nextActionLogData();
		}
	});
	
	// 动作历史记录点击处理时间
	$(".history_data").on("click", function() {
		$(this).attr("id", "selectaction");
		showLogListPanel();
	});
	
	// 次数失去焦点处理事件
	$("#score").on("blur", function() {
				
		// 添加、修改相关操作
		if ($("#plaisrecord").attr("score_isrecord")==1) {
			// 做修改操作
			option.dataset.source[2].splice(scoreLogNum, 1, $(this).val());
			} else {
				// 添加次数
				option.dataset.source[2].push($(this).val());
				if(option.dataset.source[0].length!=option.dataset.source[2].length){
					// 如果时间没添加过，添加事件
					option.dataset.source[0].push(new Date().getTime());
				}
				
				scoreLogNum++;
				$("#plaisrecord").attr("score_isrecord",1);
			}
		// 刷新折线图
		myChart.setOption(option);
		if($("#plaisrecord").attr("score_isrecord")==1&&$("#plaisrecord").attr("weight_isrecord")==1){
			$("#plaisrecord").val(1);
		}
		});
	
	// 负重失去焦点处理事件
	$("#scoreweight").on("blur", function() {

		
		// 添加、修改相关操作
		if ($("#plaisrecord").attr("weight_isrecord")==1) {
			// 做修改操作
			option.dataset.source[1].splice(weightLogNum, 1, $(this).val());
		} else {
			// 添加负重
			option.dataset.source[1].push($(this).val());
			if(option.dataset.source[0].length!=option.dataset.source[1].length){
				// 如果时间没添加过，添加事件
				option.dataset.source[0].push(new Date().getTime());
			}
			weightLogNum++;
			$("#plaisrecord").attr("weight_isrecord",1);
		}
		// 刷新折线图
		myChart.setOption(option);
		if($("#plaisrecord").attr("score_isrecord")==1&&$("#plaisrecord").attr("weight_isrecord")==1){
			$("#plaisrecord").val(1);
		}
	});
	// 列表记录按钮处理事件
	$(".item_50h img").on("click", function() {
		var url;
		var alid = $(this).attr("actionlogid");
		if($(this).attr("isrecord")==1){
			url = "editactionlog";
		}else{
			url = "addactionlog"
		}
		if(myChart!=null){
			// 重置绘图区
			myChart.clear();
		}
		// 设置dialog容器
		var content = $("#recard_dialog");
		// 根据该记录类型与状态设置dialog中的内容
		setRecordDialog($(this));
		// 弹出dialog面板
		var newTargetDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['90%' , '500px'] ,
		title : false ,
		btn : ['确定'] ,
		offset : '40px' ,
		end:function(){
			$("#plaisrecord").val(0);
			$("#plaisrecord").attr("score_isrecord",0);
			$("#plaisrecord").attr("weight_isrecord",0);
		},
		yes : function() {

			if ($("#score").val() == "" || $("#scoreweight").val() == "") {
				showErrorMsg("除了感悟其他必填哦~");
				return;
			}
			var actionlog = {};
			actionlog.plid = $("#plid").val();
			actionlog.aid = $("#aid").val();
			actionlog.num = $("#numbyplan").val();
			actionlog.score = $("#score").val();
			actionlog.scoreweight = $("#scoreweight").val();
			actionlog.comments = $("#comments").val();
			actionlog.alid = alid;
			var layer_load = layer.load(2, {
				shade : [0.5 , '#000']
			});
			$.ajax({
				url : url ,
				type : "post" ,
				data : {
				"plid" : actionlog.plid + "" ,
				"aid" : actionlog.aid + "" ,
				"num" : actionlog.num + "" ,
				"scoreweight" : actionlog.scoreweight + "" ,
				"score" : actionlog.score + "" ,
				"comments" : actionlog.comments,
				"alid":actionlog.alid,
				"deviceid":$("#deviceid").val()
				} ,
				dataType : "html" ,
				error : function(data) {
					layer.close(layer_load);
					showAjaxErrorMsg(data)
				} ,
				success : function(data) {
				window.location.reload();
				}
				});
			
		}
		});
		// 获取该动作的当前记录集
		var layer_load = layer.load(2, {
			shade : [0.5 , '#000']
		});
		$.ajax({
			url : "getalbyp" ,
			type : "post" ,
			data : {
			"pid" : $(this).attr("planid") ,
			"aid" : $(this).attr("actionid") ,
			"num" :  $(this).attr("num"),
			"alid" : $(this).attr("actionlogid")+"",
			"deviceid":$("#deviceid").val()
			} ,
			dataType : "json" ,
			error : function(data) {
				layer.close(layer_load);
				
			} ,
			success : function(data) {
				layer.close(layer_load);
				let dataset={source:[["时间"],["负重"],["次数"]]};
				
				for(let i=0;i<data.length;i++){
					
					if(data[i].lognum!=null){
						weightLogNum = data[i].lognum;
						scoreLogNum = data[i].lognum;
					}else{
						dataset.source[1].push(data[i].scoreweight);
						dataset.source[2].push(data[i].score);
					}
				}
				
				// 将时间固定成5
				for(let i=0;i<5;i++){
					dataset.source[0].push(i);
				}
				
				
				// 设置偏移量与视图区宽度
				let 	grid_w = content.width();
				let grid_leftOffSet = 0;
				
				switch(dataset.source[1].length-1)
				{
				    case 3:
				        if(scoreLogNum==-1||scoreLogNum==3){
				        		grid_leftOffSet=-20;
				        }
				        break;
				    case 4:
				    		if(scoreLogNum==-1||scoreLogNum==4){
				    			grid_leftOffSet=-30;
				        }else if(scoreLogNum==3){
				        		grid_leftOffSet=-20;
				        }
				        break;
				    case 5:
				    	if(scoreLogNum==-1||scoreLogNum==5){
				    			grid_leftOffSet=-60;
				        }else if(scoreLogNum==3){
				        		grid_leftOffSet=-20;
				        }else if(scoreLogNum==4){
				        		grid_leftOffSet=-30;
				        }
				        break;
				    default:
				}
				
				
				// 获取echarts绘图区
				myChart = echarts.init($("#dialog_chart").get(0));
				
				// 设置绘图区宽度
				$("#chart").width(content.width());
				// 设置echarts参数
				option = dialogBaseData(dataset, grid_w);
				option.grid.left=grid_leftOffSet+"%";
				// 绘制图表
				myChart.setOption(option);
			}
			});
		
	});
	ctrlBack("/habit/home");
	$("#button_es").on("click", function() {
		if(!confirm("确定要结束训练")){
			return;
		}
		if ($(this).attr("state") == 0) {
			startPlan($(this).attr("planid"));
		} else {
			endPlan($(this).attr("planid"), $(this).attr("planlogid"));
		}
	});
	$("#editplan").on("click", function() {

		layer.load(2, {
			shade : [0.5 , '#000']
		});
		toUrl("toeditplan?pid=" + $(this).attr("planid"));
	});
	$("#deleteplan").on("click", function() {

		if (!confirm("确定要删除训练？放心，动作记录数据不会被删除")) {
			return;
		}
		deletePlan($(this).attr("planid"));
	});
	$("#moreplan").on("click", function() {

	});
});
function setRecordDialog(img_obj) {

	var actiontype = img_obj.attr("actiontype");
	var isrecord = img_obj.attr("isrecord");
	var actionid = img_obj.attr("actionid");
	var planlogid = img_obj.attr("planlogid");
	var num = img_obj.attr("num");
	var actionunit = img_obj.attr("actionunit");
	var score = img_obj.attr("score");
	var scoreweight = img_obj.attr("scoreweight");
	var comments = img_obj.attr("comments");
	$("#actiontype").val(actiontype);
	$("#numbyplan").val(num);
	$("#aid").val(actionid);
	$("#plid").val(planlogid);
	$("#plaisrecord").val(isrecord);
	if(isrecord=="1"){
		$("#plaisrecord").attr("weight_isrecord",1);
		$("#plaisrecord").attr("score_isrecord",1);
	}
	if (actiontype == "0") {
		$("#score_unit").text(actionunit);
		$("#score_title").text("次数")
	} else {
		$("#score_unit").text("秒");
		$("#score_title").text("持续")
	}
	if (isrecord = 1) {
		// 如果该日志已记录过
		$("#scoreweight").val(scoreweight);
		$("#score").val(score);
		$("#comments").val(comments);
	}
}


function addActionLog(actionlog) {

	$.ajax({
	url : "addactionlog" ,
	type : "post" ,
	data : {
	"plid" : actionlog.plid + "" ,
	"aid" : actionlog.aid + "" ,
	"num" : actionlog.num + "" ,
	"scoreweight" : actionlog.scoreweight + "" ,
	"score" : actionlog.score + "" ,
	"comments" : actionlog.comments,
	"deviceid":$("#deviceid").val()
	} ,
	dataType : "html" ,
	error : function(data) {

		alert("error");
	} ,
	success : function(data) {

		alert("success");
	}
	});
}
function deletePlan(pid) {

	var layer_load = layer.load(2, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "deleteplan" ,
	type : "post" ,
	data : {
		"pid" : pid,
		"deviceid":$("#deviceid").val()
	} ,
	dataType : "html" ,
	error : function(data) {

		layer.close(layer_load);
		showAjaxErrorMsg(data.responseText);
	} ,
	success : function(data) {

		toUrl("/habit/home");
	}
	});
}
function endPlan(pid, plid) {

	var layer_load = layer.load(2, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "endplan" ,
	type : "post" ,
	data : {
	"pid" : pid ,
	"plid" : plid,
	"deviceid":$("#deviceid").val()
	} ,
	dataType : "html" ,
	error : function(data) {

		layer.close(layer_load);
		showErrorMsg(data.responseText);
	} ,
	success : function(data) {

		showToastMsg("训练已结束");
		window.location.reload()
	}
	});
}
function startPlan(pid) {

	var layer_load = layer.load(2, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "startplan" ,
	type : "post" ,
	data : {
		"pid" : pid,
		"deviceid":$("#deviceid").val()
	} ,
	dataType : "json" ,
	error : function(data) {

		layer.close(layer_load);
		showAjaxErrorMsg(data.responseText);
	} ,
	success : function(data) {

		showToastMsg("训练已开始");
		window.location.reload()
	}
	});
}

function setGridOffSet(option,size,center){
	var offSet = ((center+1)/size).toString().Number(15.7784514000.toString().match(/^\d+(?:\.\d{0,2})?/));
}

// 加载下一页动作训练历史记录，加载条数为page_offset
function nextActionLogData() {
	if (noPageData || nextPageLoding) {
		return;
	}
	nextPageLoding = true;
	ajaxObj = $.ajax({
		url : "../chart/actionloglistbypid",
		type : "post",
		dataType : "json",
		data : {
			"aid" : $("#selectaction").attr("actionid"),
			"pid":$("#selectaction").attr("planid"),
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
		$(".list_panel").hide();
		$(".bottom_black_button").hide();
			loadActionLogData();
	});
}

// 关闭二级页面，有过渡动画
function closeLogListPanel() {
	$(".list_panel").show();
	$(".bottom_black_button").show();
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


// 加载动作训练历史记录，本方法为初次加载，加载条数为page_offset
function loadActionLogData() {
	page_index = 0;
	hideLogListErrorPanel();
	hideLogListNodataPanel();
	showLogListLodingPanel();

	ajaxObj = $.ajax({
		url : "../chart/actionloglistbypid",
		type : "post",
		dataType : "json",
		data : {
			"aid" : $("#selectaction").attr("actionid"),
			"deviceid" : $("#deviceid").val(),
			"pid":$("#selectaction").attr("planid"),
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

// 清除二级页面相关数据
function clearLogListPanel() {
	if(ajaxObj!=null){
		ajaxObj.abort();
	}
	logListIsHasData = false;
	$("#selectaction").removeAttr("id");
	$(".loglist_panel_noimg").remove();
	noPageData = false;
	nextPageLoding = false;
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
