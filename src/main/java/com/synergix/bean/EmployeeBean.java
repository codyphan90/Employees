package com.synergix.bean;
import model.Employee;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class EmployeeBean {
	@Inject
    EntityManager entityManager;
	
	private List<Employee> employees;
	private Employee selectedEmployee;
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
	
	
	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	
	public void onRowSelectNavigate(SelectEvent event) {
		System.out.println("Navigation executed");
		ConfigurableNavigationHandler configurableNavigationHandler =
    			(ConfigurableNavigationHandler)FacesContext.
    			  getCurrentInstance().getApplication().getNavigationHandler();
    	
    	  configurableNavigationHandler.performNavigation("detail.xhtml?faces-redirect=true");
        
    }
	public void redirectNew(SelectEvent event) {
		System.out.println("Navigation executed");
		ConfigurableNavigationHandler configurableNavigationHandler =
    			(ConfigurableNavigationHandler)FacesContext.
    			  getCurrentInstance().getApplication().getNavigationHandler();
    	
    	  configurableNavigationHandler.performNavigation("new.xhtml?faces-redirect=true");
        
    }
	public void create(ActionEvent actionEvent) {
		//input code first 
        addMessage("Welcome to Primefaces!!");
    }
	public void close(ActionEvent actionEvent) {
		//back to summary
    }
	public void close(){
		//back to summary
	}
	public void submit(ActionEvent actionEvent) {
		// change status from Draft ('D') --> outstanding ('O')
				// have confirmation mess
    }
		
	public void abort(ActionEvent actionEvent){
		//delete then back to summary
		// have confirmation mess
	}
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
