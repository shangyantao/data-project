package com.sap.data.app.schedule;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sap.data.app.ws.TaskResourceClient;
import com.sap.data.app.ws.dto.TaskDTO;

public class TimerExecutorJob extends TimerTask {

	private TaskResourceClient rs;

	public TimerExecutorJob() {
		rs=new TaskResourceClient();
		rs.setBaseUrl("http://localhost:8080/data-app/rs");
	}

	@Override
	public void run() {
		List<TaskDTO> list = rs.findTasks();

		System.out.println(list.size() + "==============");
		for (TaskDTO taskDTO : list) {

			System.out.println("----------------");
			TaskSpringCronJob taskSpringCronJob = new TaskSpringCronJob(taskDTO);
			try {
				taskSpringCronJob.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		long delay = 1 * 1000;
		long period = 1000;
		// 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
		timer.schedule(new TimerExecutorJob(), delay, period);
	}
}
