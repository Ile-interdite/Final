package modele;

import modele.carte.CarteTresor;
import modele.carte.Helicoptere;
import modele.carte.SacDeSable;
import modele.aventurier.Aventurier;
import java.util.*;

import controller.Controleur;

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
    	ArrayList<CarteTresor> cartesTresor = this.getCartesTresor();
    	
    	if(carteTresor != null && cartesTresor.contains(carteTresor)) {
    		cartesTresor.remove(carteTresor);
    	}
    }
    
    public void donnerCarteTresor(CarteTresor carteTresor, Joueur joueur) {
    	if(carteTresor != null && joueur != null) {
    		ArrayList<CarteTresor> cartes = this.getCartesTresor();
    		
    		if(cartes.contains(carteTresor)) {
    			this.removeCarteTresor(carteTresor);
    			joueur.addCarteTresor(carteTresor);
    		}
    	}
    }
    
    public void defausserCarteTresor(CarteTresor carteTresor) {
    	ArrayList<CarteTresor> cartes = this.getCartesTresor();
    	
    	if(carteTresor != null && cartes.contains(carteTresor)) {
    		this.removeCarteTresor(carteTresor);
    		Controleur.getInstance().getDefausseTresor().add(carteTresor);
    	}
    }
    
    public void utiliserCarteTresor(CarteTresor carteTresor) {
        ArrayList<CarteTresor> cartes = this.getCartesTresor();
        
        if(cartes.contains(carteTresor)) {
        	if(carteTresor instanceof Helicoptere || carteTresor instanceof SacDeSable) {
        		this.defausserCarteTresor(carteTresor);
        		
        		if(carteTresor instanceof Helicoptere) {
        			//Activation de l'effet de la carte : déplacer le joueur où il le souhaite
        		} else {
        			//Assécher la case voulu
        		}
        	}
        }
    }
}
