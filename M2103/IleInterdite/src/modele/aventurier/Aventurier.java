package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import modele.*;
import utils.*;
import utils.Utils.*;

public abstract class Aventurier {

    Tuile tuileCourante;
    private Pion couleur;
     
    public void seDeplacer(){
        Tuile oldTuile = getTuile();
        ArrayList<Tuile> tuilePossibles = getDeplacement(oldTuile);
    }

    //override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {     
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        
        if (y>-1){
            Tuile nord = Controleur.getInstance().getTuile(x,y-1);
            if (nord!=null && nord.getEtat()!=Utils.EtatTuile.ASSECHEE){
                tuiles.add(nord);
            }
        }
        
        if (x<6){
            Tuile est = Controleur.getInstance().getTuile(x+1,y);
            if (est!=null && est.getEtat()!=Utils.EtatTuile.ASSECHEE){
                tuiles.add(est);
            }
        }
        
        if (y<6){
            Tuile sud = Controleur.getInstance().getTuile(x,y+1);
            if (sud!=null && sud.getEtat()!=Utils.EtatTuile.ASSECHEE){
                tuiles.add(sud);
            }
        }
        
        if (x>-1){
            Tuile ouest = Controleur.getInstance().getTuile(x-1,y);
            if (ouest!=null && ouest.getEtat()!=Utils.EtatTuile.ASSECHEE){
                tuiles.add(ouest);
            }
        }
       return tuiles;
    }

    public Tuile[] getAssechement() {
        // TODO - implement Aventurier.getAssechement
        throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuileCourante;
    }
    
}
