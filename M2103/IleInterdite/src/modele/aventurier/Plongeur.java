package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import modele.Tuile;
import utils.Utils;

public class Plongeur extends Aventurier {

    public Plongeur(){}
    
    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuilesAsseche = new ArrayList<>();
        ArrayList<Tuile> tuilesChemin = new ArrayList<>();
      //regarder les deplacements nrm
        //+ pour chaque case inondées réappliquer le même algorithme

        tuilesAsseche = super.getDeplacement(tuile);
        tuilesChemin = getDeplacmentEau(tuile);

        for (Tuile t : tuilesChemin) {
            tuilesAsseche.addAll(super.getDeplacement(t));
            for (Tuile t2 : getDeplacmentEau(t)) {
                if (!tuilesChemin.contains(t2)) {
                    tuilesChemin.add(t2);
                }
            }
        }
        return tuilesAsseche;
    }

    public ArrayList<Tuile> getDeplacmentEau(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > -1) {
            Tuile nord = Controleur.getInstance().getTuile(x, y - 1);
            if (nord != null && nord.getEtat() != Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nord);
            }
        }

        if (x < 6) {
            Tuile est = Controleur.getInstance().getTuile(x + 1, y);
            if (est != null && est.getEtat() != Utils.EtatTuile.ASSECHEE) {
                tuiles.add(est);
            }
        }

        if (y < 6) {
            Tuile sud = Controleur.getInstance().getTuile(x, y + 1);
            if (sud != null && sud.getEtat() != Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sud);
            }
        }

        if (x > -1) {
            Tuile ouest = Controleur.getInstance().getTuile(x - 1, y);
            if (ouest != null && ouest.getEtat() != Utils.EtatTuile.ASSECHEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }
}
