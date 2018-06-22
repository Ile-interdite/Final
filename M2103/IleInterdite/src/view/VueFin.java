package view;

import controller.Message;
import controller.Observateur;
import controller.Observe;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueFin extends JFrame implements Observe {

    private Observateur observateur;
    private JPanel principal;

    public VueFin() {
        this.setTitle("Initialisation de la partie");
        setSize(700, 700);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
        principal = new JPanel();
        
    }

    public static void main(String[] args) {

    }

    @Override
    public void setObservateur(Observateur observateur) {
        if (observateur != null) {
            this.observateur = observateur;
        }
    }

    @Override
    public void notifierObservateur(Message m) {
        getObservateur().traiterMessage(m);
    }

    @Override
    public Observateur getObservateur() {
        return observateur;
    }

}
