package modele.carte;

import controller.Controleur;

public class CarteMDE extends CarteTresor {
    
    public CarteMDE(String libelle) {
    	super(libelle);
    }
    
    public void utiliser() {
        int oldLevel = Controleur.getInstance().getNiveauEau();
        Controleur.getInstance().setNiveauEau(oldLevel + 1);
    }
}