package modele.carte;

import controller.*;
import java.util.*;
import modele.Tuile;
import utils.Utils.*;

public class CarteInondation extends Carte {
	
	private static Stack<CarteInondation> pile = new Stack<>();
	private static Stack<CarteInondation> defausse = new Stack<>();
	
	private static Stack<CarteInondation> getPile() {
		return pile;
	}
	
	private static Stack<CarteInondation> getDefausse() {
		return defausse;
	}
	
	/**
	 * Si la pile est vide, y ajoute les cartes de la défausse dans celle-ci et la mélange.
	 * 
	 * @return La carte se trouvant au sommet de la pile et la retire de celle-ci.
	 */
	public static void piocher() {
		if(getPile().isEmpty()) {
			defausseToPile();
		}
		CarteInondation carte = getPile().pop();
		carte.utiliser();
		addCarteToDefausse(carte);
	}
	
	public static void melangerPile() {
		Collections.shuffle(getPile());
	}
	
	public static void melangerDefausse() {
		Collections.shuffle(getDefausse());
	}
	
	public static void defausseToPile() {
		if(!getDefausse().isEmpty()) {
			melangerDefausse();
			
			while(!getDefausse().isEmpty()) {
				addCarteToPile(getDefausse().pop());
			}
		}
	}
	
	public static void addCarteToPile(CarteInondation carte) {
		getPile().push(carte);
	}
	
	public static void addCarteToDefausse(CarteInondation carte) {
		getDefausse().push(carte);
	}

	private Tuile tuile;
        
	public CarteInondation(Tuile tuile) {
		super(tuile.getNom());
		this.setTuile(tuile);
	}
	
	public Tuile getTuile() {
		return tuile;
	}
	
	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}
	
    public void utiliser() {
        Iterator<Tuile> iterator = Controleur.getInstance().getGrille().getTuiles().iterator();
        boolean trouve = false;
        
        while(iterator.hasNext() && !trouve) {
            Tuile tuile = iterator.next();
            
            if(this.getTuile() == tuile){
                trouve = true;
                
                if(tuile.getEtatTuile() == EtatTuile.ASSECHEE){
                    tuile.setEtat(EtatTuile.INONDEE);
                } else {
                    tuile.setEtat(EtatTuile.COULEE);
                    CarteInondation.getDefausse().remove(this);
                }
            }
        }
    }
}