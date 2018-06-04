package modele;

import modele.carte.CarteTresor;
import modele.aventurier.Aventurier;
import java.util.*;

public class Joueur {

    ArrayList<CarteTresor> cartes;
    private String nom;
    private Aventurier role;

    public Joueur(String nom) {
        this.nom=nom;
    }

    public Aventurier getRole() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
    }

    public Tuile getTuile() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param joueur
     */
    public boolean verifMemeTuile(Joueur joueur) {
        throw new UnsupportedOperationException();
    }

    public CarteTresor[] getCartes() {
        // TODO - implement Joueur.getCartes
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param carte
     */
    public void addCarte(CarteTresor carte) {
        // TODO - implement Joueur.addCarte
        throw new UnsupportedOperationException();
    }

    public void setRole(Aventurier a) {
        this.role=a;
    }

    public void setCartes(CarteTresor c) {
        cartes.add(c);
    }

    public void rmCarte() {
    }

    /**
     *
     * @param carte
     */
    public void rmCarte(CarteTresor carte) {
        // TODO - implement Joueur.rmCarte
        throw new UnsupportedOperationException();
    }

    public int getNbActionsRestantes() {
        // TODO - implement Joueur.getNbActionsRestantes
        throw new UnsupportedOperationException();
    }

    public void deplacer(Tuile tuileCible) {
        // TODO (Dorian et Mamoune)
    }
}
