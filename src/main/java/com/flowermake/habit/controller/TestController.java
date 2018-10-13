package com.flowermake.habit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/test")
	public String home(HttpServletRequest request, Model model) throws Exception {
		return "test";
	}

}
