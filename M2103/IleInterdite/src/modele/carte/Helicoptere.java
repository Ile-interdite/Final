package modele.carte;

import controller.*;
import java.util.*;
import modele.*;
import modele.aventurier.*;
import utils.Utils.*;

public class Helicoptere extends CarteTresor {
    
    public Helicoptere() {}
    
    @Override
    public void utiliserCarte(){
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
    
    public void deplacement(Tuile tuileDep, Tuile tuileFin){
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
    
    
    public void partir(){
        ///pour terminer le jeu
        //{précondition : tous les joueurs doivent être là}
        ArrayList<Tuile> tuiles = Controleur.getInstance().getGrille().getAlTuiles();
        Tuile heliport = new Tuile(null,null,null);
        for (Tuile t : tuiles){
            if (t.getNom()=="Heliport"){
                heliport = t;
            }
        }
        if (heliport.getEtatTuile()!=EtatTuile.INONDEE 
            && heliport.getAventuriers().size() == Controleur.getInstance().getAventuriers().size() 
            && Controleur.getInstance().getTresorPossedes().size()==4){
                System.out.println("Partie gagnée");
                Controleur.getInstance().setPartieActive(false);
        }
        
    }
    
    @Override
    public String toString(){
        return "carte helico";
    }
}