package com.synergix.bean;

import model.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.primefaces.event.SelectEvent;

//@ManagedBean
@Named
//@ConversationScoped
@ApplicationScoped
//@SessionScoped
//@Transactional
public class EmployeeBean {
	@Inject
	EntityManager entityManager;

	private List<Employee> employees;
	private Employee selectedEmployee;
	private Employee newEmployee;



	public Employee getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}

	@PostConstruct
	public void init() {
		selectedEmployee = new Employee();
		
		String str = "SELECT e FROM Employee e";
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
		System.out.println("Go to detail.xhtml");
		goToPage("detail.xhtml");
	}

	
	public void goToPage(String toPage){
		//using with p:commandbutton
		ConfigurableNavigationHandler confNaviHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();
		confNaviHandler.performNavigation(toPage + "?faces-redirect=true");
//		confNaviHandler.performNavigation(toPage);
	}

	public void createEmployee() {
		// input code first
		selectedEmployee = new Employee();
		goToPage("detail.xhtml");
//		goToPage("new.xhtml");
	}

	

	
	public void close() {
		String str = "SELECT e FROM Employee e";
		employees = entityManager.createQuery(str, Employee.class).getResultList();
		// back to summary
		System.out.println("go to summary page");
		goToPage("summary.xhtml");
	}
	public void submit() {
		// change status from Draft ('D') --> outstanding ('O')
		// have confirmation mess
		// selectedEmployee.setStatus(Employee.STATUS_OUTSTANDING);
		System.out.println("Info submit...");
		addMessage("Info saving!!");
//		employees.add(newEmployee);
		entityManager.persist(newEmployee);
		entityManager.flush();
		addMessage("Info saved!!");
		
//		goToPage("summary.xhtml");
		
	}

	public void abort(ActionEvent actionEvent) {
		// delete then back to summary
		// have confirmation mess
		System.out.println("Remove employee: " + this.selectedEmployee.getEmployeeCode());
		entityManager.remove(this.selectedEmployee);
	}
	
	// check Code is filled?
		public boolean checkInputedCode() {
			
			if (this.selectedEmployee.getEmployeeCode() != null && !this.selectedEmployee.getEmployeeCode().isEmpty()) {
				return true;
			}
			return false;
		}

	// Valid Code is existed?
	public void validateCode(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		System.out.println("Validating Code...");
		String codeInput = arg2.toString().trim().toUpperCase();

		String str = "SELECT e FROM Employee e WHERE e.employeeCode = :code";
		employees = entityManager.createQuery(str, Employee.class).setParameter("code", codeInput).getResultList();
		// if (employees != null && !employees.isEmpty()) {
		if (!employees.isEmpty()) {
			FacesMessage msg = new FacesMessage("Employee Code validation:", "Already Existed");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			// throw new ValidatorException(new FacesMessage("code is
			// existed!"));
		}

	}
	public Date getToday() {
        return new Date();
    }

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	@PreDestroy
	public void predestory(){
		System.out.println("Destroy...");
	}

}