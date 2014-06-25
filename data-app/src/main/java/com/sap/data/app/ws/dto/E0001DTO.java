package com.sap.data.app.ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Web Service传输User信息的DTO.
 * 
 * 只传输外部接口需要的属性.使用JAXB 2.0的annotation标注JAVA-XML映射,尽量使用默认约定.
 * 
 */
//@XmlType(name = "e0001", namespace = WsConstants.NS)
@XmlRootElement(name="e0001")
public class E0001DTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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



	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
