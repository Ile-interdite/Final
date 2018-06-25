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
import javax.swing.border.EmptyBorder;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Joueur;
import utils.Parameters;
import view.plateau.jeu.pioches.VueListePiles;

public class VueJeu extends JPanel implements Observe {
	
	private static JLabel labelInfo, labelPlayer, labelRole, labelNbActions;
	private static JPanel boutonDeplacer, boutonAssecher, boutonSpecial, boutonFinTour;
	
	public static void setLabelInfoText(String message) {
		labelInfo.setText(message);
	}
	
	public static void setEtatFinTour(boolean etat) {
		boutonFinTour.setEnabled(etat);
	}
	
	public static boolean getEtatFinTourisEnabled() {
		return boutonFinTour.isEnabled();
	}
	
	public static void setEtatBarreBouton(boolean etat) {
		boutonDeplacer.setEnabled(etat);
		boutonAssecher.setEnabled(etat);
		boutonFinTour.setEnabled(etat);
	}
	
	public static boolean getEtatBarreBouton() {
		return (boutonDeplacer.isEnabled() && boutonAssecher.isEnabled() && boutonFinTour.isEnabled());
	}
	
	private Observateur observateur;
	
	private JPanel panelHeader, panelFooter, panelCenter;
	private JPanel panelDeplacer, panelAssecher, panelSpecial, panelFinTour;
	private VueTresors vueTresors;
	private VueListePiles vueListePiles;
	private VueNiveau vueNiveau;
	private Dimension dimension;

