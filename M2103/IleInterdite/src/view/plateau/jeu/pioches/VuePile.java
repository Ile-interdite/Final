package view.plateau.jeu.pioches;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.Controleur;
import modele.Joueur;
import modele.carte.CarteTresor;

public class VuePile extends JPanel {
	
	private static HashMap<Joueur, VuePile> piles = new HashMap<>();
	
	private static HashMap<Joueur, VuePile> getPiles() {
		return piles;
	}
	
	public static VuePile getInstance(Joueur joueur) {
		return VuePile.getPiles().get(joueur);
	}
	
	private Joueur joueur;
	private JPanel cartes;

	public VuePile(Joueur joueur) {
		this.setJoueur(joueur);
		this.setLayout(new BorderLayout(0,5));
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setOpaque(false);
		VuePile.getPiles().put(this.getJoueur(), this);
		
		JLabel label = new JLabel("Cartes du joueur n°" + (Joueur.getJoueurs().indexOf(joueur)+1) + " : " + joueur.getNom());
		cartes = this.createPanelCartes();
		
		this.add(label, BorderLayout.NORTH);
		this.add(cartes, BorderLayout.CENTER);
	}
	
	private JPanel createPanelCartes() {
		JPanel cartes = new JPanel(new GridLayout(1,6));
		HashMap<Integer, HashMap<CarteTresor, Integer>> cartesTresor = Controleur.getInstance().getCartesTriees(this.getJoueur());
		
		cartes.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 0, 10), new EtchedBorder(10)));
		cartes.setOpaque(false);
		
		for(int numCarte = 0; numCarte < 6; numCarte++) {
			if(numCarte < cartesTresor.size()) {
				CarteTresor carte = (CarteTresor) cartesTresor.get(numCarte).keySet().toArray()[0];
				cartes.add(new VueCarte(this.getJoueur(), carte, numCarte, cartesTresor.get(numCarte).get(carte)));
			} else {
				cartes.add(new JLabel(""));
			}
		}
		return cartes;
	}
	
	public void update() {
		cartes.removeAll();
		HashMap<Integer, HashMap<CarteTresor, Integer>> cartesTresor = Controleur.getInstance().getCartesTriees(this.getJoueur());
						
		for(int numCarte = 0; numCarte < 6; numCarte++) {
			if(numCarte < cartesTresor.size()) {
				CarteTresor carte = (CarteTresor) cartesTresor.get(numCarte).keySet().toArray()[0];
				cartes.add(new VueCarte(joueur, carte, numCarte, cartesTresor.get(numCarte).get(carte)));
			} else {
				cartes.add(new JLabel(""));
			}
		}
		this.validate();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
