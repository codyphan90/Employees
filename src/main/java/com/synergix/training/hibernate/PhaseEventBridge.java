package com.synergix.training.hibernate;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;

public class PhaseEventBridge implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void beforePhase(PhaseEvent event) {
		final PhaseId phaseId = event.getPhaseId();
		if (phaseId == PhaseId.RESTORE_VIEW || phaseId == PhaseId.RENDER_RESPONSE) {
			try {
				if (SynTransaction.getTransactionManager().getStatus() == Status.STATUS_NO_TRANSACTION) {
					SynTransaction.getTransactionManager().begin();
					System.out.println("Transaction Begin");
				}
			} catch (NotSupportedException | SystemException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		final PhaseId phaseId = event.getPhaseId();

		final boolean endTx = (phaseId == PhaseId.INVOKE_APPLICATION) || phaseId == PhaseId.RENDER_RESPONSE
				|| event.getFacesContext().getRenderResponse() || event.getFacesContext().getResponseComplete();

		try {
			if (endTx && SynTransaction.getTransactionManager().getStatus() == Status.STATUS_ACTIVE) {
				SynTransaction.getTransactionManager().commit();
				System.out.println("Transaction Commit");
			}
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
