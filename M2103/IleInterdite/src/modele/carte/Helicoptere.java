package modele.carte;

public class Helicoptere extends CarteTresor {
    
    public Helicoptere(String libelle) {
    	super(libelle);
    }
    
    @Override
    public String toString() {
        return "carte helico";
    }
}