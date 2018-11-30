package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.ActionType;

public interface IActionTypeService {

	public int insert(ActionType actionType) throws Exception;

	public ActionType selectByPrimaryKey(Long iId) throws Exception;

	public int deleteByPrimaryKey(Long iId) throws Exception;

	public int updateByPrimaryKey(ActionType record) throws Exception;

	public List<ActionType> selectByUserId(Long uid, int m, int n) throws Exception;
}
