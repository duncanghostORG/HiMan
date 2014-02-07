package com.ww.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger LOG = Logger.getLogger(MyExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String viewName = determineViewName(ex, request);
		LOG.info("error ,   handle by NaoSimpleMappingExceptionResolver viewname:" + viewName);
		if (viewName != null) {
			return getModelAndView(viewName, ex, request);
		} else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("error");
			return mav;
		}
	}

}
