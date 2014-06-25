package com.sap.data.app.dao.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.sap.data.app.entity.event.E0001;

@Component
public class E0001DaoImpl implements E0001DaoCustom{

	private static final String QUERY_PO_BY_CODE = "select e from E0001 e where e.relGroup=? and e.relCode=? and e.itemsForRelease=?";

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<E0001> findPOByCode(String relGroup, String relCode,
			String itemsForRelease) {
		System.out.println("33333333333333"+relGroup);
		List<E0001> es=em.createQuery(QUERY_PO_BY_CODE).setParameter(1, relGroup).setParameter(2, relCode).setParameter(3, itemsForRelease).getResultList();
		return es;
	}

}
