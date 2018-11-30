package com.flowermake.habit.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.ActionLog;
import com.flowermake.habit.domain.Action_PlanDetailsJSPTemp;
import com.flowermake.habit.domain.Plan;
import com.flowermake.habit.domain.PlanAction;
import com.flowermake.habit.domain.PlanLog;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IActionLogService;
import com.flowermake.habit.service.IActionService;
import com.flowermake.habit.service.IPlanActionService;
import com.flowermake.habit.service.IPlanLogService;
import com.flowermake.habit.service.IPlanService;
import com.flowermake.habit.tools.Commons;
import com.flowermake.habit.tools.IdWorker;

@Controller
@RequestMapping("/plan")
public class PlanController {
	@Resource
	private IPlanService planService;

	@Resource
	private IPlanActionService planActionService;

	@Resource
	private IActionService actionService;

	@Resource
	private IPlanLogService planLogService;

	@Resource
	private IActionLogService actionLogService;

	@RequestMapping("/tonewplan")
	public String toNewPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "newplan";
	}

	@RequestMapping("/toplandetails")
	public String toPlanDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 取得训练id
		Long pid = new Long(request.getParameter("planid"));
		Plan plan = planService.findPlanById(pid);
		// 取得训练动作中间表数据
		List<PlanAction> planActionList = planActionService.findPlanActionByPlanId(pid, 0, 100);
		List<Action_PlanDetailsJSPTemp> tempActionList = actionService
				.selectByPlan(new Long(request.getParameter("planid")), user.getiId());
		// // 获取动作对象
		// for (Action action : actionList) {
		// Action_PlanDetailsJSPTemp tempAction = new Action_PlanDetailsJSPTemp();
		// tempAction.setAid(action.getiId());
		// tempAction.setAname(action.getvName());
		// tempAction.setNum(action.);
		// tempAction.setPid(plan.getiId());
		// tempAction.setState((byte) 0);
		// tempAction.setType(action.getTiType());
		// tempAction.setUnit(action.getvUnit());
		// tempActionList.add(tempAction);
		// }
		// 如果正在训练中，获取该训练最新一个训练中的训练日志
		if (plan.gethPlancol().equals("正在训练中...")) {
			PlanLog planLog = planLogService.findLastPlanLogByPlanId(plan.getiId());
			// 获取该动作下所有的动作日志
			List<ActionLog> actionLogList = actionLogService.findByPLId(planLog.getId());

			// 创建动作训练情况列表map
			Map<Long, List<ActionLog>> actionLogMap = new HashMap<Long, List<ActionLog>>();
			for (PlanAction planAcion : planActionList) {
				List<ActionLog> orderActionLogList = new ArrayList<ActionLog>();
				for (ActionLog actionLog : actionLogList) {
					if (planAcion.getiActionid().equals(actionLog.getiActionid())) {
						orderActionLogList.add(actionLog);
					}
				}
				// 按照动作组数排序
				orderActionLogList.sort(new Comparator<ActionLog>() {
					public int compare(ActionLog actionLog1, ActionLog actionLog2) {
						if (actionLog1.getiNumbyplan() < actionLog2.getiNumbyplan()) {
							return -1;
						} else {
							return 1;
						}

					}
				});
				actionLogMap.put(planAcion.getiActionid(), orderActionLogList);
			}

			// 未在训练中
			request.setAttribute("actionLogMap", actionLogMap);
			request.setAttribute("planlog", planLog);
			request.setAttribute("plan", plan);
			request.setAttribute("tempactionlist", tempActionList);
			return "plandetails_in";
		}
		// 未在训练中
		request.setAttribute("plan", plan);
		request.setAttribute("tempactionlist", tempActionList);
		return "plandetails_off";

	}

	@RequestMapping("/newplan")
	public void newPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		IdWorker idWork = new IdWorker();

		// 获取请求参数
		String planName = request.getParameter("planname");
		JSONArray jsonArray = JSONArray.parseArray(request.getParameter("actionlist"));

		// 组装Plan对象
		Plan plan = new Plan();
		plan.setDtCdate(new Date());
		plan.setTiState((byte) 0);
		plan.setvName(planName);
		plan.setiId(idWork.nextId());
		plan.setiUserid(user.getiId());
		plan.sethPlancol("还未进行过训练");

		// 组装PlanAction对象集合
		List<PlanAction> planActionList = new ArrayList<PlanAction>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			PlanAction planAction = new PlanAction();
			planAction.setDtCdate(new Date());
			planAction.setTiState((byte) 0);
			planAction.setiActionid(new Long(jsonObj.getString("a_id")));
			planAction.setiNum(new Integer(jsonObj.getString("num")));
			planAction.setiPlanid(plan.getiId());
			planActionList.add(planAction);
		}

		// 保存Plan对象及PlanAction对象
		// TODO 此处应加事务
		if (planService.addPlan(plan) == 1) {
			if (planActionService.insertList(planActionList) == planActionList.size()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("pid", plan.getiId() + "");
				Commons.ajaxResponse(response, jsonObj.toJSONString());
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "失败了！系统懵逼中！");
			}
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "失败了！系统懵逼中！");
		}
	}

	@RequestMapping("/editplan")
	public void editPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 获取请求参数
		String planName = request.getParameter("planname");
		JSONArray jsonArray = JSONArray.parseArray(request.getParameter("actionlist"));

		// 获取plan对象,并组装要修改的对象
		Plan plan = planService.findPlanById(new Long(request.getParameter("planid")));
		if (user.getiId().equals(plan.getiUserid())) {
			plan.setvName(planName);

			// 组装PlanAction对象集合
			List<PlanAction> planActionList = new ArrayList<PlanAction>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				PlanAction planAction = new PlanAction();
				planAction.setDtCdate(new Date());
				planAction.setTiState((byte) 0);
				planAction.setiActionid(new Long(jsonObj.getString("a_id")));
				planAction.setiNum(new Integer(jsonObj.getString("num")));
				planAction.setiPlanid(plan.getiId());
				planActionList.add(planAction);
			}

			// TODO 加事务
			// 删除现有planaction关联表数据
			planActionService.deleteByPlanId(plan.getiId());

			// 保存Plan对象及PlanAction对象
			// TODO 此处应加事务
			if (planService.updatePlan(plan) == 1) {
				for (PlanAction planAction : planActionList) {
					planActionService.addPlanAction(planAction);
				}
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("pid", plan.getiId() + "");
				Commons.ajaxResponse(response, jsonObj.toJSONString());
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "失败了！系统懵逼中！");
			}
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "你无法修改一个不存在的训练计划");
		}

	}

	@RequestMapping("startplan")
	public void startPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Plan plan = planService.findPlanById(new Long(request.getParameter("pid")));
		if (plan != null) {
			if (user.getiId().equals(plan.getiUserid())) {
				PlanLog lastPlanLog = planLogService.findLastPlanLogByPlanId(plan.getiId());
				boolean isLastIn = true;
				if (lastPlanLog != null) {
					if (lastPlanLog.getTiState() == 0) {
						isLastIn = false;
					}
				}
				if (isLastIn) {
					PlanLog planLog = new PlanLog();
					planLog.setId(new IdWorker().nextId());
					planLog.setDtCdate(new Date());
					planLog.setiPlanid(plan.getiId());
					planLog.setTiState((byte) 0);
					planLog.setiUserid(user.getiId());
					// TODO 加事务
					planLogService.addPlanLog(planLog);
					plan.sethPlancol("正在训练中...");
					planService.updatePlan(plan);
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("plid", planLog.getId() + "");
					Commons.ajaxResponse(response, jsonObj.toJSONString());
				} else {
					response.setStatus(500);
					Commons.ajaxResponse(response, "重复开始任务？懵逼中...");
				}
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "你无法开始一个不存在的训练");
			}

		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "你无法开始一个不存在的训练");
		}
	}

	@RequestMapping("endplan")
	public void endPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Plan plan = planService.findPlanById(new Long(request.getParameter("pid")));
		PlanLog planLog = planLogService.findPlanLogById(new Long(request.getParameter("plid")));
		if (plan != null && planLog != null) {
			if (user.getiId().equals(plan.getiUserid()) && user.getiId().equals(planLog.getiUserid())) {
				Date date = new Date();
				plan.sethPlancol("上次训练 " + Commons.formatDate(date, "yyyy.MM.dd HH:mm"));
				planLog.setDtLedate(date);
				planLog.setTiState((byte) 1);
				// TODO 加事务
				planService.updatePlan(plan);
				planLogService.updatePlanLog(planLog);
				Commons.ajaxResponse(response, "success");
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "没有这个训练或者这个训练没有开始过");
			}
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "没有这个训练或者这个训练没有开始过");
		}
	}

	@RequestMapping("deleteplan")
	public void deletePlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Plan plan = planService.findPlanById(new Long(request.getParameter("pid")));
		if (plan != null) {
			if (plan.getiUserid().equals(user.getiId())) {
				plan.setTiState((byte) 1);
				// 加事务
				planActionService.deleteByPlanId(plan.getiId());
				planService.updatePlan(plan);

				Commons.ajaxResponse(response, "success");
			} else {
				response.setStatus(500);
				Commons.ajaxResponse(response, "无法删除一个不存在的训练");
			}
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "无法删除一个不存在的训练");
		}
	}

	@RequestMapping("toeditplan")
	public String toEditPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 获取、声明各种数据对象
		Plan plan = planService.findPlanById(new Long(request.getParameter("pid")));
		if (user.getiId().equals(plan.getiUserid())) {
			List<PlanAction> planActionList = planActionService.findPlanActionByPlanId(plan.getiId(), 0, 100);
			List<Action> actionList = actionService.selectActionByUserId(user.getiId(), 0, 100);
			List<Action_PlanDetailsJSPTemp> tempActionList = new ArrayList<Action_PlanDetailsJSPTemp>();

			// 组装tempActionList
			for (Action action : actionList) {
				for (PlanAction planAction : planActionList) {
					if (action.getiId().equals(planAction.getiActionid())) {
						Action_PlanDetailsJSPTemp tempAction = new Action_PlanDetailsJSPTemp();
						tempAction.setAid(action.getiId());
						tempAction.setAname(action.getvName());
						tempAction.setNum(planAction.getiNum());
						tempAction.setPid(plan.getiId());
						tempAction.setType(action.getTiType());
						tempAction.setUnit(action.getvUnit());
						tempActionList.add(tempAction);
					}
				}
			}

			// 将数据保存到request
			request.setAttribute("plan", plan);
			request.setAttribute("actionlist", actionList);
			request.setAttribute("tempactionlist", tempActionList);

			return "editplan";
		} else {
			return "error";
		}

	}

	@RequestMapping("getalbyp")
	public void getActionLogByPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long pid = new Long(request.getParameter("pid"));
		long aid = new Long(request.getParameter("aid"));
		long alid = new Long(request.getParameter("alid").equals("undefined") ? "-1" : request.getParameter("alid"));
		Action action = actionService.findActionById(aid);
		if (user.getiId().equals(action.getiUserid())) {
			List<ActionLog> actionLogList = new ArrayList<ActionLog>();
			if (alid != -1) {
				// 如果本次planlog中该动作存在过至少一个actionlog，则取个actionlog前后各5条
				actionLogList = actionLogService.findByPId(pid, aid, alid, 5, 5);
			} else {
				// 如果本次planlog中该动作不存在actionlog，则取个actionlog最后5条
				actionLogList = actionLogService.findLast(pid, aid, 5);
			}

			JSONArray jsonArray = new JSONArray();
			int tempLogNum = -1;
			long tempActionLogId = -1;
			for (int i = 0; i < actionLogList.size(); i++) {
				ActionLog actionLog = actionLogList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("alid", actionLog.getiId());
				jsonObj.put("num", actionLog.getiNumbyplan());
				// jsonObj.put("date", Commons.formatDate(actionLog.getDtCdate(), "MM.dd
				// HH:mm"));
				jsonObj.put("date", actionLog.getDtCdate().getTime() + "");
				jsonObj.put("scoreweight", actionLog.getfScoreweight());
				if (action.getTiType() == 0) {
					jsonObj.put("score", actionLog.getiScorenum());
				} else {
					jsonObj.put("score", actionLog.getiScoretime());
				}
				if (actionLog.getiId().equals(alid)) {
					// 获取当前actionlog的位置
					tempLogNum = i;
					tempActionLogId = alid;
				}
				jsonArray.add(jsonObj);
			}
			JSONArray targetJsonArray = Commons.getTargetArrayByIndex(jsonArray, tempLogNum, 2);
			for (int i = 0; i < targetJsonArray.size(); i++) {
				if (((JSONObject) targetJsonArray.get(i)).getLong("alid").equals(tempActionLogId)) {
					tempLogNum = i + 1;
					break;
				}
			}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("lognum", tempLogNum);
			targetJsonArray.add(jsonObj);
			Commons.ajaxResponse(response, targetJsonArray.toJSONString());
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "失败了！系统懵逼中！");
		}

	}

	@RequestMapping("addactionlog")
	public void addActionLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long plid = new Long(request.getParameter("plid"));
		long aid = new Long(request.getParameter("aid"));
		int num = new Integer(request.getParameter("num"));
		int score = new Integer(request.getParameter("score"));
		float scoreweight = new Float(request.getParameter("scoreweight"));
		String comments = request.getParameter("comments");

		Action action = actionService.findActionById(aid);
		ActionLog actionLog = new ActionLog();
		actionLog.setDtCdate(new Date());
		actionLog.setDtLedate(new Date());
		actionLog.setiActionid(action.getiId());
		actionLog.setiId(new IdWorker().nextId());
		actionLog.setiNumbyplan(num);
		actionLog.setiPlanlogid(plid);
		actionLog.setfScoreweight(scoreweight);
		actionLog.setTiActiontype(action.getTiType());
		actionLog.setvComments(comments);
		actionLog.setTiState((byte) 0);
		actionLog.setvActionunit(action.getvUnit());
		actionLog.setiUserid(user.getiId());
		if (action.getTiType() == 0) {
			actionLog.setiScorenum(score);
		} else {
			actionLog.setiScoretime(score);
		}

		actionLogService.addActionLog(actionLog);
		Commons.ajaxResponse(response, "success");

	}

	@RequestMapping("editactionlog")
	public void editActionLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		long alid = new Long(request.getParameter("alid"));
		int score = new Integer(request.getParameter("score"));
		float scoreweight = new Float(request.getParameter("scoreweight"));
		String comments = request.getParameter("comments");
		ActionLog actionLog = actionLogService.selectByAlid(alid);
		if (user.getiId().equals(actionLog.getiUserid())) {
			actionLog.setfScoreweight(scoreweight);
			actionLog.setvComments(comments);
			if (actionLog.getTiActiontype() == 0) {
				actionLog.setiScorenum(score);
			} else {
				actionLog.setiScoretime(score);
			}
			actionLogService.updateActionLog(actionLog);
			Commons.ajaxResponse(response, "success");
		} else {
			response.setStatus(500);
			Commons.ajaxResponse(response, "你不能修改一个不存在动作记录");
		}

	}

}
