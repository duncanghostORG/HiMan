package com.ww.controllers.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ww.pojo.User;

@Controller
public class PersonalController {
	private static final Logger LOG = Logger.getLogger(PersonalController.class);
	@Autowired
	private TaskService taskService;
	 
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		LOG.info("access login in");
		ModelAndView mav = new ModelAndView();
		User user = new User();
		mav.addObject("user", user);
		mav.setViewName("sec/login");
		return mav;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView handleLogin(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		LOG.info(name + " login in");
		 
		List<Task> tasklist = taskService.createTaskQuery().processDefinitionKey(getprocess()).taskAssignee(name).orderByTaskCreateTime().asc().list();
		List<String> tasknamelist = new ArrayList<String>();
		for (Task t : tasklist) {
			tasknamelist.add(t.getName());
			 
		}
		ModelAndView ma = new ModelAndView();
		ma.addObject("tasks", tasknamelist);
		ma.addObject("username", name);
		ma.addObject("taskno", tasknamelist.size());
		
		ma.setViewName("sec/mytasks");
		return ma;

	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		return "sec/logout";
	}
	private String getprocess() {
		// TODO Auto-generated method stub
		return null;
	}
}
