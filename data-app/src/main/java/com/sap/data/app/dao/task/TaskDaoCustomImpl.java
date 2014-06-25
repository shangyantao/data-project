package com.sap.data.app.dao.task;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.sap.data.app.entity.task.Task;

@Component
public class TaskDaoCustomImpl implements TaskDaoCustom {

	private static final String QUERY_TASK_BY_STATUS = "select t from Task t where t.taskStatus=?";
	private static final String QUERY_TASK_FOR_WS = "select t from Task t where t.taskStatus='0' order by t.taskPriority";
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Task> findTasksByStatus(String status) {

		List<Task> list = em.createQuery(QUERY_TASK_BY_STATUS)
				.setParameter(1, status).getResultList();

		return list;
	}

	@Override
	public List<Task> findTasksforRS() {

		List<Task> list = em.createQuery(QUERY_TASK_FOR_WS).getResultList();

		return list;
	}

}
