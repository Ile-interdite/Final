package modele.carte;

public abstract class CarteTresor implements Comparable<CarteTresor> {
	
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

	@Override
	public int compareTo(CarteTresor carte) {
		if(this instanceof CTresor && carte instanceof CTresor) {
			CTresor carte1 = (CTresor) this;
			CTresor carte2 = (CTresor) carte;
			
			if(carte1 == carte2) {
				return 0;
			} else if(carte1.getTresor().getNum() > carte2.getTresor().getNum()) {
				return -1;
			} else {
				return 1;
			}
		} else if(this instanceof CTresor || carte instanceof CTresor) {
			return this instanceof CTresor ? 1 : -1;
		} else {
			if(this instanceof Helicoptere && carte instanceof Helicoptere) {
				return 0;
			} else if(this instanceof Helicoptere || carte instanceof Helicoptere) {
				return this instanceof Helicoptere ? -1 : 1;
			} else {
				return 0;
			}
		}
	}
}