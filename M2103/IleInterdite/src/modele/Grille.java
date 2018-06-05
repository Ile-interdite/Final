package modele;


import utils.Utils.Pion;
import java.util.ArrayList;
import java.util.Collections;
import utils.Tresor;

public class Grille {

    
        private Tuile tuiles[][];
        private ArrayList<Tuile> alTuiles = new ArrayList();
        private Tuile LePontdesAbimes,LaPortedeBronze,LaCavernedesOmbres,LaPortedeFer,
                      LaPortedOr,LesFalaisesdelOubli,LePalaisdeCorail,LaPortedArgent,LesDunesdelIllusion,
                      Heliport,LaPortedeCuivre,LeJardindesHurlements,LaForetPourpre,LeLagonPerdu,
                      LeMaraisBrumeux,Observatoire,LeRocherFantome,LaCaverneduBrasier,LeTempleduSoleil,
                      LeTempledeLaLune,LePalaisdesMarees,LeValduCrepuscule,LaTourduGuet,LeJardinDesMurmures;
        
	public Grille() {
                LePontdesAbimes = new Tuile("Le Pont des Abimes",null,null);
                LaPortedeBronze = new Tuile("La Porte de Bronze",Pion.ROUGE,null);
                LaCavernedesOmbres = new Tuile("La Caverne des Ombres",null,Tresor.CRISTAL_ARDENT);
                LaPortedeFer = new Tuile("La Porte de Fer",Pion.VIOLET,null);
                LaPortedOr = new Tuile("La Porte d’Or",null,null);          //porte du navigateur
                LesFalaisesdelOubli = new Tuile("Les Falaises de l’Oubli",null,null);
                LePalaisdeCorail = new Tuile("Le Palais de Corail",null,Tresor.CALICE_ONDE);
                LaPortedArgent = new Tuile("La Porte d’Argent",Pion.ORANGE,null);
                LesDunesdelIllusion = new Tuile("Les Dunes de l’Illusion",null,null);
                Heliport = new Tuile("Heliport",Pion.BLEU ,null);
                LaPortedeCuivre = new Tuile("La Porte de Cuivre",Pion.VERT,null);
                LeJardindesHurlements = new Tuile("Le Jardin des Hurlements",null,Tresor.STATUE_ZEPHIR);
                LaForetPourpre= new Tuile("La Foret Pourpre",null,null);
                LeLagonPerdu= new Tuile("Le Lagon Perdu",null,null);
                LeMaraisBrumeux= new Tuile("Le Marais Brumeux",null,null);
                Observatoire= new Tuile("Observatoire",null,null);
                LeRocherFantome= new Tuile("Le Rocher Fantome",null,null);
                LaCaverneduBrasier= new Tuile("La Caverne du Brasier",null,Tresor.CRISTAL_ARDENT);
                LeTempleduSoleil= new Tuile("Le Temple du Soleil",null,Tresor.PIERRE_SACREE);
                LeTempledeLaLune= new Tuile("Le Temple de La Lune",null,Tresor.PIERRE_SACREE);
                LePalaisdesMarees= new Tuile("Le Palais des Marees",null,Tresor.CALICE_ONDE);
                LeValduCrepuscule= new Tuile("Le Val du Crepuscule",null,null);
                LaTourduGuet= new Tuile("La Tour du Guet",null,null);
                LeJardinDesMurmures= new Tuile("Le Jardin des Murmures",null,Tresor.STATUE_ZEPHIR);
                
                alTuiles.add(LePontdesAbimes);
                alTuiles.add(LaPortedeBronze);
                alTuiles.add(LaCavernedesOmbres);
                alTuiles.add(LaPortedeFer);
                alTuiles.add(LaPortedOr);
                alTuiles.add(LesFalaisesdelOubli);
                alTuiles.add(LePalaisdeCorail);
                alTuiles.add(LaPortedArgent);
                alTuiles.add(LesDunesdelIllusion);
                alTuiles.add(Heliport);
                alTuiles.add(LaPortedeCuivre);
                alTuiles.add(LeJardindesHurlements);
                alTuiles.add(LaForetPourpre);
                alTuiles.add(LeLagonPerdu);
                alTuiles.add(LeMaraisBrumeux);
                alTuiles.add(Observatoire);
                alTuiles.add(LeRocherFantome);
                alTuiles.add(LaCaverneduBrasier);
                alTuiles.add(LeTempleduSoleil);
                alTuiles.add(LeTempledeLaLune);
                alTuiles.add(LePalaisdesMarees);
                alTuiles.add(LeValduCrepuscule);
                alTuiles.add(LaTourduGuet);
                alTuiles.add(LeJardinDesMurmures);
                
                
                this.setGrille();
	}

	public void setGrille() {
            //ajouter mélanger ArrayList<Tuile> alTuiles
           //(alTuiles);
           int compteur = 0;
           for(int x=0; x<6; x++){
               for (int y=0; y<6; y++){
                   if ((x!=0 && y!=0) ||
                      (x!=1 && y!=0) ||
                      (x!=4 && y!=0) ||
                      (x!=5 && y!=0) ||
                      (x!=0 && y!=1) ||
                      (x!=5 && y!=1) ||    
                      (x!=0 && y!=4) ||     
                      (x!=5 && y!=4) ||     
                      (x!=0 && y!=5) ||
                      (x!=1 && y!=5) || 
                      (x!=4 && y!=5) ||
                      (x!=5 && y!=5))
                   { 
                    tuiles[x][y]=alTuiles.get(compteur);
                    tuiles[x][y].setPosition(x,y);
                    compteur++;
                    
                   } else{
                    tuiles[x][y]=null;   
                   }
                   
               }
           }
           
	}
        
        public Tuile[][] getTuiles() {
            return tuiles;
        }

    
}
