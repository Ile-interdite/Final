package modele;

import java.util.ArrayList;
import java.util.Iterator;

import controller.Controleur;
import modele.aventurier.Aventurier;
import utils.Tresor;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import view.VuePlateau;
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
			VuePlateau.getInstance().getVueGrille().repaint();
			
			if(this.getNom().equals("Héliport")) {
				Controleur.getInstance().setPartieActive(false, "Héliport coulé");
			} else {				
				if(this.getTresor() != null && !Controleur.getInstance().getTresorsPossedes().contains(this.getTresor())) {
					boolean trouve = false;
					Iterator<Tuile> iterator = Controleur.getInstance().getGrille().getTuiles().iterator();
					
					while(iterator.hasNext() && !trouve) {
						Tuile tuile = iterator.next();
						
						if(tuile != this && tuile.getTresor() == this.getTresor()) {
							trouve = true;
						}
					}
					
					if(trouve) {
						Controleur.getInstance().setPartieActive(false, "Les deux tuiles du trésor \"" + this.getTresor().getLibelle() + "\" ont coulé");
					}
				} else {
					if(!this.getAventuriers().isEmpty()) {
						Aventurier aventurier = null;
						boolean deplace = true;
						int i = 0;
						
						while(deplace && i < this.getAventuriers().size()) {
							aventurier = this.getAventuriers().get(i);
							
							if(!aventurier.getDeplacement(this).isEmpty()) {
								//A AMELIORER
								aventurier.seDeplacer(aventurier.getDeplacement(this).get(0));
							} else {
								deplace = false;
							}
						}
						
						if(!deplace) {
							Controleur.getInstance().setPartieActive(false, "L'aventurier \"" + aventurier.getPion().getLibelle() + "\" est mort");
						}
					}
				}
			}
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
