package modele.carte;

import controller.*;
import java.util.*;
import modele.Tuile;
import utils.Utils.*;

public class CarteInondation {

	private Tuile tuile;
        
	public CarteInondation(Tuile tuile) {
		this.setTuile(tuile);
	}
	
	public Tuile getTuile() {
		return tuile;
	}
	
	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}
        
        public void utiliserCarte(){
            ArrayList<Tuile> tuiles = Controleur.getInstance().getGrille().getAlTuiles();
            boolean trouve = false;
            int i = 0; 
            while (!trouve && i<24){
                Tuile t = tuiles.get(i);
                if ( this.tuile == t){
                    trouve = true;
                    if (t.getEtatTuile()==EtatTuile.ASSECHEE){
                        t.setEtat(EtatTuile.INONDEE);
                    } else if (t.getEtatTuile()==EtatTuile.INONDEE){
                        t.setEtat(EtatTuile.COULEE);
                        Controleur.getInstance().getDefausseInondation().remove(this);
                    }
                } else {
                    i++;
                }
            }
        }
}