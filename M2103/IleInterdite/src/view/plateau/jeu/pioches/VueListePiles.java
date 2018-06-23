package view.plateau.jeu.pioches;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controleur;
import modele.Joueur;

public class VueListePiles extends JPanel {
		
	public VueListePiles() {
		Controleur controleur = Controleur.getInstance();
		this.setLayout(new GridLayout(4,1));
		this.setOpaque(false);
			
		for(int i = 0; i < 4; i++) {
			if(i < controleur.getJoueurs().size()) {
				Joueur joueur = controleur.getJoueurs().get(i);
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
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
}
