package com.flowermake.habit.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.BodyDataMapper;
import com.flowermake.habit.dao.TargetMapper;
import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.Target;
import com.flowermake.habit.service.ITargetService;

@Service("targetService")
public class TargetServiceImpl implements ITargetService {

	@Resource
	TargetMapper targetMapper;

	@Resource
	BodyDataMapper bodyDataMapper;

	public List<Target> findTargetsByUserId(long uid, int m, int n) throws Exception {
		return null;
	}

	public int countTargetByParams(long uid) throws Exception {
		return 0;
	}

	public int addTarget(Target target) throws Exception {
		return targetMapper.insert(target);
	}

	public int updateTarget(Target target) throws Exception {
		return 0;
	}

	public int deleteTargetById(long id) throws Exception {
		return targetMapper.deleteByPrimaryKey(id);
	}

	public List<Target> findTargetByDt(long uid, Date startCdt, Date endCdt, int start, int end) throws Exception {
		return targetMapper.selectByCdt(uid, startCdt, endCdt, start, end);
	}

	public List<Target> findeTargetByIndex(long uid, byte index, byte flag, int start, int end) throws Exception {
		return targetMapper.selectByIndex(uid, index, flag, start, end);
	}

	public String validateTargt(Target target) throws Exception {
		BodyData bodyData = bodyDataMapper.selectByUserId(target.getiUserid());
		List<Target> targetList = targetMapper.selectByIndex(target.getiUserid(), target.getTiIndex(), (byte) 0, 0,
				100);
		if (targetList.size() > 0) {
			return "同一个身体指标下只能有一个目标";
		} else if (target.isTargetComplate(bodyData)) {
			return "你...你不能添加一个已完成的目标";
		} else {
			return "success";
		}
	}

}
