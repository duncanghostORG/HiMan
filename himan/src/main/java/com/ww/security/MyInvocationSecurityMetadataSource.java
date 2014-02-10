package com.ww.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.ww.dao.CommonDao;

public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private boolean rejectPublicInvocations = false;

	private CommonDao commonDao;

	private static Map<String, Integer> resources = new HashMap<String, Integer>();

	public MyInvocationSecurityMetadataSource(CommonDao commonDao) {
		this.commonDao = commonDao;
		loadSecurityMetadataSource();
	}

	// According to a URL, Find out permission configuration of this URL.
	// 根据URL来查找所有能够访问该资源的角色。
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// guess object is a URL.
		// @3
		String url = ((FilterInvocation) object).getRequestUrl();

		if (resources.isEmpty())
			return null;
		Integer resourceId = resources.get(url);
		if (rejectPublicInvocations && resourceId == null) {
			throw new IllegalArgumentException("Secure object invocation " + object
					+ " was denied as public invocations are not allowed via this interceptor. ");// 请求不存在
		}
		return getRolesByResouceId(resourceId);
	}

	private Collection<ConfigAttribute> getRolesByResouceId(Integer resourceId) {
		List<String> roles = commonDao.getRoleByResourceId(resourceId);

		Collection<ConfigAttribute> atts = null;
		if (roles != null) {
			atts = new ArrayList<ConfigAttribute>();
			for (String role : roles) {
				atts.add(new SecurityConfig(role));
			}
		}

		return atts;
	}

	// 加载所有安全资源（URL）
	private void loadSecurityMetadataSource() {
		List<Map<String, Object>> resourceDtos = commonDao.getAllResource();
		if (resourceDtos != null) {
			resources.clear();
			for (Map<String, Object> dto : resourceDtos) {
				resources.put(dto.get("url").toString(), Integer.parseInt(dto.get("id").toString()));
			}
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	public void setRejectPublicInvocations(boolean rejectPublicInvocations) {
		this.rejectPublicInvocations = rejectPublicInvocations;
	}

	public void setDao(CommonDao dao) {
		this.commonDao = dao;
	}

}
