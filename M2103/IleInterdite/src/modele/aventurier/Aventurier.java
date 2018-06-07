package modele.aventurier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import controller.Controleur;
import modele.Joueur;
import modele.Tuile;
import utils.Utils;
import utils.Utils.EtatTuile;
import utils.Utils.Pion;

public abstract class Aventurier {

    private Tuile tuileCourante;
    private Pion couleur;

    @Override
    public String toString() {
        return this.getCouleur() + " " + this.getClass().getSimpleName() + " : " + this.getTuileCourante();
    }

    public void spawn() {
        Tuile[][] tuiles = Controleur.getInstance().getGrille().getTuiles();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tuile tuile = tuiles[x][y];
                if (tuile != null && tuile.getPorte() != null && tuile.getPorte().equals(this.getCouleur())) {
                	this.setTuileCourante(tuiles[x][y]);
                    tuile.addAventurier(this);
                }
            }
        }
    }
    
    public void seDeplacer() {
        Tuile oldTuile = getTuileCourante();
        ArrayList<Tuile> tuilesPossibles = getDeplacement(oldTuile);
        tuilesPossibles.remove(oldTuile); //pour enlever la tuile sur laquelle le joueur est placé

        if(!tuilesPossibles.isEmpty()) {
        	System.out.println("\n");
        	System.out.println("Choix tuiles : ");
        	this.afficherTuile(tuilesPossibles);
        	System.out.println("\n");
        	
        	Scanner scan = Controleur.getInstance().getScanner();
        	System.out.print("X ? ");
        	int x = scan.nextInt();
        	System.out.print("Y ? ");
        	int y = scan.nextInt();
        	
        	Tuile choixTuile = Controleur.getInstance().getTuile(x, y);
        	
        	if (tuilesPossibles.contains(choixTuile)) {
        		choixTuile.addAventurier(this);
        		this.setTuileCourante(choixTuile);
        		oldTuile.removeAventurier(this);
        		System.out.println("Déplacement effectué avec succès !");
        		
        		Joueur joueur = Controleur.getInstance().getJoueurCourant();
        		joueur.setPointsAction(joueur.getPointsAction() - 1);
        	} else {
        		System.out.println("Déplacement impossible !");
        	}   	
        } else {
        	System.out.println("Déplacement impossible !");
        }      
    }

    public ArrayList<Tuile> getDeplacement(Tuile tuile) {
        int x = tuile.getPosition().getX();
        int y = tuile.getPosition().getY();
        ArrayList<Tuile> tuiles = new ArrayList<>();

        if (y > 0) {
            Tuile nord = Controleur.getInstance().getTuile(x, y - 1);
            if (nord != null && nord.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(nord);
            }
        }

        if (x < 5) {
            Tuile est = Controleur.getInstance().getTuile(x + 1, y);
            if (est != null && est.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(est);
            }
        }

        if (y < 5) {
            Tuile sud = Controleur.getInstance().getTuile(x, y + 1);
            if (sud != null && sud.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(sud);
            }
        }

        if (x > 0) {
            Tuile ouest = Controleur.getInstance().getTuile(x - 1, y);
            if (ouest != null && ouest.getEtatTuile() == Utils.EtatTuile.ASSECHEE) {
                tuiles.add(ouest);
            }
        }
        return tuiles;
    }

    public void afficherTuile(ArrayList<Tuile> tuiles) {
    	Iterator<Tuile> iterator = tuiles.iterator();
    	int i = 1;
    	
    	while(iterator.hasNext()) {
    		Tuile tuile = iterator.next();
    		System.out.print(tuile.getPosition() + (iterator.hasNext() ? (i % 5 == 0 ? ",\n" : ", ") : ""));
    		i++;
    	}
    }

    public void assecher() {
        Tuile tuilCourante = this.getTuileCourante();
        ArrayList<Tuile> tuilesPossibles = this.getAssechement(tuilCourante);
        
        if(!tuilesPossibles.isEmpty()) {
        	System.out.println("Choix tuiles :");
        	this.afficherTuile(tuilesPossibles);
        	System.out.println("\n");
        	
        	Scanner scan = Controleur.getInstance().getScanner();
        	System.out.print("X ? ");
        	int x = scan.nextInt();
        	System.out.print("Y ? ");
        	int y = scan.nextInt();
        	
        	Tuile choixTuile = Controleur.getInstance().getTuile(x, y);
        	
        	if(tuilesPossibles.contains(choixTuile)) {
        		choixTuile.setEtat(EtatTuile.ASSECHEE);
        		System.out.println("Assèchement effectué avec succès !");
        		System.out.println(choixTuile.getNom() + " - Etat : " + choixTuile.getEtatTuile());
        		
        		Joueur joueur = Controleur.getInstance().getJoueurCourant();
        		joueur.setPointsAction(joueur.getPointsAction() - 1);
        	} else {
        		System.out.println("Assèchement impossible !");
        	}
        } else {        	
        	System.out.println("Assèchement impossible !");
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
    
    public Pion getCouleur() {
    	return couleur;
    }
    
    public void setCouleur(Pion couleur) {
        this.couleur = couleur;
    }
}
