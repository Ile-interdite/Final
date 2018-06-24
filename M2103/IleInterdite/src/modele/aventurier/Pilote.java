package modele.aventurier;

import java.util.ArrayList;

import modele.Grille;
import modele.Tuile;
import utils.Mode;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import view.VuePlateau;

public class Pilote extends Aventurier {

    public Pilote() {
    	super();
        this.setPion(Pion.BLEU);
    }

    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
    	ArrayList<Tuile> tuiles = new ArrayList<>();
    	if(VuePlateau.getInstance().getMode() == Mode.DEPLACEMENT_PILOTE) {
    		for (int x = 0; x < 6; x++) {
    			for (int y = 0; y < 6; y++) {
    				Tuile t = Grille.getTuile(x, y);
    				if (t != null && t.getEtatTuile() != EtatTuile.COULEE) {
    					tuiles.add(t);
    				}
    			}
    		}   		
    	} else {
    		tuiles = super.getDeplacement(tuile);
    	}
    	return tuiles;
    }

}
