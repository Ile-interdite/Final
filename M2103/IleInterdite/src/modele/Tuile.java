package modele;

import java.util.ArrayList;

import modele.aventurier.Aventurier;
import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public class Tuile {

	private String nom;
	private Position position = new Position();
	private EtatTuile etatTuile;
	private Pion porte;
	private Tresor tresor;
	
	private ArrayList<Aventurier> aventuriers = new ArrayList<>();
	
	/*public Tuile() {
		this.setEtat(EtatTuile.ASSECHEE);
	}*/

	public Tuile(String nom, Pion porte, Tresor tresor) {
		//this(); //valable avec le premier constructeur
        this.setEtat(EtatTuile.ASSECHEE); //valable si on a plus le 1er constructeur
		this.setNom(nom);
		this.setPorte(porte);
		this.setTresor(tresor);
	}


	@Override
	public String toString(){
		return this.getNom() + " - " + this.getEtatTuile() + " - " + this.getPosition();
	}
	
	private void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public ArrayList<Aventurier> getAventuriers() {
		return aventuriers;
	}

	public void addAventurier(Aventurier aventurier) {
		this.getAventuriers().add(aventurier);
	}

	public void removeAventurier(Aventurier aventurier) {
		this.getAventuriers().remove(aventurier);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.getPosition().setX(x);
		this.getPosition().setY(y);
	}
	
	public EtatTuile getEtatTuile() {
		return etatTuile;
	}
	
	public void setEtat(EtatTuile etatTuile) {
		this.etatTuile = etatTuile;
	}
	
	private void setPorte(Pion porte) {
		this.porte = porte;
	}

	public Pion getPorte() {
		return porte;
	}	
	
	private void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}
	
	public Tresor getTresor() {
		return tresor;
	}
}
