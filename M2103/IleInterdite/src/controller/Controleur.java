package controller;

import static utils.Tresor.CALICE_ONDE;
import static utils.Tresor.CRISTAL_ARDENT;
import static utils.Tresor.PIERRE_SACREE;
import static utils.Tresor.STATUE_ZEPHIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import modele.aventurier.Aventurier;
import modele.aventurier.Explorateur;
import modele.aventurier.Ingenieur;
import modele.aventurier.Messager;
import modele.aventurier.Pilote;
import modele.aventurier.Plongeur;
import modele.carte.CMDE;
import modele.carte.CTresor;
import modele.carte.CarteInondation;
import modele.carte.CarteTresor;
import modele.carte.Helicoptere;
import modele.carte.SacSable;
import utils.Tresor;
import view.VuePlateau;

public class Controleur implements Observateur {

	private int niveauEau;
	private boolean etatPartie;
	private Grille grille;
	
	private VuePlateau vuePlateau;
	
	//Collections
	private ArrayList<Tresor> tresorPossedes;
	private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteTresor> defausseTresor;
	private ArrayList<CarteInondation> pileInondation;
	private ArrayList<CarteInondation> defausseInondation;
	private ArrayList<Aventurier> aventuriers;
	private ArrayList<Joueur> joueurs;
	
	public static void main(String[] args) {}
        
	public void createAventuriers(){
		Pilote pilote = new Pilote();
		Plongeur plongeur = new Plongeur();
		Explorateur explorateur = new Explorateur();
		Messager messager = new Messager();
		Ingenieur ingenieur = new Ingenieur();

		aventuriers.add(pilote);
		aventuriers.add(plongeur);
		aventuriers.add(explorateur);
		aventuriers.add(messager);
		aventuriers.add(ingenieur);

		Collections.shuffle(aventuriers);
	}

	public void createCartes(){

		//cree carte innondation

		for (int i=0; i<5; i++){
			CTresor pierreSacree = new CTresor(PIERRE_SACREE);
			pileTresor.add(pierreSacree);
		}
		for (int i=0; i<5; i++){
			CTresor caliceOnde = new CTresor(CALICE_ONDE);
			pileTresor.add(caliceOnde);
		}
		for (int i=0; i<5; i++){
			CTresor cristalArdent = new CTresor(CRISTAL_ARDENT);
			pileTresor.add(cristalArdent);
		}
		for (int i=0; i<5; i++){
			CTresor statueZephir = new CTresor(STATUE_ZEPHIR);
			pileTresor.add(statueZephir);
		}
		for (int i=0; i<3; i++){
			CMDE monteeDeEaux = new CMDE();
			pileTresor.add(monteeDeEaux);
		}
		for (int i=0; i<3; i++){
			Helicoptere helicoptere = new Helicoptere();
			pileTresor.add(helicoptere);
		}
		for (int i=0; i<2; i++){
			SacSable sac = new SacSable();
			pileTresor.add(sac);
		}

		Collections.shuffle(pileTresor);
	}

    public void donnerCarte(int numJoueur) {
    	// TODO - implement Controleur.donnerCarte
    }

    public void choixCarte(CarteTresor carte) {
    	// TODO - implement Controleur.choixCarte
    }

	public void initJeux() {
		grille = new Grille();
		createAventuriers();
		createCartes();
		//cree carte innondation
		System.out.println("Nbr de joueur?");
		Scanner scan = new Scanner(System.in);
		int nbJoueur = Integer.parseInt(scan.nextLine());
		
		for (int i = 0; i < nbJoueur; i++){
			Joueur joueur = new Joueur("joueur 1");
			joueur.setRole(aventuriers.get(i));
			joueurs.set(i, joueur);
			
			for (int y = 0; i < 3; i++){
				joueur.setCartes(pileTresor.get(pileTresor.size() - 1));
			}
		}
		System.out.println("Niveau d'eau ?");
		int niveauEau = Integer.parseInt(scan.nextLine());
		setNiveauEau(niveauEau);
		setEtatPartie(true);
	}

    public void utiliserCarte(Joueur joueur, CarteTresor carte) {
        //ArrayList<CarteTresor> cartes = joueur.getCartesTresor();
    }

    public void defausserCarte(Joueur joueur, CarteTresor carte) {

    }

