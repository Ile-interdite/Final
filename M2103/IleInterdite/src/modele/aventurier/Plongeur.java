package modele.aventurier;

import java.util.ArrayList;

import modele.Grille;
import modele.Tuile;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Plongeur extends Aventurier {

    public Plongeur() {
    	super();
        this.setPion(Pion.BLACK);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        ArrayList<Tuile> tuilesAsseche = new ArrayList<>();
        ArrayList<Tuile> tuilesChemin = new ArrayList<>();
        
        tuilesAsseche = super.getDeplacement(tuile);
        tuilesChemin = this.getDeplacementEau(tuile);
        
        
        int i = 0;

        while(i < tuilesChemin.size()) {
        	for (Tuile tAsseche : super.getDeplacement(tuilesChemin.get(i))) {
        		if(!tuilesAsseche.contains(tAsseche)) {
        			tuilesAsseche.add(tAsseche);
        		}
        	}
        	
        	for (Tuile tMouille : getDeplacementEau(tuilesChemin.get(i))) {
        		if(!tuilesChemin.contains(tMouille)) {
        			tuilesChemin.add(tMouille);
        		}
        	}
        	i++;
        }
        
        tuilesAsseche.remove(tuile);
        
        return tuilesAsseche;
    }
    
    public ArrayList<Tuile> getDeplacementEau(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > 0) {
            Tuile nord = Grille.getTuile(x, y - 1);
            
            if (nord != null && nord.getEtatTuile() != EtatTuile.ASSECHEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Grille.getTuile(x + 1, y);
            
            if (est != null && est.getEtatTuile() != EtatTuile.ASSECHEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Grille.getTuile(x, y + 1);
            
            if (sud != null && sud.getEtatTuile() != EtatTuile.ASSECHEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Grille.getTuile(x - 1, y);
            
            if (ouest != null && ouest.getEtatTuile() != EtatTuile.ASSECHEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }
}
