
package view;

import controller.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
        
        setVisible(true);
    }
    
    public JPanel principal(){
        
        JPanel principal = new JPanel(){
            public void paintComponent(Graphics g) {
                try {
                    Image backGround = ImageIO.read(new File("M2103/IleInterdite/images/ileinterdite.jpg"));
                    g.drawImage(backGround, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                
            }
        };
        principal.setLayout(new BorderLayout());
        principal.setOpaque(false);
        
        Joueur joueurCourant = Controleur.getInstance().getJoueurCourant();
        ArrayList<Joueur> joueurs = Joueur.getJoueurs();
        Tuile tuileCourante = joueurCourant.getAventurier().getTuileCourante();
        ArrayList<Aventurier> aventurierTuileCourante = tuileCourante.getAventuriers();
        aventurierTuileCourante.remove(joueurCourant.getAventurier()); //un joueur ne peut SE donner une carte
        
        GridLayout gridJoueur = new GridLayout(5,1);
        JPanel pJoueur = new JPanel(gridJoueur);
        pJoueur.setBorder(new EmptyBorder(50, 100, 50, 100));
        pJoueur.setOpaque(false);
        gridJoueur.setVgap(10);
        
        JButton btnJoueur = new JButton();
        btnJoueurs = new ArrayList<>();
        
        GridLayout gridBtn = new GridLayout(1,2);
        JPanel pBouton = new JPanel( gridBtn);
        gridBtn.setHgap(15);
        pBouton.setBorder(new EmptyBorder(30, 30, 30, 30));
        pBouton.setOpaque(false);
        
        JButton btnDonner = new JButton("Donner");
        btnDonner.setEnabled(false);
        btnDonner.setOpaque(false);
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setOpaque(false);
        pBouton.add(btnAnnuler);
        pBouton.add(btnDonner);
        
        for (Aventurier a : aventurierTuileCourante){
            boolean trouve = false;
            int i = 0;
            String nom = new String();
            String role = new String();
            while(!trouve){
                Joueur j = joueurs.get(i);
                if (j.getAventurier()==a){
                    nom=joueurs.get(i).getNom();
                    role = j.getAventurier().getClass().getSimpleName();
                    
                    pJoueur.add(btnJoueur);
                    btnJoueur.setOpaque(false);
                    btnJoueur.setText(nom + " : " + role);  //soit metre le role soit mettre la couleur de l'aventurier
                    btnJoueurs.add(btnJoueur );
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
                    dispose();
                }
        });

        btnAnnuler.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
        });
        
        principal.add(pJoueur, BorderLayout.CENTER);
        principal.add(pBouton, BorderLayout.SOUTH);
        
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
