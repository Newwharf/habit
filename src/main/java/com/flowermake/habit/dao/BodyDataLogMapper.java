package com.flowermake.habit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.BodyDataLog;

public interface BodyDataLogMapper {
	int deleteByPrimaryKey(Long iId);

	int insert(BodyDataLog record);

	int insertSelective(BodyDataLog record);

	BodyDataLog selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(BodyDataLog record);

	int updateByPrimaryKey(BodyDataLog record);

	List<BodyDataLog> selectLatelyLog(@Param("uid") long uid);

	List<BodyDataLog> selectByIndex(@Param("index") byte index, @Param("uid") long uid, @Param("m") int m,
			@Param("n") int n);
}