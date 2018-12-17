package com.flowermake.habit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flowermake.habit.domain.ActionLog_LogChartTemp;
import com.flowermake.habit.domain.BodyDataLog;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IActionLogService;
import com.flowermake.habit.service.IActionService;
import com.flowermake.habit.service.IBodyDataLogService;
import com.flowermake.habit.tools.Commons;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@Resource
	private IBodyDataLogService bdlService;

	@Resource
	private IActionLogService actionLogService;

	@Resource
	private IActionService actionService;

	@RequestMapping("/logchart")
	public String toLogChart(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<BodyDataLog> bdlList = bdlService.selectLatelyLog(user.getiId());
		Map<Byte, BodyDataLog> bdlMap = new HashMap<Byte, BodyDataLog>();
		for (BodyDataLog bdl : bdlList) {
			bdlMap.put(bdl.getTiIndex(), bdl);
		}
		request.setAttribute("bdlMap", bdlMap);
		return "logchart";
	}

	// @RequestMapping("/actionloglist")
	// public String actionLogList(HttpServletRequest request) throws Exception {
	// User user = (User) request.getSession().getAttribute("user");
	// Action action = actionService.findActionById(new
	// Long(request.getParameter("aid")));
	// if (user.getiId().equals(action.getiUserid())) {
	// List<ActionLog> actionLogList = actionLogService.selectByAid(action.getiId(),
	// 0, 100);
	// request.setAttribute("actionLogList", actionLogList);
	// return "actionloglist";
	// } else {
	// return "error";
	// }
	// }

	@RequestMapping("/bdllist")
	public void bdlList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		int m = new Integer(request.getParameter("page_index"));
		int n = new Integer(request.getParameter("page_offset"));
		byte index = new Byte(request.getParameter("index"));
		List<BodyDataLog> bdlList = bdlService.selectByIndex(index, user.getiId(), m, n);
		Commons.ajaxResponse(response, JSONArray.toJSONString(bdlList));
	}

	@RequestMapping("/actionloglistbyaid")
	public void actionLogListByAid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long aid = new Long(request.getParameter("aid"));
		int m = new Integer(request.getParameter("page_index"));
		int n = new Integer(request.getParameter("page_offset"));
		List<ActionLog_LogChartTemp> logList = actionLogService.selectChartLogByAid(aid, user.getiId(), m, n);
		JSONArray array = new JSONArray();
		for (ActionLog_LogChartTemp log : logList) {
			JSONObject json = new JSONObject();
			json.put("actionId", log.getActionId() + "");
			json.put("actionLogComments", log.getActionLogComments());
			json.put("actionLogId", log.getActionLogId() + "");
			json.put("actionLogNumByPlan", log.getActionLogNumByPlan());
			json.put("actionLogScorenum", log.getActionLogScorenum());
			json.put("actionLogScoretime", log.getActionLogScoretime());
			json.put("actionLogScoreweight", log.getActionLogScoreweight());
			json.put("actionName", log.getActionName());
			json.put("actionType", log.getActionType());
			json.put("actionUnit", log.getActionUnit());
			json.put("planId", log.getPlanId() + "");
			json.put("planLogCdate", Commons.formatDate(log.getPlanLogCdate(), "MM月dd日 HH:mm"));
			if (log.getPlanLogEdate() != null) {
				json.put("planLogEdate", Commons.formatDate(log.getPlanLogEdate(), "MM月dd日 HH:mm"));
			} else {
				json.put("planLogEdate", "训练中");
			}

			json.put("planLogId", log.getPlanLogId() + "");
			json.put("planName", log.getPlanName());
			array.add(json);
		}
		Commons.ajaxResponse(response, array.toJSONString());
	}

	@RequestMapping("/actionloglistbypid")
	public void actionLogListByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long aid = new Long(request.getParameter("aid"));
		long pid = new Long(request.getParameter("pid"));
		int m = new Integer(request.getParameter("page_index"));
		int n = new Integer(request.getParameter("page_offset"));
		List<ActionLog_LogChartTemp> logList = actionLogService.selectChartLogByPid(pid, aid, user.getiId(), m, n);
		JSONArray array = new JSONArray();
		for (ActionLog_LogChartTemp log : logList) {
			JSONObject json = new JSONObject();
			json.put("actionId", log.getActionId() + "");
			json.put("actionLogComments", log.getActionLogComments());
			json.put("actionLogId", log.getActionLogId() + "");
			json.put("actionLogNumByPlan", log.getActionLogNumByPlan());
			json.put("actionLogScorenum", log.getActionLogScorenum());
			json.put("actionLogScoretime", log.getActionLogScoretime());
			json.put("actionLogScoreweight", log.getActionLogScoreweight());
			json.put("actionName", log.getActionName());
			json.put("actionType", log.getActionType());
			json.put("actionUnit", log.getActionUnit());
			json.put("planId", log.getPlanId() + "");
			json.put("planLogCdate", Commons.formatDate(log.getPlanLogCdate(), "MM月dd日 HH:mm"));
			if (log.getPlanLogEdate() != null) {
				json.put("planLogEdate", Commons.formatDate(log.getPlanLogEdate(), "MM月dd日 HH:mm"));
			} else {
				json.put("planLogEdate", "训练中");
			}
			json.put("planLogId", log.getPlanLogId() + "");
			json.put("planName", log.getPlanName());
			array.add(json);
		}
		Commons.ajaxResponse(response, array.toJSONString());
	}

}
