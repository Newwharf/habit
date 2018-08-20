package com.flowermake.habit.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.BodyDataMapper;
import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.service.IBodyDataService;

@Service("bodyDataService")
public class BodyDataServiceImpl implements IBodyDataService{

	@Resource
	BodyDataMapper bodyDataMapper;
	
	public BodyData findBodyDataByUserId(long userId) throws Exception {
		return bodyDataMapper.selectByUserId(userId);
	}

	public int addBodyData(BodyData bodyData) throws Exception {
		return bodyDataMapper.insert(bodyData);
	}

	public int updateBodyData(BodyData bodyData) throws Exception {
		return bodyDataMapper.updateByPrimaryKey(bodyData);
	}

	public BodyData selectByUserId(Long iUserid) throws Exception {
		return bodyDataMapper.selectByUserId(iUserid);
	}

}
