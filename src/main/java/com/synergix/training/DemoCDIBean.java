package com.synergix.training;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class DemoCDIBean {
	
//	private String name = "Peter";
	private String name ;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sayHello(){
		System.out.println("Hello "+ name);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hi There!", "I know you are" + name));
	}
	
	public String getCurrentTime() {        
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM-yyyy HH:mm:ss"));
    }
}
