package view;

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
				Image image = ImageIO.read(new File("M2103/IleInterdite/images/tresors/" + tresor.getLibelle().replaceAll(" ", "") + ".png"));
				g.drawImage(image, (int) (this.getWidth() * 0.1), (int) (this.getHeight()/16 +  ((this.getHeight()/4)*i)), 50, 70,  this);
				
				String disabled = "";
				
				if(!Controleur.getInstance().getTresorPossedes().contains(tresor)) {
					disabled = "_disabled";
				}
				
				Image imageCheck = ImageIO.read(new File("M2103/IleInterdite/images/icones/iconDone" + disabled + ".png"));
				g.drawImage(imageCheck, (int) (this.getWidth() * 0.5), (int) (this.getHeight()/16 +  ((this.getHeight()/4)*i)), 60, 55,  this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
