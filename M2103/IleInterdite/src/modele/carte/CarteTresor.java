package modele.carte;

import java.util.Collections;
import java.util.Stack;

public abstract class CarteTresor extends Carte implements Comparable<CarteTresor> {
	
	private static Stack<CarteTresor> pile = new Stack<>();
	private static Stack<CarteTresor> defausse = new Stack<>();
	
	private static Stack<CarteTresor> getPile() {
		return pile;
	}
	
	private static Stack<CarteTresor> getDefausse() {
		return defausse;
	}
	
	/**
	 * Si la pile est vide, y ajoute les cartes de la défausse dans celle-ci et la mélange.
	 * 
	 * @return La carte se trouvant au sommet de la pile et la retire de celle-ci.
	 */
	public static CarteTresor piocher() {
		if(getPile().isEmpty()) {
			defausseToPile();
		}
		return CarteTresor.getPile().pop();
	}
	
	public static void defausseToPile() {
		if(!getDefausse().isEmpty()) {			
			while(!getDefausse().isEmpty()) {
				addCarteToPile(getDefausse().pop());
			}
			melangerPile();
		}
	}
	
	public static void melangerPile() {
		Collections.shuffle(getPile());
	}
	
	public static void addCarteToPile(CarteTresor carte) {
		CarteTresor.getPile().push(carte);
		Collections.shuffle(CarteTresor.getPile());
	}
	
	public static void addCarteToDefausse(CarteTresor carte) {
		CarteTresor.getDefausse().push(carte);
	}
	
	public CarteTresor(String libelle) {
		super(libelle);
	}

	@Override
	public int compareTo(CarteTresor carte) {
		if(this instanceof CTresor && carte instanceof CTresor) {
			CTresor carte1 = (CTresor) this;
			CTresor carte2 = (CTresor) carte;
			
			if(carte1.getTresor() == carte2.getTresor()) {
				return 0;
			} else if(carte1.getTresor().getNum() > carte2.getTresor().getNum()) {
				return 1;
			} else {
				return -1;
			}
		} else if(this instanceof CTresor || carte instanceof CTresor) {
			return this instanceof CTresor ? 1 : -1;
		} else {
			if(this instanceof CarteHelicoptere && carte instanceof CarteHelicoptere) {
				return 0;
			} else if(this instanceof CarteHelicoptere || carte instanceof CarteHelicoptere) {
				return this instanceof CarteHelicoptere ? -1 : 1;
			} else {
				return 0;
			}
		}
	}
}