package com.flowermake.habit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IUserService;

/**
 * 该拦截器主要用于验证是否为新用户
 * 
 * @author admin
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Resource
	private IUserService userService;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			return true;
		} else {
			String deviceid = request.getParameter("deviceid");
			User user = userService.selectByDid(deviceid);
			if (user == null) {
				// 注册一个新用户
				user = userService.register(deviceid);
			}
			// 将用户信息存入session
			request.getSession().setAttribute("user", user);
			return true;
		}
	}

}
