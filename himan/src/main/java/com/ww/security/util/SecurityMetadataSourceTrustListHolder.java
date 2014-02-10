package com.ww.security.util;

import java.util.ArrayList;
import java.util.List;

public class SecurityMetadataSourceTrustListHolder {
	private static final List<String> smsTrustList = new ArrayList<String>();

	public void setTrustList(ArrayList<String> trustList) {
		smsTrustList.clear();
		for (String s : trustList) {
			smsTrustList.add(s);
		}
	}

	public static Boolean isTrustSecurityMetadataSource(String sms) {
		return smsTrustList.contains(sms);
	}
}
