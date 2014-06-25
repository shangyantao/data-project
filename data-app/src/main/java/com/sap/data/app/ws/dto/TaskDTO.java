package com.sap.data.app.ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskDTO {

	private Long id;
	private String eventType;
	private int userId;
	private String triggerTime;
	private int taskPriority;
	private String taskStatus;
	private String inputParas;
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
	public String getInputParas() {
		return inputParas;
	}
	public void setInputParas(String inputParas) {
		this.inputParas = inputParas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
