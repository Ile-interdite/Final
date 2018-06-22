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
import view.listeners.ClickCardListener;

public class VueCarte extends JPanel {
	
	private boolean addButtons = false;
	private double extendsWidth;
	private double extendsHeight;
	private CarteTresor carte;
	private int numCarte, nbOccurence;
	
	public VueCarte(CarteTresor carte, int numCarte, int nbOccurence) {
		this.setCarteTresor(carte);
		this.setNumCarte(numCarte);
		this.setNbOccurence(nbOccurence);
		this.setExtendsWidth(0);
		this.setExtendsHeight(0);
		
		JLabel label = new JLabel(this.getNbOccurence() > 1 ? ("X-" + this.getNbOccurence()) : "", JLabel.LEFT);
		label.setFont(label.getFont().deriveFont(18.0f));
		label.setForeground(Color.RED);
		label.setBounds(label.getX(), label.getY() + 100, 100, 100);
		this.add(label);
	}

	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/cartes/" + carte.getLibelle().replaceAll(" ", "") + ".png"));
			int x = (int) (0 - (this.getWidth() * (this.extendsWidth - (extendsWidth == 0 ? 0 : 1))/2));
			int y = (int) (0 - (this.getHeight() * (this.extendsWidth - (extendsWidth == 0 ? 0 : 1))/2));
			int width = (int) ((this.getWidth() - 2) * (extendsWidth == 0 ? 1 : extendsWidth));
			int height = (int) (this.getHeight() * (extendsHeight == 0 ? 1 : extendsHeight));
			
			g.drawImage(image, x, y, width, height, this);
			this.addMouseListener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMouseListener() {
		this.addMouseListener(new ClickCardListener(this));
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
	
	public double getExtendsWidth() {
		return extendsWidth;
	}
	
	public void setExtendsWidth(double extendsWidth) {
		this.extendsWidth = extendsWidth;
	}
	
	public double getExtendsHeight() {
		return extendsHeight;
	}
	
	public void setExtendsHeight(double extendsHeight) {
		this.extendsHeight = extendsHeight;
	}
}
