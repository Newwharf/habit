package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.PlanActionMapper;
import com.flowermake.habit.domain.PlanAction;
import com.flowermake.habit.service.IPlanActionService;

@Service("planActionService")
public class PlanActionServiceImpl implements IPlanActionService {

	@Resource
	PlanActionMapper planActionMapper;

	public int addPlanAction(PlanAction planAction) throws Exception {
		return planActionMapper.insert(planAction);
	}

	public List<PlanAction> findPlanActionByPlanId(long planId, int m, int n) throws Exception {
		return planActionMapper.selectByPlanId(planId, m, n);
	}

	public int updatePlanAction(PlanAction planAction) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public PlanAction findPlanActionById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PlanAction> findPlanActionByActionid(long actionId, int m, int n) throws Exception {
		return planActionMapper.selectByActionId(actionId, m, n);
	}

	public int deleteByPlanId(long planid) throws Exception {
		return planActionMapper.updateStateByPlanId(planid);
	}

}
