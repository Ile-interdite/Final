package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Tuile;
import modele.aventurier.Aventurier;
import utils.Mode;
import utils.Utils.EtatTuile;

public class VueTuile extends JPanel implements Observe {
    
	private Observateur observateur;
	
	private Tuile tuile;
    private int xO, yO, cote;
    
    public VueTuile(int xO, int yO, int cote, Tuile tuile) {
    	this.setObservateur(Controleur.getInstance());
    	this.setTuile(tuile);
        this.setXO(xO);
        this.setYO(yO);
        this.setCote(cote);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	if(this.getTuile() != null) {
			try {
				Tuile tuile = this.getTuile();
				EtatTuile etatTuile = tuile.getEtatTuile();
				String fichier = "M2103/IleInterdite/images/tuiles/" + tuile.getNom().replaceAll(" ", "").replaceAll("'", "") + (etatTuile == EtatTuile.INONDEE ? "_Inonde" : "") + ".png";
				Image image = ImageIO.read(new File(etatTuile == EtatTuile.COULEE ? "M2103/IleInterdite/images/ocean.jpg" : fichier));
				
				g2.drawImage(image, 5, 5, this.getWidth() - 10, this.getHeight() - 10, this);
				
				Mode mode = VueGrille.getMode();
				
				if(mode == Mode.DEPLACEMENT || mode == Mode.ASSECHEMENT) {
					Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getRole();
					
					boolean bool1 = mode == Mode.DEPLACEMENT && aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile);
					boolean bool2 = mode == Mode.ASSECHEMENT && aventurier.getAssechement(aventurier.getTuileCourante()).contains(tuile);
					
					if(bool1 || bool2) {
						Color colTrans = new Color(255, 255, 0, 80);
						g2.setColor(colTrans);
						g2.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
					}
				}
				
				this.addMouseListener(tuile);
				
				if(!tuile.getAventuriers().isEmpty()) {
					ArrayList<Aventurier> aventuriers = tuile.getAventuriers();
					
					for(int i = 0; i < aventuriers.size(); i++) {		
						g2.setColor(aventuriers.get(i).getPion().getCouleur());
						g2.fillOval(10 + (10*i), 10, 30, 30);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		g2.setColor(Color.BLUE);
    		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    	}
    }
    
    public void addMouseListener(Tuile tuile) {
    	Mode mode = VueGrille.getMode();
    	
    	this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(mode != Mode.NORMAL) {
					Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getRole();
					
					if(aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile)) {
						Message message = new Message();
						message.setTargetTuile(tuile);
						
						switch(mode) {
						case DEPLACEMENT:
							message.setTypeMessage(TypeMessage.DEPLACEMENT);
							break;
						case ASSECHEMENT:
							message.setTypeMessage(TypeMessage.ASSECHEMENT);
							break;
						default:
							break;
						}
						notifierObservateur(message);				
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
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
    }
    
    public Tuile getTuile() {
    	return tuile;
    }
    
    public void setTuile(Tuile tuile) {
    	this.tuile = tuile;
    }

    public int getXO() {
        return xO;
    }

    private void setXO(int xO) {
        this.xO = xO;
    }

    public int getYO() {
        return yO;
    }

    private void setYO(int yO) {
        this.yO = yO;
    }

    public int getCote() {
        return cote;
    }

    private void setCote(int cote) {
        this.cote = cote;
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
