package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Joueur;

public class VueJeu extends JPanel implements Observe {
	
	private Observateur observateur;
	
	private JPanel panelHeader, panelFooter, panelCenter;
	private JButton deplacer, assecher, donnerCarte, special, finTour;
	private static JLabel labelInfo, labelPlayer, labelRole, labelNbActions;
	private Dimension dimension;

	public VueJeu(int width, int height) {
		this.setObservateur(Controleur.getInstance());
		this.setLayout(new BorderLayout());
		dimension = new Dimension(width, height);
		
		panelHeader = new JPanel();
		labelInfo = new JLabel("");
		labelInfo.setFont(labelInfo.getFont().deriveFont(24.0f));
		panelHeader.add(labelInfo);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Controleur.getInstance().getJoueurCourant().getRole().getPion().getCouleur());
		
		panelFooter = createFooterPanel();
		
		this.add(panelHeader, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelFooter, BorderLayout.SOUTH);
		this.add(new Label(), BorderLayout.WEST);
		this.add(new Label(), BorderLayout.EAST);
	}
	
	public static void setLabelInfoText(String message) {
		labelInfo.setText(message);
	}
	
	public JPanel createFooterPanel() {
		JPanel panelFooter = new JPanel(new BorderLayout());
		//=============================================
		// Création du panel d'informations
		//=============================================
		JPanel infos = new JPanel(new GridLayout(2,2));
		
		Joueur joueur = Controleur.getInstance().getJoueurCourant();
		int numJoueur = Controleur.getInstance().getJoueurs().indexOf(joueur);
		labelPlayer = new JLabel("Joueur n°" + (numJoueur + 1) + " : " + joueur.getName());
		labelRole = new JLabel("Role : " + joueur.getRole().getClass().getSimpleName());
		labelNbActions = new JLabel("Actions restantes : " + joueur.getPointsAction());
		
		infos.add(labelPlayer);
		infos.add(new JLabel(""));
		infos.add(labelRole);
		infos.add(labelNbActions);
		//=============================================
		// Création du panel de boutons
		//=============================================
		JPanel buttons = new JPanel(new BorderLayout());
		
		JPanel panelAction = new JPanel(new GridLayout(2,1));
		JLabel labelAction = new JLabel("Actions", SwingConstants.CENTER);
		labelAction.setFont(labelAction.getFont().deriveFont(18.0f));
		panelAction.add(labelAction);
		panelAction.add(new JLabel(""));
		
		JPanel actionButtons = this.createActionButtons();
		finTour = new JButton("Fin du tour");
		finTour.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.FIN_TOUR);
				
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		
		buttons.add(infos, BorderLayout.NORTH);
		buttons.add(actionButtons, BorderLayout.CENTER);
		buttons.add(finTour, BorderLayout.SOUTH);
		
		panelFooter.add(buttons, BorderLayout.CENTER);
		panelFooter.add(new JLabel(" "), BorderLayout.SOUTH);
		return panelFooter;
	}
	
	public JPanel createActionButtons() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(1,4));
		Dimension dim = new Dimension(0, (int)(dimension.getHeight() * 0.08));
		buttons.setPreferredSize(dim);
		
		deplacer = new JButton("Se déplacer");
		deplacer.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.DEPLACEMENT);
				
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				deplacer.setBackground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deplacer.setBackground(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		
		assecher = new JButton("Assécher");
		assecher.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.ASSECHEMENT);
				
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				assecher.setBackground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				assecher.setBackground(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		
		donnerCarte = new JButton("Donner carte");
		donnerCarte.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		
		special = new JButton("");
		special.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		buttons.add(deplacer);
		buttons.add(assecher);
		buttons.add(donnerCarte);
		buttons.add(special);
		panel.add(buttons, BorderLayout.CENTER);
		panel.add(new JLabel("  "), BorderLayout.WEST);
		panel.add(new JLabel("  "), BorderLayout.EAST);
		return panel;
	}
	
	public void refresh() {
		panelCenter.setBackground(Controleur.getInstance().getJoueurCourant().getRole().getPion().getCouleur());
		
		Joueur joueur = Controleur.getInstance().getJoueurCourant();
		int numJoueur = Controleur.getInstance().getJoueurs().indexOf(joueur);
		labelPlayer = new JLabel("Joueur n°" + (numJoueur + 1) + " : " + joueur.getName());
		labelRole = new JLabel("Role : " + joueur.getRole().getClass().getSimpleName());
		labelNbActions = new JLabel("Actions restantes : " + joueur.getPointsAction());
	}

	@Override
	public void setObservateur(Observateur observateur) {
		if(observateur != null) {
			this.observateur = observateur;
		}
	}

	@Override
	public void notifierObservateur(Message m) {
		this.getObservateur().traiterMessage(m);
	}

	@Override
	public Observateur getObservateur() {
		return observateur;
	}
}
