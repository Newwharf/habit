package com.flowermake.habit.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowermake.habit.dao.UserMapper;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;

	public int addUser(User user) throws Exception {
		user.setDtCdate(new Date());
		return this.userMapper.insert(user);
	}

//	public User findUserByWid(String wid) throws Exception {
//		return this.userMapper.selectByWid(wid);
//	}

	public User findUserById(long id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

	public int updateUser(User user) throws Exception {
		return 0;
	}




}
