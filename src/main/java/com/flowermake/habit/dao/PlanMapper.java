package com.flowermake.habit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.Plan;

public interface PlanMapper {
	int deleteByPrimaryKey(Long iId);

	int insert(Plan record);

	int insertSelective(Plan record);

	Plan selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(Plan record);

	int updateByPrimaryKey(Plan record);

	List<Plan> selectByUserId(@Param("u_id") long u_id, @Param("start") int start, @Param("end") int end);
}