package com.ww.controllers.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlowController {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private TaskService taskservice;
	@Autowired
	private HistoryService historyService;

	@Autowired
	private FormService formService;

	@RequestMapping("tasklist")
	public String tasklist() {
		return "list";
	}

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public void start() {

		Map<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put("name", "Miss Piggy");
		processVariables.put("income", 100l);
		processVariables.put("loanAmount", 10l);
		processVariables.put("emailAddress", "miss.piggy@localhost");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("loanProcess",processVariables);
		 
		// Task task = taskservice.createTaskQuery().singleResult();
		// System.out.println("Complete order"+ task.getName());
		// taskservice.complete(task.getId());
		// System.out.println("done");

		List<FormProperty> propList = formService.getStartFormData(processInstance.getProcessDefinitionId()).getFormProperties();
		System.out.println("*************" + propList.size());
		
		Map<String, String> formProperties = new HashMap<String, String>();
		formProperties.put("name", "Miss Piggy");
		formProperties.put("emailAddress", "piggy@localhost");
		formProperties.put("income", "400");
		formProperties.put("loanAmount", "100");
		formService.submitStartFormData(processInstance.getProcessDefinitionId(), formProperties);
	}
}
