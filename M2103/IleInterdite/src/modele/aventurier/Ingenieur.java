package modele.aventurier;

import utils.Utils.Pion;

public class Ingenieur extends Aventurier {
    
    public Ingenieur() {
    	super();
        setCouleur(Pion.ROUGE);
        super.spawn();
    }
}