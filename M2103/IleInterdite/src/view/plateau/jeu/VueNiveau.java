package view.plateau.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.Controleur;

public class VueNiveau extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		try {
            Image image = ImageIO.read(new File("M2103/IleInterdite/images/niveau.png"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			
			int x = 9;
			int y = this.getHeight() - 121 - (53*Controleur.getInstance().getNiveauEau());
			int width = 60;
			int height = 30;
			
            Image imageStick = ImageIO.read(new File("M2103/IleInterdite/images/stick.png"));
			g.drawImage(imageStick, x, y, width, height, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
