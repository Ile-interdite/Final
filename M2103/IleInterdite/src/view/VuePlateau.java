package view;

import javax.swing.JFrame;

public class VuePlateau extends JFrame {
	
	private static VuePlateau frame;
	private VueGrille vueGrille;
        
    public static void main(String[] args) {
        new VuePlateau();
    }
    
    public static VuePlateau getFrame() {
    	return frame;
    }
    
    public VuePlateau() {
    	frame = this;
        this.setTitle("Plateau");
        this.setSize(1800,900);
        this.setLocationRelativeTo(null);
        vueGrille = new VueGrille();
        this.add(vueGrille);
        this.setVisible(true);
        repaint();
    }
    
    public void refresh() {
    	vueGrille.refresh();
    }
}
