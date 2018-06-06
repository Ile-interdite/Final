package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import modele.Tuile;
import utils.Utils.*;

public class Plongeur extends Aventurier {

    public Plongeur(){
        setCouleur(Pion.VIOLET);
        super.spawn();
    }
    
    /*@Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuilesAsseche = new ArrayList<>();
        ArrayList<Tuile> tuilesCheminCourantes = new ArrayList<>();
        ArrayList<Tuile> tuilesChemin = new ArrayList<>();
      //regarder les deplacements nrm
        //+ pour chaque case inondées réappliquer le même algorithme

        tuilesAsseche = super.getDeplacement(tuile);
        System.out.println("nb tuiles normales : " + tuilesAsseche.size());
        tuilesCheminCourantes = getDeplacmentEau(tuile);
        
        for(Tuile t : tuilesCheminCourantes) {
            tuilesChemin.add(t);
        }

        boolean trouve = true;
        int nbIte = 1;
        
        while(trouve) {
            trouve = false;
            
            for (Tuile t : tuilesCheminCourantes) {
                tuilesAsseche.addAll(super.getDeplacement(t));
                nbIte = 1;
            
                for (Tuile t2 : getDeplacmentEau(t)) {
                    System.out.println(nbIte);
                    nbIte++;
                    if (!tuilesChemin.contains(t2)) {
                        tuilesChemin.add(t2);
                        trouve = true;
                    }
                }
            } 
        }
        tuilesAsseche.remove(tuile);
        return tuilesAsseche;
    }*/
    
    @Override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();

        ArrayList<Tuile> tuilesAsseche = new ArrayList<>();
        ArrayList<Tuile> tuilesChemin = new ArrayList<>();
        ArrayList<Tuile> tuilesEphemere = new ArrayList<>();
        
        tuilesAsseche = super.getDeplacement(tuile);
        tuilesChemin = getDeplacmentEau(tuile);
        
        
        tuilesEphemere = tuilesChemin;
        
        int i = 0;

            while(i<tuilesChemin.size() ){
                for (Tuile tAsseche : super.getDeplacement(tuilesChemin.get(i))){
                    if(!tuilesAsseche.contains(tAsseche)){
                        tuilesAsseche.add(tAsseche);
                    }
                }
                for (Tuile tMouille : getDeplacmentEau(tuilesChemin.get(i))){
                    if(!tuilesChemin.contains(tMouille)){
                        tuilesChemin.add(tMouille);
                    }
                }
                i++;
            }
        
        tuilesAsseche.remove(tuile);
        System.out.println("Case inondée :");
        super.afficherTuile(tuilesChemin);
        System.out.println("\n");
        System.out.println("Choix tuiles :");
        super.afficherTuile(tuilesAsseche);
        System.out.println("\n");
        
        return tuilesAsseche;
    }
    public ArrayList<Tuile> getDeplacmentEau(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > 0) {
            Tuile nord = Controleur.getInstance().getTuile(x, y - 1);
            if (nord != null && nord.getEtat() != EtatTuile.ASSECHEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Controleur.getInstance().getTuile(x + 1, y);
            if (est != null && est.getEtat() != EtatTuile.ASSECHEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Controleur.getInstance().getTuile(x, y + 1);
            if (sud != null && sud.getEtat() != EtatTuile.ASSECHEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Controleur.getInstance().getTuile(x - 1, y);
            if (ouest != null && ouest.getEtat() != EtatTuile.ASSECHEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }
}
