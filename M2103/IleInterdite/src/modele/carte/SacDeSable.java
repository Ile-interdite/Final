package modele.carte;

public class SacDeSable extends CarteTresor {
        
    public SacDeSable(String libelle) {
    	super(libelle);
    }
    
    @Override
    public String toString(){
        return "carte sac de sable";
    }
}