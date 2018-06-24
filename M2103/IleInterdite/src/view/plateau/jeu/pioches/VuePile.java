package view.plateau.jeu.pioches;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Joueur;
import modele.carte.CarteTresor;
import utils.Mode;
import view.VuePlateau;

public class VuePile extends JPanel implements Observe {
	
	private static HashMap<Joueur, VuePile> piles = new HashMap<>();
	
	private static HashMap<Joueur, VuePile> getPiles() {
		return piles;
	}
	
	public static VuePile getInstance(Joueur joueur) {
		return VuePile.getPiles().get(joueur);
	}
	
	private Observateur observateur;
	
	private boolean asBorder = false;
	private Joueur joueur;
	private JPanel cartes;
	private MouseListener mouseListener;

	public VuePile(Joueur joueur) {
		this.setObservateur(Controleur.getInstance());
		this.setJoueur(joueur);
		this.setLayout(new BorderLayout(0,5));
		this.setBorder(new EmptyBorder(0,0,10,0));
		this.setOpaque(false);
		VuePile.getPiles().put(this.getJoueur(), this);
		
		JLabel label = new JLabel("Cartes du joueur n°" + (Joueur.getJoueurs().indexOf(joueur)+1) + " : " + joueur.getNom(), JLabel.CENTER);
		label.setForeground(joueur.getAventurier().getPion().getCouleur());
		label.setFont(label.getFont().deriveFont(14.0f));
		cartes = this.createPanelCartes();
		
		this.add(label, BorderLayout.NORTH);
		this.add(cartes, BorderLayout.CENTER);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(VuePlateau.getInstance().getMode() == Mode.ECHANGE && asBorder) {
			g.setColor(Color.GREEN);
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
		}
		this.addMouseListener();
	}
	
	public void addMouseListener() {
		if(mouseListener == null) {
			mouseListener = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(VuePlateau.getInstance().getMode() == Mode.ECHANGE) {
						if(getJoueur() != Controleur.getInstance().getJoueurCourant()) {
							Message message = new Message();
							message.setTypeMessage(TypeMessage.DONNER_CARTE);
							message.setJoueurCible(getJoueur());
							notifierObservateur(message);
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if(VuePlateau.getInstance().getMode() == Mode.ECHANGE) {
						if(getJoueur() != Controleur.getInstance().getJoueurCourant()) {
							setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							asBorder = true;
							repaint();
						}
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if(VuePlateau.getInstance().getMode() == Mode.ECHANGE) {
						if(getJoueur() != Controleur.getInstance().getJoueurCourant()) {
							setCursor(Cursor.getDefaultCursor());
							asBorder = false;
							repaint();
						}	
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}	
			};
			addMouseListener(mouseListener);
		}
	}
	
	private JPanel createPanelCartes() {
		JPanel cartes = new JPanel(new GridLayout(1,6));
		HashMap<Integer, HashMap<CarteTresor, Integer>> cartesTresor = Controleur.getInstance().getCartesTriees(this.getJoueur());
		
		cartes.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 20, 3, 20), new EtchedBorder(10)));
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
		this.repaint();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	@Override
	public void setObservateur(Observateur observateur) {
		if(observateur != null) {
			this.observateur = observateur;
		}
	}

	@Override
	public void notifierObservateur(Message m) {
		this.getObservateur().traiterMessage(m);
	}

	@Override
	public Observateur getObservateur() {
		return observateur;
	}
}
