package com.sap.data.app.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sap.data.app.dao.event.E0001Dao;
import com.sap.data.app.dao.event.E0001DaoCustom;
import com.sap.data.app.entity.event.E0001;

//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class E0001Manager {
	
	private E0001Dao e0001Dao;
	
	private E0001DaoCustom e0001DaoCustom;
	
	

	public List<E0001> getAllPO() {
		return (List<E0001>) e0001Dao.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public List<E0001> findPOByCode(String relGroup,String relCode,String itemsForRelease) {
		return e0001DaoCustom.findPOByCode(relGroup,relCode,itemsForRelease);
	}

	@Transactional(readOnly = false)
	public void saveE0001(E0001 entity) {
		 e0001Dao.save(entity);
	}
	
	@Autowired
	public void setE0001Dao(E0001Dao e0001Dao) {
		this.e0001Dao = e0001Dao;
	}
	@Autowired
	public void setE0001DaoCustom(E0001DaoCustom e0001DaoCustom) {
		this.e0001DaoCustom = e0001DaoCustom;
	}
}
