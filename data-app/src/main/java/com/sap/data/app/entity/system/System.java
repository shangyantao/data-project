package com.sap.data.app.entity.system;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//表名与类名不相同时重新定义表名.
//@Table(name = "DM_SYSTEM")
//默认的缓存策略.
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class System {

	private String systemDes;
	private String comments;
	 
}
