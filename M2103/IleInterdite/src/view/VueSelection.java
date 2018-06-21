package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;

public class VueSelection extends JPanel implements Observe {

    private Observateur observateur;
    private JPanel header, center, niveau, joueur, footer;
    private JLabel nomJeu, etat;
    private ButtonGroup difficulte;
    private JRadioButton[] boutons;
    private JButton addJoueur, removeJoueur, btnStop, btnStart;
    private JTextField j1, j2, j3, j4;
    private int joueurEnPLus = 0;
    private boolean verif1,verif2 = false;
    private Image backgroundImage;

    public VueSelection() {
        //new BorderLayout();
        this.setLayout(new BorderLayout());

        try {
            this.backgroundImage = ImageIO.read(new FileInputStream("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/ileinterdite.jpg"));
        } catch (IOException ex) {
            System.out.println("Erreur : image");
        }

        setHeader();
        setCenter();
        setFooter();
        
        this.add(header, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setOpaque(false);

        
        header.setOpaque(false);
        center.setOpaque(false);
        niveau.setOpaque(false);
        joueur.setOpaque(false);
        footer.setOpaque(false);
        
    }
    
    public void setHeader(){
        header = new JPanel();
        nomJeu = new JLabel("Ile Interdite");
        nomJeu.setForeground(new Color(203,101,80));
        nomJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.add(nomJeu);
    }
    
    public void setCenter(){
        center = new JPanel(new GridLayout(2, 1));
        niveau = new JPanel(new GridLayout(1, 5));
        GridLayout caseJoueur = new GridLayout(8, 1);
        joueur = new JPanel(caseJoueur);
        caseJoueur.setHgap(5); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
        caseJoueur.setVgap(5); //Cinq pixels d'espace entre les colonnes (V comme Vertical)
        JLabel label;

        //creation choix niveau
        difficulte = new ButtonGroup();
        boutons = new JRadioButton[4];
        
        label = new JLabel("Niveau de jeu : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        niveau.add(label);
        JRadioButton bouton = new JRadioButton();
        bouton.setOpaque(false);

        bouton = new JRadioButton("Novice");
        bouton.setForeground(Color.white);
        boutons[0]=bouton;
        difficulte.add(boutons[0]);
        niveau.add(boutons[0]); 
        bouton.setOpaque(false);
        bouton.setSelected(true);

        bouton = new JRadioButton("Normal");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        boutons[1]=bouton;
        bouton.setOpaque(false);

        bouton = new JRadioButton("Elite");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        boutons[2]=bouton;
        bouton.setOpaque(false);

        bouton = new JRadioButton("Légendaire");
        bouton.setForeground(Color.white);
        difficulte.add(bouton);
        niveau.add(bouton);
        boutons[3]=bouton;
        bouton.setOpaque(false);

        //creation joueur
        
        JPanel panelJ1 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 1 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ1.add(label);
        j1 = new JTextField();
        j1.setBorder(null);
        panelJ1.add(j1);
        panelJ1.add(new JLabel());
 
        JPanel panelJ2 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 2 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ2.add(label);
        j2 = new JTextField();
        j2.setBorder(null);
        panelJ2.add(j2);
        panelJ2.add(new JLabel());
        
        JPanel panelJ3 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 3 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ3.add(label);
        j3 = new JTextField();
        j3.setBorder(null);
        panelJ3.add(j3);
        panelJ3.add(new JLabel());
        
        JPanel panelJ4 = new JPanel(new GridLayout(1, 3));
        label = new JLabel("Joueur 4 : ", SwingConstants.RIGHT);
        label.setForeground(Color.white);
        panelJ4.add(label);
        j4 = new JTextField();
        j4.setBorder(null);
        panelJ4.add(j4);
        panelJ4.add(new JLabel());
        
        j1.addActionListener( new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j1.getDocument().getLength() != 0 ){
                    verif1 = true;
                }else {
                    verif1 = false;
                }
                if (verif1 && verif2){
                    btnStart.setEnabled(true);
                }else{
                    btnStart.setEnabled(false);
                }
            }
        });
        
        j2.addActionListener( new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {             
                if (j2.getDocument().getLength() != 0 ){
                    verif2 = true;
                }else {
                    verif2 = false;
                }
                if (verif1 && verif2){
                    btnStart.setEnabled(true);
                }else{
                    btnStart.setEnabled(false);
                }
            }
        });
        
        j3.addActionListener( new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {             
                if (j3.getDocument().getLength() == 0 && j4.getDocument().getLength() != 0 ){
                    j3.setText(j4.getText());
                    joueurEnPLus=1;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                }
            }
        });
        
        j4.addActionListener( new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {             
                if (j3.getDocument().getLength() == 0 && j4.getDocument().getLength() != 0 ){
                    j3.setText(j4.getText());
                    joueurEnPLus=1;
                    j4.setText(null);
                    panelJ4.setVisible(false);
                    addJoueur.setEnabled(true);
                } 
            }
        });

        JPanel panelBtn = new JPanel(new GridLayout(1, 3));
        
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
                if (joueurEnPLus<0){
                    joueurEnPLus=0;
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

        //ajout à joueur
        joueur.add(panelJ1);
        joueur.add(panelJ2);
        joueur.add(panelJ3);
        joueur.add(panelJ4);
        joueur.add(new JLabel());
        joueur.add(panelBtn);
        joueur.add(new JLabel());
        joueur.add(new JLabel());
        
        //ajout à center
        center.add(niveau);
        center.add(joueur);
        
        panelJ1.setOpaque(false);
        panelJ2.setOpaque(false);
        panelJ3.setOpaque(false);
        panelJ4.setOpaque(false);
        panelBtn.setOpaque(false);
        panelAR.setOpaque(false);
    }
    
    public void setFooter(){
        footer = new JPanel(new GridLayout(2, 3));

        btnStart = new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/start.png"));
        btnStart.setBackground(Color.red);
        btnStart.setBorder(null);
        btnStart.setOpaque(false);
        btnStart.setEnabled(false);
        
        btnStop = new JButton(new ImageIcon("C:/Users/baretd/Documents/NetBeansProjects/Final/M2103/IleInterdite/images/icones/sortie.png"));
        btnStop.setBackground(Color.red);
        btnStop.setBorder(null);
        btnStop.setOpaque(false);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.COMMENCER_PARTIE);
                    m.addNomJoueur(j1.getText());
                    m.addNomJoueur(j2.getText());
                    if (j3.getDocument().getLength() != 0){
                        m.addNomJoueur(j3.getText());
                    }
                    if (j4.getDocument().getLength() != 0){
                        m.addNomJoueur(j4.getText());
                    }
                    
                    //récupération du niveau d'eau
                    boolean trouve = false;
                    int i = 0;
                    while (!trouve && i<4){
                        if (boutons[i].isSelected()){
                            trouve=true;
                        }
                        i++;
                    }
                    m.setDifficulte(i);
                    notifierObservateur(m);
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        footer.add(btnStop);
        etat = new JLabel("!Appuyez sur Entrée pour valider le nom!");
        etat.setForeground(Color.red);
        footer.add(etat);
        footer.add(btnStart);
        footer.add(new JLabel());
        footer.add(new JLabel());
        footer.add(new JLabel());
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
