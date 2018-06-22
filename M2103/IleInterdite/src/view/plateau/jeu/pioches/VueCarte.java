package view.plateau.jeu.pioches;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.carte.CarteTresor;

public class VueCarte extends JPanel {
	
	private CarteTresor carte;
	private int numCarte, nbOccurence;
	
	public VueCarte(CarteTresor carte, int numCarte, int nbOccurence) {
		this.setCarteTresor(carte);
		this.setNumCarte(numCarte);
		this.setNbOccurence(nbOccurence);
		JLabel label = new JLabel(this.getNbOccurence() > 1 ? ("X-" + this.getNbOccurence()) : "", JLabel.LEFT);
		label.setFont(label.getFont().deriveFont(18.0f));
		label.setForeground(Color.RED);
		label.setBounds(label.getX(), label.getY() + 100, 100, 100);
		this.add(label);
	}

	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/cartes/" + carte.getLibelle().replaceAll(" ", "") + ".png"));
			g.drawImage(image, 2, 0, this.getWidth() - 2, this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CarteTresor getCarteTresor() {
		return carte;
	}
	
	private void setCarteTresor(CarteTresor carte) {
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
}
