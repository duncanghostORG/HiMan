package com.ww.controllers.workflow;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ww.exceptions.BizException;
import com.ww.pojo.SRole;
import com.ww.pojo.SUser;
import com.ww.services.UserServices;

@Controller
public class UserController {
	@Autowired
	private UserServices userservice;

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public ModelAndView showUserAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new SUser());
		mav.setViewName("user/user-input");
		return mav;
	}

	@RequestMapping(value = "role/add", method = RequestMethod.GET)
	public ModelAndView showGroupAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("group", new SRole());
		mav.setViewName("user/role-input");
		return mav;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute SUser user, RedirectAttributes redirectAttributes) throws BizException {
		user.setCreate_by("wang");
		user.setUpdated_by("wang");
		user.setEnable("1");
		userservice.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "Successfully added..");
		return "redirect:/user/add";
	}

	@RequestMapping(value = "role/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute SRole role, RedirectAttributes redirectAttributes) {
		role.setCreate_by("william");
		role.setUpdated_by("william");
		userservice.saveRole(role);
		redirectAttributes.addFlashAttribute("message", "Successfully added..");
		return "redirect:/role/add";
	}

	@ModelAttribute("rolemap")
	public List<SRole> getRoles() {
		List<SRole> listgroup = userservice.getAllRoles();
		if (listgroup == null || listgroup.size() == 0) {
			listgroup = new ArrayList<SRole>();
			SRole role = new SRole();
			listgroup.add(role);

		}

		return listgroup;
	}

	@RequestMapping("alluser")
	public String list() {
		List<SUser> userlist = userservice.getAllUsers();
		return "user/list";
	}

	@InitBinder
	public static void registerDoubleFormat(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.TYPE, new CustomerIntegerEditor());
		 
	 
	}

	private static class CustomerIntegerEditor extends PropertyEditorSupport {
		public String getAsText() {
			Integer d = (Integer) getValue();
			return d.toString();
		}

		public void setAsText(String str) {
			if (str == null || str.equals(""))
				setValue(0);
			else
				setValue(Integer.parseInt(str));
		}
	}

}
