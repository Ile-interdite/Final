package modele;

import java.util.ArrayList;
import java.util.Collections;

import utils.Tresor;
import utils.Utils.Pion;

public class Grille {
	
	private static Tuile tableauTuiles[][] = new Tuile[6][6];
	
	private static Tuile[][] getTableauTuiles() {
        return tableauTuiles;
    }
	
	public static Tuile getTuile(int x, int y) {
        return Grille.getTableauTuiles()[x][y];
    }

    private ArrayList<Tuile> tuiles = new ArrayList<>();

    public Grille() {
    	Tuile Heliport = new Tuile("Héliport", Pion.BLEU, null);
    	Tuile LaCavernedesOmbres = new Tuile("La Caverne des Ombres", null, Tresor.CRISTAL_ARDENT);
    	Tuile LaCaverneduBrasier = new Tuile("La Caverne du Brasier", null, Tresor.CRISTAL_ARDENT);
    	Tuile LaForetPourpre = new Tuile("La Forêt Pourpre", null, null);
    	Tuile LaPortedArgent = new Tuile("La Porte d'Argent", Pion.WHITE, null);
    	Tuile LaPortedeBronze = new Tuile("La Porte de Bronze", Pion.ROUGE, null);
    	Tuile LaPortedeCuivre = new Tuile("La Porte de Cuivre", Pion.VERT, null);
    	Tuile LaPortedeFer = new Tuile("La Porte de Fer", Pion.BLACK, null);
    	Tuile LaPortedOr = new Tuile("La Porte d'Or", Pion.JAUNE, null);
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

        this.addTuile(LePontdesAbimes);
        this.addTuile(LaPortedeBronze);
        this.addTuile(LaCavernedesOmbres);
        this.addTuile(LaPortedeFer);
        this.addTuile(LaPortedOr);
        this.addTuile(LesFalaisesdelOubli);
        this.addTuile(LePalaisdeCorail);
        this.addTuile(LaPortedArgent);
        this.addTuile(LesDunesdelIllusion);
        this.addTuile(Heliport);
        this.addTuile(LaPortedeCuivre);
        this.addTuile(LeJardindesHurlements);
        this.addTuile(LaForetPourpre);
        this.addTuile(LeLagonPerdu);
        this.addTuile(LeMaraisBrumeux);
        this.addTuile(Observatoire);
        this.addTuile(LeRocherFantome);
        this.addTuile(LaCaverneduBrasier);
        this.addTuile(LeTempleduSoleil);
        this.addTuile(LeTempledeLaLune);
        this.addTuile(LePalaisdesMarees);
        this.addTuile(LeValduCrepuscule);
        this.addTuile(LaTourduGuet);
        this.addTuile(LeJardindesMurmures);

        this.setGrille();
    }

    public void setGrille() {
        Collections.shuffle(this.getTuiles());
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
                    Grille.getTableauTuiles()[x][y] = null;
                } else {
                	Grille.getTableauTuiles()[x][y] = this.getTuiles().get(compteur);
                	Grille.getTableauTuiles()[x][y].setPosition(x, y);

                    compteur++;
                }

            }
        }
    }
    
    public ArrayList<Tuile> getTuiles() {
    	return tuiles;
    }
    
    private void addTuile(Tuile tuile) {
    	this.getTuiles().add(tuile);
    }
}
