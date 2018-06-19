package view;

import javax.swing.JFrame;

public class IHM {
    private JFrame frame;
    private VueSelection vues= new VueSelection();
    
    public IHM(){
        
        frame = new JFrame("Initialisation de la partie");
        frame.setSize(700, 700);
        
        //pour ne pas changer la taille de la fenetre
        frame.setResizable(false);

        //pour mettre au centre de l'écran
        frame.setLocationRelativeTo(null);
        frame.add(vues);
        vues.repaint();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new IHM();
    }
}
