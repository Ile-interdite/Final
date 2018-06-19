package view;

import javax.swing.JFrame;

public class VuePlateau extends JFrame {
    
    private static VuePlateau vuePlateau;
    
    public static void main(String[] args) {
        new VuePlateau();
    }
    
    public VuePlateau() {
        this.setTitle("Plateau");
        this.setSize(800,800);
        //this.setLocationRelativeTo(null);
        VueGrille vueGrille = new VueGrille();
        this.add(vueGrille);
        this.setVisible(true);
        vueGrille.repaint();
    }
}
