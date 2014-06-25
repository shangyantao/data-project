package com.sap.data.app.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

//JPA 基类的标识
@MappedSuperclass
public abstract class UuidEntity {

	protected String uuid;

	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}