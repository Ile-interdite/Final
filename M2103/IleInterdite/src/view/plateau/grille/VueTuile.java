package view.plateau.grille;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import modele.carte.CarteSacsDeSable;
import utils.Mode;
import utils.Utils.EtatTuile;
import view.VuePlateau;

public class VueTuile extends JPanel implements Observe {
	
	private static HashMap<Tuile, VueTuile> vuesTuiles = new HashMap<>();
	
	private static HashMap<Tuile, VueTuile> getVuesTuiles() {
		return vuesTuiles;
	}
	
	public static VueTuile getInstance(Tuile tuile) {
		return VueTuile.getVuesTuiles().get(tuile);
	}
	
	public static void repaintAll() {		
		for(VueTuile vueTuile : VueTuile.getVuesTuiles().values()) {
			if(vueTuile.getTuile().getEtatTuile() != EtatTuile.COULEE) {
				vueTuile.repaint();
			}
		}
	}
    
	private Observateur observateur;
	private MouseListener mouseListener;
	
	//private JPanel vueTuile = this;
	private Tuile tuile;
    private int xO, yO, cote;
    private boolean asBorder = false;
    
    public VueTuile(int xO, int yO, int cote, Tuile tuile) {
    	this.setObservateur(Controleur.getInstance());
    	this.setTuile(tuile);
        this.setXO(xO);
        this.setYO(yO);
        this.setCote(cote);
        VueTuile.getVuesTuiles().put(this.getTuile(), this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	
		try {
			Tuile tuile = this.getTuile();
			EtatTuile etatTuile = tuile.getEtatTuile();
			
			if(etatTuile != EtatTuile.COULEE) {
				String fichier = "M2103/IleInterdite/images/tuiles/" + (etatTuile == EtatTuile.INONDEE ? "inondées/" : "asséchées/") + tuile.getNom().replaceAll(" ", "").replaceAll("'", "") + ".png";
				Image image = ImageIO.read(new File(fichier));
				int x = 5;
				int y = x;
				int width = this.getWidth() - 10;
				int height = this.getHeight() - 10;
			
				g2.drawImage(image, x, y, width, height, this);
				
				if(asBorder) {
					g2.setColor(Color.GREEN);
					g2.drawRect(x, y, width - 1, height - 1);
				}
				
				Mode mode = VuePlateau.getInstance().getMode();
				boolean tuileMode = false;
				
				if(mode != Mode.NORMAL) {
					Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getAventurier();
					
					boolean bool1 = mode == Mode.DEPLACEMENT && aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile);
					boolean bool2 = mode == Mode.ASSECHEMENT && aventurier.getAssechement(aventurier.getTuileCourante()).contains(tuile);
					
					if(bool1 || bool2 || mode == Mode.DEPLACEMENT_SPECIAL || (mode == Mode.ASSECHEMENT_SPECIAL && tuile.getEtatTuile() == EtatTuile.INONDEE)) {
						Color colTrans = new Color(255, 255, 0, 60);
						g2.setColor(colTrans);
						g2.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
						tuileMode = true;
					}
				}
				
				if(Controleur.getInstance().getJoueurCourant().getAventurier().getTuileCourante() == tuile && !tuileMode) {
					Color colTrans = new Color(50, 255, 50, 40);
					g2.setColor(colTrans);
					g2.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
				}
				
				this.addMouseListener();
				
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
    
    @Override
    public void repaint() {
    	if(this.getTuile() != null) {
    		super.repaint(5,5, this.getWidth() - 10, this.getHeight() - 10);    		
    	}
    }
    
    public void addMouseListener() {
    	if(mouseListener == null) {
    		mouseListener = new MouseListener() {
    			
    			@Override
    			public void mouseClicked(MouseEvent e) {}
    			
    			@Override
    			public void mouseEntered(MouseEvent e) {
    				if(getTuile().getEtatTuile() != EtatTuile.COULEE) {
    					Mode mode = VuePlateau.getInstance().getMode();
    					
    					if(mode != Mode.NORMAL) {
    						Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getAventurier();
    						
    						boolean bool1 = mode == Mode.DEPLACEMENT && aventurier.getDeplacement(aventurier.getTuileCourante()).contains(tuile);
    						boolean bool2 = mode == Mode.ASSECHEMENT && aventurier.getAssechement(aventurier.getTuileCourante()).contains(tuile);
    						
    						if(bool1 || bool2 || mode == Mode.DEPLACEMENT_SPECIAL || (mode == Mode.ASSECHEMENT_SPECIAL && tuile.getEtatTuile() == EtatTuile.INONDEE)) {
    							setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    							asBorder = true;
    						}
    					}
    					repaint();
    				}
    			}
    			
    			@Override
    			public void mouseExited(MouseEvent e) {
    				if(getTuile().getEtatTuile() != EtatTuile.COULEE) {
    					setCursor(Cursor.getDefaultCursor());
    					asBorder = false;
    					repaint();    					
    				}
    			}
    			
    			@Override
    			public void mousePressed(MouseEvent e) {}
    			
    			@Override
    			public void mouseReleased(MouseEvent e) {
    				if(getTuile().getEtatTuile() != EtatTuile.COULEE) {
    					Mode mode = VuePlateau.getInstance().getMode();
    					
    					if(mode != Mode.NORMAL) {
    						Aventurier aventurier = Controleur.getInstance().getJoueurCourant().getAventurier();
    						
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
    								message.setCarteTresor(Controleur.getInstance().getJoueurCourant().getCartes().get(0));
    								break;
    							case ASSECHEMENT_SPECIAL:
    								message.setTypeMessage(TypeMessage.UTILISER_CARTE);
    								
    								CarteTresor carte = null;
    								Iterator<CarteTresor> iterator = Controleur.getInstance().getJoueurCourant().getCartes().iterator();
    								boolean trouve = false;
    								
    								while(iterator.hasNext() && !trouve) {
    									carte = iterator.next();
    									
    									if(carte instanceof CarteSacsDeSable) {
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
    					setCursor(Cursor.getDefaultCursor());
    				}
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
