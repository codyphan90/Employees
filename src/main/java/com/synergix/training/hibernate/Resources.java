package com.synergix.training.hibernate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

@ApplicationScoped
public class Resources {
	
	@Produces
	EntityManager produceEntityManager(){
		return EmLocator.getEntityManager();
	}
}
