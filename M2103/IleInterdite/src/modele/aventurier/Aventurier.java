package modele.aventurier;

import java.util.ArrayList;
import java.util.Collections;

import controller.Controleur;
import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import utils.Mode;
import utils.Utils;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;
import view.VuePlateau;

public abstract class Aventurier {
	
	private static ArrayList<Aventurier> aventuriers = new ArrayList<>();
	
	public static ArrayList<Aventurier> getAventuriers() {
		return aventuriers;
	}
	
	public static void createAventuriers() {
        Pilote pilote = new Pilote();
        Plongeur plongeur = new Plongeur();
        Explorateur explorateur = new Explorateur();
        Messager messager = new Messager();
        Ingenieur ingenieur = new Ingenieur();
        Navigateur navigateur = new Navigateur();

        Aventurier.getAventuriers().add(pilote);
        Aventurier.getAventuriers().add(plongeur);
        Aventurier.getAventuriers().add(explorateur);
        Aventurier.getAventuriers().add(messager);
        Aventurier.getAventuriers().add(ingenieur);
        Aventurier.getAventuriers().add(navigateur);

        Collections.shuffle(aventuriers);
    }

    private Tuile tuileCourante;
    private Pion pion;

    @Override
    public String toString() {
        return this.getPion() + " " + this.getClass().getSimpleName() + " : " + this.getTuileCourante();
    }

    public void spawn() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tuile tuile = Grille.getTuile(x, y);
                
                if (tuile != null && tuile.getPorte() != null && tuile.getPorte().equals(this.getPion())) {
                	this.setTuileCourante(tuile);
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
			Utils.sendMessage("Déplacement effectué avec succès !");
    	}
    }

    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > 0) {
            Tuile nord = Grille.getTuile(x, y - 1);
            if (nord != null && nord.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Grille.getTuile(x + 1, y);
            if (est != null && est.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Grille.getTuile(x, y + 1);
            if (sud != null && sud.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Grille.getTuile(x - 1, y);
            if (ouest != null && ouest.getEtatTuile() != Utils.EtatTuile.COULEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }

    public void assecher(Tuile tuile) {
    	Tuile tuileCourante = this.getTuileCourante();
    	
    	if(VuePlateau.getInstance().getMode() == Mode.ASSECHEMENT) {
    		ArrayList<Tuile> tuilesPossibles = this.getAssechement(tuileCourante);
    		
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
            Tuile nord = Grille.getTuile(x, y - 1);
            if (nord != null && nord.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Grille.getTuile(x + 1, y);
            if (est != null && est.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Grille.getTuile(x, y + 1);
            if (sud != null && sud.getEtatTuile() == Utils.EtatTuile.INONDEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Grille.getTuile(x - 1, y);
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
