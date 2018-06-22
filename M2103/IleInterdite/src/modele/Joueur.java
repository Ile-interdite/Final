package modele;

import java.util.ArrayList;
import java.util.TreeSet;

import controller.Controleur;
import modele.aventurier.Aventurier;
import modele.carte.CarteTresor;
import modele.carte.Helicoptere;
import modele.carte.SacDeSable;

public class Joueur {

	private Aventurier role;
	private String name;
	private int pointsAction = 3;

	ArrayList<CarteTresor> cartesTresor = new ArrayList<>();

	/**
	 * Création d'un nouveau joueur.
	 * Nombre de joueurs minimum : 2
	 * Nombre de joueurs maximum : 4
	 * 
	 * @param name Le nom du joueur.
	 */
	public Joueur(String name) {
		this.setName(name);
	}

	@Override
	public String toString(){
		return this.getName() + " " + this.getRole(); 
	}
	
	public String getName() {
		return name;
	}

	/**
	 * Définit le nom du joueur dans le jeu.
	 * 
	 * @param name Le nom du joueur.
	 */
	public void setName(String name) {
		if(name != null) {
			this.name = name;
		}
	}

	/**
	 * @return Le rôle du joueur dans le jeu.
	 */
	public Aventurier getRole() {
		return role;
	}

	/**
	 * Définit le rôle du joueur dans le jeu.
	 * 
	 * @param role Le rôle du joueur.
	 */
	public void setRole(Aventurier role) {
		if(role != null) {
			this.role = role;
		}
	}

	/**
	 * @return Le nombre de points d'action du joueur.
	 */
	public int getPointsAction() {
		return pointsAction;
	}
	
	/**
	 * Définit le nombre de points d'action du joueur.
	 * 
	 * @param pointsAction Le nombre de points d'action.
	 */
	public void setPointsAction(int pointsAction) {
		this.pointsAction = pointsAction;
	}
	
	public ArrayList<CarteTresor> trierCartesTresor() {
		ArrayList<CarteTresor> cartesTresor = this.cartesTresor;
		TreeSet<CarteTresor> cartesTresorTriees = new TreeSet<>();
		
		for(CarteTresor carteTresor : cartesTresor) {
			cartesTresorTriees.add(carteTresor);
		}
		
		cartesTresor.clear();
		
		for(CarteTresor carteTresor : cartesTresorTriees) {
			cartesTresor.add(carteTresor);
		}
		
		return cartesTresor;
	}

	/**
	 * @return La liste des cartes "Trésor" possédées par le joueur.
	 */
	public ArrayList<CarteTresor> getCartesTresor() {
		return this.trierCartesTresor();
	}

	/**
	 * Ajoute une carte "Trésor" à la liste des cartes du joueur.
	 * 
	 * @param carteTresor La carte "Trésor" à ajouter à la liste.
	 */
	public void addCarteTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.getCartesTresor().add(carteTresor);
		}
	}

	public void removeCarteTresor(CarteTresor carteTresor) {
		if(carteTresor != null && this.getCartesTresor().contains(carteTresor)) {
			this.getCartesTresor().remove(carteTresor);
		}
	}

	public void donnerCarteTresor(CarteTresor carteTresor, Joueur joueur) {
		if(carteTresor != null 
                    && joueur != null
                    && !(carteTresor instanceof SacDeSable)
                    && !(carteTresor instanceof Helicoptere)) {
			if(this.getCartesTresor().contains(carteTresor)) {
				this.removeCarteTresor(carteTresor);
				joueur.addCarteTresor(carteTresor);
			}
		}
	}

	public void defausserCarteTresor(CarteTresor carteTresor) {    	
		if(carteTresor != null && this.getCartesTresor().contains(carteTresor)) {
			this.removeCarteTresor(carteTresor);
			Controleur.getInstance().getDefausseTresor().add(carteTresor);
		}
	}

	public void piocherCarteTresor() {
		CarteTresor carteTresor = Controleur.getInstance().popCarteTresor();

		if(carteTresor != null) {
			this.getCartesTresor().add(carteTresor);
		}
	}

	public void utiliserCarteTresor(CarteTresor carteTresor) {        
		if(this.getCartesTresor().contains(carteTresor)) {
			if(carteTresor instanceof Helicoptere || carteTresor instanceof SacDeSable) {
                            this.defausserCarteTresor(carteTresor);
                            carteTresor.utiliserCarte();
			}
		}
	}
}
