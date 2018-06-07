package modele.aventurier;

import java.util.ArrayList;

import controller.Controleur;
import modele.Tuile;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Pilote extends Aventurier {

    public Pilote() {
    	super();
        setCouleur(Pion.BLEU);
    }

    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        ArrayList<Tuile> tuiles = new ArrayList<>();

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tuile t = Controleur.getInstance().getTuile(x, y);
                if (t != null && t.getEtatTuile() != EtatTuile.COULEE) {
                    tuiles.add(t);
                }
            }
        }
        return tuiles;
    }

}
