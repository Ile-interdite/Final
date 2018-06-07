package modele.aventurier;

import java.util.ArrayList;

import controller.Controleur;
import modele.Tuile;
import utils.Utils;
import utils.Utils.Pion;

public class Explorateur extends Aventurier {

    public Explorateur() {
    	super();
        this.setCouleur(Pion.VERT);
        super.spawn();
    }

    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        tuiles = super.getDeplacement(tuile);

        if (y > 0 && x < 5) {
            Tuile nordEst = Controleur.getInstance().getTuile(x + 1, y - 1);
            if (nordEst != null && nordEst.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nordEst);
            }
        }

        if (y < 5 && x < 5) {
            Tuile sudEst = Controleur.getInstance().getTuile(x + 1, y + 1);
            if (sudEst != null && sudEst.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sudEst);
            }
        }

        if (y < 5 && x > 0) {
            Tuile sudOuest = Controleur.getInstance().getTuile(x - 1, y + 1);
            if (sudOuest != null && sudOuest.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sudOuest);
            }
        }

        if (y > 0 && x > 0) {
            Tuile nordOuest = Controleur.getInstance().getTuile(x - 1, y - 1);
            if (nordOuest != null && nordOuest.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nordOuest);
            }
        }
        super.afficherTuile(tuiles);
        return tuiles;
    }

    @Override
    public ArrayList<Tuile> getAssechement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        tuiles = super.getAssechement(tuile);

        if (y > 0 && x < 5) {
            Tuile nordEst = Controleur.getInstance().getTuile(x + 1, y - 1);
            if (nordEst != null && nordEst.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nordEst);
            }
        }

        if (y < 5 && x < 5) {
            Tuile sudEst = Controleur.getInstance().getTuile(x + 1, y + 1);
            if (sudEst != null && sudEst.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sudEst);
            }
        }

        if (y < 5 && x > 0) {
            Tuile sudOuest = Controleur.getInstance().getTuile(x - 1, y + 1);
            if (sudOuest != null && sudOuest.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sudOuest);
            }
        }

        if (y > 0 && x > 0) {
            Tuile nordOuest = Controleur.getInstance().getTuile(x - 1, y - 1);
            if (nordOuest != null && nordOuest.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nordOuest);
            }
        }
        return tuiles;
    }
}
