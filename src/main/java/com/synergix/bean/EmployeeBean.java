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
	
	public void create(){
		//input code first 
		
	}
	public void close(){
		//back to summary
	}
	
	public void submit(){
		// change status from Draft ('D') --> outstanding ('O')
		// have confirmation mess
		
	}
	public void abort(){
		//delete then back to summary
		// have confirmation mess
	}
	
}
