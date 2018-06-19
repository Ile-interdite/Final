package modele.carte;

import controller.*;
import java.util.*;
import modele.Tuile;
import utils.Utils.*;

public class CarteInondation {

	String nom;
        
	public CarteInondation(String nom) {
		this.setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
        
        public void utiliserCarte(){
            ArrayList<Tuile> tuiles = Controleur.getInstance().getGrille().getAlTuiles();
            for (Tuile t : tuiles){
                if ( this.nom == t.getNom()){
                    if (t.getEtatTuile()==EtatTuile.ASSECHEE){
                        t.setEtat(EtatTuile.INONDEE);
                    } else if (t.getEtatTuile()==EtatTuile.INONDEE){
                        t.setEtat(EtatTuile.COULEE);
                    }
                }
            }
        }
    
    @Override
    public String toString(){
        return nom;
    }
}