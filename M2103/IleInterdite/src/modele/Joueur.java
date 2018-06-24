package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import controller.Controleur;
import modele.aventurier.Aventurier;
import modele.aventurier.Navigateur;
import modele.carte.CarteHelicoptere;
import modele.carte.CarteInondation;
import modele.carte.CarteMDE;
import modele.carte.CarteTresor;
import utils.Mode;
import view.VuePlateau;

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
            joueur.piocherCarte(5);
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
		VuePlateau.getInstance().getVueJeu().refresh();
		
		if(this.getAventurier() instanceof Navigateur && pointsAction == 3 && VuePlateau.getInstance() == null) {
			this.pointsAction++;
		}
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
		return getMain();
	}
	
	public void addCarte(CarteTresor carte) {
		this.getMain().add(carte);
		this.updateCartes();
	}
	
	public void removeCarte(CarteTresor carte) {
		this.getMain().remove(carte);
		this.updateCartes();
	}
	
	/**
	 * Met à jour l'affichage des cartes.
	 */
	public void updateCartes() {
		if(Controleur.getInstance().isPartieActive()) {
			VuePlateau.getInstance().getVueJeu().getVueListePiles().update(this);
		}
	}
	
	public void trierCartes() {
		ArrayList<CarteTresor> cartesH = new ArrayList<>();
		ArrayList<CarteTresor> cartesSac = new ArrayList<>();
		ArrayList<CarteTresor> carteStatue = new ArrayList<>();
		ArrayList<CarteTresor> carteCristal = new ArrayList<>();
		ArrayList<CarteTresor> carteCalice = new ArrayList<>();

//		for(CarteTresor c : this.){
//			
//		}
		
		
		
		
		/*TreeSet<CarteTresor> cartesTresorTriees = new TreeSet<>();
		
		for(CarteTresor carte : this.getMain()) {
			cartesTresorTriees.add(carte);
		}
		this.getMain().clear();
		
		for(CarteTresor carte : cartesTresorTriees) {
			this.getMain().add(carte);
		}*/
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
