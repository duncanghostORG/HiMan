package com.ww.controllers.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ww.pojo.User;

@Controller
public class PersonalController {
	private static final Logger LOG = Logger
			.getLogger(PersonalController.class);
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		LOG.info("access login in");
		ModelAndView mav = new ModelAndView();
		User user = new User();
		String sign = request.getParameter("sign");
		if (sign != null) {
			mav.addObject("sign", "Login Failed:" + sign);
		}
		mav.addObject("user", user);
		mav.setViewName("sec/login");
		return mav;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView handleLogin(HttpServletRequest request) {
		LOG.info("******************login post*************************");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		LOG.info(name + " login in");

		List<Task> tasklist = taskService.createTaskQuery()
				.processDefinitionKey(getprocess()).taskAssignee(name)
				.orderByTaskCreateTime().asc().list();
		List<String> tasknamelist = new ArrayList<String>();
		for (Task t : tasklist) {
			tasknamelist.add(t.getName());

		}
		ModelAndView ma = new ModelAndView();
		ma.addObject("tasks", tasknamelist);
		ma.addObject("username", name);
		ma.addObject("taskno", tasknamelist.size());

		ma.setViewName("home");
		return ma;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		return "sec/logout";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		 
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
	   String username= securityContextImpl.getAuthentication().getName();
		// 登录密码，未加密的
		System.out.println("Credentials:"
				+ securityContextImpl.getAuthentication().getCredentials());
		WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl
				.getAuthentication().getDetails();



		//String username = authentication.getName();
		ModelAndView ma = new ModelAndView();
		ma.addObject("username", username);
		ma.setViewName("home");
		return ma;
	}

	private String getprocess() {
		// TODO Auto-generated method stub
		return null;
	}
}
