package modele.carte;

import java.util.ArrayList;
import modele.*;
import modele.aventurier.*;

public class Helicoptere extends CarteTresor {
    
    public Helicoptere() {}
    
    @Override
    public void utiliserCarte(){
        
    }
    
    //modifier pour un ou plusieurs joueur à deplacer
    public void deplacement(Tuile tuileDep, Tuile tuileFin){
        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        aventuriers = tuileDep.getAventuriers();
        for (Aventurier a : aventuriers){
            tuileDep.removeAventurier(a);
            tuileFin.addAventurier(a);
        }
    }
    
    
    /*public void partir(){
        ///pour terminer le jeu
        //{précondition : tous les joueurs doivent être là}
    }*/
}