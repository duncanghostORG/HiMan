package com.ww.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlacehoderConfiguer extends PropertyPlaceholderConfigurer {
	private static final Logger LOG = Logger.getLogger(EncryptPropertyPlacehoderConfiguer.class);

	// private String[] encryptPropNames = {"ldap.principal","ldap.password"};
	private String[] encryptPropNames = { "" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#
	 * convertProperty(java.lang.String, java.lang.String)
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		String decryptValue = "";
		if (isEncryptProp(propertyName)) {
			LOG.debug("propertyName:" + propertyName);
			LOG.debug("propertyValue:" + propertyValue);
			try {
				// decryptValue = AESUtils.getDecryptString(propertyValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			decryptValue = propertyValue;
		}
		LOG.debug("decryptValue:" + decryptValue);
		return decryptValue;

	}

	private boolean isEncryptProp(String propertyName) {
		for (String str : encryptPropNames) {
			if (str.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
