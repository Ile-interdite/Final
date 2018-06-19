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
import controller.Observe;
import controller.TypeMessage;
import utils.Utils;

public class VueSelection extends Observe {
    
    private JFrame frame;
    private JPanel panelBody, panelNbJoueurs, panelCenter, panelFooter;
    private JButton commencer, quitter;
    private JTextField nameJoueur1, nameJoueur2, nameJoueur3, nameJoueur4;
    private JRadioButton deuxJoueurs, troisJoueurs, quatreJoueurs;
    private ButtonGroup nbJoueurs;
    
    public VueSelection() {
        frame = new JFrame("Initialisation de la partie");
        frame.setSize(300,250);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        //==========================================================
        // Création du panel principal
        //==========================================================
        panelBody = new JPanel(new BorderLayout());
        panelBody.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //==========================================================
        // Création du panelNbJoueur
        //==========================================================
        panelNbJoueurs = new JPanel();
        JLabel labelNbJoueurs = new JLabel("Nombre de joueurs : ");
        
        nbJoueurs = new ButtonGroup();
        deuxJoueurs = new JRadioButton("2");
        deuxJoueurs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameJoueur3.setText("");
				nameJoueur3.setEnabled(false);
				nameJoueur4.setText("");
				nameJoueur4.setEnabled(false);
			}
        	
        });
        troisJoueurs = new JRadioButton("3");
        troisJoueurs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameJoueur3.setEnabled(true);
				nameJoueur4.setText("");
				nameJoueur4.setEnabled(false);
			}
        	
        });
        quatreJoueurs = new JRadioButton("4");
        quatreJoueurs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameJoueur3.setEnabled(true);
				nameJoueur4.setEnabled(true);
			}
        	
        });
        deuxJoueurs.setSelected(true);
        
        nbJoueurs.add(deuxJoueurs);
        nbJoueurs.add(troisJoueurs);
        nbJoueurs.add(quatreJoueurs);
        
        panelNbJoueurs.add(labelNbJoueurs);
        panelNbJoueurs.add(deuxJoueurs);
        panelNbJoueurs.add(troisJoueurs);
        panelNbJoueurs.add(quatreJoueurs);
        //==========================================================
        // Création du panelCenter
        //==========================================================
        panelCenter = new JPanel(new GridLayout(4,2));
        
        JLabel joueur1 = new JLabel("Joueur n°1 : ");
        joueur1.setHorizontalAlignment(SwingConstants.TRAILING);
        nameJoueur1 = new JTextField();
        
        JLabel joueur2 = new JLabel("Joueur n°2 : ");
        joueur2.setHorizontalAlignment(SwingConstants.TRAILING);
        nameJoueur2 = new JTextField();
        
        JLabel joueur3 = new JLabel("Joueur n°3 : ");
        joueur3.setHorizontalAlignment(SwingConstants.TRAILING);
        nameJoueur3 = new JTextField();
        nameJoueur3.setEnabled(false);
        
        JLabel joueur4 = new JLabel("Joueur n°4 : ");
        joueur4.setHorizontalAlignment(SwingConstants.TRAILING);
        nameJoueur4 = new JTextField();
        nameJoueur4.setEnabled(false);
        
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
            	if(!nameJoueur1.getText().equals("") && !nameJoueur2.getText().equals("")) {
            		boolean joueur3 = troisJoueurs.isSelected() && !nameJoueur3.getText().equals("");
            		boolean joueur4 = quatreJoueurs.isSelected() && (!nameJoueur3.getText().equals("") && !nameJoueur4.getText().equals(""));
            		
            		if(deuxJoueurs.isSelected() || joueur3 || joueur4) {
            			Message message = new Message();
            			message.setTypeMessage(TypeMessage.COMMENCER_PARTIE);
            			message.addNomJoueur(nameJoueur1.getText());
            			message.addNomJoueur(nameJoueur2.getText());
            			
            			if(troisJoueurs.isSelected() || quatreJoueurs.isSelected()) {
            				message.addNomJoueur(nameJoueur3.getText());
            			}
            			
            			if(quatreJoueurs.isSelected()) {
            				message.addNomJoueur(nameJoueur4.getText());
            			}
            			notifierObservateur(message);
            			frame.dispose();
            			return;
            		}
            	}
            	Utils.afficherInformation("Veuillez saisir le nom des joueurs");
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
        panelBody.add(panelNbJoueurs, BorderLayout.NORTH);
        panelBody.add(panelCenter, BorderLayout.CENTER);
        panelBody.add(panelFooter, BorderLayout.SOUTH);
        
        frame.add(panelBody, constraint);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new VueSelection();
    }
}