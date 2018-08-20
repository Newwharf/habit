package com.flowermake.habit.interceptor;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IUserService;

/**
 * 该拦截器主要用于验证是否登录
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
//		// 判断是否已获取了微信用户信息
//		if(request.getSession().getAttribute("wchat_token")!=null) {
//			return true;
//		}else {
//			//如果没有微信用户信息，则跳转到微信确认登录界面
//			//TODO 实现转发微信确认登录页面代码
//			return false;
//		}
		
		User user = userService.findUserById(977073165870039040L);
		request.getSession().setAttribute("user", user);
		return true;
		
	}
	

}
