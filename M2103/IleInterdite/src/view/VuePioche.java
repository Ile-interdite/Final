package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.carte.CarteInondation;
import modele.carte.CarteTresor;
import utils.Parameters;

public class VuePioche extends JFrame implements Observe {
	
	private Observateur observateur;
	private JPanel principal;
	private JLabel info,tresor,innond;
	
	public VuePioche() {
		this.setObservateur(Controleur.getInstance());
        this.setTitle("Pioche de cartes");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        setSize(width/2,(int)(height*0.8));
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
		
		principal = new JPanel(new BorderLayout()) {
			@Override
			public void paintComponent(Graphics g) {
				try {
					Image image = ImageIO.read(new File(Parameters.IMAGES + "plaque.jpg"));
					int width = this.getWidth();
					int height = this.getHeight();
					int x = 0;
					int y = 0;
					g.drawImage(image, x, y, width, height, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		principal.add(header(),BorderLayout.NORTH);
		principal.add(center(),BorderLayout.CENTER);
		principal.add(footer(),BorderLayout.SOUTH);
		
		add(principal);
		setVisible(true);
	}
	
	
	public JPanel header() {
		JPanel header = new JPanel();
		info = new JLabel("Fin du tour du joueur : " + Controleur.getInstance().getJoueurCourant().getNom());
		header.add(info);
		
		return header;
	}
	
	public JPanel center() {
		JPanel center = new JPanel(new GridLayout(2,1));
		JPanel haut = new JPanel(new BorderLayout());
		JPanel bas = new JPanel(new BorderLayout());
		tresor = new JLabel("Cartes \"Trésor\" piochées : ");
		int size = CarteTresor.getPioche().size();
		JPanel hautpan = new JPanel(new GridLayout(1, size));
		
		for(int i = 0; i < size; i++) {
			JPanel tr = new JPanel() {
				private CarteTresor carte = CarteTresor.getPioche().pop();
				
				@Override
				public void paintComponent(Graphics g) {
					try {
						Image image = ImageIO.read(new File(Parameters.CARTES + carte.getLibelle().replaceAll(" ", "") + ".png"));
						int width = (int) (Parameters.CARTE_WIDTH * 0.4);
						int height = (int) (Parameters.CARTE_HEIGHT * 0.4);
						int x = (this.getWidth() - width)/2;
						int y = (this.getHeight() - height)/2;
						g.drawImage(image, x, y, width, height, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};			
			hautpan.add(tr);
		}
		
		haut.add(tresor, BorderLayout.NORTH);
		haut.add(hautpan, BorderLayout.CENTER);
		
		innond = new JLabel("Cartes \"Inondation\" piochées : ");
		size = CarteInondation.getPioche().size();
		JPanel basPan = new JPanel(new GridLayout(1, size));
		
		for(int i = 0; i < size; i++) {
			JPanel in = new JPanel() {
				private CarteInondation carte = CarteInondation.getPioche().pop();
				
				@Override
				public void paintComponent(Graphics g) {
					try {
						Image image = ImageIO.read(new File(Parameters.CARTES + carte.getLibelle().replaceAll(" ", "").replaceAll("'", "") + ".png"));
						int width = (int) (Parameters.CARTE_WIDTH * 0.4);
						int height = (int) (Parameters.CARTE_HEIGHT * 0.4);
						int x = (this.getWidth() - width)/2;
						int y = (this.getHeight() - height)/2;
						g.drawImage(image, x, y, width, height, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};			
			basPan.add(in);
		}
		
		bas.add(innond, BorderLayout.NORTH);
		bas.add(basPan, BorderLayout.CENTER);
		
		center.add(haut);
		center.add(bas);
		return center;
	}
	
	
	
	public JPanel footer() {
		JPanel footer = new JPanel();
		JButton button = new JButton("Tour suivant");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message message = new Message();
				message.setTypeMessage(TypeMessage.TOUR_SUIVANT);
				notifierObservateur(message);
				dispose();
			}
		});
		footer.add(button);
		return footer;
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
