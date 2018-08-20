$(function() {

	$('.new_button').on("click", function() {

		var content = $(".information_panel");
		var newTargetDialog = layer.open({
		type : 1 ,
		skin : 'dialog_2button' ,
		closeBtn : 2 ,
		content : content ,
		area : ['80%' , '300px'] ,
		title : false ,
		btn : ['确定'] ,
		offset : '100px' ,
		yes : function() {

			layer.close(newTargetDialog);
			var loding_shade = layer.load(1, {
				shade : [0.5 , '#000']
			});
			var ti_index = $("#ti_index").val();
			var ti_nexus = $("#ti_nexus").val();
			var f_value = $("#f_value").val();
			if (ti_index == "sbxw" || ti_nexus == "sbxw" || f_value == "") {
				layer.msg("请先好好填完目标信息", {
				time : "2000" ,
				offset : "200px" ,
				anim : "6"
				});
				return;
			}
			$.ajax({
			type : "post" ,
			url : "newtarget" ,
			data : {
			"ti_index" : $("#ti_index").val() ,
			"ti_nexus" : $("#ti_nexus").val() ,
			"f_value" : $("#f_value").val()
			} ,
			dataType : "json" ,
			error : function(data) {

				var msg = "失败了！系统懵逼中...";
				if (data.responseText.length <= 20) {
					msg = data.responseText;
				}
				layer.close(loding_shade);
				layer.msg(msg, {
				time : "3000" ,
				offset : "200px" ,
				anim : "6"
				});
			} ,
			success : function(data) {

				layer.close(loding_shade);
				layer.msg("添加目标成功", {
				time : "2000" ,
				offset : "200px"
				});
				var target_item = "<div id='target_" + data.tid + "' style='display:none;' class='target_in'>" + "<div class='target_in_dec'></div>" + "<div>" + "<span>" + data.msg + "</span><br>" + "<font>" + data.score + "</font>" + "</div>" + "<img class='delete_button' src='../img/delete.png' width='25px;' t_id='" + data.tid + "'>" + "</div>";
				$("#target_list").prepend(target_item);
				$("#target_list div:first").slideDown(200);
				$("#ti_index").val("sbxw");
				$("#ti_nexus").val("sbxw");
				$("#f_value").val("");
			}
			});
		}
		});
	});
	$("#ti_index").change(function() {

		var ti_nexus_val = $("#ti_index").val();
		switch (ti_nexus_val) {
			case "-1" :
				$("#target_unit").text("");
				break;
			case "1" :
				$("#target_unit").text("kg");
				break;
			case "2" :
				$("#target_unit").text("%");
				break;
			default :
				$("#target_unit").text("cm");
				break;
		}
	});
	$("#target_list").on("click", ".delete_button", function() {

		var t_id = $(this).attr("t_id");
		var loding_shade = layer.load(1, {
			shade : [0.5 , '#000']
		});
		$.ajax({
		type : "post" ,
		url : "deletetarget" ,
		data : {
			"t_id" : t_id
		} ,
		dataType : "html" ,
		error : function(data) {

			layer.close(loding_shade);
			layer.msg("失败了！系统懵逼中...", {
			time : "2000" ,
			offset : "200px" ,
			anim : "6"
			});
		} ,
		success : function(data) {

			layer.close(loding_shade);
			layer.msg("目标已删除", {
			time : "2000" ,
			offset : "200px"
			});
			$("#target_" + t_id).slideUp(200);
		}
		});
	});
	ctrlBack("/habit/home");
});
