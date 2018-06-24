package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import controller.Controleur;
import modele.aventurier.Aventurier;
import modele.carte.CarteHelicoptere;
import modele.carte.CarteInondation;
import modele.carte.CarteMDE;
import modele.carte.CarteTresor;
import utils.Mode;
import view.VuePlateau;
import view.plateau.jeu.pioches.VuePile;

public class Joueur {
	
	private static ArrayList<Joueur> joueurs = new ArrayList<>();
	
	public static ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public static void addJoueur(Joueur joueur) {
		getJoueurs().add(joueur);
	}
	
	public static void initialiserJoueurs(List<String> nomsJoueurs) {
		for (int i = 0; i < nomsJoueurs.size(); i++) {
            Joueur joueur = new Joueur(nomsJoueurs.get(i));
            Aventurier aventurier = Aventurier.getAventuriers().get(i);
            joueur.setAventurier(aventurier);
            aventurier.spawn();
            addJoueur(joueur);
            joueur.piocherCarte(2);
        }
	}

	private Aventurier aventurier;
	private String nom;
	private int pointsAction = 3;
	private ArrayList<CarteTresor> cartes = new ArrayList<>();
	
	public Joueur(String nom) {
		this.setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {	
		this.nom = nom;
	}

	public Aventurier getAventurier() {
		return aventurier;
	}

	public void setAventurier(Aventurier aventurier) {
		this.aventurier = aventurier;
	}

	public int getPointsAction() {
		return pointsAction;
	}
	
	public void setPointsAction(int pointsAction) {
		this.pointsAction = pointsAction;
	}
	
	/**
	 * @return La pile de cartes du joueur.
	 */
	private ArrayList<CarteTresor> getMain() {
		return cartes;
	}
	
	/**
	 * @return Une nouvelle liste de cartes comportant celles du joueur.
	 */
	public ArrayList<CarteTresor> getCartes() {
		ArrayList<CarteTresor> cartes = new ArrayList<>();
		
		for(CarteTresor carte : this.getMain()) {
			cartes.add(carte);
		}
		return cartes;
	}
	
	public void addCarte(CarteTresor carte) {
		this.getMain().add(carte);
		this.trierCartes();
		this.updateCartes();
	}
	
	public void removeCarte(CarteTresor carte) {
		this.getMain().remove(carte);
		this.trierCartes();
		this.updateCartes();
	}
	
	/**
	 * Met à jour l'affichage des cartes.
	 */
	public void updateCartes() {
		if(Controleur.getInstance().isPartieActive()) {
			VuePile.getInstance(this).update();
		}
	}
	
	public void trierCartes() {
		TreeSet<CarteTresor> cartesTresorTriees = new TreeSet<>();
		
		for(CarteTresor carte : this.getMain()) {
			cartesTresorTriees.add(carte);
		}
		this.getMain().clear();
		
		for(CarteTresor carte : cartesTresorTriees) {
			this.getMain().add(carte);
		}
	}
	
	public void defausserCarte(CarteTresor carte) {    	
		this.removeCarte(carte);
		CarteTresor.addCarteToDefausse(carte);
	}
	
	public void utiliserCarte(CarteTresor carte, Tuile tuile) {
		this.defausserCarte(carte);
		
		if(carte instanceof CarteHelicoptere) {
			this.getAventurier().seDeplacer(tuile);
		} else {
			this.getAventurier().assecher(tuile);
		}
		VuePlateau.getInstance().setMode(Mode.NORMAL);
	}
	
	public void donnerCarte(CarteTresor carte, Joueur joueur) {
		this.removeCarte(carte);
		joueur.addCarte(carte);
        this.setPointsAction(this.getPointsAction() - 1);
	}
	
	public void piocherCarte(int nombre) {
		boolean MDE = false;
		
		for(int i = 0; i < nombre; i++) {
			CarteTresor carte = CarteTresor.piocher();
			
			if(carte instanceof CarteMDE) {
				CarteMDE carteMDE = (CarteMDE) carte;
				
				if(Controleur.getInstance().isPartieActive()) {
					carteMDE.utiliser();
					MDE = true;
				} else {
					this.piocherCarte(1);
					CarteTresor.addCarteToPile(carteMDE);
				}
			} else {
				this.addCarte(carte);
			}
		}
		
		if(MDE) {
			CarteInondation.defausseToPile();
		}
	}
}
