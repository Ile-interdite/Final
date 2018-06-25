package view.plateau.jeu.pioches;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Joueur;

public class VueListePiles extends JPanel {
	
	private ArrayList<VuePile> vuesPiles = new ArrayList<>();
			
	public VueListePiles() {
		this.setLayout(new GridLayout(4,1));
		this.setBorder(new EmptyBorder(0, 0, 50, 0));
		this.setOpaque(false);
			
		for(int i = 0; i < 4; i++) {
			if(i < Joueur.getJoueurs().size()) {
				Joueur joueur = Joueur.getJoueurs().get(i);
				VuePile vuePile = new VuePile(joueur);
				this.add(vuePile);
			} else {
				this.add(new JLabel(""));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/fond_boussole.jpg"));
			g.drawImage(image, 0, 0 - (int) (this.getHeight() * 0.025), this.getWidth(), (int) (this.getHeight() * (1.05)), this);
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void update(Joueur joueur) {
		this.repaint();
		VuePile.getInstance(joueur).update();
	}

	public ArrayList<VuePile> getVuesPiles() {
		return vuesPiles;
	}

	public void setVuesPiles(ArrayList<VuePile> vuesPiles) {
		this.vuesPiles = vuesPiles;
	}
}
