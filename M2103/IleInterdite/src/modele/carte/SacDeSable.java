package modele.carte;

import controller.Controleur;
import java.util.ArrayList;
import java.util.Scanner;
import modele.*;
import modele.aventurier.Aventurier;
import utils.Utils.*;

public class SacDeSable extends CarteTresor {
        
    public SacDeSable() {}
    
    @Override
    public void utiliserCarte(){
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        tuilesPossibles = choixTuile();
        System.out.println("Faire le choix de la tuile à assecher");
        Scanner scan = Controleur.getInstance().getScanner();
        	System.out.print("X ? ");
        	int x = scan.nextInt();
        	System.out.print("Y ? ");
        	int y = scan.nextInt();
        
                
        Tuile tuile = Controleur.getInstance().getTuile(x, y);
        tuile.setEtat(EtatTuile.ASSECHEE);
    }
    
    
    
    public ArrayList<Tuile> choixTuile(){
        Grille grille = Controleur.getInstance().getGrille();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for (Tuile t : grille.getAlTuiles()){
            if (t.getEtatTuile()==EtatTuile.INONDEE){
                tuiles.add(t);
            }
        }
        return tuiles;
    }
}