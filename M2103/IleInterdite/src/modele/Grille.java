package modele;

import java.util.ArrayList;

import utils.Tresor;
import utils.Utils;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Grille {

    private Tuile tuiles[][] = new Tuile[6][6];
    private ArrayList<Tuile> alTuiles = new ArrayList<>();
    private Tuile LePontdesAbimes, LaPortedeBronze, LaCavernedesOmbres, LaPortedeFer,
            LaPortedOr, LesFalaisesdelOubli, LePalaisdeCorail, LaPortedArgent, LesDunesdelIllusion,
            Heliport, LaPortedeCuivre, LeJardindesHurlements, LaForetPourpre, LeLagonPerdu,
            LeMaraisBrumeux, Observatoire, LeRocherFantome, LaCaverneduBrasier, LeTempleduSoleil,
            LeTempledeLaLune, LePalaisdesMarees, LeValduCrepuscule, LaTourduGuet, LeJardinDesMurmures;

    public Grille() {
        LePontdesAbimes = new Tuile("Le Pont des Abimes", null, null);
        LaPortedeBronze = new Tuile("La Porte de Bronze", Pion.ROUGE, null);
        LaCavernedesOmbres = new Tuile("La Caverne des Ombres", null, Tresor.CRISTAL_ARDENT);
        LaPortedeFer = new Tuile("La Porte de Fer", Pion.VIOLET, null);
        LaPortedOr = new Tuile("La Porte d’Or", null, null);          //porte du navigateur
        LesFalaisesdelOubli = new Tuile("Les Falaises de l’Oubli", null, null);
        LePalaisdeCorail = new Tuile("Le Palais de Corail", null, Tresor.CALICE_ONDE);
        LaPortedArgent = new Tuile("La Porte d’Argent", Pion.ORANGE, null);
        LesDunesdelIllusion = new Tuile("Les Dunes de l’Illusion", null, null);
        Heliport = new Tuile("Heliport", Pion.BLEU, null);
        LaPortedeCuivre = new Tuile("La Porte de Cuivre", Pion.VERT, null);
        LeJardindesHurlements = new Tuile("Le Jardin des Hurlements", null, Tresor.STATUE_ZEPHIR);
        LaForetPourpre = new Tuile("La Foret Pourpre", null, null);
        LeLagonPerdu = new Tuile("Le Lagon Perdu", null, null);
        LeMaraisBrumeux = new Tuile("Le Marais Brumeux", null, null);
        Observatoire = new Tuile("Observatoire", null, null);
        LeRocherFantome = new Tuile("Le Rocher Fantome", null, null);
        LaCaverneduBrasier = new Tuile("La Caverne du Brasier", null, Tresor.CRISTAL_ARDENT);
        LeTempleduSoleil = new Tuile("Le Temple du Soleil", null, Tresor.PIERRE_SACREE);
        LeTempledeLaLune = new Tuile("Le Temple de La Lune", null, Tresor.PIERRE_SACREE);
        LePalaisdesMarees = new Tuile("Le Palais des Marees", null, Tresor.CALICE_ONDE);
        LeValduCrepuscule = new Tuile("Le Val du Crepuscule", null, null);
        LaTourduGuet = new Tuile("La Tour du Guet", null, null);
        LeJardinDesMurmures = new Tuile("Le Jardin des Murmures", null, Tresor.STATUE_ZEPHIR);

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
        this.addAlTuile(LeJardinDesMurmures);

        /*debug */
        this.LesDunesdelIllusion.setEtat(Utils.EtatTuile.COULEE);
        this.LeMaraisBrumeux.setEtat(Utils.EtatTuile.COULEE);
        this.LeRocherFantome.setEtat(Utils.EtatTuile.COULEE);
        this.LeTempledeLaLune.setEtat(Utils.EtatTuile.COULEE);
        this.LaPortedeBronze.setEtat(Utils.EtatTuile.INONDEE);
        this.LeLagonPerdu.setEtat(Utils.EtatTuile.INONDEE);
        this.Observatoire.setEtat(Utils.EtatTuile.INONDEE);
        this.LeJardinDesMurmures.setEtat(Utils.EtatTuile.INONDEE);
        this.LaCaverneduBrasier.setEtat(Utils.EtatTuile.INONDEE);

        this.setGrille();
    }

    public void setGrille() {
        //ajouter mélanger ArrayList<Tuile> alTuiles
        //(alTuiles);
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
                        || (x == 5 && y == 5)) //&& compteur<24
                        ) 
                {
                    this.getTuiles()[x][y] = null;
                    //System.out.println("pas if");
                } else {
                	this.getTuiles()[x][y] = this.getAlTuiles().get(compteur);
                	this.getTuiles()[x][y].setPosition(x, y);

                    compteur++;
                    //System.out.println(compteur + "if");
                    //System.out.println(x + " " + y);
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

    public void afficher() {
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
            	Tuile tuile = this.getTuiles()[x][y];
            	char charact = '.';
            	
            	if(tuile != null) {
            		EtatTuile etatTuile = tuile.getEtatTuile();
            		
            		if(etatTuile != EtatTuile.COULEE) {
            			if(etatTuile == EtatTuile.ASSECHEE) {
            				charact = 'O';
            			} else {
            				charact = '~';
            			}
            		} else {
            			charact = '/';
            		}
               	}
                System.out.print(" " + charact + " ");
            }
            System.out.print("\n");
        }
    }
}
