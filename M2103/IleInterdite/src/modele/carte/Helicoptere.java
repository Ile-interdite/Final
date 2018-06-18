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
        
    }
    
    public void deplacement(Tuile tuileDep, Tuile tuileFin){
        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        aventuriers = tuileDep.getAventuriers();
        ArrayList<Aventurier> aventuriersADeplacer = new ArrayList<>();
        
        for (Aventurier a : aventuriers){
            System.out.println("Voulez-vous deplacer le : " +a.getClass().getSimpleName());
            Scanner scan = Controleur.getInstance().getScanner();
        	System.out.print("oui/non ? ");
        	String x = scan.nextLine();
                if (x=="oui"){
                   aventuriersADeplacer.add(a); 
                }
        }
        
        for (Aventurier a : aventuriersADeplacer){
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
        if (heliport!=null && heliport.getEtatTuile()!=EtatTuile.INONDEE 
            && heliport.getAventuriers().size() == Controleur.getInstance().getAventuriers().size() 
            && Controleur.getInstance().getTresorPossedes().size()==4){
                System.out.println("Partie gagner");
                Controleur.getInstance().setPartieActive(false);
        }
        
    }
}