    @Override
    public void traiterMessage(Message m) {
        if(m != null) {
            Joueur joueur = m.getJoueur();

            if(joueur != null) {
                switch(m.getTypeMessage()) {
                    case UTILISER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();

                        if(carte != null) {
                            utiliserCarte(joueur, carte);
                        }
                        break;
                    }
                    case DEFAUSSER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();

                        if(carte != null) {
                            defausserCarte(joueur, carte);
                        }
                        break;
                    }
                    case DEPLACEMENT: {
                        Tuile tuileCible = m.getTuileCible();

                        if(joueur != null) {
                           
                        }
                        break;
                    }
                    case ASSECHEMENT: {
                        Tuile tuileCible = m.getTuileCible();

                        if(joueur != null) {

                        }
                        break;
                    }
                    case DONNER_CARTE: {
                        break;
                    }
                    case RECUPERER_TRESOR: {
                        break;
                    }
                    default: {
                        break;
                    }
                }   
            }
        }
    }
    
    public boolean getEtatPartie() {
    	return etatPartie;
    }
    
    public void setEtatPartie(boolean etatPartie) {
    	this.etatPartie = etatPartie;
    }
    
    /**
     * @return La grille de jeu.
     */
    public Grille getGrille() {
    	return grille;
    }
    
    /**
     * @param grille La grille de jeu.
     */
    public void setGrille(Grille grille) {
    	this.grille = grille;
    }
    
    public int getNiveauEau() {
    	return niveauEau;
    }
    
    public void setNiveauEau(int niveauEau) {
    	if(niveauEau >= 2) {
    		this.niveauEau = niveauEau;
    	}
    }

    /**
     * @return La vue du plateau de jeu.
     */
	public VuePlateau getVuePlateau() {
		return vuePlateau;
	}

	/**
	 * @param vuePlateau La vue du plateau de jeu.
	 */
	public void setVuePlateau(VuePlateau vuePlateau) {
		this.vuePlateau = vuePlateau;
	}

	/**
	 * @return La liste des trésors possédés par les joueurs.
	 */
	public ArrayList<Tresor> getTresorPossedes() {
		return tresorPossedes;
	}

	/**
	 * Ajoute un trésor à la liste des trésors possédés
	 * si les joueurs ne le possèdent pas déjà.
	 * 
	 * @param tresor Le trésor à ajouter.
	 */
	public void addTresorPossedes(Tresor tresor) {
		if(tresor != null && !this.tresorPossedes.contains(tresor)) {
			this.tresorPossedes.add(tresor);
		}
	}
	
	/**
	 * @return La liste des cartes "Trésor" dans la pile associée.
	 */
	public ArrayList<CarteTresor> getPileTresor() {
		return pileTresor;
	}
	
	/**
	 * Ajoute une carte "Trésor" à la pile associée.
	 * 
	 * @param carteTresor La carte "Trésor" à ajouter à la pile. 
	 */
	public void addPileTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.pileTresor.add(carteTresor);
		}
	}

	/**
	 * @return La liste des cartes "Trésor" dans la défausse associée.
	 */
	public ArrayList<CarteTresor> getDefausseTresor() {
		return defausseTresor;
	}

	/**
	 * Ajoute une carte "Trésor" à la défausse associée.
	 * 
	 * @param carteTresor La carte "Trésor" à ajouter à la défausse.
	 */
	public void addDefausseTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.defausseTresor.add(carteTresor);			
		}
	}

	/**
	 * @return La liste des cartes "Inondation" dans la pile associée.
	 */
	public ArrayList<CarteInondation> getPileInondation() {
		return pileInondation;
	}

	/**
	 * Ajoute une carte "Inondation" à la pile associée.
	 * 
	 * @param carteInondation La carte "Inondation" à ajouter à la pile.
	 */
	public void addPileInondation(CarteInondation carteInondation) {
		if(carteInondation != null) {
			this.pileInondation.add(carteInondation);
		}
	}

	/**
	 * @return La liste des cartes "Inondation" dans la défausse associée.
	 */
	public ArrayList<CarteInondation> getDefausseInondation() {
		return defausseInondation;
	}

	/**
	 * Ajoute une carte "Inondation" à la défausse associée.
	 * 
	 * @param carteInondation La carte "Inondation" à ajouter à la défausse.
	 */
	public void addDefausseInondation(CarteInondation carteInondation) {
		if(carteInondation != null) {
			this.defausseInondation.add(carteInondation);
		}
	}

	/**
	 * @return La liste des aventuriers.
	 */
	public ArrayList<Aventurier> getAventuriers() {
		return aventuriers;
	}
	
	/**
	 * Ajoute un aventurier à la liste des aventuriers.
	 * 
	 * @param aventurier L'aventurier à ajouter à la liste.
	 */
	public void addAventurier(Aventurier aventurier) {
		if(aventurier != null) {
			this.aventuriers.add(aventurier);
		}
	}
	
	/**
	 * @return La lsite des joueurs.
	 */
	public ArrayList<Joueur> getJoueurs() {
    	return joueurs;
    }
    
	/**
	 * Ajoute un joueur à la liste des joueurs.
	 * 
	 * @param joueur Le joueur à ajouter à la liste.
	 */
    public void addJoueur(Joueur joueur) {
    	if(joueur != null) {
    		this.joueurs.add(joueur);
    	}
    }
}
