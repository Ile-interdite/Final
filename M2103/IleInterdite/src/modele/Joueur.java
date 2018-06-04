package modele;

import modele.carte.CarteTresor;
import modele.aventurier.Aventurier;
import java.util.*;

public class Joueur {

    Collection<CarteTresor> cartes;
    private String nom;
    private Aventurier role;

    public Aventurier getRole() {
        return role;
    }
}
