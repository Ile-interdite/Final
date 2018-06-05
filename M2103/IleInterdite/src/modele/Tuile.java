package modele;

import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import modele.aventurier.Aventurier;
import java.util.*;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Tuile {

    Collection<Aventurier> aventuriers;
    private String nom;
    private EtatTuile etat;
    private Pion porte;
    private Position position;
    private Tresor trésor;

    public Tuile(String nom,Pion porte, Tresor trésor){
        this.nom=nom;
        this.porte=porte;
        this.trésor=trésor;
        this.etat=EtatTuile.ASSECHEE;
    }

    
    public void addAventurier(Joueur j) {
        ;
    }

    public void rmAventurier(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void setEtat() {
        throw new UnsupportedOperationException();
    }

    public Position getPosition() {
        return position;
    }
    
    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public EtatTuile getEtat() {
        return etat;
    }
    

    
}
