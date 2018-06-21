
package view;

import controller.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modele.*;
import modele.aventurier.*;

public class VueDonnerCarte extends JFrame implements Observe {
    
    private Observateur observateur;  
    private ArrayList<JButton> btnJoueurs;
    private Joueur joueurCible;
    public VueDonnerCarte(){
        this.setTitle("Initialisation de la partie");
        setSize(700, 400);
        
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);    
        
        this.add(principal());
        
    }
    
    public JPanel principal(){
        
        JPanel principal = new JPanel();
        principal.setLayout(new GridLayout(5,1));
        
        Joueur joueurCourant = Controleur.getInstance().getJoueurCourant();
        ArrayList<Joueur> joueurs = Controleur.getInstance().getJoueurs();
        Tuile tuileCourante = joueurCourant.getRole().getTuileCourante();
        
        JButton btnJoueur = new JButton();
        btnJoueurs = new ArrayList<>();
        
        JPanel pBouton = new JPanel( new GridLayout(1,2));
        JButton btnDonner = new JButton("Donner");
        btnDonner.setEnabled(false);
        JButton btnAnnuler = new JButton("Annuler");
        pBouton.add(btnDonner);
        pBouton.add(btnAnnuler);
        
        for (Aventurier a : tuileCourante.getAventuriers()){
            boolean trouve = false;
            int i = 0;
            String nom = new String();
            while(!trouve){
                Joueur j = joueurs.get(i);
                if (j.getRole()==a){
                    nom=joueurs.get(i).getName();
                    principal.add(btnJoueur);
                    btnJoueur.setText(nom);
                    
                    btnJoueurs.add(btnJoueur);
                    
                    btnJoueur.addActionListener( new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (JButton b : btnJoueurs){
                                if (b.equals(btnJoueur)){
                                    setJoueurCible(j);
                                    b.setBackground(Color.green);
                                    b.setEnabled(false);
                                    btnDonner.setEnabled(true);
                                }
                            }
                        }
                    });
                    trouve = true; 
                }else{
                    i++;
                }
            }
        }

        btnDonner.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.DONNER_CARTE);
                    m.setJoueurCible(joueurCible);
                    notifierObservateur(m);
                }
        });

        btnAnnuler.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
        });
        
        principal.add(pBouton);
        
        return principal;
    }
    
    public void setJoueurCible(Joueur joueur) {
        this.joueurCible = joueur;
    }
    
    @Override
    public void setObservateur(Observateur observateur) {}

    @Override
    public void notifierObservateur(Message m) {}

    @Override
    public Observateur getObservateur() {
        return observateur;
    }
    
}
