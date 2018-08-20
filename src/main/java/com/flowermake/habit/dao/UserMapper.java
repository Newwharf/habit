package com.flowermake.habit.dao;

import com.flowermake.habit.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long iId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}