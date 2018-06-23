package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import controller.Controleur;
import modele.aventurier.Aventurier;
import modele.carte.CMDE;
import modele.carte.CarteTresor;
import modele.carte.Helicoptere;
import modele.carte.SacDeSable;
import utils.Mode;
import view.VuePlateau;
import view.plateau.jeu.pioches.VuePile;

public class Joueur {

	private Aventurier role;
	private String name;
	private int pointsAction = 3;

	ArrayList<CarteTresor> cartesTresor = new ArrayList<>();

	/**
	 * Création d'un nouveau joueur.
	 * Nombre de joueurs minimum : 2
	 * Nombre de joueurs maximum : 4
	 * 
	 * @param name Le nom du joueur.
	 */
	public Joueur(String name) {
		this.setName(name);
	}

	@Override
	public String toString(){
		return this.getName() + " " + this.getRole(); 
	}
	
	public String getName() {
		return name;
	}

	/**
	 * Définit le nom du joueur dans le jeu.
	 * 
	 * @param name Le nom du joueur.
	 */
	public void setName(String name) {
		if(name != null) {
			this.name = name;
		}
	}

	/**
	 * @return Le rôle du joueur dans le jeu.
	 */
	public Aventurier getRole() {
		return role;
	}

	/**
	 * Définit le rôle du joueur dans le jeu.
	 * 
	 * @param role Le rôle du joueur.
	 */
	public void setRole(Aventurier role) {
		if(role != null) {
			this.role = role;
		}
	}

	/**
	 * @return Le nombre de points d'action du joueur.
	 */
	public int getPointsAction() {
		return pointsAction;
	}
	
	/**
	 * Définit le nombre de points d'action du joueur.
	 * 
	 * @param pointsAction Le nombre de points d'action.
	 */
	public void setPointsAction(int pointsAction) {
		this.pointsAction = pointsAction;
	}
	
	public ArrayList<CarteTresor> trierCartesTresor() {
		ArrayList<CarteTresor> cartesTresor = this.cartesTresor;
		TreeSet<CarteTresor> cartesTresorTriees = new TreeSet<>();
		
		for(CarteTresor carteTresor : cartesTresor) {
			cartesTresorTriees.add(carteTresor);
		}
		
		cartesTresor.clear();
		
		for(CarteTresor carteTresor : cartesTresorTriees) {
			cartesTresor.add(carteTresor);
		}
		
		return cartesTresor;
	}

	/**
	 * @return La liste des cartes "Trésor" possédées par le joueur.
	 */
	public ArrayList<CarteTresor> getCartesTresor() {
		return this.trierCartesTresor();
	}

	/**
	 * Ajoute une carte "Trésor" à la liste des cartes du joueur.
	 * 
	 * @param carteTresor La carte "Trésor" à ajouter à la liste.
	 */
	public void addCarteTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.getCartesTresor().add(carteTresor);
			VuePile vuePile = VuePile.getPile(this);
			
			if(vuePile != null) {
				vuePile.updatePile(vuePile);				
			}
		}
	}

	public void removeCarteTresor(CarteTresor carteTresor) {
		if(carteTresor != null && this.getCartesTresor().contains(carteTresor)) {
			this.getCartesTresor().remove(carteTresor);
			VuePile vuePile = VuePile.getPile(this);
			
			if(vuePile != null) {
				vuePile.updatePile(vuePile);				
			}
		}
	}

	public void donnerCarteTresor(CarteTresor carteTresor, Joueur joueur) {
		if(carteTresor != null 
                    && joueur != null
                    && !(carteTresor instanceof SacDeSable)
                    && !(carteTresor instanceof Helicoptere)) {
			if(this.getCartesTresor().contains(carteTresor)) {
				this.removeCarteTresor(carteTresor);
				joueur.addCarteTresor(carteTresor);
                                this.setPointsAction(getPointsAction()-1);
			}
		}
	}

	public void defausserCarteTresor(CarteTresor carteTresor) {    	
		if(carteTresor != null && this.getCartesTresor().contains(carteTresor)) {
			this.removeCarteTresor(carteTresor);
			Controleur.getInstance().getDefausseTresor().add(carteTresor);
		}
	}

	public void piocherCarteTresor() {
		CarteTresor carteTresor = Controleur.getInstance().popCarteTresor();

		if(carteTresor != null) {
			this.getCartesTresor().add(carteTresor);
		}
	}

	public void utiliserCarteTresor(CarteTresor carte, Tuile tuile) {        
		if(this.getCartesTresor().contains(carte)) {
			if(carte instanceof Helicoptere || carte instanceof SacDeSable) {
				this.defausserCarteTresor(carte);
				
				if(carte instanceof Helicoptere) {
					this.getRole().seDeplacer(tuile);
				} else {
					this.getRole().assecher(tuile);
				}
				VuePlateau.getInstance().setMode(Mode.NORMAL);
			}
		}
	}
	
	public void tirerCarteTresor(int nbCarte, boolean debutPartie) {
        int nbCMDE = 0;
        for (int i = 0; i < nbCarte; i++) {
            CarteTresor carte =  Controleur.getInstance().popCarteTresor();
            if (carte instanceof CMDE && !debutPartie) {
                CMDE c = (CMDE) carte;
                nbCMDE ++ ;
                c.utiliserCarte();
                Controleur.getInstance().addDefausseTresor(carte);
            } else if (carte instanceof CMDE && debutPartie) {
            	CMDE c = (CMDE) carte;
            	Controleur.getInstance().addPileTresor(c);
            	Collections.shuffle(Controleur.getInstance().getPileTresor());
            }else {
                j.addCarteTresor(carte);
            }
            Controleur.getInstance().getPileTresor().remove(carte);
        }
        
        if(nbCMDE!=1) {
        ArrayList<CarteTresor> alTresor = new ArrayList<>();
        alTresor = Controleur.getInstance().getDefausseTresor();
        
        if(!alTresor.isEmpty()) {
        	Collections.shuffle(alTresor);

            for (CarteTresor c : alTresor) {
                Controleur.getInstance().addPileTresor(c);
            }
        }
        
    }
        
    }
}
