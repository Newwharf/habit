package com.flowermake.habit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flowermake.habit.domain.BodyData;
import com.flowermake.habit.domain.BodyDataLog;
import com.flowermake.habit.domain.User;
import com.flowermake.habit.service.IBodyDataLogService;
import com.flowermake.habit.service.IBodyDataService;
import com.flowermake.habit.service.IUserService;
import com.flowermake.habit.tools.Commons;
import com.flowermake.habit.tools.IdWorker;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@Resource
	private IBodyDataLogService bodyDataLogService;

	@Resource
	private IBodyDataService bodyDataService;

	@RequestMapping("/tosignup")
	public String toSignup(HttpServletRequest request, Model model) {
		return "tosignup";
	}

	@RequestMapping("/userinformation")
	public String toUserInformation(HttpServletRequest request, Model model) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		BodyData bodyData = bodyDataService.findBodyDataByUserId(user.getiId());
		request.setAttribute("bodyData", bodyData);
		return "userinformation";
	}

	@RequestMapping("/edituserinformation")
	public void editUserInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 获取当前用户以及身体数据
		User user = (User) request.getSession().getAttribute("user");
		BodyData bodyData = bodyDataService.findBodyDataByUserId(user.getiId());

		// 从请求中取出用户数据
		String name = request.getParameter("username");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String headimg = request.getParameter("headimg");

		// 组装用户对象
		user.setvImgurl(headimg);
		user.setvName(name);
		user.setdBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		user.setTiSex(new Byte(sex));

		// 从请求中取出身体数据数据
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String bodyfat = request.getParameter("bodyfat");
		String shouldersize = request.getParameter("shouldersize");
		String bust = request.getParameter("bust");
		String abdominalsize = request.getParameter("abdominalsize");
		String waistline = request.getParameter("waistline");
		String hipline = request.getParameter("hipline");
		String larmsize = request.getParameter("larmsize");
		String lforearmsize = request.getParameter("lforearmsize");
		String rarmsize = request.getParameter("rarmsize").toString();
		String rforearmsize = request.getParameter("rforearmsize");
		String lthighsize = request.getParameter("lthighsize");
		String lcrussize = request.getParameter("lcrussize");
		String rthighsize = request.getParameter("rthighsize");
		String rcrussize = request.getParameter("rcrussize");

		// 取出是否修改标示
		int height_isUpdate = new Integer(request.getParameter("height_isUpdate"));
		int weight_isUpdate = new Integer(request.getParameter("weight_isUpdate"));
		int bodyfat_isUpdate = new Integer(request.getParameter("bodyfat_isUpdate"));
		int shouldersize_isUpdate = new Integer(request.getParameter("shouldersize_isUpdate"));
		int bust_isUpdate = new Integer(request.getParameter("bust_isUpdate"));
		int abdominalsize_isUpdate = new Integer(request.getParameter("abdominalsize_isUpdate"));
		int waistline_isUpdate = new Integer(request.getParameter("waistline_isUpdate"));
		int hipline_isUpdate = new Integer(request.getParameter("hipline_isUpdate"));
		int larmsize_isUpdate = new Integer(request.getParameter("larmsize_isUpdate"));
		int lforearmsize_isUpdate = new Integer(request.getParameter("lforearmsize_isUpdate"));
		int rarmsize_isUpdate = new Integer(request.getParameter("rarmsize_isUpdate"));
		int rforearmsize_isUpdate = new Integer(request.getParameter("rforearmsize_isUpdate"));
		int lthighsize_isUpdate = new Integer(request.getParameter("lthighsize_isUpdate"));
		int lcrussize_isUpdate = new Integer(request.getParameter("lcrussize_isUpdate"));
		int rthighsize_isUpdate = new Integer(request.getParameter("rthighsize_isUpdate"));
		int rcrussize_isUpdate = new Integer(request.getParameter("rcrussize_isUpdate"));

		// 组装身体数据
		if (!height.equals("")) {
			bodyData.setfLastheight(new Float(height));
			if (height_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 0);
				bdl.setfScore(bodyData.getfLastheight());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!weight.equals("")) {
			bodyData.setfLastweight(new Float(weight));
			if (weight_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 1);
				bdl.setfScore(bodyData.getfLastweight());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!bodyfat.equals("")) {
			bodyData.setfLastbodyfat(new Float(bodyfat));
			if (bodyfat_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 2);
				bdl.setfScore(bodyData.getfLastbodyfat());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!shouldersize.equals("")) {
			bodyData.setfLastshouldersize(new Float(shouldersize));
			if (shouldersize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 3);
				bdl.setfScore(bodyData.getfLastshouldersize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!bust.equals("")) {
			bodyData.setfLastbust(new Float(bust));
			if (bust_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 4);
				bdl.setfScore(bodyData.getfLastbust());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!abdominalsize.equals("")) {
			bodyData.setfLastabdominalsize(new Float(abdominalsize));
			if (abdominalsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 5);
				bdl.setfScore(bodyData.getfLastabdominalsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!waistline.equals("")) {
			bodyData.setfLastwaistline(new Float(waistline));
			if (waistline_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 6);
				bdl.setfScore(bodyData.getfLastwaistline());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!hipline.equals("")) {
			bodyData.setfLasthipline(new Float(hipline));
			if (hipline_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 7);
				bdl.setfScore(bodyData.getfLasthipline());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!larmsize.equals("")) {
			bodyData.setfLastlarmsize(new Float(larmsize));
			if (larmsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 8);
				bdl.setfScore(bodyData.getfLastlarmsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!lforearmsize.equals("")) {
			bodyData.setfLastlforearmsize(new Float(lforearmsize));
			if (lforearmsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 10);
				bdl.setfScore(bodyData.getfLastlforearmsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!rarmsize.equals("")) {
			bodyData.setfLastrarmsize(new Float(rarmsize));
			if (rarmsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 9);
				bdl.setfScore(bodyData.getfLastrarmsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!rforearmsize.equals("")) {
			bodyData.setfLastrforearmsize(new Float(rforearmsize));
			if (rforearmsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 11);
				bdl.setfScore(bodyData.getfLastrforearmsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!lthighsize.equals("")) {
			bodyData.setfLastlthighsize(new Float(lthighsize));
			if (lthighsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 12);
				bdl.setfScore(bodyData.getfLastlthighsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!lcrussize.equals("")) {
			bodyData.setfLastlcrussize(new Float(lcrussize));
			if (lcrussize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 14);
				bdl.setfScore(bodyData.getfLastlcrussize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!rthighsize.equals("")) {
			bodyData.setfLastrthighsize(new Float(rthighsize));
			if (rthighsize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 13);
				bdl.setfScore(bodyData.getfLastrthighsize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}
		if (!rcrussize.equals("")) {
			bodyData.setfLastrcrussize(new Float(rcrussize));
			if (rcrussize_isUpdate == 1) {
				BodyDataLog bdl = new BodyDataLog();
				bdl.setiId(new IdWorker().nextId());
				bdl.setiUserid(user.getiId());
				bdl.setDtCdate(new Date());
				bdl.setvComments("");
				bdl.setTiIndex((byte) 15);
				bdl.setfScore(bodyData.getfLastrcrussize());
				bodyDataLogService.addBodyDataLog(bdl);
			}
		}

		userService.updateUser(user);
		bodyDataService.updateBodyData(bodyData);
		Commons.ajaxResponse(response, "success");

	}

	@RequestMapping("/signup")
	public String signup(HttpServletRequest request, Model model) throws Exception {
		User user = new User();
		// 从微信用户信息中取出昵称、头像链接、openid
		// String name = session.getAttribute("wechat_name").toString();
		// String img_url = session.getAttribute("wechat_imgurl").toString();
		// String wechat_id = session.getAttribute("wechat_id").toString();

		String name = "李平平";
		String img_url = "https://z1.muscache.cn/im/pictures/user/efb8c8e8-37da-4023-a715-95287ac7179c.jpg?aki_policy=profile_x_medium";
		String wechat_id = new IdWorker().nextId() + "";

		// 组装用户对象
		user.setvName(name);
		user.setvImgurl(img_url);
		user.setvWechatuid(wechat_id);
		user.setdBirthday(Commons.getDateByAge(Integer.valueOf(request.getParameter("age"))));
		user.setTiSex(Byte.valueOf(request.getParameter("sex")));
		user.setiId(new IdWorker().nextId());
		user.setDtCdate(new Date());

		// 保存到数据库并保存到session中
		if (userService.addUser(user) == 1) {
			request.getSession().setAttribute("user", user);
			return "redirect:../home";
		} else {
			return "error";
		}
	}
}