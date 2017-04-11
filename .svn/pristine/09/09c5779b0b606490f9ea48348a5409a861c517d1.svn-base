package com.synergix.training;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.synergix.training.entity.Mt_employee;

@Named
@Transactional
@ConversationScoped
public class HelloWorldBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager entityManager;

	public String getEmployeeName() {
		String str = "SELECT e FROM Mt_employee e";

		List<Mt_employee> employees = entityManager.createQuery(str, Mt_employee.class).getResultList();

		return employees.isEmpty() ? "NOT FOUND" : employees.get(0).getEmployee_name();
	}
}
