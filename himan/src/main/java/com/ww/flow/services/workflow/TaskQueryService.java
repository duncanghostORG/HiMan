package com.ww.flow.services.workflow;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskQueryService {
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;

	/**
	 * 获取未签收的任务查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	@Transactional(readOnly = true)
	public TaskQuery createUnsignedTaskQuery(String userId) {
		TaskQuery taskCandidateUserQuery = taskService.createTaskQuery().processDefinitionKey(getProcessDefKey()).taskCandidateUser(userId);

		return taskCandidateUserQuery;
	}

	/**
	 * 获取正在处理的任务查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	@Transactional(readOnly = true)
	public TaskQuery createTodoTaskQuery(String userId) {
		TaskQuery taskAssigneeQuery = taskService.createTaskQuery().processDefinitionKey(getProcessDefKey()).taskAssignee(userId);
		return taskAssigneeQuery;

	}

	private String getProcessDefKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取未经完成的流程实例查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	@Transactional(readOnly = true)
	public ProcessInstanceQuery createUnFinishedProcessInstanceQuery(String userId) {
		ProcessInstanceQuery unfinishedQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(getProcessDefKey()).active();
		return unfinishedQuery;
	}

	/**
	 * 获取已经完成的流程实例查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	@Transactional(readOnly = true)
	public HistoricProcessInstanceQuery createFinishedProcessInstanceQuery(String userId) {
		HistoricProcessInstanceQuery finishedQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(getProcessDefKey()).finished();
		return finishedQuery;
	}

}
