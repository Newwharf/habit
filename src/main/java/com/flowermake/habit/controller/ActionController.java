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
import com.flowermake.habit.domain.ActionType;
import com.flowermake.habit.domain.Action_NewPlanJSPTemp;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IActionService;
import com.flowermake.habit.service.IActionTypeService;
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

	@Resource
	private IActionTypeService actionTypeService;

	@RequestMapping("/test")
	public String test() throws Exception {
		return "test";
	}

	@RequestMapping("/actionlist")
	public String actionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long typeid = new Long(request.getParameter("typeid"));
		ActionType actionType = actionTypeService.selectByPrimaryKey(typeid);
		if (actionType.getiUserid().equals(user.getiId())) {
			List<Action> actionList = actionService.findActionByTypeId(typeid, 0, 100);
			request.setAttribute("actionList", actionList);
			request.setAttribute("actiontypeid", typeid);
			return "actionlist";
		} else {
			return "error";
		}
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
		action.setiActionTypeid(new Long(request.getParameter("actiontypeid")));
		String msg = actionService.addAction(action);
		if (msg.equals("success")) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("i_id", action.getiId() + "");
			jsonObj.put("v_name", action.getvName());
			jsonObj.put("v_unit", action.getvUnit());
			jsonObj.put("ti_type", action.getTiType() + "");
			jsonObj.put("actiontypeid", action.getiActionTypeid() + "");
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

	@RequestMapping("/actiontypelist")
	public String actionTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<ActionType> actionTypeList = actionTypeService.selectByUserId(user.getiId(), 0, 100);
		request.setAttribute("actionTypeList", actionTypeList);
		return "actiontypelist";
	}

	@RequestMapping("/newactiontype")
	public void addActionType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String name = request.getParameter("type_name");
		String remarks = request.getParameter("type_remarks");
		if ("".equals(name) || name == null) {
			response.setStatus(500);
			Commons.ajaxResponse(response, "error");
		} else {
			ActionType actionType = new ActionType();
			actionType.setDtCdate(new Date());
			actionType.setiId(new IdWorker().nextId());
			actionType.setiUserid(user.getiId());
			actionType.setTiState((byte) 0);
			actionType.setvName(name);
			if (remarks == null || "".equals(remarks)) {
				remarks = "点我管理具体动作";
			}
			actionType.setvRemarks(remarks);
			if (actionTypeService.insert(actionType) == 1) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", actionType.getiId() + "");
				jsonObj.put("name", actionType.getvName());
				jsonObj.put("remarks", actionType.getvRemarks());
				Commons.ajaxResponse(response, jsonObj.toJSONString());
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "失败了！系统懵逼中...");
			}
		}
	}

	@RequestMapping("/editactiontype")
	public void editActionType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "";
		User user = (User) request.getSession().getAttribute("user");
		long type_id = new Long(request.getParameter("type_id"));
		String type_name = request.getParameter("type_name");
		String type_remarks = request.getParameter("type_remarks");

		ActionType actionType = actionTypeService.selectByPrimaryKey(type_id);
		if (actionType.getiUserid().equals(user.getiId())) {
			actionType.setvName(type_name);
			actionType.setvRemarks(type_remarks);
			if (actionTypeService.updateByPrimaryKey(actionType) == 1) {
				msg = "success";
			} else {
				msg = "失败了！系统懵逼中...";
			}
		} else {
			msg = "同学！你不能修改别人的分类！";
		}

		if (msg.equals("success")) {
			Commons.ajaxResponse(response, msg);
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, msg);
		}
	}

	@RequestMapping("/deleteactiontype")
	public void deleteActionType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "";
		User user = (User) request.getSession().getAttribute("user");
		long type_id = new Long(request.getParameter("type_id"));

		ActionType actionType = actionTypeService.selectByPrimaryKey(type_id);
		if (actionType.getiUserid().equals(user.getiId())) {
			if (planActionService.selectByActionTypeId(1, type_id, 0, 1).size() > 0) {
				msg = "训练计划中还存在该分类下的动作，请先删除计划中的动作";
			} else {
				if (actionTypeService.deleteByPrimaryKey(actionType.getiId()) == 1) {
					msg = "success";
				} else {
					msg = "失败了！系统懵逼中...";
				}
			}
		} else {
			msg = "同学！你不能删除别人的分类！";
		}

		if (msg.equals("success")) {
			Commons.ajaxResponse(response, msg);
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, msg);
		}
	}

	@RequestMapping("ajaxactionlist")
	public void ajaxActionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<Action_NewPlanJSPTemp> actionList = actionService.selectByUserId(user.getiId());
		JSONArray array = new JSONArray();
		for (Action_NewPlanJSPTemp ant : actionList) {
			JSONObject obj = new JSONObject();
			obj.put("actionTypeId", ant.getActionTypeId() + "");
			obj.put("actionTypeName", ant.getActionTypeName());
			obj.put("actionId", ant.getActionId() + "");
			obj.put("actionName", ant.getActionName());
			obj.put("actionUnit", ant.getActionUnit());
			obj.put("actionType", ant.getActionType());
			array.add(obj);
		}
		Commons.ajaxResponse(response, array.toJSONString());
	}

}
