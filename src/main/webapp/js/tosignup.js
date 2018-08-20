$(function() {

	$("#famale").on("click", function() {

		$("#famale").css("display", "none");
		$("#famale-h").css("display", "inline");
		$("#male-h").css("display", "none");
		$("#male").css("display", "inline");
		$("#sex").val(1);
	});
	$("#male").on("click", function() {

		$("#male-h").css("display", "inline");
		$("#male").css("display", "none");
		$("#famale").css("display", "inline");
		$("#famale-h").css("display", "none");
		$("#sex").val(0);
	});
	// 禁止图片放大缩小
	$("#famale-h").on("click", function() {

	});
	$("#male-h").on("click", function() {

	});
	// 获取文档高度
	var window_body_height = $(document.body).height();
	// 获取输入框焦点时，重置提示内容
	$('#input-39').on('focus', function() {

		$('#temp').attr("data-content", "单位：岁");
	});
	// 输入框字符串改变时根据输入值显示不同的提示
	$("#input-39").keyup(function() {

		var input_val = $('#input-39').val();
		if (input_val == "00") {
			$('#input-39').val("0");
		} else if (input_val.length > 1 && input_val.indexOf("0") == 0) {
			$('#input-39').val(input_val.slice(1));
		} else if (input_val < 0) {
			$('#temp').attr("data-content", "我们尚不支持时间以外的用户");
			$('#input-39').val("0");
		} else if (input_val > 99) {
			$('#temp').attr("data-content", "为了您的健康着想，我们仅支持100岁以下的用户");
			$('#input-39').val("99");
		}
	});
	// 输入框失去焦点时显示提交按钮
	$("#input-39").on("blur", function() {

		$("#id_button_ok").show();
	});
	// 解决输入法遮挡
	$(window).resize(function() {

		if ($(document.body).height() < window_body_height) {
			$("#id_button_ok").hide();
			var scrollHeight = $('body').prop("scrollHeight");
			$('body').scrollTop($('#input-39').offset().top);
		} else if (($(document.body).height() + 10) >= window_body_height) {
			$("#id_button_ok").show();
		}
	});
});
