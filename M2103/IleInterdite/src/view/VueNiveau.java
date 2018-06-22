package view;

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
			//Image image = ImageIO.read(new File("M2103/IleInterdite/images/niveau.png"));
                        //CHEMIN RELATIF
                        Image image = ImageIO.read(new File("images/niveau.png"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			
			//Image imageStick = ImageIO.read(new File("M2103/IleInterdite/images/stick.png"));
                        //CHEMIN RELATIF
                        Image imageStick = ImageIO.read(new File("images/stick.png"));
			g.drawImage(imageStick, -10, this.getHeight() - 124 - (48*Controleur.getInstance().getNiveauEau()), 100, 50, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
