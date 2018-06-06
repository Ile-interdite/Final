package modele;

import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import modele.aventurier.Aventurier;
import java.util.*;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Tuile {

    ArrayList<Aventurier> aventuriers;
    private String nom;
    private EtatTuile etat;
    private Pion porte;
    private Position position = new Position();
    private Tresor trésor;

    public Tuile(String nom,Pion porte, Tresor trésor){
        this.nom=nom;
        this.porte=porte;
        this.trésor=trésor;
        this.etat=EtatTuile.ASSECHEE;
    }

    @Override
    public String toString(){
        //return nom + " " + etat + " " + position;
        return nom;
    }
    
    public void addAventurier(Aventurier a) {
        aventuriers.add(a);
    }

    public void rmAventurier(Aventurier a) {
        aventuriers.remove(a);
    }

    public void setEtat(EtatTuile etat) {
        this.etat=etat;
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

    public Pion getPorte() {
        return porte;
    }
    
    

    
}
