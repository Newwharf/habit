package com.flowermake.habit.impl;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.ActionLogMapper;
import com.flowermake.habit.domain.ActionLog;
import com.flowermake.habit.service.IActionLogService;

@Service("actionLogService")
public class ActionLogServiceImpl implements IActionLogService {

	@Resource
	ActionLogMapper actionLogMapper;

	public int addActionLog(ActionLog actionLog) throws Exception {
		return actionLogMapper.insert(actionLog);
	}

	public int deleteActionById(long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ActionLog> findByPLId(long pl_id) throws Exception {
		return actionLogMapper.selectByPLId(pl_id);
	}

	public List<ActionLog> findByPId(long pid, long aid, long alid, int before, int after) throws Exception {
		ActionLog actionLog = actionLogMapper.selectByPrimaryKey(alid);
		List<ActionLog> beforeList = actionLogMapper.selectByPIdBeforeDate(pid, aid, actionLog.getDtCdate(), before);
		List<ActionLog> afterList = actionLogMapper.selectByPIdAfterDate(pid, aid, actionLog.getDtCdate(), after);
		beforeList.add(actionLog);
		beforeList.addAll(afterList);
		// 按照创建时间正序排序
		beforeList.sort(new Comparator<ActionLog>() {
			public int compare(ActionLog actionLog1, ActionLog actionLog2) {
				if (actionLog2.getDtCdate().before(actionLog1.getDtCdate())) {
					return 1;
				} else {
					return -1;
				}

			}
		});
		return beforeList;
	}

	public List<ActionLog> findLast(long pid, long aid, int n) {
		List<ActionLog> actionLogList = actionLogMapper.selectLast(pid, aid, n);
		// 按照创建时间正序排序
		actionLogList.sort(new Comparator<ActionLog>() {
			public int compare(ActionLog actionLog1, ActionLog actionLog2) {
				if (actionLog2.getDtCdate().before(actionLog1.getDtCdate())) {
					return 1;
				} else {
					return -1;
				}

			}
		});
		return actionLogList;
	}

	public List<ActionLog> selectLatelyLog(long uid) {
		return actionLogMapper.selectLatelyLog(uid);
	}

	public List<ActionLog> selectByAid(long aid, int m, int n) {
		return actionLogMapper.selectByAid(aid, m, n);
	}

	public int updateActionLog(ActionLog actionLog) {
		return actionLogMapper.updateByPrimaryKey(actionLog);
	}

	public ActionLog selectByAlid(long alid) {
		return actionLogMapper.selectByPrimaryKey(alid);
	}

	public List<ActionLog> selectByLastPlanLog(long pid) {
		return actionLogMapper.selectByLastPlanLog(pid);
	}

}
