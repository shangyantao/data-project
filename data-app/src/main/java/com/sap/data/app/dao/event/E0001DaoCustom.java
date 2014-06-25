package com.sap.data.app.dao.event;

import java.util.List;

import com.sap.data.app.entity.event.E0001;

public interface E0001DaoCustom {

	List<E0001> findPOByCode(String relGroup,String relCode,String itemsForRelease);
}
