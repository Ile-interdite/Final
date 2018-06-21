package modele.carte;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controleur;
import modele.Tuile;
import utils.Utils.EtatTuile;

public class SacDeSable extends CarteTresor {
        
    public SacDeSable(String libelle) {
    	super(libelle);
    }
    
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
        ArrayList<Tuile> tuiles = Controleur.getInstance().getGrille().getAlTuiles();
        for (Tuile t : tuiles){
            if (t.getEtatTuile()==EtatTuile.INONDEE){
                System.out.println(t);
            }
        }
        return tuiles;
    }
    
    @Override
    public String toString(){
        return "carte sac de sable";
    }
}