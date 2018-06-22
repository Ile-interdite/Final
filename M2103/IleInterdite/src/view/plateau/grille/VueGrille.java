package view.plateau.grille;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VueGrille extends JPanel {
    
    @Override
    public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/fond_ocean.jpg"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
