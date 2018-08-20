package com.flowermake.habit.tools;


import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;

import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IBodyDataService;
import com.flowermake.habit.service.ITargetService;

/**
 * 域对象验证工具类
 * 
 * @author lihan
 *
 */
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ValidateDomain {

	@Resource
	private ITargetService targetService;

	@Resource
	private IBodyDataService bodyDataService;

	public static boolean validateUser(User user) {
		// TODO user对象验证待实现
		return true;
	}

	public static boolean validateBodyData(BodyData bodyData) {
		// TODO bodyData对象验证待实现
		return true;
	}
}
