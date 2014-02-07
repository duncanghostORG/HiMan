package com.ww.pojo;

import java.io.Serializable;

public class LoanApplication implements Serializable {
	private boolean creditCheckOk;
	private String customerName;
	private long requestAmount;
	private long income;
	private String emailAddress;
	private boolean requestApproved;

	
	
	public boolean isRequestApproved() {
		return requestApproved;
	}

	public void setRequestApproved(boolean requestApproved) {
		this.requestApproved = requestApproved;
	}

	public long getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(long requestAmount) {
		this.requestAmount = requestAmount;
	}

	public boolean isCreditCheckOk() {
		return creditCheckOk;
	}

	public void setCreditCheckOk(boolean creditCheckOk) {
		this.creditCheckOk = creditCheckOk;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
