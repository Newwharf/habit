package com.flowermake.habit.dao;

import com.flowermake.habit.domain.BodyData;

public interface BodyDataMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(BodyData record);

    int insertSelective(BodyData record);

    BodyData selectByPrimaryKey(Long iId);
    
    BodyData selectByUserId(Long iUserid);

    int updateByPrimaryKeySelective(BodyData record);

    int updateByPrimaryKey(BodyData record);
}