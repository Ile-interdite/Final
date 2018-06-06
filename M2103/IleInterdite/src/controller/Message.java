package controller;

import modele.Joueur;
import modele.Tuile;
import modele.carte.CarteTresor;
import utils.Tresor;

public class Message {

    private TypeMessage typeMessage;
    private Tresor tresor;
    private Joueur joueur, joueurCible;
    private CarteTresor carteTresor;
    private Tuile tuileCible;

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
        
    public Joueur getJoueur() {
        return joueur;
    }
        
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
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

    public Tuile getTuileCible() {
        return tuileCible;
    }

    public void setTuileCible(Tuile tuileCible) {
        this.tuileCible = tuileCible;
    }   
}