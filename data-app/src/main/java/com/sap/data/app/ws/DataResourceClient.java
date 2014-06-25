package com.sap.data.app.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sap.data.app.ws.dto.DataDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Component
public class DataResourceClient {

	private WebResource client;

	public void setBaseUrl(String baseUrl) {
		client = Client.create().resource(baseUrl);
	}
	
	public void postData(Map<String,Object> mapData){
		
		DataDTO dataDTO=new DataDTO();
		dataDTO.setData(mapData);
		client.path("data/postData").type(MediaType.APPLICATION_JSON).post(DataDTO.class, dataDTO);
	}
	
	public static void main(String[] args) {
		
		DataResourceClient client=new DataResourceClient();
		client.setBaseUrl("http://localhost:8080/data-app/rs");
		Map<String,Object> mapData=new HashMap<String, Object>();
		mapData.put("ssss", new String("xx"));
		client.postData(mapData);
	}
}
