package com.synergix.bean;
import model.Employee;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ManagedBean
@SessionScoped
public class EmployeeBean {
	@Inject
    EntityManager entityManager;
	
	private List<Employee> employees;
	
	@PostConstruct
	public void init(){
		String str = "SELECT n FROM Employee n";
		employees = entityManager.createQuery(str, Employee.class).getResultList();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
