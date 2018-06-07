package modele.carte;

public class CarteInondation {

	String nom;
        
	public CarteInondation(String nom) {
		this.setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}