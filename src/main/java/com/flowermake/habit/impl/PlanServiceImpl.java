package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.PlanMapper;
import com.flowermake.habit.domain.Plan;
import com.flowermake.habit.service.IPlanService;

@Service("planService")
public class PlanServiceImpl implements IPlanService {

	@Resource
	PlanMapper planMapper;

	public List<Plan> findPlansByUserId(long uid, int m, int n) throws Exception {
		return planMapper.selectByUserId(uid, m, n);
	}

	public Plan findPlanById(long id) throws Exception {
		return planMapper.selectByPrimaryKey(id);
	}

	public int deletePlanById(long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updatePlan(Plan plan) throws Exception {
		return planMapper.updateByPrimaryKey(plan);
	}

	public int addPlan(Plan plan) throws Exception {
		return planMapper.insert(plan);
	}

}
