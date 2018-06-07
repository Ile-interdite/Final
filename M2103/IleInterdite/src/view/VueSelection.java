package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Observe;

public class VueSelection extends Observe {
    
    private JFrame frame;
    private JPanel panelBody, panelHeader, panelCenter, panelFooter;
    private JButton commencer, quitter;
    private JTextField nameJoueur1, nameJoueur2, nameJoueur3, nameJoueur4;
    
    public VueSelection() {
        frame = new JFrame("Sélection");
        frame.setSize(400,400);
        //==========================================================
        // Création du panel principal
        //==========================================================
        panelBody = new JPanel(new BorderLayout());
        //==========================================================
        // Création du panelHeader
        //==========================================================
        panelHeader = new JPanel();
        JLabel titre = new JLabel("Initialisation du jeu");
        panelHeader.add(titre);
        //==========================================================
        // Création du panelCenter
        //==========================================================
        panelCenter = new JPanel(new GridLayout(4,2));
        
        JLabel joueur1 = new JLabel("Joueur n°1 : ");
        nameJoueur1 = new JTextField();
        
        JLabel joueur2 = new JLabel("Joueur n°2 : ");
        nameJoueur2 = new JTextField();
        
        JLabel joueur3 = new JLabel("Joueur n°3 : ");
        nameJoueur3 = new JTextField();
        
        JLabel joueur4 = new JLabel("Joueur n°4 : ");
        nameJoueur4 = new JTextField();
        
        panelCenter.add(joueur1);
        panelCenter.add(nameJoueur1);
        panelCenter.add(joueur2);
        panelCenter.add(nameJoueur2);
        panelCenter.add(joueur3);
        panelCenter.add(nameJoueur3);
        panelCenter.add(joueur4);
        panelCenter.add(nameJoueur4);
        //==========================================================
        // Création du panelFooter
        //==========================================================
        panelFooter = new JPanel(new BorderLayout());
        
        commencer = new JButton("Commencer");
        commencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Message message = new Message(TypeMessage.COMMENCER);
            }
        });
        
        quitter = new JButton("Quitter");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panelFooter.add(commencer, BorderLayout.WEST);
        panelFooter.add(quitter, BorderLayout.EAST);
        //==========================================================
        panelBody.add(panelHeader, BorderLayout.NORTH);
        panelBody.add(panelCenter, BorderLayout.CENTER);
        panelBody.add(panelFooter, BorderLayout.SOUTH);
        
        frame.add(panelBody);
        frame.setVisible(true);
    }
}
