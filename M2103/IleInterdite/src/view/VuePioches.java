package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.Controleur;
import modele.Joueur;
import modele.carte.CarteTresor;

public class VuePioches extends JPanel {
	
	public VuePioches() {
		this.setLayout(new GridLayout(4,1));
		
		
		for(int i = 0; i < 4; i++) {
			if(i < Controleur.getInstance().getJoueurs().size()) {
				JPanel pioche = new JPanel(new BorderLayout(0,10));
				pioche.setBorder(new EmptyBorder(10,10,10,10));
				Controleur controleur = Controleur.getInstance();
				Joueur joueur = controleur.getJoueurs().get(i);
				JLabel label = new JLabel("Cartes du joueur n°" + (controleur.getJoueurs().indexOf(joueur)+1) + " : " + joueur.getName());
				
				JPanel cartes = new JPanel(new GridLayout(1,6));
				cartes.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 30, 10, 30), new EtchedBorder()));
												
//				for(CarteTresor tresor : this.getCartesTriees(joueur).keySet()) {
//					
//				}
				
				HashMap<CarteTresor, Integer> cartesTresor = this.getCartesTriees(joueur);
												
				for(int numCarte = 0; numCarte < 6; numCarte++) {
					if(numCarte < cartesTresor.size()) {
						CarteTresor carte = (CarteTresor) cartesTresor.keySet().toArray()[numCarte];
						cartes.add(new VueCarte(carte, numCarte, cartesTresor.get(carte)));
					} else {
						cartes.add(new JLabel(""));
					}
				}
				
				pioche.add(label, BorderLayout.NORTH);
				pioche.add(cartes, BorderLayout.CENTER);
				this.add(pioche);
			} else {
				this.add(new JLabel(""));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File("M2103/IleInterdite/images/fond-blanc-gris.jpg"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<CarteTresor, Integer> getCartesTriees(Joueur joueur) {
		HashMap<CarteTresor, Integer> cartes = new HashMap<>();
		ArrayList<CarteTresor> cartesTresor = new ArrayList<>();
		
		for(CarteTresor carte : joueur.getCartesTresor()) {
			cartesTresor.add(carte);
		}
				
		while(!cartesTresor.isEmpty()) {
			int nbOccurence = 0;
			CarteTresor carte = cartesTresor.get(0);
			cartesTresor.remove(carte);
			boolean trouve = true;
			
			while(trouve) {
				nbOccurence++;
				trouve = false;
				
				if(nbOccurence < cartesTresor.size()) {
					CarteTresor carteTresor = cartesTresor.get(0);
					
					if(carteTresor.getLibelle().equals(carte.getLibelle())) {
						trouve = true;
						cartesTresor.remove(carteTresor);						
					}
				}
			}
			cartes.put(carte, nbOccurence);
		}
		return cartes;
	}
}
