package com.sap.data.app.ws;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


import com.sap.data.app.ws.dto.DataDTO;

//@Component
//@Path("/data")
public class DataResourceService {

	
	    @Path("/postData")
	    @Consumes({MediaType.APPLICATION_JSON })
	    public DataDTO postData(DataDTO dataDTO){
	    	Map<String,Object> mapData=dataDTO.getData();
	    	Set<Map.Entry<String,Object>> setDatas=mapData.entrySet();
	    	for (Map.Entry<String, Object> entry : setDatas) {
	    		System.out.println("Key:"+entry.getKey()+"Value:"+entry.getValue());
			}
	    	return dataDTO;
	    } 
		
}
