package controller;

import utils.Tresor;
import modele.carte.CarteTresor;
import modele.carte.CarteInondation;
import controller.Observateur;
import controller.Message;
import java.awt.Color;
import view.VuePlateau;
import java.util.*;
import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import view.VueAventurier;

public class Controleur implements Observateur {

    VuePlateau vuePlateau;
    private Collection<Tresor> tresorPossedes;
    private int niveauEau;
    private boolean etatPartie;
    private Grille grille;
    private Joueur joueurs[] = new Joueur[4]; 
    private CarteTresor cartes[];
    private CarteTresor pileTresor[];
    private CarteInondation pileInond[];
    private CarteInondation defausseInond[];
    

    public void seDeplacer() {
            // TODO - implement Controleur.seDeplacer
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param numJoueur
     */
    public Joueur getJoueur(int numJoueur) {
            // TODO - implement Controleur.getJoueur
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param x
     * @param y
     */
    public Tuile getTuile(int x, int y) {
            // TODO - implement Controleur.getTuile
            throw new UnsupportedOperationException();
    }

    public void assecher() {
            // TODO - implement Controleur.assecher
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param numJoueur
     */
    public void donnerCarte(int numJoueur) {
            // TODO - implement Controleur.donnerCarte
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param carte
     */
    public void choixCarte(CarteTresor carte) {
            // TODO - implement Controleur.choixCarte
            throw new UnsupportedOperationException();
    }

    /**
     * Description méthode
     * 
     * Détails
     * 
     *
     * @return type
     */
    public void initJeux() {
            grille = new Grille();




    }

    public void setNiveauEau() {
            // TODO - implement Controleur.setNiveauEau
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param etat
     */
    public void setEtatParti(boolean eta0t) {
            // TODO - implement Controleur.setEtatParti
            throw new UnsupportedOperationException();
    }

    public void utiliserCarte(Joueur joueur, CarteTresor carte) {
        //ArrayList<CarteTresor> cartes = joueur.getCartesTresor();
    }

    public void defausserCarte(Joueur joueur, CarteTresor carte) {

    }

    @Override
    public void traiterMessage(Message m) {
        if(m != null) {
            Joueur joueur = m.getJoueur();

            if(joueur != null) {
                switch(m.getTypeMessage()) {
                    case UTILISER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();

                        if(carte != null) {
                            utiliserCarte(joueur, carte);
                        }
                        break;
                    }
                    case DEFAUSSER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();

                        if(carte != null) {
                            defausserCarte(joueur, carte);
                        }
                        break;
                    }
                    case DEPLACEMENT: {
                        Tuile tuileCible = m.getTuileCible();

                        if(joueur != null) {
                           
                        }
                        break;
                    }
                    case ASSECHEMENT: {
                        Tuile tuileCible = m.getTuileCible();

                        if(joueur != null) {

                        }
                        break;
                    }
                    case DONNER_CARTE: {
                        break;
                    }
                    case RECUPERER_TRESOR: {
                        break;
                    }
                    default: {
                        break;
                    }
                }   
            }
        }
    }

}