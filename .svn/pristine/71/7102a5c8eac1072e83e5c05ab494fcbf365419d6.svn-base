package com.synergix.training.hibernate;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
public class EmLocator {
	
	private static EntityManagerFactory emf;
	public static EntityManagerFactory getEntityManagerFactory(){
		if(EmLocator.emf == null){
			synchronized (EmLocator.class) {
				if(EmLocator.emf == null){
					EmLocator.emf = Persistence.createEntityManagerFactory("PUnit");
					System.out.println("Entity Manager Created!");
				}
			}
		}
		return EmLocator.emf;
	}
	
	public static EntityManager getEntityManager(){
		EntityManager entityManager = EmLocator.getEntityManagerFactory().createEntityManager();
		if(entityManager.isJoinedToTransaction()){
			entityManager.joinTransaction();
		}
		return entityManager;
	}
}
