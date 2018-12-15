package com.flowermake.habit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.BodyDataLogMapper;
import com.flowermake.habit.domain.BodyDataLog;
import com.flowermake.habit.service.IBodyDataLogService;

@Service("bodyDataLogService")
public class BodyDataLogServiceImpl implements IBodyDataLogService {
	@Resource
	BodyDataLogMapper bodyDataLogMapper;

	public int addBodyDataLog(BodyDataLog bodyDataLog) throws Exception {
		return bodyDataLogMapper.insert(bodyDataLog);
	}

	public int deleteBodyDataLogById(long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBodyDataLog(BodyDataLog bodyDataLog) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public BodyDataLog findBodyDataLogById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BodyDataLog> findBodyDataLogByUserId(long id, int m, int n) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BodyDataLog> selectLatelyLog(long uid) throws Exception {
		return bodyDataLogMapper.selectLatelyLog(uid);
	}

	public List<BodyDataLog> selectByIndex(byte index, long uid, int m, int n) {
		return bodyDataLogMapper.selectByIndex(index, uid, m, n);
	}

	public int insertList(List<BodyDataLog> list) {
		return bodyDataLogMapper.insertList(list);
	}

}
