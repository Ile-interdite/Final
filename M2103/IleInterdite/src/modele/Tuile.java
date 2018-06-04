package modele;

import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import modele.aventurier.Aventurier;
import java.util.*;

public class Tuile {

    Collection<Aventurier> aventuriers;
    private String nom;
    private EtatTuile etat;
    private Pion porte;
    private int x;
    private int y;
    private Tresor trésor;

    public Tuile(String nom,Pion porte, Tresor trésor){
        this.nom=nom;
        this.porte=porte;
        this.trésor=trésor;
        this.etat=EtatTuile.ASSECHEE;
    }
    /**
     * 
     * @param j
     */
    public void addAventurier(Joueur j) {
        ;
    }

    /**
     * 
     * @param j
     */
    
    public void rmAventurier(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void setEtat() {
        throw new UnsupportedOperationException();
    }

}