package view.plateau.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.Controleur;
import utils.Tresor;

public class VueTresors extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		int i = 0;
		
		for(Tresor tresor : Tresor.values()) {
			try {
				int width = 75;
				int height = 105;
				int itemHeight = (int) (this.getHeight()/(4.5));
				int x = (int) (this.getWidth() * 0.1);
				int y = (itemHeight - height)/2 + itemHeight*(i);
				
				Image image = ImageIO.read(new File("M2103/IleInterdite/images/tresors/" + tresor.getLibelle().replaceAll(" ", "") + ".png"));
				g.drawImage(image, x, y, width, height, this);
				
				String disabled = "";
				
				if(!Controleur.getInstance().getTresorsPossedes().contains(tresor)) {
					disabled = "_disabled";
				}
				
				Image imageCheck = ImageIO.read(new File("M2103/IleInterdite/images/icones/iconDone" + disabled + ".png"));
				g.drawImage(imageCheck, (int) (this.getWidth() * 0.5), y + 25, 60, 55,  this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
