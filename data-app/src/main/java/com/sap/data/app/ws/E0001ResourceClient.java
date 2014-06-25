package com.sap.data.app.ws;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sap.data.app.ws.dto.TaskDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Component
public class E0001ResourceClient {
	
	public static void main(String[] args) {
		
		Client client = Client.create();
		 
		WebResource webResource = client
		   .resource("http://localhost:8080/data-app/rs/task/search");
 
		ClientResponse response = webResource.accept("application/xml")
                   .get(ClientResponse.class);
		System.out.println(response);
		
		List<TaskDTO> list = response.getEntity(new GenericType<List<TaskDTO>>(){});
		System.out.println(list);
		 
 
		/*if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
 
		List<E0001DTO> list = response.getEntity(List.class);
 
		System.out.println("Output from Server .... \n"+list);*/
		
	}

}
