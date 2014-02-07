package com.ww.controllers.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ww.pojo.Group;
import com.ww.pojo.User;
import com.ww.services.UserServices;

@Controller
public class UserController {
	@Autowired
	private UserServices userservice;

	@RequestMapping("/")
	public String index() {
		System.out.println("hahah");
		return "";
	}

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public ModelAndView showUserAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new User());
		mav.setViewName("user/user-input");
		return mav;
	}

	@RequestMapping(value = "group/add", method = RequestMethod.GET)
	public ModelAndView showGroupAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("group", new Group());
		mav.setViewName("user/group-input");
		return mav;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userservice.save(user);
		redirectAttributes.addFlashAttribute("message", "Successfully added..");
		return "redirect:/user/add";
	}

	@RequestMapping(value = "group/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute Group group, RedirectAttributes redirectAttributes) {
		userservice.saveGroup(group);
		redirectAttributes.addFlashAttribute("message", "Successfully added..");
		return "redirect:/group/add";
	}

	@ModelAttribute("groupmap")
	public List<Group> getGroups() {
		List<Group> listgroup = userservice.getAllGroups();
	 

		return listgroup;
	}

	@RequestMapping("alluser")
	public String list() {
		List<User> userlist = userservice.getAllUsers();
		return "user/list";
	}

}
