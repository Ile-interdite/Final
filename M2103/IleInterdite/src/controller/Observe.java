package controller;

public interface Observe {
	
	public void setObservateur(Observateur observateur);

	public void notifierObservateur(Message m);
	
	public Observateur getObservateur();
}