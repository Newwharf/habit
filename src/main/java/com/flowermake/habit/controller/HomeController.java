package com.flowermake.habit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.Plan;
import com.flowermake.habit.domain.Target;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IBodyDataService;
import com.flowermake.habit.service.IPlanService;
import com.flowermake.habit.service.ITargetService;
import com.flowermake.habit.service.IUserService;
import com.flowermake.habit.tools.IdWorker;

@Controller
@RequestMapping("/")
public class HomeController {
	@Resource
	private IUserService userService;

	@Resource
	private IBodyDataService bodyDataService;

	@Resource
	private ITargetService targetService;

	@Resource
	private IPlanService planService;

	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) throws Exception {
		User user = (User) request.getSession().getAttribute("user");

		// 查询并身体数据
		BodyData bodyData = bodyDataService.findBodyDataByUserId(user.getiId());
		if (bodyData == null) {
			bodyData = new BodyData();
			bodyData.setiId(new IdWorker().nextId());
			bodyData.setiUserid(user.getiId());
			bodyDataService.addBodyData(bodyData);
		}
		// 查询目标集合保存
		List<Target> targetList = targetService.findTargetByDt(user.getiId(),
				new SimpleDateFormat("yyyyMMdd").parse("19900101"), new Date(), 0, 3);

		// 查询训练集合
		List<Plan> planList = planService.findPlansByUserId(user.getiId(), 0, 100);

		request.setAttribute("targetList", targetList);
		request.setAttribute("planlist", planList);
		request.getSession().setAttribute("bodyData", bodyData);

		return "home";
	}

}
