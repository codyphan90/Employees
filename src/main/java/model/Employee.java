package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the NICK_EMPLOYEE database table.
 * 
 */
@Entity
@Table(name="NICK_EMPLOYEE")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_CODE")
	private String employeeCode;

	@Column(name="EMPLOYEE_ADDRESS")
	private String employeeAddress;

	@Temporal(TemporalType.DATE)
	@Column(name="EMPLOYEE_DOB")
	private Date employeeDob;

	@Column(name="EMPLOYEE_GENDER")
	private String employeeGender;

	@Column(name="EMPLOYEE_LANGUAGES")
	private String employeeLanguages;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name="EMPLOYEE_SHORTNAME")
	private String employeeShortname;

	@Column(name="EMPLOYEE_TYPES")
	private String employeeTypes;

	public Employee() {
	}

	public String getEmployeeCode() {
		return this.employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeAddress() {
		return this.employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public Date getEmployeeDob() {
		return this.employeeDob;
	}

	public void setEmployeeDob(Date employeeDob) {
		this.employeeDob = employeeDob;
	}

	public String getEmployeeGender() {
		return this.employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeLanguages() {
		return this.employeeLanguages;
	}

	public void setEmployeeLanguages(String employeeLanguages) {
		this.employeeLanguages = employeeLanguages;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeShortname() {
		return this.employeeShortname;
	}

	public void setEmployeeShortname(String employeeShortname) {
		this.employeeShortname = employeeShortname;
	}

	public String getEmployeeTypes() {
		return this.employeeTypes;
	}

	public void setEmployeeTypes(String employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

}