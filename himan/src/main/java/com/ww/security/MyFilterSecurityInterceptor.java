package com.ww.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.ww.security.util.SecurityConstants;
import com.ww.security.util.SecurityMetadataSourceTrustListHolder;
import com.ww.security.util.SecurityUserTrustListHolder;
import com.ww.security.util.SecutiryRequestUtil;
import com.ww.security.util.SessionUserDetailsUtil;

public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	private static Logger LOG = Logger.getLogger(MyFilterSecurityInterceptor.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// String url =
		// httpRequest.getRequestURI().replaceFirst(httpRequest.getContextPath(),
		// "");
		String url = httpRequest.getRequestURI();
		// 1.1）判断登陆状态：如果未登陆则要求登陆
		if (!SessionUserDetailsUtil.isLogined()) {
			LOG.info("未登陆用户，From IP:" + SecutiryRequestUtil.getRequestIp(httpRequest) + "访问 ：URI" + url);
			String redirecturl = httpRequest.getContextPath() + SecurityConstants.LOGIN_URL;
			LOG.info("Redirct to:" + redirecturl);
			httpResponse.sendRedirect(redirecturl);

			return;
		}

		// 1.2）过用户白名单：如果为超级管理员，则直接执行
		if (SecurityUserTrustListHolder.isTrustUser(SessionUserDetailsUtil.getLoginUserName())) {
			chain.doFilter(request, response);
			return;
		}

		// 1.3）过资源(URL)白名单：如果为公共页面，直接执行
		if (SecurityMetadataSourceTrustListHolder.isTrustSecurityMetadataSource(url)) {
			chain.doFilter(request, response);
			return;
		}

		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);

	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		// @2,进行安全验证
		InterceptorStatusToken token = null;
		try {
			token = super.beforeInvocation(fi);
		} catch (IllegalArgumentException e) {
			HttpServletRequest httpRequest = fi.getRequest();
			HttpServletResponse httpResponse = fi.getResponse();
			String url = httpRequest.getRequestURI().replaceFirst(httpRequest.getContextPath(), "");
			LOG.info("用户 " + SessionUserDetailsUtil.getLoginUserName() + "，From IP:" + SecutiryRequestUtil.getRequestIp(httpRequest) + "。尝试访问未授权(或者) URI:"
					+ url);
			httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(SecurityConstants.NOT_ACCEPTABLE);
			dispatcher.forward(httpRequest, httpResponse);
			return;
		}
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;

	}

}
