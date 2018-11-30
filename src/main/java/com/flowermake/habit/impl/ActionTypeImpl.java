package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.ActionTypeMapper;
import com.flowermake.habit.domain.ActionType;
import com.flowermake.habit.service.IActionTypeService;

@Service("actionTypeService")
public class ActionTypeImpl implements IActionTypeService{
	
	@Resource
	private ActionTypeMapper actionTypeMapper;

	public int insert(ActionType actionType) throws Exception {
		return actionTypeMapper.insert(actionType);
	}

	public ActionType selectByPrimaryKey(Long iId) throws Exception {
		return actionTypeMapper.selectByPrimaryKey(iId);
	}

	public int deleteByPrimaryKey(Long iId) throws Exception {
		return actionTypeMapper.deleteByPrimaryKey(iId);
	}

	public int updateByPrimaryKey(ActionType actionType) throws Exception {
		return actionTypeMapper.updateByPrimaryKey(actionType);
	}

	public List<ActionType> selectByUserId(Long uid, int m, int n) throws Exception {
		return actionTypeMapper.selectByUserId(uid, m, n);
	}

}
