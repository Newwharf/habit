$(function() {

	var myChart;
	var option;
	var logNum=0;
	// 负重失去焦点处理事件
	$("#scoreweight").on("blur", function() {

		var x_data = option.xAxis.data;
		var y_data = option.series[0].data;
		// TODO 添加之后的处理，修改之后的相关处理还未完成
		if ($("#plaisrecord").val()==1) {
			// 做修改操作
			y_data.splice(logNum-1, 1, $(this).val());
			updateData(myChart, x_data,y_data)
		} else {
			// 得到当前时间
			var date = new Date();
			var dateStr = date.getMonth() + "." + date.getDay() + " " + date.getHours() + ":" + date.getMinutes();
			// 做添加操作
			addData(myChart, option, $(this).val(),dateStr);
			logNum++;
			$("#plaisrecord").val("1");
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
			var layer_load = layer.load(1, {
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
				"alid":actionlog.alid
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
		var layer_load = layer.load(1, {
			shade : [0.5 , '#000']
		});
		$.ajax({
			url : "getalbyp" ,
			type : "post" ,
			data : {
			"pid" : $(this).attr("planid") ,
			"aid" : $(this).attr("actionid") ,
			"num" :  $(this).attr("num"),
			"alid" : $(this).attr("actionlogid")+""
			} ,
			dataType : "json" ,
			error : function(data) {
				layer.close(layer_load);
				
			} ,
			success : function(data) {
				layer.close(layer_load);
				var xData = [];
				var yData = [];
				
				for(var i=0;i<data.length;i++){
					if(data[i].lognum!=null){
						logNum = data[i].lognum;
					}else{
						var logData = [data[i].date,data[i].scoreweight]; 
						xData.push(data[i].date);
						yData.push(data[i].scoreweight);
					}
				}
				// 获取echarts绘图区
				myChart = echarts.init($("#dialog_chart").get(0));
				
				// 设置绘图区宽度
				$("#chart").width(content.width());
				// 设置echarts参数
				option = dialogBaseData({"x":xData,"y":yData}, content.width());
				// 设置绘图区grid偏移
				var leftOffSet = ((2-(logNum==-1?4:logNum))*25);
				option.grid.left=leftOffSet+"%";
				// 绘制图表
				myChart.setOption(option);
			}
			});
		
	});
	ctrlBack("/habit/home");
	$("#button_es").on("click", function() {

		if ($(this).attr("state") == 0) {
			startPlan($(this).attr("planid"));
		} else {
			endPlan($(this).attr("planid"), $(this).attr("planlogid"));
		}
	});
	$("#editplan").on("click", function() {

		layer.load(1, {
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
	"comments" : actionlog.comments
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

	var layer_load = layer.load(1, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "deleteplan" ,
	type : "post" ,
	data : {
		"pid" : pid
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

	var layer_load = layer.load(1, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "endplan" ,
	type : "post" ,
	data : {
	"pid" : pid ,
	"plid" : plid
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

	var layer_load = layer.load(1, {
		shade : [0.5 , '#000']
	});
	$.ajax({
	url : "startplan" ,
	type : "post" ,
	data : {
		"pid" : pid
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
