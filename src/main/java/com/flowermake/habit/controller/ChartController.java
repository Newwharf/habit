package com.flowermake.habit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.ActionLog;
import com.flowermake.habit.domain.BodyDataLog;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IActionLogService;
import com.flowermake.habit.service.IActionService;
import com.flowermake.habit.service.IBodyDataLogService;

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
		List<ActionLog> actionLogList = actionLogService.selectLatelyLog(user.getiId());
		List<Action> actionList = actionService.findActionByUserId(user.getiId(), 0, 100);
		Map<Byte, BodyDataLog> bdlMap = new HashMap<Byte, BodyDataLog>();
		Map<Long, ActionLog> actionLogMap = new HashMap<Long, ActionLog>();
		for (BodyDataLog bdl : bdlList) {
			bdlMap.put(bdl.getTiIndex(), bdl);
		}
		for (ActionLog actionLog : actionLogList) {
			actionLogMap.put(actionLog.getiActionid(), actionLog);
		}
		request.setAttribute("actionList", actionList);
		request.setAttribute("bdlMap", bdlMap);
		request.setAttribute("actionLogMap", actionLogMap);
		return "logchart";
	}

	@RequestMapping("/actionloglist")
	public String actionLogList(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Action action = actionService.findActionById(new Long(request.getParameter("aid")));
		if ( user.getiId().equals(action.getiUserid())) {
			List<ActionLog> actionLogList = actionLogService.selectByAid(action.getiId(), 0, 100);
			request.setAttribute("actionLogList", actionLogList);
			return "actionloglist";
		} else {
			return "error";
		}
	}

	@RequestMapping("/bdllist")
	public String bdlList(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		byte index = new Byte(request.getParameter("index"));
		List<BodyDataLog> bdlList = bdlService.selectByIndex(index, user.getiId(), 0, 100);
		request.setAttribute("bdlList", bdlList);
		return "bdllist";

	}

}
