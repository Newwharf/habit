package com.flowermake.habit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.ActionType;

public interface ActionTypeMapper {
	int deleteByPrimaryKey(Long iId);

	int insert(ActionType record);

	int insertSelective(ActionType record);

	ActionType selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(ActionType record);

	int updateByPrimaryKey(ActionType record);

	List<ActionType> selectByUserId(@Param("uid") long uid, @Param("m") int m, @Param("n") int n);
}