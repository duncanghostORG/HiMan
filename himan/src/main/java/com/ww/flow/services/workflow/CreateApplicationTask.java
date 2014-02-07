package com.ww.flow.services.workflow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.ww.pojo.LoanApplication;

public class CreateApplicationTask implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LoanApplication la = new LoanApplication();
		la.setCreditCheckOk((Boolean) execution.getVariable("creditCheckOk"));
		la.setCustomerName((String) execution.getVariable("name"));
		la.setRequestAmount((Long) execution.getVariable("loanAmount"));
		la.setIncome((Long) execution.getVariable("income"));
		la.setEmailAddress((String) execution.getVariable("emailAddress"));

		execution.setVariable("loanApplication", la);
	}
}
