package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import controller.Controleur;
import modele.Tuile;
import utils.Mode;

public class VuePlateau extends JFrame {
	
	private static VuePlateau frame;
	
	private Mode mode;
	private VueJeu vueJeu;
	private VueGrille vueGrille;
	        
    public static void main(String[] args) {
        new VuePlateau();
    }
    
    public static VuePlateau getFrame() {
    	return frame;
    }
    
    public VuePlateau() {
    	this.configFrame();
    	vueGrille = new VueGrille();
    	vueGrille.setLayout(new GridLayout(6,6));
        
        Dimension size = VuePlateau.getFrame().getSize();
        int cote = (int) size.getHeight();
        int xO = (int) (size.getWidth()/2 - (cote/2));
        int yO = (int) (size.getHeight()/2 - (cote/2));  
        int width = (int)(size.getWidth() - cote);
        int height = cote;
        
        vueJeu = new VueJeu(width, height);
        vueJeu.setPreferredSize(new Dimension(width, height));
        
        this.add(vueGrille, BorderLayout.CENTER);
        this.add(vueJeu, BorderLayout.EAST);
        this.drawTuiles(cote, xO, yO);
        
        this.setVisible(true);
    }
    
    public void configFrame() {
    	frame = this;
        this.setTitle("Plateau");
        this.setSize(1800,900);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }
    
    public void drawTuiles(int cote, int xO, int yO) {
    	int coteTuile = cote/6;
    	
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
            	boolean bool1 = ((i <= 1 && i >= 0) || (i >= 4 && i <= 5)) && (j == 0 || j == 5);
            	boolean bool2 = (i == 0 || i  == 5) && (j == 1 || j == 4);
            	VueTuile vueTuile = null;
            	
            	if(!(bool1 || bool2)) {
            		Tuile tuile = Controleur.getInstance().getTuile(j, i);
            		int xD = (int)(xO + (coteTuile*i));
            		int yD = (int)(yO + (coteTuile*j));
            		
            		vueTuile = new VueTuile(xD, yD, cote/6, tuile);
            	} else {
            		vueTuile = new VueTuile(0, 0, 0, null);
            	}
            	vueGrille.add(vueTuile);
            }
        }
    }
    
    public VueGrille getVueGrille() {
    	return vueGrille;
    }
    
    public void refresh() {
    	this.repaint();
    	vueJeu.refresh();
    }
    
    public Mode getMode() {
    	return mode;
    }
    
    public void setMode(Mode mode) {
    	if(mode != null && mode.getDeclaringClass() != null) {
    		this.mode = mode;
    	}
    }
}
