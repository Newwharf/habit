Array.prototype.indexOf = function(val) {

	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {

	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
var preUrl = "#";
var isCtrlBack = false;
var keyback = true;
function showAjaxErrorMsg(msg) {

	if (msg.length <= 20) {
		msg = data.responseText;
	} else {
		msg = "失败了！系统懵逼中！"
	}
	layer.msg(msg, {
	time : "2000" ,
	offset : "200px" ,
	anim : "6"
	});
}
function showErrorMsg(msg) {

	layer.msg(msg, {
	time : "2000" ,
	offset : "200px" ,
	anim : "6"
	});
}
function toUrl(url) {
	if(url.indexOf("?")>-1){
		$(location).attr('href', url+'&deviceid='+$("#deviceid").val());
	}else{
		$(location).attr('href', url+'?deviceid='+$("#deviceid").val());
	}
	
}
function showToastMsg(msg) {

	layer.msg(msg, {
	time : "2000" ,
	offset : "200px"
	});
}
// 替换后退事件开始
window.addEventListener("popstate", function(e) {
	
	if(keyback){
		let more_panel_body = $("#more_panel_body");
		let information_panel = $(".information_panel");
		let clip_div = $("#clip_div");
		let recard_dialog = $("#recard_dialog");
		let list_panel_dialog = $(".list_panel_dialog");
		
		if(more_panel_body.length>0){
			if(more_panel_body.is(':visible')){
				$("#more_panel_close").click();
				return;
			}
		}if(information_panel.length>0){
			if(information_panel.is(':visible')){
				$(".layui-layer-close").click();
				return;
			}
		}if(clip_div.length>0){
			if(clip_div.is(':visible')){
				$("#clip_cancel").click();
				return;
			}
		}if(recard_dialog.length>0){
			if(recard_dialog.is(':visible')){
				$(".layui-layer-close").click();
				return;
			}
		}if(list_panel_dialog.length>0){
			if(list_panel_dialog.is(':visible')){
				$(".layui-layer-close").click();
				return;
			}
			$(".layui-layer-close").click();
			return;
		}
	}
	
	if (isCtrlBack) {
		toUrl(preUrl);
	}
}, false);

function pushHistory() {

	if (isCtrlBack) {
		var state = {
		title : "title" ,
		url : "#"
		};
		window.history.pushState(state, "title", "#");
	}
}
// 替换后退事件结束
function ctrlBack(url) {

	isCtrlBack = true;
	preUrl = url;
	pushHistory();
}

//取得指定get参数
function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
