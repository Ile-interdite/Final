package modele.aventurier;

import controller.*;
import java.util.ArrayList;
import java.util.Scanner;
import modele.*;
import utils.*;
import utils.Utils.*;

public abstract class Aventurier {

    Tuile tuileCourante;
    private Pion couleur;
     
    public void seDeplacer(){
        Tuile oldTuile = getTuile();
        ArrayList<Tuile> tuilesPossibles = getDeplacement(oldTuile);
        
        System.out.println("X?");
        Scanner scan = new Scanner(System.in);
        int x = Integer.parseInt(scan.nextLine());
        System.out.println("Y?");
        int y = Integer.parseInt(scan.nextLine());
        Tuile choixTuile = Controleur.getInstance().getTuile(x,y);
        
        if (tuilesPossibles.contains(choixTuile)){
            choixTuile.addAventurier(this);
            tuileCourante = choixTuile;
            oldTuile.rmAventurier(this);
        } else {
            System.out.println("Impossible de se déplacer");
        }
    }

    //override
    public ArrayList<Tuile> getDeplacement(Tuile tuile) {     
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        
        if (y>-1){
            Tuile nord = Controleur.getInstance().getTuile(x,y-1);
            if (nord!=null && nord.getEtat()==Utils.EtatTuile.ASSECHEE){
                tuiles.add(nord);
            }
        }
        
        if (x<6){
            Tuile est = Controleur.getInstance().getTuile(x+1,y);
            if (est!=null && est.getEtat()==Utils.EtatTuile.ASSECHEE){
                tuiles.add(est);
            }
        }
        
        if (y<6){
            Tuile sud = Controleur.getInstance().getTuile(x,y+1);
            if (sud!=null && sud.getEtat()==Utils.EtatTuile.ASSECHEE){
                tuiles.add(sud);
            }
        }
        
        if (x>-1){
            Tuile ouest = Controleur.getInstance().getTuile(x-1,y);
            if (ouest!=null && ouest.getEtat()==Utils.EtatTuile.ASSECHEE){
                tuiles.add(ouest);
            }
        }
       return tuiles;
    }
    
    public void assecher(){
        Tuile tuilCourante = getTuile();
        ArrayList<Tuile> tuilesPossibles = getAssechement(tuilCourante);
        
        System.out.println("X?");
        Scanner scan = new Scanner(System.in);
        int x = Integer.parseInt(scan.nextLine());
        System.out.println("Y?");
        int y = Integer.parseInt(scan.nextLine());
        Tuile choixTuile = Controleur.getInstance().getTuile(x,y);
        
        if (tuilesPossibles.contains(choixTuile)){
            choixTuile.setEtat(EtatTuile.ASSECHEE);
        } else {
            System.out.println("Impossible d'assecherr");
        }
    }
    
    public ArrayList<Tuile> getAssechement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();
        
        if (tuile.getEtat()==EtatTuile.INONDEE){
           tuiles.add(tuile); 
        }
        
        if (y>-1){
            Tuile nord = Controleur.getInstance().getTuile(x,y-1);
            if (nord!=null && nord.getEtat()==Utils.EtatTuile.INONDEE){
                tuiles.add(nord);
            }
        }
        
        if (x<6){
            Tuile est = Controleur.getInstance().getTuile(x+1,y);
            if (est!=null && est.getEtat()==Utils.EtatTuile.INONDEE){
                tuiles.add(est);
            }
        }
        
        if (y<6){
            Tuile sud = Controleur.getInstance().getTuile(x,y+1);
            if (sud!=null && sud.getEtat()==Utils.EtatTuile.INONDEE){
                tuiles.add(sud);
            }
        }
        
        if (x>-1){
            Tuile ouest = Controleur.getInstance().getTuile(x-1,y);
            if (ouest!=null && ouest.getEtat()==Utils.EtatTuile.INONDEE){
                tuiles.add(ouest);
            }
        }
       return tuiles;
    }

    public Tuile getTuile() {
        return tuileCourante;
    }
    
}
