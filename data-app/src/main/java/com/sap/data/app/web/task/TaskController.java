package com.sap.data.app.web.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sap.data.app.entity.task.Task;
import com.sap.data.app.entity.task.TaskLog;
import com.sap.data.app.service.task.TaskManager;
import com.sap.data.app.ws.TaskConstant;

@Controller
@RequestMapping(value = "/task")
public class TaskController {

	@Autowired
	TaskManager taskManager;
	
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		
		List<Task> tasks=taskManager.findAll();
		model.addAttribute("tasks", tasks);
		
		return "task/taskList";
	}
	
	@RequestMapping(value = "logs/{id}")
	public String listLogs(Model model,@PathVariable("id") Long id) {
		List<TaskLog> taskLogs=taskManager.findAllTaskLog(id);
		model.addAttribute("taskLogs", taskLogs);
		return "task/logList";
	} 
	
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("task", new Task());
		return "task/taskForm";
	} 
	
	@RequestMapping(value = "save")
	public String save(Task task, RedirectAttributes redirectAttributes) {
		task.setTaskStatus(TaskConstant.TASK_STATUS_NOT_STARTED);
		taskManager.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "创建任务" + task.getId()+ "成功");
		return "redirect:/task/list/";
	}
	
	@RequestMapping(value = "update/{id}")
	public String updateForm(Model model,@PathVariable("id") Long id) {
		Task task=taskManager.getTaskById(id);
		model.addAttribute("task", task);
		return "task/updateForm";
	}
	
	@RequestMapping(value = "updateTask")
	public String updateTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
		taskManager.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "修改任务" + task.getId()+ "成功");
		return "redirect:/task/list/";
	}
	
	@RequestMapping(value = "execute/{id}")
	public String executeTask(Model model,@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Task task=taskManager.getTaskById(id);
		task.setTriggerTime(TaskConstant.TASK_ACTION_IMMEDIATE);
		task.setTaskStatus(TaskConstant.TASK_STATUS_NOT_STARTED);
		taskManager.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "已经成功发出执行任务ID为"+task.getId()+"的命令" );
		return "redirect:/task/list/";
	}
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		taskManager.deleteTask(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/task/list/";
	}
	
	@RequestMapping(value = "copy/{id}")
	public String copyTask(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Task task=taskManager.getTaskById(id);
		Task copyTask=new Task();
		copyTask.setEventType(task.getEventType());
		copyTask.setInputParas(task.getInputParas());
		copyTask.setTaskPriority(task.getTaskPriority());
		copyTask.setTaskStatus(TaskConstant.TASK_STATUS_NOT_STARTED);
		copyTask.setTriggerTime(task.getTriggerTime());
		copyTask.setUserId(task.getUserId());
		taskManager.saveTask(copyTask);
		redirectAttributes.addFlashAttribute("message", "复制任务成功");
		return "redirect:/task/list/";
	}
}
