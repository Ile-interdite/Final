package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.Grille;
import modele.Tuile;
import utils.Mode;
import view.plateau.grille.VueGrille;
import view.plateau.grille.VueTuile;
import view.plateau.jeu.VueJeu;
import view.plateau.jeu.pioches.VueListePiles;
import view.plateau.jeu.pioches.VuePile;

public class VuePlateau extends JFrame {
	
	private static VuePlateau instance;
	
	public static VuePlateau getInstance() {
		return instance;
	}
	
	private Mode mode;
	private VueJeu vueJeu;
	private VueGrille vueGrille;
    
    
    public VuePlateau() {
    	this.configFrame();
    	vueGrille = new VueGrille();
    	vueGrille.setLayout(new GridLayout(6,6));
        
        Dimension size = this.getSize();
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
    	instance = this;
        this.setTitle("Plateau");
        this.setSize(1900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
    }
    
    public void drawTuiles(int cote, int xO, int yO) {
    	int coteTuile = cote/6;
    	
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
            	boolean bool1 = ((i <= 1 && i >= 0) || (i >= 4 && i <= 5)) && (j == 0 || j == 5);
            	boolean bool2 = (i == 0 || i  == 5) && (j == 1 || j == 4);
            	
            	if(!(bool1 || bool2)) {
            		Tuile tuile = Grille.getTuile(j, i);
            		int xD = (int)(xO + (coteTuile*i));
            		int yD = (int)(yO + (coteTuile*j));
            		
            		VueTuile vueTuile = new VueTuile(xD, yD, cote/6, tuile);
            		vueGrille.add(vueTuile);
            	} else {
            		vueGrille.add(new JLabel(""));
            	}
            }
        }
    }
    
    public void refresh() {
    	System.out.println();
    	System.out.println("test");
    	this.repaint();
    	vueJeu.refresh();
    }
    
    public Mode getMode() {
    	return mode;
    }
    
    public void setMode(Mode mode) {
    	if(mode != null && mode.getDeclaringClass() != null) {
    		this.mode = mode;
    		
    		if(mode != Mode.ECHANGE) {
    			VueTuile.repaintAll();    			
    		} else {
    			VueListePiles vueListePiles = this.getVueJeu().getVueListePiles();
    			vueListePiles.repaint();
    			
    			for(VuePile vuePile : vueListePiles.getVuesPiles()) {
    				vuePile.update();
    			}
    		}
    	}
    }
    
    public VueGrille getVueGrille() {
    	return vueGrille;
    }
    
    public VueJeu getVueJeu() {
    	return vueJeu;
    }
}
