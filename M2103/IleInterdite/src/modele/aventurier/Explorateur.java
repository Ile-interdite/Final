package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import modele.Tuile;
import utils.Utils;

public class Explorateur extends Aventurier {

    public Explorateur(){}
    
    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        
        tuiles = super.getDeplacement(tuile);
        
        if (y > -1 &&  x < 6) {
            Tuile nordEst = Controleur.getInstance().getTuile(x+1, y - 1);
            if (nordEst != null && nordEst.getEtat() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nordEst);
            }
        }

        
        if (y < 6 &&  x < 6) {
            Tuile sudEst = Controleur.getInstance().getTuile(x+1, y + 1);
            if (sudEst != null && sudEst.getEtat() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sudEst);
            }
        }
        
        if (y < 6 &&  x > -1) {
            Tuile sudOuest = Controleur.getInstance().getTuile(x-1, y + 1);
            if (sudOuest != null && sudOuest.getEtat() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sudOuest);
            }
        }
        
        if (y > -1 &&  x > -1) {
            Tuile nordOuest = Controleur.getInstance().getTuile(x-1, y - 1);
            if (nordOuest != null && nordOuest.getEtat() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nordOuest);
            }
        }
        return tuiles;
    }
    
    @Override
    public ArrayList<Tuile> getAssechement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        tuiles = super.getAssechement(tuile);
        
        if (y > -1 &&  x < 6) {
            Tuile nordEst = Controleur.getInstance().getTuile(x+1, y - 1);
            if (nordEst != null && nordEst.getEtat() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nordEst);
            }
        }

        
        if (y < 6 &&  x < 6) {
            Tuile sudEst = Controleur.getInstance().getTuile(x+1, y + 1);
            if (sudEst != null && sudEst.getEtat() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sudEst);
            }
        }
        
        if (y < 6 &&  x > -1) {
            Tuile sudOuest = Controleur.getInstance().getTuile(x-1, y + 1);
            if (sudOuest != null && sudOuest.getEtat() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sudOuest);
            }
        }
        
        if (y > -1 &&  x > -1) {
            Tuile nordOuest = Controleur.getInstance().getTuile(x-1, y - 1);
            if (nordOuest != null && nordOuest.getEtat() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nordOuest);
            }
        }
       return tuiles;
    }
}
