package view.plateau.grille;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.Controleur;
import controller.Message;
import controller.Observateur;
import controller.Observe;
import controller.TypeMessage;
import modele.Tuile;
import modele.aventurier.Aventurier;
import modele.carte.CarteTresor;
import modele.carte.SacDeSable;
import utils.Mode;
import utils.Utils.EtatTuile;

public class VueTuile extends JPanel implements Observe {
    
	private Observateur observateur;
	private MouseListener mouseListener;
	
	//private JPanel vueTuile = this;
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
				if(etatTuile != EtatTuile.COULEE) {
					String fichier = "M2103/IleInterdite/images/tuiles/" + (etatTuile == EtatTuile.INONDEE ? "inondées/" : "asséchées/") + tuile.getNom().replaceAll(" ", "").replaceAll("'", "") + ".png";
					Image image = ImageIO.read(new File(fichier));
				
					g2.drawImage(image, 5, 5, this.getWidth() - 10, this.getHeight() - 10, this);
					
					Mode mode = Controleur.getInstance().getVuePlateau().getMode();
					boolean tuileMode = false;
					
					if(mode != Mode.NORMAL) {
						Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getRole();
						
						boolean bool1 = mode == Mode.DEPLACEMENT && aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile);
						boolean bool2 = mode == Mode.ASSECHEMENT && aventurier.getAssechement(aventurier.getTuileCourante()).contains(tuile);
						
						if(bool1 || bool2 || mode == Mode.DEPLACEMENT_SPECIAL || (mode == Mode.ASSECHEMENT_SPECIAL && tuile.getEtatTuile() == EtatTuile.INONDEE)) {
							Color colTrans = new Color(255, 255, 0, 80);
							g2.setColor(colTrans);
							g2.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
							tuileMode = true;
						}
					}
					
					if(Controleur.getInstance().getJoueurCourant().getRole().getTuileCourante() == tuile && !tuileMode) {
						Color colTrans = new Color(50, 255, 50, 80);
						g2.setColor(colTrans);
						g2.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
					}
					
					this.addMouseListener(tuile);
					
					if(!tuile.getAventuriers().isEmpty()) {
						ArrayList<Aventurier> aventuriers = tuile.getAventuriers();
						
						for(int i = 0; i < aventuriers.size(); i++) {		
							Image imagePion = ImageIO.read(new File("M2103/IleInterdite/images/pions/pion" + aventuriers.get(i).getPion().getLibelle() + ".png"));
							g2.drawImage(imagePion, 10 + (30*i), 10, 50, 50, this);
						}
					}				
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void addMouseListener(Tuile tuile) {
    	if(mouseListener == null) {
    		mouseListener = new MouseListener() {
    			
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				Mode mode = Controleur.getInstance().getVuePlateau().getMode();
    				
    				if(mode != Mode.NORMAL) {
    					Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getRole();
    					
    					boolean bool1 = mode == Mode.DEPLACEMENT && aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile);
    					boolean bool2 = mode == Mode.ASSECHEMENT && aventurier.getAssechement(aventurier.getTuileCourante()).contains(tuile);
    					
    					if(bool1 || bool2 || mode == Mode.DEPLACEMENT_SPECIAL || (mode == Mode.ASSECHEMENT_SPECIAL && tuile.getEtatTuile() == EtatTuile.INONDEE)) {
    						Message message = new Message();
    						message.setTargetTuile(tuile);
    						
    						switch(mode) {
    						case DEPLACEMENT:
    							message.setTypeMessage(TypeMessage.DEPLACEMENT);
    							break;
    						case ASSECHEMENT:
    							message.setTypeMessage(TypeMessage.ASSECHEMENT);
    							break;
    						case DEPLACEMENT_SPECIAL:
    							message.setTypeMessage(TypeMessage.UTILISER_CARTE);
    							message.setCarteTresor(Controleur.getInstance().getJoueurCourant().getCartesTresor().get(0));
    							break;
    						case ASSECHEMENT_SPECIAL:
    							message.setTypeMessage(TypeMessage.UTILISER_CARTE);
    							
    							CarteTresor carte = null;
    							Iterator<CarteTresor> iterator = Controleur.getInstance().getJoueurCourant().getCartesTresor().iterator();
    							boolean trouve = false;
    							
    							while(iterator.hasNext() && !trouve) {
    								carte = iterator.next();
    								
    								if(carte instanceof SacDeSable) {
    									trouve = true;
    								}
    							}
    							message.setCarteTresor(carte);
    							break;
    						default:
    							break;
    						}
    						notifierObservateur(message);				
    					}
    				}
    			}
    			
    			@Override
    			public void mouseEntered(MouseEvent e) {
    				//vueTuile.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
    			}
    			
    			@Override
    			public void mouseExited(MouseEvent e) {
    				//vueTuile.setBorder(BorderFactory.createEmptyBorder());
    			}
    			
    			@Override
    			public void mousePressed(MouseEvent e) {
    				
    			}
    			
    			@Override
    			public void mouseReleased(MouseEvent e) {
    				
    			}
    			
    		};
    		this.addMouseListener(mouseListener);
    	}
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
