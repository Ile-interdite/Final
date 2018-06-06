package controller;

import static utils.Tresor.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import modele.*;
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
import modele.carte.SacDeSable;
import utils.Tresor;
import view.VuePlateau;

public class Controleur implements Observateur {

	private int niveauEau;
	private Grille grille;
	private boolean etatPartie;
	
	private static Controleur controleur;
	private VuePlateau vuePlateau;
	
	//Collections
	private ArrayList<Tresor> tresorPossedes = new ArrayList<>();
	private ArrayList<CarteTresor> pileTresor = new ArrayList<>();
	private ArrayList<CarteTresor> defausseTresor = new ArrayList<>();
	private ArrayList<CarteInondation> pileInondation = new ArrayList<>();
	private ArrayList<CarteInondation> defausseInondation = new ArrayList<>();
	private ArrayList<Aventurier> aventuriers = new ArrayList<>();
	private ArrayList<Joueur> joueurs = new ArrayList<>();
	
	public static void main(String[] args) {}
	
	public Controleur() {
		controleur = this;
	}
	
	public static Controleur getInstance() {
		return controleur;
	}
        
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
		CarteInondation c1 = new CarteInondation("Le Pont des Abimes");
		CarteInondation c2 = new CarteInondation("La Porte de Bronze");
		CarteInondation c3 = new CarteInondation("La Caverne des Ombres");
		CarteInondation c4 = new CarteInondation("La Porte de Fer");
		CarteInondation c5 = new CarteInondation("La Porte d’Or");
		CarteInondation c6 = new CarteInondation("Les Falaises de l’Oubli");
		CarteInondation c7 = new CarteInondation("Le Palais de Corail");
		CarteInondation c8 = new CarteInondation("La Porte d’Argent");
		CarteInondation c9 = new CarteInondation("Les Dunes de l’Illusion");
		CarteInondation c10 = new CarteInondation("Heliport");
		CarteInondation c11 = new CarteInondation("La Porte de Cuivre");
		CarteInondation c12 = new CarteInondation("Le Jardin des Hurlements");
		CarteInondation c13 = new CarteInondation("La Foret Pourpre");
		CarteInondation c14 = new CarteInondation("Le Lagon Perdu");
		CarteInondation c15 = new CarteInondation("Le Marais Brumeux");
		CarteInondation c16 = new CarteInondation("Observatoire");
		CarteInondation c17 = new CarteInondation("Le Rocher Fantome");
		CarteInondation c18 = new CarteInondation("La Caverne du Brasier");
		CarteInondation c19 = new CarteInondation("Le Temple du Soleil");
		CarteInondation c20 = new CarteInondation("Le Temple de La Lune");
		CarteInondation c21 = new CarteInondation("Le Palais des Marees");
		CarteInondation c22 = new CarteInondation("Le Val du Crepuscule");
		CarteInondation c23 = new CarteInondation("La Tour du Guet");
		CarteInondation c24 = new CarteInondation("Le Jardin des Murmures");

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
			SacDeSable sac = new SacDeSable();
			pileTresor.add(sac);
		}

		Collections.shuffle(pileTresor);
	}

    public void choixCarte(CarteTresor carte) {
    	// TODO - implement Controleur.choixCarte
    }

	public void initJeux() {
        grille = new Grille();
        createAventuriers();
        createCartes();
        
        System.out.println("Nbr de joueur?");
        Scanner scan = new Scanner(System.in);
        int nbJoueur = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < nbJoueur; i++) {
            Joueur joueur = new Joueur("joueur 1");
            joueur.setRole(aventuriers.get(i));
            joueurs.set(i, joueur);

            for (int y = 0; i < 2; i++) {
                joueur.setCartes(pileTresor.get(pileTresor.size() - 1));
                //ENLEVER LES CARTES DE LA PILE
            }
        }
        System.out.println("Niveau d'eau ?");
        int niveauEau = Integer.parseInt(scan.nextLine());
        setNiveauEau(niveauEau);
        //inondée les Tuiles en conséquence
        setEtatPartie(true);
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
                            joueur.utiliserCarteTresor(carte);
                        }
                        break;
                    }
                    case DEFAUSSER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();

                        if(carte != null) {
                            joueur.defausserCarteTresor(carte);
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
    
    public Tuile getTuile(int x,int y) {
    	return grille.getTuiles()[x][y];
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
	 * @return La liste des tr�sors poss�d�s par les joueurs.
	 */
	public ArrayList<Tresor> getTresorPossedes() {
		return tresorPossedes;
	}

	/**
	 * Ajoute un trésor à la liste des trésors possédés
	 * si les joueurs ne le possèdent pas déjà.
	 * 
	 * @param tresor Le tr�sor � ajouter.
	 */
	public void addTresorPossedes(Tresor tresor) {
		if(tresor != null && !this.tresorPossedes.contains(tresor)) {
			this.tresorPossedes.add(tresor);
		}
	}
	
	/**
	 * Retire un trésor à la liste des trésors possédés
	 * si les joueurs le possèdent déjà.
	 * 
	 * @param tresor Le trésor à retirer.
	 */
	public void removeTresorPossedes(Tresor tresor) {		
		if(tresor != null && this.getTresorPossedes().contains(tresor)) {
			this.getTresorPossedes().remove(tresor);
		}
	}
	
	/**
	 * @return La liste des cartes "Tr�sor" dans la pile associ�e.
	 */
	public ArrayList<CarteTresor> getPileTresor() {
		return pileTresor;
	}
	
	/**
	 * Ajoute une carte "Tr�sor" � la pile associ�e.
	 * 
	 * @param carteTresor La carte "Tr�sor" � ajouter � la pile. 
	 */
	public void addPileTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.pileTresor.add(carteTresor);
		}
	}

	/**
	 * @return La liste des cartes "Tr�sor" dans la d�fausse associ�e.
	 */
	public ArrayList<CarteTresor> getDefausseTresor() {
		return defausseTresor;
	}

	/**
	 * Ajoute une carte "Tr�sor" � la d�fausse associ�e.
	 * 
	 * @param carteTresor La carte "Tr�sor" � ajouter � la d�fausse.
	 */
	public void addDefausseTresor(CarteTresor carteTresor) {
		if(carteTresor != null) {
			this.defausseTresor.add(carteTresor);			
		}
	}

	/**
	 * @return La liste des cartes "Inondation" dans la pile associ�e.
	 */
	public ArrayList<CarteInondation> getPileInondation() {
		return pileInondation;
	}

	/**
	 * Ajoute une carte "Inondation" � la pile associ�e.
	 * 
	 * @param carteInondation La carte "Inondation" � ajouter � la pile.
	 */
	public void addPileInondation(CarteInondation carteInondation) {
		if(carteInondation != null) {
			this.pileInondation.add(carteInondation);
		}
	}

	/**
	 * @return La liste des cartes "Inondation" dans la d�fausse associ�e.
	 */
	public ArrayList<CarteInondation> getDefausseInondation() {
		return defausseInondation;
	}

	/**
	 * Ajoute une carte "Inondation" � la d�fausse associ�e.
	 * 
	 * @param carteInondation La carte "Inondation" � ajouter � la d�fausse.
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
	 * Ajoute un aventurier � la liste des aventuriers.
	 * 
	 * @param aventurier L'aventurier � ajouter � la liste.
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
	 * Ajoute un joueur � la liste des joueurs.
	 * 
	 * @param joueur Le joueur � ajouter � la liste.
	 */
    public void addJoueur(Joueur joueur) {
    	if(joueur != null) {
    		this.joueurs.add(joueur);
    	}
    }
}
