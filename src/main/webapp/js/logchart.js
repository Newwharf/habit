$(function() {

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("/habit/home");
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

		if ($(this).attr("isJump") == "true") {
			layer.load(2, {
				shade : [ 0.5, '#000' ]
			});
			toUrl('bdllist?type=0&index=' + $(this).attr("data"));
		}
	});
	$(".target_in").on("click", function() {

		if ($(this).attr("isJump") == "true") {
			layer.load(2, {
				shade : [ 0.5, '#000' ]
			});
			toUrl('actionloglist?type=1&aid=' + $(this).attr("data"));
		}
	});
	ctrlBack("/habit/home");
});
