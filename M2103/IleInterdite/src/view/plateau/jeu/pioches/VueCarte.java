package view.plateau.jeu.pioches;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Joueur;
import modele.Tuile;
import modele.carte.CTresor;
import modele.carte.CarteTresor;
import modele.carte.CarteHelicoptere;

public class VueCarte extends JPanel implements Observe {
	
	private Controleur controleur;
	private Observateur observateur;
	private MouseListener mouseListener;	
	
	private Joueur joueur;
	private CarteTresor carte;
	public boolean addButtons = false;
	private int numCarte, nbOccurence;
	
	public VueCarte(Joueur joueur, CarteTresor carte, int numCarte, int nbOccurence) {
		this.setControleur(Controleur.getInstance());
		this.setObservateur(controleur);
		this.setJoueur(joueur);
		this.setCarte(carte);
		this.setNumCarte(numCarte);
		this.setNbOccurence(nbOccurence);
		
		JLabel label = new JLabel(this.getNbOccurence() > 1 ? ("X-" + this.getNbOccurence()) : "", JLabel.LEFT);
		label.setFont(label.getFont().deriveFont(18.0f));
		label.setForeground(Color.RED);
		label.setBounds(label.getX(), label.getY() + 100, 100, 100);
		this.add(label);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		try {
			Image imageCarte = ImageIO.read(new File("M2103/IleInterdite/images/cartes/" + carte.getLibelle().replaceAll(" ", "") + ".png"));
			int x = 0;
			int y = 0;
			int width = this.getWidth() - 2;
			int height = this.getHeight();
			this.addClickCardListener();
			
			g.drawImage(imageCarte, x, y, width, height, this);
			
			if(addButtons) {
				Joueur joueur = this.getJoueur();
				Tuile tuile = joueur.getAventurier().getTuileCourante();
				CarteTresor carte = this.getCarte();
				Image image = null;
				
				if(carte instanceof CTresor) {
					image = ImageIO.read(new File("M2103/IleInterdite/images/icones/iconGive" + (tuile.getAventuriers().size() > 1 ? "" : "_disabled") + ".png"));					
				} else {
					String type = (carte instanceof CarteHelicoptere ? "iconMove" : "iconDry");
					image = ImageIO.read(new File("M2103/IleInterdite/images/icones/" + type + ".png"));
				}
				
				x = 10;
				y = (int) (this.getHeight() * 0.65);
				int side = (int) (this.getWidth() * 0.2);
				
				g.drawImage(image, x, y, side, side, this);
				
				Image discard = ImageIO.read(new File("M2103/IleInterdite/images/icones/trash.png"));
				x = this.getWidth() - x - side;
				
				g.drawImage(discard, x, y, side, side, this);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addClickCardListener() {
		if(mouseListener == null) {
			mouseListener = new MouseListener() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(getControleur().getJoueurCourant() == getJoueur()) {
						CarteTresor carte = getCarte();
						int x = 10;
						int y = (int) (getHeight() * 0.65);
						int side = (int) (getWidth() * 0.2);
						
						if((e.getX() >= x && e.getX() <= x + side) && (e.getY() >= y && e.getY() <= y + side)) {
							Message message = new Message();
							
							if(carte instanceof CTresor) {
								message.setTypeMessage(TypeMessage.DONNER_CARTE);
								message.setCarteTresor(carte);								
							} else {
								
							}
							
							notifierObservateur(message);
						}
						
						x = getWidth() - x - side;
						
						if((e.getX() >= x && e.getX() <= x + side) && (e.getY() >= y && e.getY() <= y + side)) {
							Message message = new Message();
							message.setTypeMessage(TypeMessage.DEFAUSSER_CARTE);
							message.setCarteTresor(carte);
							
							notifierObservateur(message);
						}
						
						if(!(carte instanceof CTresor)) {
							x = (getWidth() - side)/2;
							y = getHeight() - (int) (side*1.25);
							
							if((e.getX() >= x && e.getX() <= x + side) && (e.getY() >= y && e.getY() <= y + side)) {
								Message message = new Message();
								message.setTypeMessage(TypeMessage.UTILISER_CARTE);
								message.setCarteTresor(carte);
								
								notifierObservateur(message);
							}
						}				
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					if(getControleur().getJoueurCourant() == getJoueur()) {
						addButtons = true;
						repaint();
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					if(getControleur().getJoueurCourant() == getJoueur()) {
						addButtons = false;
						repaint();
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseReleased(MouseEvent e) {}
			};
			this.addMouseListener(mouseListener);
		}
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	private void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public CarteTresor getCarte() {
		return carte;
	}
	
	private void setCarte(CarteTresor carte) {
		this.carte = carte;
	}
	
	public int getNumCarte() {
		return numCarte;
	}
	
	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}
	
	public int getNbOccurence() {
		return nbOccurence;
	}
	
	public void setNbOccurence(int nbOccurence) {
		this.nbOccurence = nbOccurence;
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
