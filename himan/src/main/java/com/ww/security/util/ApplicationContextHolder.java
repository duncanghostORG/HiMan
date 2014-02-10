package com.ww.security.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appCtx = applicationContext;

	}

	/**
	 * 这是一个便利的方法，帮助我们快速得到一个BEAN
	 * 
	 * @param beanName
	 *            bean的名字
	 * @return 返回一个bean对象
	 */
	public static Object getBean(String beanName) throws Exception {
		if (appCtx == null) {
			throw new Exception("ApplicationContext do not success inject");
		}
		return appCtx.getBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}
}
