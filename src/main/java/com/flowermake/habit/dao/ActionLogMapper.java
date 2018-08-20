package com.flowermake.habit.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.ActionLog;

public interface ActionLogMapper {

	int deleteByPrimaryKey(Long iId);

	int insert(ActionLog record);

	int insertSelective(ActionLog record);

	ActionLog selectByPrimaryKey(Long iId);

	int updateByPrimaryKeySelective(ActionLog record);

	int updateByPrimaryKey(ActionLog record);

	List<ActionLog> selectByPLId(@Param("pl_id") long pl_id);

	List<ActionLog> selectByPIdAfterDate(@Param("pid") long pid, @Param("aid") long aid, @Param("cdate") Date cdate,
			@Param("len") int len);

	List<ActionLog> selectByPIdBeforeDate(@Param("pid") long pid, @Param("aid") long aid, @Param("cdate") Date cdate,
			@Param("len") int len);

	List<ActionLog> selectLast(@Param("pid") long pid, @Param("aid") long aid, @Param("n") int n);

	List<ActionLog> selectLatelyLog(@Param("uid") long uid);

	List<ActionLog> selectByAid(@Param("aid") long aid, @Param("m") int m, @Param("n") int n);

	List<ActionLog> selectByLastPlanLog(@Param("pid") long pid);

}