package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Tuile;
import utils.Utils.EtatTuile;

public class VueTuile extends JPanel {
    
	private Tuile tuile;
    private int xO, yO, cote;
    
    public VueTuile(int xO, int yO, int cote, Tuile tuile) {
    	this.setTuile(tuile);
        this.setXO(xO);
        this.setYO(yO);
        this.setCote(cote);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	if(this.getXO() != 0 && this.getYO() != 0 && this.getCote() != 0 && tuile != null) {
    		Image image;
			try {
				Tuile tuile = this.getTuile();
				EtatTuile etatTuile = tuile.getEtatTuile();
				String fichier = "images/tuiles/" + tuile.getNom().replaceAll(" ", "").replaceAll("'", "") + (etatTuile == EtatTuile.INONDEE ? "_Inonde" : "") + ".png";
				image = ImageIO.read(new File(etatTuile == EtatTuile.COULEE ? "images/ocean.jpg" : fichier));
				g.drawImage(image, 5, 5, this.getWidth() - 10, this.getHeight() - 10, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		g.setColor(Color.BLUE);
    		g.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
    	}
    }
    
    public Tuile getTuile() {
    	return tuile;
    }
    
    public void setTuile(Tuile tuile) {
    	this.tuile = tuile;
    }

    public int getXO() {
        return xO;
    }

    private void setXO(int xO) {
        this.xO = xO;
    }

    public int getYO() {
        return yO;
    }

    private void setYO(int yO) {
        this.yO = yO;
    }

    public int getCote() {
        return cote;
    }

    private void setCote(int cote) {
        this.cote = cote;
    }
}
