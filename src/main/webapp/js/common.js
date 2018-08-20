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

	$(location).attr('href', url);
}
function showToastMsg(msg) {

	layer.msg(msg, {
	time : "2000" ,
	offset : "200px"
	});
}
// 替换后退事件开始
window.addEventListener("popstate", function(e) {

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
