package com.flowermake.habit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.PlanAction;

public interface PlanActionMapper {
	int insert(PlanAction record);

	int insertSelective(PlanAction record);

	List<PlanAction> selectByActionId(@Param("a_id") long a_id, @Param("start") int start, @Param("end") int end);

	List<PlanAction> selectByPlanId(@Param("p_id") long p_id, @Param("start") int start, @Param("end") int end);

	List<PlanAction> selectByActionTypeId(@Param("state") long state, @Param("at_id") long at_id,
			@Param("start") int start, @Param("end") int end);

	int updateStateByPlanId(@Param("p_id") long p_id);

	int insertList(List<PlanAction> list);
}