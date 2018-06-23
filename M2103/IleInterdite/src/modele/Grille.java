package modele;

import java.util.ArrayList;
import java.util.Collections;

import utils.Tresor;
import utils.Utils.Pion;

public class Grille {

    private Tuile tuiles[][] = new Tuile[6][6];
    private ArrayList<Tuile> alTuiles = new ArrayList<>();

    public Grille() {
    	Tuile Heliport = new Tuile("Héliport", Pion.BLEU, null);
    	Tuile LaCavernedesOmbres = new Tuile("La Caverne des Ombres", null, Tresor.CRISTAL_ARDENT);
    	Tuile LaCaverneduBrasier = new Tuile("La Caverne du Brasier", null, Tresor.CRISTAL_ARDENT);
    	Tuile LaForetPourpre = new Tuile("La Forêt Pourpre", null, null);
    	Tuile LaPortedArgent = new Tuile("La Porte d'Argent", Pion.WHITE, null);
    	Tuile LaPortedeBronze = new Tuile("La Porte de Bronze", Pion.ROUGE, null);
    	Tuile LaPortedeCuivre = new Tuile("La Porte de Cuivre", Pion.VERT, null);
    	Tuile LaPortedeFer = new Tuile("La Porte de Fer", Pion.BLACK, null);
    	Tuile LaPortedOr = new Tuile("La Porte d'Or", null, null);
    	Tuile LaTourduGuet = new Tuile("La Tour du Guet", null, null);
    	Tuile LeJardindesHurlements = new Tuile("Le Jardin des Hurlements", null, Tresor.STATUE_ZEPHIR);
    	Tuile LeJardindesMurmures = new Tuile("Le Jardin des Murmures", null, Tresor.STATUE_ZEPHIR);
    	Tuile LeLagonPerdu = new Tuile("Le Lagon Perdu", null, null);
    	Tuile LeMaraisBrumeux = new Tuile("Le Marais Brumeux", null, null);
    	Tuile LePalaisdeCorail = new Tuile("Le Palais de Corail", null, Tresor.CALICE_ONDE);
    	Tuile LePalaisdesMarees = new Tuile("Le Palais des Marées", null, Tresor.CALICE_ONDE);
        Tuile LePontdesAbimes = new Tuile("Le Pont des Abîmes", null, null);
        Tuile LeRocherFantome = new Tuile("Le Rocher Fantôme", null, null);
        Tuile LesDunesdelIllusion = new Tuile("Les Dunes de l'Illusion", null, null);
        Tuile LesFalaisesdelOubli = new Tuile("Les Falaises de l'Oubli", null, null);
        Tuile LeTempledeLaLune = new Tuile("Le Temple de La Lune", null, Tresor.PIERRE_SACREE);
        Tuile LeTempleduSoleil = new Tuile("Le Temple du Soleil", null, Tresor.PIERRE_SACREE);
        Tuile LeValduCrepuscule = new Tuile("Le Val du Crépuscule", null, null);
        Tuile Observatoire = new Tuile("Observatoire", null, null);

        this.addAlTuile(LePontdesAbimes);
        this.addAlTuile(LaPortedeBronze);
        this.addAlTuile(LaCavernedesOmbres);
        this.addAlTuile(LaPortedeFer);
        this.addAlTuile(LaPortedOr);
        this.addAlTuile(LesFalaisesdelOubli);
        this.addAlTuile(LePalaisdeCorail);
        this.addAlTuile(LaPortedArgent);
        this.addAlTuile(LesDunesdelIllusion);
        this.addAlTuile(Heliport);
        this.addAlTuile(LaPortedeCuivre);
        this.addAlTuile(LeJardindesHurlements);
        this.addAlTuile(LaForetPourpre);
        this.addAlTuile(LeLagonPerdu);
        this.addAlTuile(LeMaraisBrumeux);
        this.addAlTuile(Observatoire);
        this.addAlTuile(LeRocherFantome);
        this.addAlTuile(LaCaverneduBrasier);
        this.addAlTuile(LeTempleduSoleil);
        this.addAlTuile(LeTempledeLaLune);
        this.addAlTuile(LePalaisdesMarees);
        this.addAlTuile(LeValduCrepuscule);
        this.addAlTuile(LaTourduGuet);
        this.addAlTuile(LeJardindesMurmures);

        this.setGrille();
    }

    public void setGrille() {
        Collections.shuffle(alTuiles);
        int compteur = 0;
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                if (((x == 0 && y == 0)
                        || (x == 1 && y == 0)
                        || (x == 4 && y == 0)
                        || (x == 5 && y == 0)
                        || (x == 0 && y == 1)
                        || (x == 5 && y == 1)
                        || (x == 0 && y == 4)
                        || (x == 5 && y == 4)
                        || (x == 0 && y == 5)
                        || (x == 1 && y == 5)
                        || (x == 4 && y == 5)
                        || (x == 5 && y == 5))
                        ) 
                {
                    this.getTuiles()[x][y] = null;
                } else {
                	this.getTuiles()[x][y] = this.getAlTuiles().get(compteur);
                	this.getTuiles()[x][y].setPosition(x, y);

                    compteur++;
                }

            }
        }

    }

    public Tuile[][] getTuiles() {
        return tuiles;
    }
    
    public ArrayList<Tuile> getAlTuiles() {
    	return alTuiles;
    }
    
    public void addAlTuile(Tuile tuile) {
    	this.getAlTuiles().add(tuile);
    }
}
