$(function() {

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
			layer.load(1, {
				shade : [0.5 , '#000']
			});
			$(location).attr('href', '../chart/bdllist?index=' + $(this).attr("data"));
		}
	});
	$(".target_in").on("click", function() {

		if ($(this).attr("isJump")=="true") {
			layer.load(1, {
				shade : [0.5 , '#000']
			});
			$(location).attr('href', '../chart/actionloglist?aid=' + $(this).attr("data"));
		}
	});
	ctrlBack("../home");
});
