package com.sap.data.app.entity.event;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.sap.data.app.entity.IdEntity;

@Entity
@Table(name="event_E0001")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class E0001 extends IdEntity {

	private String relGroup;
	private String relCode;
	private String itemsForRelease;
	private String poNumber;
	private String vendor;
	public String getRelGroup() {
		return relGroup;
	}
	public void setRelGroup(String relGroup) {
		this.relGroup = relGroup;
	}
	
	public String getRelCode() {
		return relCode;
	}
	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}
	public String getItemsForRelease() {
		return itemsForRelease;
	}
	public void setItemsForRelease(String itemsForRelease) {
		this.itemsForRelease = itemsForRelease;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	
}
