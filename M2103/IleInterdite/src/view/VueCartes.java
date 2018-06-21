package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controleur;
import modele.Joueur;
import modele.carte.CarteTresor;

public class VueCartes extends JPanel {
	
	private Joueur joueur;
	
	public VueCartes(Joueur joueur) {
		this.setJoueur(joueur);
		Controleur controleur = Controleur.getInstance();
		JLabel label = new JLabel("Cartes du joueur n°" + (controleur.getJoueurs().indexOf(joueur)+1) + " : " + joueur.getName());
		this.add(label);
	}

	public void paintComponent(Graphics g) {
		ArrayList<CarteTresor> cartes = this.getJoueur().getCartesTresor();
		int widthCard = (int) (this.getWidth() * 0.15);
		int heightCard = (int) (this.getHeight() * 0.6);
		int xO = (this.getWidth() - (widthCard*5))/2;
		int yO = (int)((this.getHeight() - heightCard)/1.5);
		
		g.setColor(Color.BLACK);
		g.drawRect(xO, yO, widthCard*5, heightCard);
		int i = 0;
		
		for(CarteTresor carte : cartes) {
			try {
				Image image = ImageIO.read(new File("M2103/IleInterdite/images/cartes/" + carte.getLibelle().replaceAll(" ", "") + ".png"));
				g.drawImage(image, xO + (widthCard*i), yO, widthCard, heightCard, this);
				i++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
