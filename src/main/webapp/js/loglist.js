$(function() {

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("logchart?type=" + getQueryString("type"));
	});

	$(".remark").on("click", function() {

		var remark = $(this).attr("alt");
		var content = $(".information_panel");
		$(".information_panel p").text(remark);
		var newTargetDialog = layer.open({
			type : 1,
			skin : 'dialog_2button',
			closeBtn : 2,
			content : content,
			area : [ '80%', '300px' ],
			title : false,
			btn : [ '确定' ],
			offset : '100px',
			yes : function() {

				layer.close(newTargetDialog);
			}
		});
	});
	ctrlBack("logchart?type=" + getQueryString("type"));
});
