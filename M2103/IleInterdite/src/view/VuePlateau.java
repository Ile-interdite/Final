package view;

import javax.swing.JFrame;

public class VuePlateau extends JFrame {
	
	private static VuePlateau frame;
        
    public static void main(String[] args) {
        new VuePlateau();
    }
    
    public static VuePlateau getFrame() {
    	return frame;
    }
    
    public VuePlateau() {
    	frame = this;
        this.setTitle("Plateau");
        this.setSize(1000,1000);
        //this.setLocationRelativeTo(null);
        VueGrille vueGrille = new VueGrille();
        this.add(vueGrille);
        this.setVisible(true);
    }
}
