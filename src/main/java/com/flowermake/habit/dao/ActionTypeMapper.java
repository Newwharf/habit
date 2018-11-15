package com.flowermake.habit.dao;

import java.util.List;

import com.flowermake.habit.domain.ActionType;

public interface ActionTypeMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(ActionType record);

    int insertSelective(ActionType record);

    ActionType selectByPrimaryKey(Long iId);

    int updateByPrimaryKeySelective(ActionType record);

    int updateByPrimaryKey(ActionType record);
    
    List<ActionType> selectByUserId(Long uid,int m,int n);
}