	public VueJeu(int width, int height) {
		this.setObservateur(Controleur.getInstance());
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		dimension = new Dimension(width, height);
		
		panelHeader = new JPanel();
		labelInfo = new JLabel("");
		labelInfo.setFont(labelInfo.getFont().deriveFont(24.0f));
		panelHeader.add(labelInfo);
		panelHeader.setOpaque(false);
		
		panelCenter = this.createPanelCenter();
		
		panelFooter = this.createPanelFooter();
		
		this.add(panelHeader, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelFooter, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/fond_carte.png"));
			g.drawImage(image, 0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JPanel createPanelCenter() {
		JPanel panelCenter = new JPanel(new BorderLayout(20, 0));
		panelCenter.setBorder(new EmptyBorder(30, 20, 0, 0));
		panelCenter.setBackground(Color.GREEN);
		panelCenter.setOpaque(false);
		
		vueTresors = new VueTresors();
		vueTresors.setPreferredSize(new Dimension((int) (dimension.getWidth() * 0.2), 50));
		vueTresors.setBackground(Color.red);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(0, 0, 20, 0));
		vueListePiles = new VueListePiles();
		vueListePiles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(vueListePiles, BorderLayout.CENTER);
		
		vueNiveau = new VueNiveau();
		vueNiveau.setPreferredSize(new Dimension((int) (dimension.getWidth() * 0.2), 50));
		
		panelCenter.add(vueTresors, BorderLayout.WEST);
		panelCenter.add(panel, BorderLayout.CENTER);
		panelCenter.add(vueNiveau, BorderLayout.EAST);
		return panelCenter;
	}
	
	public JPanel createPanelFooter() {
		JPanel panelFooter = new JPanel(new BorderLayout());
		panelFooter.setOpaque(false);
		panelFooter.setPreferredSize(new Dimension(0, 200));

//		JPanel panelInfos = new JPanel(new GridLayout(3,1));
//		panelInfos.setOpaque(false);
//		
//		Joueur joueur = Controleur.getInstance().getJoueurCourant();
//		int numJoueur = Joueur.getJoueurs().indexOf(joueur);
//		labelPlayer = new JLabel("Joueur n°" + (numJoueur + 1) + " : " + joueur.getNom());
//		labelPlayer.setOpaque(false);
//		labelRole = new JLabel("Role : " + joueur.getAventurier().getClass().getSimpleName());
//		labelRole.setOpaque(false);
//		labelNbActions = new JLabel("Actions restantes : " + joueur.getPointsAction());
//		labelNbActions.setOpaque(false);
//		
//		panelInfos.add(labelPlayer);
//		panelInfos.add(labelRole);
//		panelInfos.add(labelNbActions);

		JPanel buttons = new JPanel(new BorderLayout());
		buttons.setOpaque(false);
		
		JPanel panelAction = new JPanel(new GridLayout(2,1));
		JLabel labelAction = new JLabel("Actions", SwingConstants.CENTER);
		labelAction.setFont(labelAction.getFont().deriveFont(18.0f));
		panelAction.add(labelAction);
		panelAction.add(new JLabel(""));
		
		JPanel actionButtons = this.createActionButtons();
		
		//buttons.add(panelInfos, BorderLayout.NORTH);
		buttons.add(actionButtons, BorderLayout.CENTER);
		
		panelFooter.add(buttons, BorderLayout.CENTER);
		panelFooter.add(new JLabel(" "), BorderLayout.SOUTH);
		return panelFooter;
	}
	
	public JPanel createActionButtons() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		
		JPanel buttons = new JPanel(new GridLayout(1,4));
		buttons.setOpaque(false);
		Dimension dim = new Dimension(0, (int)(dimension.getHeight() * 0.08));
		buttons.setPreferredSize(dim);
		
		panelDeplacer = new JPanel(new BorderLayout());
		panelDeplacer.setOpaque(false);
		boutonDeplacer = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				try {
					Image image = ImageIO.read(new File(Parameters.IMAGE_MOVE));
					int width = Parameters.IMAGE_MOVE_WIDTH;
					int height = Parameters.IMAGE_MOVE_HEIGHT;
					int x = this.getWidth()/2 - width/2;
					int y = this.getHeight()/2 - height/2;
					g.drawImage(image, x, y, width, height, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		boutonDeplacer.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.DEPLACEMENT);
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
			
		});
		panelDeplacer.add(boutonDeplacer, BorderLayout.CENTER);
		
		panelAssecher = new JPanel(new BorderLayout());
		panelAssecher.setOpaque(false);
		boutonAssecher = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				try {
					Image image = ImageIO.read(new File(Parameters.IMAGE_DRY));
					int width = Parameters.IMAGE_DRY_WIDTH;
					int height = Parameters.IMAGE_DRY_HEIGHT;
					int x = this.getWidth()/2 - width/2;
					int y = this.getHeight()/2 - height/2;
					g.drawImage(image, x, y, width, height, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		boutonAssecher.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.ASSECHEMENT);
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		panelAssecher.add(boutonAssecher, BorderLayout.CENTER);
		
		panelSpecial = new JPanel(new BorderLayout());
		panelSpecial.setOpaque(false);
		boutonSpecial = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				try {
					Image image = ImageIO.read(new File(Parameters.IMAGE_CLAIM));
					int width = Parameters.IMAGE_CLAIM_WIDTH;
					int height = Parameters.IMAGE_CLAIM_HEIGHT;
					int x = this.getWidth()/2 - width/2;
					int y = this.getHeight()/2 - height/2;
					g.drawImage(image, x, y, width, height, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		boutonSpecial.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.REGLE);
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		boutonSpecial.setEnabled(false);
		panelSpecial.add(boutonSpecial, BorderLayout.CENTER);
		
		panelFinTour = new JPanel(new BorderLayout());
		panelFinTour.setOpaque(false);
		boutonFinTour = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				try {
					Image image = ImageIO.read(new File(Parameters.IMAGE_FIN_TOUR));
					int width = Parameters.IMAGE_FIN_TOUR_WIDTH;
					int height = Parameters.IMAGE_FIN_TOUR_HEIGHT;
					int x = this.getWidth()/2 - width/2;
					int y = this.getHeight()/2 - height/2;
					g.drawImage(image, x, y, width, height, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		boutonFinTour.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.FIN_TOUR);
				notifierObservateur(message);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		panelFinTour.add(boutonFinTour, BorderLayout.CENTER);
		
		buttons.add(panelDeplacer);
		buttons.add(panelAssecher);
		buttons.add(panelSpecial);
		buttons.add(panelFinTour);
		panel.add(buttons, BorderLayout.CENTER);
		return panel;
	}

	public void refresh() {
		panelFooter.setBackground(Controleur.getInstance().getJoueurCourant().getAventurier().getPion().getCouleur());
		
		Joueur joueur = Controleur.getInstance().getJoueurCourant();
		int numJoueur = Joueur.getJoueurs().indexOf(joueur);
		//labelPlayer.setText("Joueur n°" + (numJoueur + 1) + " : " + joueur.getNom());
		//labelRole.setText("Role : " + joueur.getAventurier().getClass().getSimpleName());
		//labelNbActions.setText("Actions restantes : " + joueur.getPointsAction());
	}
	
	public VueTresors getVueTresors() {
		return vueTresors;
	}

	public VueListePiles getVueListePiles() {
		return vueListePiles;
	}

	public VueNiveau getVueNiveau() {
		return vueNiveau;
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
