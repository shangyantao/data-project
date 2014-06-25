package com.sap.data.app.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sap.data.app.dao.task.TaskDao;
import com.sap.data.app.dao.task.TaskDaoCustom;
import com.sap.data.app.dao.task.TaskLogDao;
import com.sap.data.app.entity.task.Task;
import com.sap.data.app.entity.task.TaskLog;

@Component
@Transactional(readOnly = true)
public class TaskManager {

	private TaskDaoCustom taskDaoCustom;
	private TaskDao taskDao;
	private TaskLogDao taskLogDao;

	public List<Task> findTasksByStatus(String status) {
		
		return taskDaoCustom.findTasksByStatus(status);
	}
	
	public List<Task> findTasksforRS(){
	
		return taskDaoCustom.findTasksforRS();
	}
	
    public Task getTaskById(Long id) {
		
		return taskDao.findOne(id);
	}
	public List<Task> findAll(){
		
		return (List<Task>)taskDao.findAll();
	}
	
	public List<TaskLog> findAllTaskLog(Long id){
		Task task=this.getTaskById(id);
		return task.getListTaskLogs();
	}
	@Transactional(readOnly = false)
	public void saveTask(Task task){
		
		taskDao.save(task);
	}
	
	

	/**
	 * 删除任务
	 */
	@Transactional(readOnly = false)
	public void deleteTask(Long id) {
		taskDao.delete(id);
	}
	
	
	
	@Autowired
	public void setTaskDaoCustom(TaskDaoCustom taskDaoCustom) {
		this.taskDaoCustom = taskDaoCustom;
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	@Autowired
	public void setTaskLogDao(TaskLogDao taskLogDao) {
		this.taskLogDao = taskLogDao;
	}
	
	
	
}
