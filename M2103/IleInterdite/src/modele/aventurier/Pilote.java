package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import modele.Tuile;
import utils.Utils.*;

public class Pilote extends Aventurier {
    
    public Pilote(){}
    
    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        ArrayList<Tuile> tuiles = new ArrayList<>();

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tuile t = Controleur.getInstance().getTuile(x, y);
                if (t.getEtat() == EtatTuile.ASSECHEE) {
                    tuiles.add(t);
                }
            }
        }
        return tuiles;
    }

}
