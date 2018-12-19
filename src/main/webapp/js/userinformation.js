$(function() {
	var cropper;

	// 返回按钮处理事件
	$(".nvabar_back").on("click", function() {
		keyback = true;
		toUrl("/habit/home");
	});

	$("#headimg").on("click", function() {
		$("#headimgfile").click();
	});

	$("#clip_ok").on("click", function() {
		hideLoding();
		let imgdata = cropper.getCroppedCanvas({
			width : 85,
			height : 85,
			imageSmoothingQuality : "high"
		});
		let base64url = imgdata.toDataURL('image/jpeg');
		$("#headimg").attr("src", base64url);
		$("#headimg").attr("trueSrc", base64url);
		$("#clip_img").attr("src", "");
		$("#clip_div").hide();
		cropper.destroy();
	});

	$("#clip_cancel").on("click", function() {
		hideLoding();
		$("#clip_img").attr("src", "");
		$("#clip_div").hide();
		cropper.destroy();
	});

	$("#headimgfile").on("change", function() {
		showLoding();
		let file = document.getElementById("headimgfile").files[0];
		let reader = new FileReader();
		reader.onload = function(e) {
			$("#clip_img").attr("src", e.target.result);
			cropper = new Cropper(document.getElementById("clip_img"), {
				aspectRatio : 1 / 1,
				viewMode : 1,
				guides : false,
				background : false,
				toggleDragModeOnDblclick : false,
				dragMode : "move"
			});
			hideLoding();
			$("#clip_div").show();
		};
		reader.readAsDataURL(file);
	});

	$("[type='number']").on("change", function() {
		if($(this).attr("history_val")!=$(this).val()){
			$(this).next().val(1);
		}
	});
	$("form").submit(function() {

		if ($("#username").val().length < 1 || $("#username").val().length > 10) {
			showErrorMsg("名称应在1~5个字的范围");
			return false;
		} else if ($("#birthday").val() == "") {
			showErrorMsg("没有生日是不行的！");
			return false;
		} else if ($("#height").val() != "" && ($("#height").val() <= 50 || $("#height").val() >= 250)) {
			showErrorMsg("我没有歧视的意思，但你这个身高怕是不适合健身");
			return false;
		} else if ($("#weight").val() != "" && ($("#weight").val() <= 30 || $("#weight").val() >= 150)) {
			showErrorMsg("你这个体重先去医院吧朋友！");
			return false;
		} else if ($("#bodyfat").val() != "" && ($("#bodyfat").val() <= 5 || $("#bodyfat").val() >= 60)) {
			showErrorMsg("你这个体脂绝对是病！得先治！");
			return false;
		} else if ($("#shouldersize").val() != "" && ($("#shouldersize").val() <= 5 || $("#shouldersize").val() >= 60)) {
			showErrorMsg("这个肩宽不正常，不支持");
			return false;
		} else if ($("#bust").val() != "" && ($("#bust").val() <= 20 || $("#bust").val() >= 150)) {
			showErrorMsg("这个胸围不正常，不支持");
			return false;
		} else if ($("#abdominalsize").val() != "" && ($("#abdominalsize").val() <= 20 || $("#abdominalsize").val() >= 150)) {
			showErrorMsg("这个腹围不正常，不支持");
			return false;
		} else if ($("#waistline").val() != "" && ($("#waistline").val() <= 20 || $("#waistline").val() >= 150)) {
			showErrorMsg("这个腰围不正常，不支持");
			return false;
		} else if ($("#hipline").val() != "" && ($("#hipline").val() <= 20 || $("#hipline").val() >= 150)) {
			showErrorMsg("这个臀围不正常，不支持");
			return false;
		} else if ($("#larmsize").val() != "" && ($("#larmsize").val() <= 5 || $("#larmsize").val() >= 150)) {
			showErrorMsg("这个左上臂围不正常，不支持");
			return false;
		} else if ($("#lforearmsize").val() != "" && ($("#lforearmsize").val() <= 5 || $("#lforearmsize").val() >= 150)) {
			showErrorMsg("这个左前臂围不正常，不支持");
			return false;
		} else if ($("#rarmsize").val() != "" && ($("#rarmsize").val() <= 5 || $("#rarmsize").val() >= 150)) {
			showErrorMsg("这个右上臂围不正常，不支持");
			return false;
		} else if ($("#rforearmsize").val() != "" && ($("#rforearmsize").val() <= 5 || $("#rforearmsize").val() >= 150)) {
			showErrorMsg("这个右前臂围不正常，不支持");
			return false;
		} else if ($("#lthighsize").val() != "" && ($("#lthighsize").val() <= 5 || $("#lthighsize").val() >= 150)) {
			showErrorMsg("这个左大腿围不正常，不支持");
			return false;
		} else if ($("#lcrussize").val() != "" && ($("#lcrussize").val() <= 5 || $("#lcrussize").val() >= 150)) {
			showErrorMsg("这个左小腿围不正常，不支持");
			return false;
		} else if ($("#rthighsize").val() != "" && ($("#rthighsize").val() <= 5 || $("#rthighsize").val() >= 150)) {
			showErrorMsg("这个右大腿围不正常，不支持");
			return false;
		} else if ($("#rcrussize").val() != "" && ($("#rcrussize").val() <= 5 || $("#rcrussize").val() >= 150)) {
			showErrorMsg("这个右小腿围不正常，不支持");
			return false;
		} else {
			return true;
		}
	});
	$("#id_button_ok").on("click", function() {

		$("#id_button_ok span").hide();
		$("#id_button_ok").animate({
			width : '50px',
			borderRadius : '50%'
		}, "fast");
		$("#loding_tip").show();
		$.ajax({
			type : "post",
			url : "edituserinformation",
			dataType : "html",
			data : {
				"headimg" : $("#headimg").attr("src"),
				"username" : $("#username").val(),
				"sex" : $("#sex").val(),
				"birthday" : $("#birthday").val(),
				"height" : $("#height").val(),
				"weight" : $("#weight").val(),
				"bodyfat" : $("#bodyfat").val(),
				"shouldersize" : $("#shouldersize").val(),
				"bust" : $("#bust").val(),
				"abdominalsize" : $("#abdominalsize").val(),
				"waistline" : $("#waistline").val(),
				"hipline" : $("#hipline").val(),
				"larmsize" : $("#larmsize").val(),
				"lforearmsize" : $("#lforearmsize").val(),
				"rarmsize" : $("#rarmsize").val(),
				"rforearmsize" : $("#rforearmsize").val(),
				"lthighsize" : $("#lthighsize").val(),
				"lcrussize" : $("#lcrussize").val(),
				"rthighsize" : $("#rthighsize").val(),
				"rcrussize" : $("#rcrussize").val(),
				"height_isUpdate" : $("[name='height_isUpdate']").val(),
				"weight_isUpdate" : $("[name='weight_isUpdate']").val(),
				"bodyfat_isUpdate" : $("[name='bodyfat_isUpdate']").val(),
				"shouldersize_isUpdate" : $("[name='shouldersize_isUpdate']").val(),
				"bust_isUpdate" : $("[name='bust_isUpdate']").val(),
				"abdominalsize_isUpdate" : $("[name='abdominalsize_isUpdate']").val(),
				"waistline_isUpdate" : $("[name='waistline_isUpdate']").val(),
				"hipline_isUpdate" : $("[name='hipline_isUpdate']").val(),
				"larmsize_isUpdate" : $("[name='larmsize_isUpdate']").val(),
				"lforearmsize_isUpdate" : $("[name='lforearmsize_isUpdate']").val(),
				"rarmsize_isUpdate" : $("[name='rarmsize_isUpdate']").val(),
				"rforearmsize_isUpdate" : $("[name='rforearmsize_isUpdate']").val(),
				"lthighsize_isUpdate" : $("[name='lthighsize_isUpdate']").val(),
				"lcrussize_isUpdate" : $("[name='lcrussize_isUpdate']").val(),
				"rthighsize_isUpdate" : $("[name='rthighsize_isUpdate']").val(),
				"rcrussize_isUpdate" : $("[name='rcrussize_isUpdate']").val(),
				"deviceid" : $("#deviceid").val()
			},
			error : function(data) {

				layer.msg("修改失败", {
					time : "2000",
					offset : "200px",
					anim : "6"
				});
				$("#loding_tip").hide();
				$("#error_tip").show();
				setTimeout(function() {

					$("#id_button_ok").animate({
						width : "90%",
						borderRadius : '0%'
					}, "fast");
					$("#error_tip").hide();
					$("#id_button_ok span").show();
				}, 1000);
			},
			success : function(data) {

				layer.msg("修改成功", {
					time : "2000",
					offset : "200px"
				});
				$("#loding_tip").hide();
				$("#success_tip").show();
				setTimeout(function() {

					$("#id_button_ok").animate({
						width : "90%",
						borderRadius : '0%'
					}, "fast");
					$("#success_tip").hide();
					$("#id_button_ok span").show();
				}, 1000);
				initUpdateState();
			}
		});
	});
	ctrlBack("/habit/home");
})

//初始化更新状态
function initUpdateState(){
	
}var isUpdate_hidden = $("[type='hidden']");
var input_number = $("[type='number']");
for (var i = 0; i < isUpdate_hidden.length; i++) {
	isUpdate_hidden.eq(i).val(0);
}
for(var i=0;i<input_number.length;i++){
	input_number.eq(i).attr("history_val",input_number.eq(i).val());
}
