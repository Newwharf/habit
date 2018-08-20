package com.flowermake.habit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.Target;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IBodyDataService;
import com.flowermake.habit.service.ITargetService;
import com.flowermake.habit.tools.Commons;
import com.flowermake.habit.tools.IdWorker;

@Controller
@RequestMapping("/target")
public class TargetController {

	@Resource
	private ITargetService targetService;
	@Resource
	private IBodyDataService bodyDataService;

	@RequestMapping("targetlist")
	public String targetLis(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 查询并将目标集合保存到request
		List<Target> targetList = targetService.findTargetByDt(user.getiId(),
				new SimpleDateFormat("yyyyMMdd").parse("19900101"), new Date(), 0, 100);
		request.setAttribute("targetList", targetList);
		return "targetlist";
	}

	@RequestMapping("/newtarget")
	public void newTarget(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		BodyData bodyData = bodyDataService.findBodyDataByUserId(user.getiId());

		// 从请求中获取目标相关数据
		String index = request.getParameter("ti_index");
		String nexus = request.getParameter("ti_nexus");
		String value = request.getParameter("f_value");

		byte ti_index = new Byte(index);
		byte ti_nexus = new Byte(nexus);
		float f_value = new Float(value);

		long tid = new IdWorker().nextId();

		Target target = new Target();
		target.setiId(tid);
		target.setDtCdate(new Date());
		target.setfValue(f_value);
		target.setiUserid(user.getiId());
		target.setTiIndex(ti_index);
		target.setTiNexus(ti_nexus);
		target.setTiFlag((byte) 0);
		target.setTiState((byte) 0);
		target.setvMsg(target.getTargetMsg());
		String score = target.getTargetScore(bodyData);

		String vd_msg = targetService.validateTargt(target);

		if (vd_msg.equals("success")) {
			targetService.addTarget(target);
			JSONObject json = new JSONObject();
			json.put("tid", tid+"");
			json.put("msg", target.getvMsg());
			json.put("score", score+"");
			Commons.ajaxResponse(response, json.toJSONString());
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, vd_msg);
		}
	}

	@RequestMapping("deletetarget")
	public void deleteTarget(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, Exception {
		if (targetService.deleteTargetById(new Long(request.getParameter("t_id"))) == 1) {
			Commons.ajaxResponse(response, "success");
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "失败了！系统懵逼中！");
		}

	}
}
