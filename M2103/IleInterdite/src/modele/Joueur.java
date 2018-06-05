package modele;

import modele.carte.CarteTresor;
import modele.aventurier.Aventurier;
import java.util.*;

public class Joueur {

	private Aventurier role;
	private String name;
	private int pointsAction;
	
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
    
    /**
     * @return Le nom du joueur dans le jeu.
     */
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
     * Retire un point d'action au joueur.
     */
    public void removePointAction() {
    	this.pointsAction--;
    }
    
    /**
     * @return La liste des cartes "Trésor" possédées par le joueur.
     */
    public ArrayList<CarteTresor> getCartesTresor() {
    	return cartesTresor;
    }
    
    /**
     * Ajoute une carte "Trésor" à la liste des cartes du joueur.
     * 
     * @param carteTresor La carte "Trésor" à ajouter à la liste.
     */
    public void addCarteTresor(CarteTresor carteTresor) {
    	if(carteTresor != null) {
    		this.cartesTresor.add(carteTresor);
    	}
    }
    
    public void removeCarteTresor(CarteTresor carteTresor) {
    	if(carteTresor != null && this.cartesTresor.contains(carteTresor)) {
    		this.cartesTresor.remove(carteTresor);
    		//Ajouter carte à la défausse
    	}
    }
}
