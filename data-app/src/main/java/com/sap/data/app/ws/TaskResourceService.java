package com.sap.data.app.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.data.app.entity.task.Task;
import com.sap.data.app.entity.task.TaskLog;
import com.sap.data.app.service.task.TaskManager;
import com.sap.data.app.ws.dto.DataDTO;
import com.sap.data.app.ws.dto.TaskDTO;
import com.sap.data.core.mapper.BeanMapper;
import com.sap.data.core.rest.RsResponse;
import com.sap.data.ws.WsConstants;

@Component
@Path("/task")
public class TaskResourceService {

	private TaskManager taskManager;

	@GET
	@Path("/search")
	@Produces({ MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public List<TaskDTO> searchTasks() {
		try {
			
			List<Task> entityList = taskManager.findTasksforRS();

			List<TaskDTO> taskDTOList = BeanMapper.mapList(entityList,
					TaskDTO.class);

			for (Task task : entityList) {
				task.setTaskStatus(TaskConstant.TASK_STATUS_HAVE_STARTED);
				TaskLog taskLog=new TaskLog();
				taskLog.setTask(task);
				taskLog.setLogType(TaskConstant.TASK_LOG_TYPE_STATUS);
				taskLog.setLogTime(new Date());
				if(true==StringUtils.equalsIgnoreCase(task.getTriggerTime(), "immediate")){
					taskLog.setLogDesc("ID:"+task.getId()+"的任务已经执行了立即执行");
				}else{
					taskLog.setLogDesc("ID:"+task.getId()+"的任务已经启动");
				}
				List<TaskLog> tasklogs=new ArrayList<TaskLog>();
				tasklogs.add(taskLog);
				task.setListTaskLogs(tasklogs);
				taskManager.saveTask(task);
			}
			return taskDTOList;
		} catch (RuntimeException e) {
			throw RsResponse.buildDefaultException(e);
		}
	}

	@PUT
	@Path("/updateTaskStatus")
	@Produces({ MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public TaskDTO updateTaskStatus(TaskDTO taskDTO) {

		Task task = taskManager.getTaskById(taskDTO.getId());
		// System.out.println(task);
		task.setTaskStatus(taskDTO.getTaskStatus());
		taskManager.saveTask(task);
		return taskDTO;

	}

	
	
	@PUT
	@Path("/data")
	@Produces({ MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public DataDTO pushData(DataDTO dataDTO) {
		TaskDTO taskDTO = dataDTO.getTaskDTO();
		Task task = taskManager.getTaskById(taskDTO.getId());
		List<TaskLog> tasklogs=new ArrayList<TaskLog>();
		Map<String, Object> mapData = dataDTO.getData();
		System.out.println("map size:"+mapData.size());
		
		if(mapData.size()==0) return dataDTO;
		
		if(mapData.containsKey("errorMessage")){
			System.out.println(String.valueOf(mapData.get("errorMessage")));
			task.setTaskStatus(TaskConstant.TASK_STATUS_UNFINISHED);
			TaskLog taskLog=new TaskLog();
			taskLog.setTask(task);
			taskLog.setLogType(TaskConstant.TASK_LOG_SAP_DATA);
			taskLog.setLogTime(new Date());
			taskLog.setLogDesc(String.valueOf(mapData.get("errorMessage")));
			tasklogs.add(taskLog);
			task.setListTaskLogs(tasklogs);
		}else{
			task.setTaskStatus(TaskConstant.TASK_STATUS_COMPLETED);
			TaskLog taskLog=new TaskLog();
			taskLog.setTask(task);
			taskLog.setLogType(TaskConstant.TASK_LOG_TYPE_STATUS);
			taskLog.setLogTime(new Date());
			taskLog.setLogDesc("ID:"+task.getId()+"的任务已经完成");
			tasklogs.add(taskLog);
			task.setListTaskLogs(tasklogs);
			
		}
		taskManager.saveTask(task);
		
		Set<Map.Entry<String, Object>> setDatas = mapData.entrySet();
		for (Map.Entry<String, Object> entry : setDatas) {
			
			System.out.println("Key:" + entry.getKey() + "Value:"
					+ entry.getValue());
		}
		
		return dataDTO;

	}

	@Autowired
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

}
