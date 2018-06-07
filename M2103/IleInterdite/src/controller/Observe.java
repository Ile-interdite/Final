package controller;

public class Observe {

	private Observateur observateur;

	public void setObservateur(Observateur observateur) {
		if(observateur != null) {
			this.observateur = observateur;
		}
	}

	public void notifierObservateur(Message m) {
		if(this.getObservateur() != null) {
			this.getObservateur().traiterMessage(m);
		}
	}
	
	public Observateur getObservateur() {
		return observateur;
	}
}