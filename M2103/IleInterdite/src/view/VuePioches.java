package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controleur;

public class VuePioches extends JPanel {
	
	public VuePioches() {
		this.setLayout(new GridLayout(4,1));
		
		for(int i = 0; i < 4; i++) {
			if(i < Controleur.getInstance().getJoueurs().size()) {
				VueCartes vueCartes = new VueCartes(Controleur.getInstance().getJoueurs().get(i));
				this.add(vueCartes);
			} else {
				this.add(new JLabel(""));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		try {   
                    Image image = ImageIO.read(new File("images/fond-blanc-gris.jpg"));
                    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
                    e.printStackTrace();
		}
	}
}
