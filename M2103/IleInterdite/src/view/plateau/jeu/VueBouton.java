package view.plateau.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VueBouton extends JPanel {
	
	private Image image;
	private MouseListener mouseListener;

	public VueBouton(File file) {
		try {
			this.setImage(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
