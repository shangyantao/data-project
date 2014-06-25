package com.sap.data.app.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sap.data.app.dao.system.CompanyDao;
import com.sap.data.app.dao.system.EventDao;
import com.sap.data.app.entity.system.Company;
import com.sap.data.app.entity.system.Event;

//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class SystemManager {

	private CompanyDao companyDao;
	private EventDao eventDao;
	
	public List<Company> getAllCompany(){
		
		return (List<Company>) companyDao.findAll();
	}
	
	public List<Event> getAllEvent(){
		
		return (List<Event>)eventDao.findAll();
	}
	
	@Transactional(readOnly = false)
	public void saveCompany(Company entity){
		
		companyDao.save(entity);
	}
	
	@Transactional(readOnly = false)
	public void deleteCompany(String companyId){
		
		companyDao.delete(companyId);
	}
	
	public Event getEvent(String id){
		
		return eventDao.findOne(id);
	}
	@Transactional(readOnly = false)
	public void saveEvent(Event entity){
		
		eventDao.save(entity);
	}
	
	@Transactional(readOnly = false)
	public void deleteEvent(String eventId){
		
		eventDao.delete(eventId);
	}
	
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	
	
}
