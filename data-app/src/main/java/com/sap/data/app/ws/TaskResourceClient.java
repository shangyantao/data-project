package com.sap.data.app.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sap.data.app.ws.dto.DataDTO;
import com.sap.data.app.ws.dto.TaskDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Component
public class TaskResourceClient {

	private WebResource client;

	private GenericType<List<TaskDTO>> taskListType = new GenericType<List<TaskDTO>>() {
	};

	public void setBaseUrl(String baseUrl) {
		client = Client.create().resource(baseUrl);
	}

	public List<TaskDTO> findTasks() {
		return client.path("/task/search").accept(MediaType.APPLICATION_JSON)
				.get(taskListType);
	}

	public void postData(DataDTO dataDTO) {

		client.path("/task/data").accept(MediaType.APPLICATION_JSON)
				.entity(dataDTO, MediaType.APPLICATION_JSON).put(DataDTO.class);
	}

	public void updateTaskStatus(TaskDTO taskDTO) {

		client.path("/task/updateTaskStatus").accept(MediaType.APPLICATION_JSON)
		.entity(taskDTO, MediaType.APPLICATION_JSON).put(TaskDTO.class);

	}

	public static void main(String[] args) {
		
		TaskResourceClient client = new TaskResourceClient();
		client.setBaseUrl("http://localhost:8080/data-app/rs");
		TaskDTO dto = new TaskDTO();
		dto.setId(new Long(2));
		dto.setTaskStatus(TaskConstant.TASK_STATUS_HAVE_STARTED);
		client.updateTaskStatus(dto);

		/*TaskResourceClient client = new TaskResourceClient();
		client.setBaseUrl("http://localhost:8080/data-app/rs");
		HashMap<String, Object> mapData = new HashMap<String, Object>();
		TaskDTO dto = new TaskDTO();
		dto.setEventType("E0002");
		dto.setInputParas("relGroup=02;relCode=PU;itemsForRelease=x");
		dto.setTaskPriority(1);
		mapData.put("task", dto);
		client.postData(mapData);*/

		/*
		 * List<TaskDTO> list =client.findTasks(); System.out.println(list);
		 */

		/*
		 * System.getProperties().setProperty("http.proxySet", "true");
		 * System.getProperties().setProperty("http.proxyHost",
		 * "proxy.pal.sap.corp");
		 * System.getProperties().setProperty("http.proxyPort", "8080");
		 * 
		 * TaskResourceClient client=new TaskResourceClient();
		 * client.setBaseUrl("http://115.28.205.21:8080/data-app/rs");
		 * ClientResponse clientResponse =client.findTasks();
		 * 
		 * System.out.println(clientResponse);
		 */

		/*
		 * HashMap<String,String> mapParas=new HashMap<String,String>(); String
		 * paras="relGroup=02;relCode=PU;itemsForRelease=x"; String
		 * inputParas[]=paras.split(";"); for (String elem : inputParas) {
		 * String elems[]=elem.split("="); mapParas.put(elems[0], elems[1]); }
		 */
		/*
		 * String paras="relGroup=02;relCode=PU;itemsForRelease=x";
		 * Map<String,String> mapParas=Utils.parasToMap(paras);
		 * 
		 * Set<Map.Entry<String,String>> setParas=mapParas.entrySet(); for
		 * (Map.Entry<String, String> entry : setParas) {
		 * System.out.println("Key:"+entry.getKey());
		 * System.out.println("Value:"+entry.getValue()); }
		 */

	}

}
