package com.flowermake.habit.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.UserMapper;
import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IUserService;
import com.flowermake.habit.tools.Commons;
import com.flowermake.habit.tools.IdWorker;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;

	public int addUser(User user) throws Exception {
		user.setDtCdate(new Date());
		return this.userMapper.insert(user);
	}

	public User findUserById(long id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

	public int updateUser(User user) throws Exception {
		return userMapper.updateByPrimaryKey(user);
	}

	public User selectByDid(String did) throws Exception {
		return userMapper.selectByDid(did);
	}

	public User register(String deviceid) throws Exception {
		// 组装用户对象
		IdWorker iw = new IdWorker();
		User user = new User();
		user.setdBirthday(Commons.getDateByAge(18));
		user.setDtCdate(new Date());
		user.setiId(iw.nextId());
		user.setTiSex((byte) 0);
		user.setvDeviceId(deviceid);
		user.setvImgurl("/habit/img/default_headimg.png");
		user.setvName("点此设置个人信息");
		user.setvTel("");
		user.setvWechatuid(iw.nextId()+"");

		// 组装身体数据对象
		BodyData bodyData = new BodyData();
		bodyData.setiId(new IdWorker().nextId());
		bodyData.setiUserid(user.getiId());
		if (userMapper.register(user, bodyData) > 0) {
			return user;
		} else {
			return null;
		}

	}

}
