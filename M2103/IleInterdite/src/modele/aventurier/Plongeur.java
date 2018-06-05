package modele.aventurier;

import controller.Controleur;
import java.util.ArrayList;
import modele.Tuile;
import utils.Utils;

public class Plongeur extends Aventurier {

    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
      //regarder les deplacements nrm
      //+ pour chaque case inondées réappliquer le même algorithme
    }
}
