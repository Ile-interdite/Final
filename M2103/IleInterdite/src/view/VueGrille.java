package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.Controleur;
import modele.Tuile;

public class VueGrille extends JPanel {
    
    private JPanel grille;
    private int cote;
    private int xO;
    private int yO;
    
    public VueGrille() {
        this.setLayout(new BorderLayout());
        grille = new JPanel();
        grille.setLayout(new GridLayout(6,6));
        
        Dimension size = VuePlateau.getFrame().getSize();
        this.setCote(600);
        this.setXO((int)(size.getWidth()/2 - (cote/2)));
        this.setYO((int)(size.getHeight()/2 - (cote/2)));
        
        this.add(grille, BorderLayout.CENTER);
        this.drawTuiles();
    }
    
    public void drawTuiles() {
    	int coteTuile = this.getCote()/6;
    	System.out.println(coteTuile);
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
            	boolean bool1 = ((i <= 1 && i >= 0) || (i >= 4 && i <= 5)) && (j == 0 || j == 5);
            	boolean bool2 = (i == 0 || i  == 5) && (j == 1 || j == 4);
            	VueTuile vueTuile = null;
            	
            	if(!(bool1 || bool2)) {
            		Tuile tuile = Controleur.getInstance().getTuile(j, i);
            		int xD = (int)(this.getXO() + (coteTuile*i));
            		int yD = (int)(this.getYO() + (coteTuile*j));
            		
            		vueTuile = new VueTuile(xD, yD, this.getCote()/6, tuile);
            	} else {
            		vueTuile = new VueTuile(0, 0, 0, null);
            	}
            	grille.add(vueTuile);
            }
        }
    }

    public int getCote() {
        return cote;
    }

    private void setCote(int cote) {
        this.cote = cote;
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
}
