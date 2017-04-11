package com.synergix.training.hibernate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;

public class SynTransaction {
	private static final String[] JNDI_LOCATIONS = { "java:jboss/TransactionManager", "java:/TransactionManager", "java:appserver/TransactionManager", "java:comp/TransactionManager", };
	private static volatile String foundJndiLocation;
	private static TransactionManager transactionManager;
	
	public static TransactionManager getTransactionManager(){
		if(transactionManager == null){
			if (SynTransaction.foundJndiLocation != null) {
				try {
					SynTransaction.transactionManager = (TransactionManager) new InitialContext().lookup(SynTransaction.foundJndiLocation);
					return SynTransaction.transactionManager;
				}
				catch (final NamingException e) {
					System.out.println("Could not find transaction manager under" + SynTransaction.foundJndiLocation);
				}
			}
			
			for (final String location : SynTransaction.JNDI_LOCATIONS) {
				try {
					SynTransaction.transactionManager = (TransactionManager) new InitialContext().lookup(location);
					SynTransaction.foundJndiLocation = location;
					return SynTransaction.transactionManager;
				}
				catch (final NamingException e) {
					System.out.println("Could not find transaction manager under" + location);
				}
			}
			
			throw new RuntimeException("Could not find TransactionManager in JNDI");
		}
		
		return SynTransaction.transactionManager;
	}
	
}
