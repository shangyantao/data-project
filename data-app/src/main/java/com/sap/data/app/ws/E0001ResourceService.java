package com.sap.data.app.ws;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.data.app.entity.event.E0001;
import com.sap.data.app.service.event.E0001Manager;
import com.sap.data.app.ws.dto.E0001DTO;
import com.sap.data.core.mapper.BeanMapper;
import com.sap.data.core.rest.RsResponse;
import com.sap.data.ws.WsConstants;

@Component
@Path("/e0001")
public class E0001ResourceService {

	
	private E0001Manager e0001Manager;

	@Context
	private UriInfo uriInfo;
	
	/**
	 * 查询用户, 请求数据为URL中的请求参数, 返回用户列表.
	 */
	@GET
	@Path("/search")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET })
	@Produces(MediaType.APPLICATION_XML)
	public List<E0001DTO> searchPOS() {
		try {
			List<E0001> entityList = e0001Manager.getAllPO();

			System.out.println(entityList+"0000000000000");
			return BeanMapper.mapList(entityList, E0001DTO.class);
			//String output="hello";
			//return Response.status(200).entity(output).build();
		} catch (RuntimeException e) {
			throw RsResponse.buildDefaultException(e);
		}
	}
	
	/**
	 * 创建PO, 请求数据为POST过来的JSON/XML格式编码的DTO, 返回表示所创建PO的URI.
	 */
	@POST
	@Path("/create")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public Response create(E0001DTO e0001DTO) {
		//转换并创建PO
	
			E0001 e0001Entity = BeanMapper.map(e0001DTO, E0001.class);

			e0001Manager.saveE0001(e0001Entity);

			URI createdUri = uriInfo.getAbsolutePathBuilder().path(e0001Entity.getPoNumber().toString()).build();
			return Response.created(createdUri).build();
		
	}
	
	

	@Autowired
	public void setE0001Manager(E0001Manager e0001Manager) {
		this.e0001Manager = e0001Manager;
	}
	
	
}
