package com.flowermake.habit.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IActionService;
import com.flowermake.habit.service.IPlanActionService;
import com.flowermake.habit.tools.Commons;
import com.flowermake.habit.tools.IdWorker;

@Controller
@RequestMapping("/action")
public class ActionController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IPlanActionService planActionService;

	@Resource
	private IActionService actionService;

	@RequestMapping("/test")
	public String test() throws Exception {
		return "test";
	}

	@RequestMapping("/actionlist")
	public String actionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<Action> actionList = actionService.findActionByUserId(user.getiId(), 0, 100);
		request.setAttribute("actionList", actionList);
		return "actionlist";
	}

	@RequestMapping("ajaxactionlist")
	public void ajaxActionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<Action> actionList = actionService.findActionByUserId(user.getiId(), 0, 100);
		JSONArray actionJsonList = new JSONArray();
		for (Action action : actionList) {
			JSONObject actionJson = new JSONObject();
			actionJson.put("id", action.getiId() + "");
			actionJson.put("type", action.getTiType() + "");
			actionJson.put("name", action.getvName());
			actionJson.put("unit", action.getvUnit());
			actionJsonList.add(actionJson);
		}
		Commons.ajaxResponse(response, actionJsonList.toJSONString());
	}

	@RequestMapping("/newaction")
	public void addAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Action action = new Action();
		action.setDtCdate(new Date());
		action.setiId(new IdWorker().nextId());
		action.setiUserid(user.getiId());
		action.setsState((byte) 0);
		action.setTiType(new Byte(request.getParameter("ti_type")));
		action.setvName(request.getParameter("v_name"));
		action.setvUnit(request.getParameter("v_unit"));
		String msg = actionService.addAction(action);
		if (msg.equals("success")) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("i_id", action.getiId() + "");
			jsonObj.put("v_name", action.getvName());
			jsonObj.put("v_unit", action.getvUnit());
			jsonObj.put("ti_type", action.getTiType() + "");
			Commons.ajaxResponse(response, jsonObj.toJSONString());
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, msg);
		}
	}

	@RequestMapping("/editaction")
	public void editAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Action action = actionService.findActionById(new Long(request.getParameter("i_id")));
		action.setvName(request.getParameter("v_name"));
		if (action.getTiType() == 0) {
			action.setvUnit(request.getParameter("v_unit"));
		}
		if (user.getiId().equals(action.getiUserid())) {
			int msg = actionService.updateAction(action);
			if (msg == 1) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("i_id", action.getiId() + "");
				jsonObj.put("v_name", action.getvName());
				jsonObj.put("v_unit", action.getvUnit());
				jsonObj.put("ti_type", action.getTiType() + "");
				Commons.ajaxResponse(response, jsonObj.toJSONString());
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "失败了！系统懵逼中...");
			}
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "同学！你不能修改别人的动作！");
		}

	}

	@RequestMapping("/deleteaction")
	public void deleteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String msg = "success";
		long actionId = new Long(request.getParameter("a_id"));
		if (planActionService.findPlanActionByActionid(actionId, 0, 1).size() > 0) {
			msg = "请先删除训练计划里的该动作！";
		} else {
			Action action = actionService.findActionById(actionId);
			if (user.getiId().equals(action.getiUserid())) {
				action.setsState((byte) 1);
				if (actionService.updateAction(action) != 1) {
					msg = "失败了！系统懵逼中！";
				}
			} else {
				msg = "同学！你不能删除别人的动作！";
			}
		}
		if (msg.equals("success")) {

		} else {
			response.setStatus(500);
		}
		Commons.ajaxResponse(response, msg);
	}

	@RequestMapping("/actionlist")
	public String actionTypeList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		User user = (User) request.getSession().getAttribute("user");
		
		
		return "actionlist";
	}
	
	@RequestMapping("addactiontype")
	public void addActionType(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
	}
	
	@RequestMapping("editactiontype")
	public void editActionType(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
	}
	
	@RequestMapping("deleteactiontype")
	public void deleteActionType(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
	}
	
}
