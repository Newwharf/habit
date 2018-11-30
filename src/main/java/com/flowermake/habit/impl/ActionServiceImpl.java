package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.ActionMapper;
import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.Action_NewPlanJSPTemp;
import com.flowermake.habit.domain.Action_PlanDetailsJSPTemp;
import com.flowermake.habit.service.IActionService;

@Service("actionService")
public class ActionServiceImpl implements IActionService {

	@Resource
	ActionMapper actionMaaper;

	public String addAction(Action action) throws Exception {
		String msg = valiDataAction(action);
		if (msg.equals("success")) {
			if (actionMaaper.insert(action) != 1) {
				msg = "插入数据库时出错";
			}
		}
		return msg;
	}

	public String valiDataAction(Action action) throws Exception {
		// 暂时不需要验证
		return "success";
	}

	public List<Action> findActionByTypeId(long typeid, int m, int n) throws Exception {
		return actionMaaper.selectByTypeId(typeid, m, n);
	}

	public int deleteActionById(long id) throws Exception {
		return actionMaaper.deleteByPrimaryKey(id);
	}

	public Action findActionById(long id) throws Exception {
		return actionMaaper.selectByPrimaryKey(id);
	}

	public int updateAction(Action action) throws Exception {
		return actionMaaper.updateByPrimaryKey(action);
	}

	public List<Action_PlanDetailsJSPTemp> selectByPlan(long pid, long uid) throws Exception {
		return actionMaaper.selectByPlan(pid, uid);
	}

	public List<Action_NewPlanJSPTemp> selectByUserId(long uid) throws Exception {
		return actionMaaper.selectByUserId(uid);
	}

	public List<Action> selectActionByUserId(long uid, int m, int n) throws Exception {
		return actionMaaper.selectActionByUserId(uid, m, n);
	}

}
