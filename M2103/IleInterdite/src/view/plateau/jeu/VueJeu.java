package view.plateau.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import view.plateau.jeu.pioches.VueListePiles;

public class VueJeu extends JPanel implements Observe {
	
	private static VueJeu vueJeu;
	private static JPanel panelHeader, panelFooter, panelCenter;
	
	private Observateur observateur;
	
	private JButton deplacer, assecher, donnerCarte, special, finTour;
	private static JLabel labelInfo, labelPlayer, labelRole, labelNbActions;
	private Dimension dimension;

	public VueJeu(int width, int height) {
		this.setObservateur(Controleur.getInstance());
		this.setLayout(new BorderLayout(0, 50));
		dimension = new Dimension(width, height);
		
		panelHeader = new JPanel();
		labelInfo = new JLabel("");
		labelInfo.setFont(labelInfo.getFont().deriveFont(24.0f));
		panelHeader.add(labelInfo);
		panelHeader.setOpaque(false);
		
		panelCenter = this.createPanelCenter();
		panelCenter.setOpaque(false);
		
		panelFooter = this.createPanelFooter();
		panelFooter.setBackground(Controleur.getInstance().getJoueurCourant().getRole().getPion().getCouleur());
		panelFooter.setOpaque(false);
		
		this.add(panelHeader, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelFooter, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/fond_carte.jpg"));
			g.drawImage(image, 0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setLabelInfoText(String message) {
		labelInfo.setText(message);
	}
	
	public JPanel createPanelCenter() {
		JPanel panelCenter = new JPanel(new BorderLayout(10,0));
		
		VueTresors vueTresors = new VueTresors();
		vueTresors.setPreferredSize(new Dimension((int) (dimension.getWidth() * 0.2), 50));
		
		VueListePiles vuePioches = new VueListePiles();
		vuePioches.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		VueNiveau vueMDE = new VueNiveau();
		vueMDE.setPreferredSize(new Dimension((int) (dimension.getWidth() * 0.2), 50));
		
		panelCenter.add(vueTresors, BorderLayout.WEST);
		panelCenter.add(vuePioches, BorderLayout.CENTER);
		panelCenter.add(vueMDE, BorderLayout.EAST);
		return panelCenter;
	}
	
	public JPanel createPanelFooter() {
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
				Message m = new Message();
                                m.setTypeMessage(TypeMessage.DONNER_CARTE);
                                notifierObservateur(m);
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
	
	public static JPanel getPanelHeader() {
		return panelHeader;
	}

	public static JPanel getPanelFooter() {
		return panelFooter;
	}

	public static JPanel getPanelCenter() {
		return panelCenter;
	}

	public void refresh() {
		panelFooter.setBackground(Controleur.getInstance().getJoueurCourant().getRole().getPion().getCouleur());
		
		Joueur joueur = Controleur.getInstance().getJoueurCourant();
		int numJoueur = Controleur.getInstance().getJoueurs().indexOf(joueur);
		labelPlayer.setText("Joueur n°" + (numJoueur + 1) + " : " + joueur.getName());
		labelRole.setText("Role : " + joueur.getRole().getClass().getSimpleName());
		labelNbActions.setText("Actions restantes : " + joueur.getPointsAction());
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
