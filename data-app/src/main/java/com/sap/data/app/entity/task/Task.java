package com.sap.data.app.entity.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.sap.data.app.entity.IdEntity;

@Entity
@Table(name="task_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends IdEntity {

	private String eventType;
	private int userId;
	private String triggerTime;
	private int taskPriority;
	private String taskStatus;
	private String inputParas;
	private List<TaskLog> listTaskLogs=new ArrayList<TaskLog>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="task")
	public List<TaskLog> getListTaskLogs() {
		return listTaskLogs;
	}
	public void setListTaskLogs(List<TaskLog> listTaskLogs) {
		this.listTaskLogs = listTaskLogs;
	}
	public String getInputParas() {
		return inputParas;
	}
	public void setInputParas(String inputParas) {
		this.inputParas = inputParas;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTriggerTime() {
		return triggerTime;
	}
	public void setTriggerTime(String triggerTime) {
		this.triggerTime = triggerTime;
	}
	public int getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
