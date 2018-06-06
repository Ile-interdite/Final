package modele.aventurier;

import utils.Utils.*;

public class Ingenieur extends Aventurier {
    
    public Ingenieur(){
        setCouleur(Pion.ROUGE);
        super.spawn();
    }
}