package modele.carte;

import controller.Controleur;
import java.util.ArrayList;
import java.util.Collections;

public class CMDE extends CarteTresor {
    
    public CMDE() {}
    
    @Override
    public void utiliserCarte(){
        int newLevel = Controleur.getInstance().getNiveauEau();
        Controleur.getInstance().setNiveauEau(newLevel+1);
        
        ArrayList<CarteTresor> alTresor = new ArrayList<>();
        alTresor= Controleur.getInstance().getDefausseTresor();
        Collections.shuffle(alTresor);
        
        for (CarteTresor alCarte : alTresor){
            Controleur.getInstance().addPileTresor(alCarte);
        }
        
        Controleur.getInstance().addDefausseTresor(this); 
    }
    
}