package com.ww.security.util;

import java.util.ArrayList;
import java.util.List;

public class SecurityUserTrustListHolder {
	private static final List<String> userTrustList = new ArrayList<String>();

	public void setTrustList(ArrayList<String> trustList) {
		userTrustList.clear();
		for (String s : trustList) {
			userTrustList.add(s);
		}
	}

	public static Boolean isTrustUser(String user) {
		return userTrustList.contains(user);
	}
}
