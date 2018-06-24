package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;

public class VueSelection extends JFrame implements Observe {

    private Observateur observateur;
    private JFrame frame;
    private JPanel principal,header,center,footer,niveau,joueur;
    private JLabel nomJeu, etat;
    private ButtonGroup difficulte;
    private JRadioButton[] boutons;
    private JButton addJoueur, removeJoueur, btnStop, btnStart;
    private JTextField j1, j2, j3, j4;
    private int joueurEnPLus = 2;
    private boolean verif1, verif2 = false;
    private Image backgroundImage;
    
    public VueSelection() {
    	this.setObservateur(Controleur.getInstance());
        this.setTitle("Initialisation de la partie");
        setSize(700, 700);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);

        principal = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                try {
                    backgroundImage = ImageIO.read(new File("M2103/IleInterdite/images/ileinterdite.png"));
                    //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException ex) {
                    System.out.println("Erreur : image");
                }

                
            }
        };
        principal.setLayout(new BorderLayout());

        principal.add(setCenter(), BorderLayout.CENTER);
        principal.add(setFooter(), BorderLayout.SOUTH);
        principal.setOpaque(false);    
        //préparation du JFrame (this)
        add(principal);
        //vues.repaint();
        setVisible(true);
    }

    public JPanel setCenter() {
        center = new JPanel(new BorderLayout());
        JLabel label;

        GridLayout caseJoueur = new GridLayout(8, 1);
        JPanel joueur = new JPanel(caseJoueur);

        caseJoueur.setHgap(5); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
        caseJoueur.setVgap(5); //Cinq pixels d'espace entre les colonnes (V comme Vertical)     

        //creation joueur
        JPanel panelJ1 = new JPanel(new GridLayout(1, 2));
        label = new JLabel("Joueur 1 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ1.add(label);
        j1 = new JTextField();
        j1.setBorder(null);
        panelJ1.add(j1);

        JPanel panelJ2 = new JPanel(new GridLayout(1, 2));
        label = new JLabel("Joueur 2 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ2.add(label);
        j2 = new JTextField();
        j2.setBorder(null);
        panelJ2.add(j2);

        JPanel panelJ3 = new JPanel(new GridLayout(1, 2));
        label = new JLabel("Joueur 3 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ3.add(label);
        j3 = new JTextField();
        j3.setBorder(null);
        panelJ3.add(j3);

        JPanel panelJ4 = new JPanel(new GridLayout(1, 2));
        label = new JLabel("Joueur 4 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ4.add(label);
        j4 = new JTextField();
        j4.setBorder(null);
        panelJ4.add(j4);

        //action sur les zones de textes
        j1.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j1.getDocument().getLength() != 0) {
                    verif1 = true;
                } else {
                    verif1 = false;
                }
                verif();
            }
        });

        j2.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j2.getDocument().getLength() != 0) {
                    verif2 = true;
                } else {
                    verif2 = false;
                }
                verif();
            }
        });

        j3.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j3.getDocument().getLength() == 0 && j4.getDocument().getLength() != 0) {
                    j3.setText(j4.getText());
                    joueurEnPLus = 1;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                } else if (j2.getDocument().getLength() == 0 && j3.getDocument().getLength() != 0 && j4.getDocument().getLength() == 0) {
                    j2.setText(j3.getText());
                    joueurEnPLus = 0;
                    j3.setText(null);
                    panelJ3.setVisible(false);
                    addJoueur.setEnabled(true);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                } else if (j2.getDocument().getLength() == 0 && j3.getDocument().getLength() != 0 && j4.getDocument().getLength() != 0){
                    j2.setText(j3.getText());
                    joueurEnPLus = 1;
                    j3.setText(j4.getText());
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                }
                if (j2.getDocument().getLength() != 0) {
                    verif2 = true;
                }else{
                    verif2 = false;
                }
                verif();
            }
        });

        j4.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j2.getDocument().getLength() == 0 && j3.getDocument().getLength() == 0 && j4.getDocument().getLength() != 0) {
                    j2.setText(j4.getText());
                    joueurEnPLus = 0;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    j3.setText(null);
                    panelJ3.setVisible(false);
                    addJoueur.setEnabled(true);
                } else if (j4.getDocument().getLength() == 0) {
                    joueurEnPLus = 1;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                } else if (j3.getDocument().getLength() == 0 && j4.getDocument().getLength() != 0) {
                    j3.setText(j4.getText());
                    joueurEnPLus = 1;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                } else if (j2.getDocument().getLength() == 0 && j3.getDocument().getLength() != 0 && j4.getDocument().getLength() != 0) {
                    j2.setText(j4.getText());
                    joueurEnPLus = 0;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                }
                if (j2.getDocument().getLength() != 0) {
                    verif2 = true;
                }else{
                    verif2 = false;
                }
                verif();
            }
        });

        //boutons ajout et supression de joueur
        JPanel panelBtn = new JPanel(new GridLayout(1, 2));

        addJoueur = new JButton();
        addJoueur = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/plus.png"));
        addJoueur.setBorderPainted(false);
        addJoueur.setContentAreaFilled(false);
        addJoueur.setFocusPainted(false);
        addJoueur.setOpaque(false);

        removeJoueur = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/moins.png"));
        removeJoueur.setBorderPainted(false);
        removeJoueur.setContentAreaFilled(false);
        removeJoueur.setFocusPainted(false);
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
                if (joueurEnPLus < 0) {
                    joueurEnPLus = 0;
                }

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
        
        
        
        
        
        
        
        
        
        
        
        
        panelJ3.setVisible(true);
        panelJ4.setVisible(true);
        j1.setText("titi");
        j2.setText("tata");
        j3.setText("toto");
        j4.setText("tutu");
        
        
        
        
        
        
        
        
        
        
        
        

        panelJ1.setOpaque(false);
        panelJ2.setOpaque(false);
        panelJ3.setOpaque(false);
        panelJ4.setOpaque(false);
        panelBtn.setOpaque(false);
        panelAR.setOpaque(false);

        //ajout à joueur
        joueur.add(panelJ1);
        joueur.add(panelJ2);
        joueur.add(panelJ3);
        joueur.add(panelJ4);
        joueur.add(new JLabel());
        joueur.add(panelBtn);
        
        center.setBorder(new EmptyBorder(250, 30, 30, 30));
        joueur.setBorder(new EmptyBorder(100, 50, 0, 220));

        center.add(setNiveau(), BorderLayout.NORTH);
        center.add(joueur, BorderLayout.CENTER);

        center.setOpaque(false);
        joueur.setOpaque(false);

        return center;
    }

    public JPanel setNiveau() {
        JPanel niveau = new JPanel(new GridLayout(1, 5));
        JLabel label;
        difficulte = new ButtonGroup();
        boutons = new JRadioButton[4];

        label = new JLabel("Niveau de jeu : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        JPanel pLvl = new JPanel(); //pour set les dimensions
        pLvl.add(label);
        niveau.add(pLvl);
        JRadioButton bouton = new JRadioButton();
        bouton.setOpaque(false);

        bouton = new JRadioButton("Novice");
        bouton.setForeground(Color.white);
        boutons[0] = bouton;
        difficulte.add(boutons[0]); //pour choix unique
        JPanel pBtnNovice = new JPanel(); //pour set les dimensions
        pBtnNovice.add(boutons[0]);
        niveau.add(pBtnNovice);     //ajout au panel du choix de niveau
        boutons[0].setBorderPainted(false);
        boutons[0].setContentAreaFilled(false);
        boutons[0].setFocusPainted(false);

        bouton = new JRadioButton("Normal");
        bouton.setForeground(Color.white);
        boutons[1] = bouton;
        boutons[1].setSelected(true);
        difficulte.add(boutons[1]);
        JPanel pBtnNrm = new JPanel();
        pBtnNrm.add(boutons[1]);
        niveau.add(pBtnNrm);
        boutons[1].setBorderPainted(false);
        boutons[1].setContentAreaFilled(false);
        boutons[1].setFocusPainted(false);

        bouton = new JRadioButton("Elite");
        bouton.setForeground(Color.white);
        boutons[2] = bouton;
        difficulte.add(boutons[2]);
        JPanel pBtnElite = new JPanel();
        pBtnElite.add(boutons[2]);
        niveau.add(pBtnElite);
        boutons[2].setBorderPainted(false);
        boutons[2].setContentAreaFilled(false);
        boutons[2].setFocusPainted(false);

        bouton = new JRadioButton("Légendaire");
        bouton.setForeground(Color.white);
        boutons[3] = bouton;
        difficulte.add(boutons[3]);
        JPanel pBtnLegendaire = new JPanel();
        pBtnLegendaire.add(boutons[3]);
        niveau.add(pBtnLegendaire);
        boutons[3].setBorderPainted(false);
        boutons[3].setContentAreaFilled(false);
        boutons[3].setFocusPainted(false);

        pLvl.setOpaque(false);
        pBtnNovice.setOpaque(false);
        pBtnNrm.setOpaque(false);
        pBtnElite.setOpaque(false);
        pBtnLegendaire.setOpaque(false);
        niveau.setOpaque(false);

        return niveau;
    }

    public JPanel setFooter() {
        JPanel footer = new JPanel(new GridLayout(1, 3));

        btnStart = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/start.png"));
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusPainted(false);
        
        
        
        
        
        
        
        btnStart.setEnabled(true);
        
        
        
        
        
        
        
        
        
        
        
        
        JPanel pStart = new JPanel();
        pStart.add(btnStart);
        pStart.setOpaque(false);

        btnStop = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/sortie.png"));
        btnStop.setBorderPainted(false);
        btnStop.setContentAreaFilled(false);
        btnStop.setFocusPainted(false);
        JPanel pStop = new JPanel();
        pStop.add(btnStop);
        pStop.setOpaque(false);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypeMessage.COMMENCER_PARTIE);
                m.getNomsJoueurs().addAll(recupJoueur());
                m.setDifficulte(recupDifficulte());
                notifierObservateur(m);
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        footer.add(pStop);
        etat = new JLabel("", JLabel.CENTER);
        etat.setForeground(Color.red);
        footer.add(etat);
        footer.add(pStart);

        footer.setBorder(new EmptyBorder(0, 0, 30, 0));
        footer.setOpaque(false);

        return footer;
    }

    public void verif() {
        if (verif1 && verif2) {
            btnStart.setEnabled(true);
        } else {
            btnStart.setEnabled(false);
        }
    }
    
    public int recupDifficulte(){
        boolean trouve = false;
        int i = 0;
        while (!trouve && i < 4) {
            if (boutons[i].isSelected()) {
            trouve = true;
            } else {i++;}
        }
        return i;
    }
    
    public ArrayList<String> recupJoueur(){
        ArrayList<String> nomsJoueurs = new ArrayList<>();
        
        nomsJoueurs.add(j1.getText());
        nomsJoueurs.add(j2.getText());
        if (j3.getDocument().getLength() != 0) {
            nomsJoueurs.add(j3.getText());
        }
        if (j4.getDocument().getLength() != 0) {
            nomsJoueurs.add(j4.getText());
        }
        
        return nomsJoueurs;
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
