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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import utils.Utils;

public class VueSelection extends JPanel implements Observe {

    private Observateur observateur;
    private JPanel header, center, niveau, joueur, footer;
    private JLabel nomJeu;
    private ButtonGroup difficulte;
    private JButton addJoueur, removeJoueur, btnStop, btnStart;
    private JTextField j1, j2, j3, j4;
    private int joueurEnPLus = 0;
    private Image backgroundImage;

    public VueSelection() {

        new BorderLayout(); 
        //this.setLayout(new BorderLayout());

        try {
            this.backgroundImage = ImageIO.read(new FileInputStream("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/ileinterdite.jpg"));
        } catch (IOException ex) {
            System.out.println("Erreur : image");
        }

        //////////////////////////
        // HEADER
        //////////////////////////
        header = new JPanel();
        nomJeu = new JLabel("Ile Interdite");
        nomJeu.setForeground(new Color(203,101,80));
        nomJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.add(nomJeu);
        

        //////////////////////////
        // CENTRE
        //////////////////////////
        center = new JPanel(new GridLayout(3, 1));
        niveau = new JPanel(new GridLayout(1, 5));
        GridLayout caseJoueur = new GridLayout(6, 1);
        joueur = new JPanel(caseJoueur);
        caseJoueur.setHgap(5); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
        caseJoueur.setVgap(5);
        JLabel label;

        //creation choix niveau
        difficulte = new ButtonGroup();
        label = new JLabel("Niveau de jeu : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        niveau.add(label);
        JRadioButton bouton = new JRadioButton();
        bouton.setOpaque(false);

        bouton = new JRadioButton("Novice");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        bouton.setOpaque(false);
        bouton.setSelected(true);

        bouton = new JRadioButton("Normal");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        bouton.setOpaque(false);

        bouton = new JRadioButton("Elite");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        bouton.setOpaque(false);

        bouton = new JRadioButton("Légendaire");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        bouton.setOpaque(false);

        //creation joueur
        Dimension dim = new Dimension(4,2);
        
        JPanel panelJ1 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 1 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ1.add(label);
        j1 = new JTextField();
        j1.setBorder(null);
        j1.setPreferredSize(dim);
        panelJ1.add(j1);
        panelJ1.add(new JLabel());

        JPanel panelJ2 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 2 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ2.add(label);
        j2 = new JTextField();
        j2.setBorder(null);
        j2.setPreferredSize(dim);
                
        panelJ2.add(j2);
        panelJ2.add(new JLabel());

        JPanel panelJ3 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 3 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ3.add(label);
        j3 = new JTextField();
        j3.setPreferredSize(dim);
        j3.setBorder(null);
        panelJ3.add(j3);
        panelJ3.add(new JLabel());

        JPanel panelJ4 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 4 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ4.add(label);
        j4 = new JTextField();
        j4.setPreferredSize(dim);
        j4.setBorder(null);
        panelJ4.add(j4);
        panelJ4.add(new JLabel());

        JPanel panelBtn = new JPanel(new GridLayout(1, 1));
        
        addJoueur = new JButton();        
        addJoueur= new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/plus.png"));
        addJoueur.setBackground(Color.red);
        addJoueur.setBorder(null);
        addJoueur.setOpaque(false);
        
        removeJoueur = new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/moins.png"));
        removeJoueur.setBackground(Color.red);
        removeJoueur.setBorder(null);
        removeJoueur.setOpaque(false);
        removeJoueur.setEnabled(false);
        
        
        

        panelBtn.add(addJoueur);
        addJoueur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joueurEnPLus++;
                if (joueurEnPLus == 1) {
                    panelJ3.setVisible(true);
                    removeJoueur.setEnabled(true);
                } else if (joueurEnPLus == 2) {
                    panelJ4.setVisible(true);
                    addJoueur.setEnabled(false);
                }
            }

        });
        removeJoueur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joueurEnPLus--;
                if (joueurEnPLus == 0) {
                    panelJ3.setVisible(false);
                    j3.setText(null);
                    removeJoueur.setEnabled(false);

                } else if (joueurEnPLus == 1) {
                    panelJ4.setVisible(false);
                    j4.setText(null);
                    addJoueur.setEnabled(true);
                }
            }
        });

        JPanel panelAR = new JPanel(new GridLayout(1, 2));
        panelAR.add(removeJoueur);
        panelAR.add(addJoueur);
        panelBtn.add(panelAR);
        panelBtn.add(new JLabel());
        panelBtn.add(new JLabel());

        panelJ3.setVisible(false);
        panelJ4.setVisible(false);

        //ajout à joueur
        joueur.add(panelJ1);
        joueur.add(panelJ2);
        joueur.add(panelJ3);
        joueur.add(panelJ4);
        joueur.add(new JLabel());
        joueur.add(panelBtn);

        //ajout à center
        center.add(niveau);
        center.add(joueur);

        //config Princiapl
        

        //////////////////////////
        //Footer
        //////////////////////////
        footer = new JPanel(new GridLayout(1, 3));

        btnStart = new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/start.png"));
        btnStart.setBackground(Color.red);
        btnStart.setBorder(null);
        btnStart.setOpaque(false);
        
        btnStop = new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/sortie.png"));
        btnStop.setBackground(Color.red);
        btnStop.setBorder(null);
        btnStop.setOpaque(false);

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
        footer.add(new JLabel());
        footer.add(btnStart);
        btnStart.setEnabled(false);

        //////////////////////////
        //ajout à This
        //////////////////////////
        this.add(header, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setOpaque(false);

        header.setOpaque(false);
        center.setOpaque(false);
        niveau.setOpaque(false);
        joueur.setOpaque(false);
        footer.setOpaque(false);
        panelJ1.setOpaque(false);
        panelJ2.setOpaque(false);
        panelJ3.setOpaque(false);
        panelJ4.setOpaque(false);
        panelBtn.setOpaque(false);
        panelAR.setOpaque(false);
    }

    public void isRight() {
        if (j1.getSelectedText() != null && j2.getSelectedText() != null) {
            btnStart.setEnabled(true);
            repaint();
        }
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
