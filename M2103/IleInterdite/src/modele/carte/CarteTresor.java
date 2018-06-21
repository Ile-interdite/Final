package modele.carte;

public abstract class CarteTresor {
	
	private String libelle;
	
	public CarteTresor(String libelle) {
		this.setLibelle(libelle);
	}

    public void utiliserCarte() {}
    
    public String getLibelle() {
    	return libelle;
    }
    
    public void setLibelle(String libelle) {
    	this.libelle = libelle;
    }
}