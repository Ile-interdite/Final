package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import utils.Utils;

public class VueSelection implements Observe {

    private Observateur observateur;
    private JFrame frame;
    private JPanel principal ,header, center, niveau, joueur, footer;
    private JLabel nomJeu;
    private ButtonGroup difficulte;
    private JButton addJoueur, btnStop, btnStart;
    private JTextField j1, j2, j3, j4;
    private int joueurEnPLus = 0;
    public VueSelection() {
        frame = new JFrame("Initialisation de la partie");
        frame.setSize(700, 700);
        
        //pour ne pas changer la taille de la fenetre
        frame.setResizable(false);

        //pour mettre au centre de l'écran
        frame.setLocationRelativeTo(null);
        
        principal = new JPanel( new BorderLayout());
        
        //////////////////////////
        // HEADER
        //////////////////////////
        header = new JPanel();
        nomJeu = new JLabel("Ile Interdite");
        nomJeu.setFont( new Font("Arial",Font.BOLD,100));
        header.add(nomJeu);
        principal.add(header, BorderLayout.NORTH);
        
        //////////////////////////
        // CENTRE
        //////////////////////////
        center = new JPanel( new GridLayout(3,1));
        niveau = new JPanel( new GridLayout(1,5));
        joueur = new JPanel( new GridLayout(5,1));
        
        //creation choix niveau
        difficulte = new ButtonGroup();
        niveau.add( new JLabel("Niveau de jeu : ", SwingConstants.RIGHT));
        JRadioButton bouton = new JRadioButton();
        
        bouton = new JRadioButton("Novice");
        difficulte.add(bouton);
        niveau.add(bouton);
        
        bouton = new JRadioButton("Normal");
        difficulte.add(bouton);
        niveau.add(bouton);
        
        bouton = new JRadioButton("Elite");
        difficulte.add(bouton);
        niveau.add(bouton);
        
        bouton = new JRadioButton("Légendaire");
        difficulte.add(bouton);
        niveau.add(bouton);
        
        //creation joueur
        JPanel panelJ1 = new JPanel( new GridLayout(1,3));
        panelJ1.add( new JLabel("Joueur 1 :", SwingConstants.RIGHT));
        j1 = new JTextField();
        /*recupération dim*/Dimension dim  = j1.getSize();/*recupération dim*/
        j1.setPreferredSize(dim);
        panelJ1.add(j1);
        panelJ1.add(new JLabel());
        
        
        JPanel panelJ2 = new JPanel( new GridLayout(1,3));
        panelJ2.add( new JLabel("Joueur 2 :", SwingConstants.RIGHT));
        j2 = new JTextField();
        j2.setPreferredSize(dim);
        panelJ2.add(j2);
        panelJ2.add(new JLabel());
        
        JPanel panelJ3 = new JPanel( new GridLayout(1,3));
        panelJ3.add( new JLabel("Joueur 3 :", SwingConstants.RIGHT));
        j3 = new JTextField();
        j3.setPreferredSize(dim);
        panelJ3.add(j3);
        panelJ3.add(new JLabel());
        
        JPanel panelJ4 = new JPanel( new GridLayout(1,3));
        panelJ4.add( new JLabel("Joueur 4 :", SwingConstants.RIGHT));
        j4 = new JTextField();
        j4.setPreferredSize(dim);
        panelJ4.add(j4);
        panelJ4.add(new JLabel());
        
        JPanel panelBtn = new JPanel( new GridLayout(1,3));
        panelBtn.add( new JLabel());
        panelBtn.add( new JLabel());
        addJoueur = new JButton("+");
        panelBtn.add(addJoueur);
        addJoueur.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    joueurEnPLus++;
                    if (joueurEnPLus == 1){
                        panelJ3.setVisible(true);
                    } else if (joueurEnPLus == 2){
                        panelJ4.setVisible(true);
                        addJoueur.setEnabled(false);
                    }
                }
        });
        
        panelJ3.setVisible(false);
        panelJ4.setVisible(false);
        
        joueur.add(panelJ1);
        joueur.add(panelJ2);
        joueur.add(panelJ3);
        joueur.add(panelJ4);
        joueur.add(panelBtn);
        
        
        //ajout à center
        center.add(niveau);
        center.add(joueur);
        
        //config Princiapl
        principal.add(center, BorderLayout.CENTER);

        //////////////////////////
        // Ajout à Footer
        //////////////////////////
        footer = new JPanel( new GridLayout(1,3));
        
        btnStart = new JButton("Demarrer");
        btnStop = new JButton("Partir");
        
        btnStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FAIRE
                }
        });
        
        btnStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
        });
        
        footer.add(btnStop);
        footer.add( new JLabel());
        footer.add(btnStart);
        
        //config principal
        principal.add(footer, BorderLayout.SOUTH);
        //////////////////////////
        // Ajout à Frame
        //////////////////////////
        frame.add(principal);
        frame.setVisible(true);
    }

    @Override
    public void setObservateur(Observateur observateur) {
        if (observateur != null) {
            this.observateur = observateur;
        }
    }

    @Override
    public void notifierObservateur(Message m) {
        observateur.traiterMessage(m);
    }

    @Override
    public Observateur getObservateur() {
        return observateur;
    }

    public static void main(String[] args) {
        new VueSelection();
    }
}
