package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.PlanLogMapper;
import com.flowermake.habit.domain.PlanLog;
import com.flowermake.habit.service.IPlanLogService;

@Service("planLogService")
public class PlanLogServiceImpl implements IPlanLogService {

	@Resource
	PlanLogMapper planLogMapper;

	public PlanLog findLastPlanLogByPlanId(long planId) throws Exception {
		return planLogMapper.selectLastByPlanId(planId);
	}

	public int addPlanLog(PlanLog planLog) throws Exception {
		return planLogMapper.insert(planLog);
	}

	public int deletePlanLog(long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<PlanLog> findPlanLogByPlanId(long planId, int m, int n) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public PlanLog findPlanLogById(long plid) throws Exception {
		return planLogMapper.selectByPrimaryKey(plid);
	}

	public int updatePlanLog(PlanLog planLog) throws Exception {
		return planLogMapper.updateByPrimaryKey(planLog);
	}

}
