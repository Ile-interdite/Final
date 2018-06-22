package modele.carte;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controleur;
import modele.Tuile;
import modele.aventurier.Aventurier;
import utils.Utils.EtatTuile;

public class Helicoptere extends CarteTresor {
    
    public Helicoptere(String libelle) {
    	super(libelle);
    }
    
    //VERSION CONSOLE
    /*@Override
    public void utiliserCarte() {
        Scanner scan = Controleur.getInstance().getScanner();
        System.out.println("1. deplacement \n2. partir ");
        int choix = scan.nextInt();
        
        if (choix==1){
          System.out.println("X depart? ");
        int x = scan.nextInt();
        System.out.println("Y depart? ");
        int y = scan.nextInt();
        System.out.println("X arrive? ");
        int xA = scan.nextInt();
        System.out.println("Y arrive? ");
        int yA = scan.nextInt();
        Tuile tuileDep = Controleur.getInstance().getTuile(x,y);
        Tuile tuileAr = Controleur.getInstance().getTuile(xA,yA);
        deplacement(tuileDep,tuileAr);  
        }else if (choix==2){
            partir();
        }
        
    }
    
    public void deplacement(Tuile tuileDep, Tuile tuileFin) {
        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        aventuriers = tuileDep.getAventuriers();
        ArrayList<Aventurier> aventuriersADeplacer = new ArrayList<>();
               
        for (Aventurier a : aventuriers){
            System.out.println("Voulez-vous deplacer le : " +a.getClass().getSimpleName());
            
            System.out.print("1.oui/2.non ? ");
            Scanner scan2 = new Scanner(System.in);
            int x = scan2.nextInt();
            if (x==1){
                   aventuriersADeplacer.add(a); 
            }
        }
        
        for (Aventurier a : aventuriersADeplacer){
            a.setTuileCourante(tuileFin);
            tuileDep.removeAventurier(a);
            tuileFin.addAventurier(a);
        }
    }
    
    //quand la partie est gagnée
    public void partir() {
        ArrayList<Tuile> tuiles = Controleur.getInstance().getGrille().getAlTuiles();
        Tuile heliport = null;
        for (Tuile t : tuiles){
            if (t.getNom().equals("Heliport")){
                heliport = t;
            }
        }
        if ((heliport.getEtatTuile()!=EtatTuile.INONDEE )
            &&( heliport.getAventuriers().size() == Controleur.getInstance().getJoueurs().size() )
            && (Controleur.getInstance().getTresorPossedes().size()==4)){
                Controleur.getInstance().setPartieActive(false);
        }
    }*/
    
    @Override
    public void utiliserCarte() {
        
    }
    
    public void deplacement( Tuile tuileFin, ArrayList<Aventurier> aventuriers) {
        Tuile tuileDep = aventuriers.get(0).getTuileCourante();
        for (Aventurier a : aventuriers){
            tuileDep.removeAventurier(a);
            a.setTuileCourante(tuileFin);
            tuileFin.addAventurier(a);
        }
    }
    
    @Override
    public String toString() {
        return "carte helico";
    }
}