package modele;

import java.util.ArrayList;

import modele.aventurier.Aventurier;
import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import view.plateau.grille.VueGrille;
import view.plateau.grille.VueTuile;

public class Tuile {

	private String nom;
	private Position position = new Position();
	private EtatTuile etatTuile;
	private Pion porte;
	private Tresor tresor;
	
	private ArrayList<Aventurier> aventuriers = new ArrayList<>();

	public Tuile(String nom, Pion porte, Tresor tresor) {
        this.setEtat(EtatTuile.ASSECHEE);
		this.setNom(nom);
		this.setPorte(porte);
		this.setTresor(tresor);
	}

	public void repaint() {
		VueTuile vueTuile = VueTuile.getInstance(this);
		
		if(vueTuile != null) {
			VueTuile.getInstance(this).repaint();			
		}
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
		this.repaint();
	}

	public void removeAventurier(Aventurier aventurier) {
		this.getAventuriers().remove(aventurier);
		this.repaint();
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
		
		if(etatTuile == EtatTuile.COULEE) {
			VueGrille.getInstance().repaint();
		} else {
			this.repaint();			
		}
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
