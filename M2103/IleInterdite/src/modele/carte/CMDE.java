package modele.carte;

import controller.Controleur;
import java.util.ArrayList;
import java.util.Collections;

public class CMDE extends CarteTresor {
    
    public CMDE(String libelle) {
    	super(libelle);
    }
    
    public void utiliserCarte() {
        int oldLevel = Controleur.getInstance().getNiveauEau();
        Controleur.getInstance().setNiveauEau(oldLevel+1);
    }
}

