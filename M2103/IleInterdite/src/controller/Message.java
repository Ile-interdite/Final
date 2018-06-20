package controller;

import java.util.ArrayList;

import modele.Joueur;
import modele.Tuile;
import modele.carte.CarteTresor;
import utils.Tresor;

public class Message {

    private TypeMessage typeMessage;
    private Tresor tresor;
    private Tuile targetTuile;
    private Joueur joueurCible;
    private CarteTresor carteTresor;
    private ArrayList<String> nomsJoueurs = new ArrayList<>();

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
        
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
    
    public Tresor getTresor() {
    	return tresor;
    }
    
    public void setTresor(Tresor tresor) {
    	this.tresor = tresor;
    }
    
    public Tuile getTargetTuile() {
    	return targetTuile;
    }
    
    public void setTargetTuile(Tuile targetTuile) {
    	this.targetTuile = targetTuile;
    }
    
    public Joueur getJoueurCible() {
    	return joueurCible;
    }
    
    public void setJoueurCible(Joueur joueurCible) {
    	this.joueurCible = joueurCible;
    }

    public CarteTresor getCarteTresor() {
        return carteTresor;
    }

    public void setCarteTresor(CarteTresor carteTresor) {
        this.carteTresor = carteTresor;
    }
    
    public ArrayList<String> getNomsJoueurs() {
    	return nomsJoueurs;
    }
    
    public void addNomJoueur(String nomJoueur) {
    	this.getNomsJoueurs().add(nomJoueur);
    }
}