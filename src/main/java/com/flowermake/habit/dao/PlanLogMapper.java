package com.flowermake.habit.dao;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.PlanLog;

public interface PlanLogMapper {
	int deleteByPrimaryKey(Long id);

	int insert(PlanLog record);

	int insertSelective(PlanLog record);

	PlanLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(PlanLog record);

	int updateByPrimaryKey(PlanLog record);

	PlanLog selectLastByPlanId(@Param("p_id") long p_id);
}