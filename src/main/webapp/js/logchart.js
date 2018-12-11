$(function() {
	
	//返回按钮处理事件
	$(".nvabar_back").on("click",function(){
		keyback = true;
		window.history.back();
	});

	if (getQueryString("type") == "1") {
		$("#actionlogchart").show();
		$("#bodylogchart").hide();
	}
	$("#bodylogchart .tab_h50_unselected").on("click", function() {

		$("#actionlogchart").show();
		$("#bodylogchart").hide();
	});
	$("#actionlogchart .tab_h50_unselected").on("click", function() {

		$("#actionlogchart").hide();
		$("#bodylogchart").show();
	});
	$(".logochart_bodydata_item").on("click", function() {

		if ($(this).attr("isJump")=="true") {
			
			toUrl('../chart/bdllist?index=' + $(this).attr("data"));
		}
	});
	$(".target_in").on("click", function() {

		if ($(this).attr("isJump")=="true") {
			
			toUrl('../chart/actionloglist?aid=' + $(this).attr("data"));
		}
	});
	
});
