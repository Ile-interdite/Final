package modele.aventurier;

import java.util.ArrayList;

import controller.Controleur;
import modele.Joueur;
import modele.Tuile;
import utils.Mode;
import utils.Utils;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import view.VuePlateau;
import view.plateau.grille.VueTuile;

public abstract class Aventurier {

    private Tuile tuileCourante;
    private Pion pion;

    @Override
    public String toString() {
        return this.getPion() + " " + this.getClass().getSimpleName() + " : " + this.getTuileCourante();
    }

    public void spawn() {
        Tuile[][] tuiles = Controleur.getInstance().getGrille().getTuiles();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tuile tuile = tuiles[x][y];
                if (tuile != null && tuile.getPorte() != null && tuile.getPorte().equals(this.getPion())) {
                	this.setTuileCourante(tuiles[x][y]);
                    tuile.addAventurier(this);
                }
            }
        }
    }
    
    public void seDeplacer(Tuile tuile) {
    	Tuile tuileCourante = getTuileCourante();
    	
    	if(VuePlateau.getInstance().getMode() == Mode.DEPLACEMENT) {
    		ArrayList<Tuile> tuilesPossibles = getDeplacement(tuileCourante);
    		tuilesPossibles.remove(tuileCourante);
    		
    		if(!tuilesPossibles.isEmpty()) {
    			if (tuilesPossibles.contains(tuile)) {
    				tuileCourante.removeAventurier(this);
    				tuile.addAventurier(this);
    				this.setTuileCourante(tuile);
    				
    				Joueur joueur = Controleur.getInstance().getJoueurCourant();
    				joueur.setPointsAction(joueur.getPointsAction() - 1);
    				
    				Utils.sendMessage("Déplacement effectué avec succès !");
    				VueTuile.getInstance(tuileCourante).repaint();
    				VueTuile.getInstance(tuile).repaint();
    			} else {
    				Utils.sendMessage("Déplacement impossible !");
    			}   	
    		} else {
    			Utils.sendMessage("Déplacement impossible !");
    		}		
    	} else {
    		tuileCourante.removeAventurier(this);
			tuile.addAventurier(this);
			this.setTuileCourante(tuile);
			VueTuile.getInstance(tuileCourante).repaint();
			VueTuile.getInstance(tuile).repaint();
			Utils.sendMessage("Déplacement effectué avec succès !");
    	}
    }

    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > 0) {
            Tuile nord = Controleur.getInstance().getTuile(x, y - 1);
            if (nord != null && nord.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Controleur.getInstance().getTuile(x + 1, y);
            if (est != null && est.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Controleur.getInstance().getTuile(x, y + 1);
            if (sud != null && sud.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Controleur.getInstance().getTuile(x - 1, y);
            if (ouest != null && ouest.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }

    public void assecher(Tuile tuile) {
    	if(VuePlateau.getInstance().getMode() == Mode.ASSECHEMENT) {
    		Tuile tuilCourante = this.getTuileCourante();
    		ArrayList<Tuile> tuilesPossibles = this.getAssechement(tuilCourante);
    		
    		if(!tuilesPossibles.isEmpty()) {
    			if(tuilesPossibles.contains(tuile)) {
    				tuile.setEtat(EtatTuile.ASSECHEE);
    				
    				Joueur joueur = Controleur.getInstance().getJoueurCourant();
    				joueur.setPointsAction(joueur.getPointsAction() - 1);
    				
    				Utils.sendMessage("Assèchement effectué avec succès !");
    			} else {
    				Utils.sendMessage("Assèchement impossible !");
    			}
    		} else {        	
    			Utils.sendMessage("Assèchement impossible !");
    		}
    	} else {
    		tuile.setEtat(EtatTuile.ASSECHEE);
    		Utils.sendMessage("Assèchement effectué avec succès !");
    	}
    }

    public ArrayList<Tuile> getAssechement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (tuile.getEtatTuile() == EtatTuile.INONDEE) {
            tuiles.add(tuile);
        }

        if (y > 0) {
            Tuile nord = Controleur.getInstance().getTuile(x, y - 1);
            if (nord != null && nord.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Controleur.getInstance().getTuile(x + 1, y);
            if (est != null && est.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Controleur.getInstance().getTuile(x, y + 1);
            if (sud != null && sud.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Controleur.getInstance().getTuile(x - 1, y);
            if (ouest != null && ouest.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }

    public Tuile getTuileCourante() {
        return tuileCourante;
    }

    public void setTuileCourante(Tuile tuileCourante) {
    	this.tuileCourante = tuileCourante;
    }
    
    public Pion getPion() {
    	return pion;
    }
    
    protected void setPion(Pion pion) {
        this.pion = pion;
    }
}
