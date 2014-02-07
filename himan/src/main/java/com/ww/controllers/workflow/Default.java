package com.ww.controllers.workflow;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Default {
	private static final Logger LOG = Logger.getLogger(Default.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		LOG.info("Start app");
		return "index";
	}
}
