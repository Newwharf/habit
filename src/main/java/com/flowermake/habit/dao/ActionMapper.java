package com.flowermake.habit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.Action_NewPlanJSPTemp;
import com.flowermake.habit.domain.Action_PlanDetailsJSPTemp;

public interface ActionMapper {
	int deleteByPrimaryKey(Long iId);

	int insert(Action record);

	int insertSelective(Action record);

	Action selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(Action record);

	int updateByPrimaryKey(Action record);

	List<Action_NewPlanJSPTemp> selectByUserId(@Param("uid") long uid);

	List<Action> selectByTypeId(@Param("typeid") long typeid, @Param("start") int start, @Param("end") int end);

	List<Action_PlanDetailsJSPTemp> selectByPlan(@Param("pid") long pid, @Param("uid") long uid);

	List<Action> selectActionByUserId(@Param("uid") long uid, @Param("m") int m, @Param("n") int n);
}