package com.flowermake.habit.dao;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.User;

public interface UserMapper {
	
	int deleteByPrimaryKey(Long iId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByDid(@Param("did") String did);

	int register(@Param("user") User user, @Param("bodyData") BodyData bodyData);
}