package controller;

import utils.Tresor;
import modele.carte.CarteTresor;
import modele.carte.CarteInondation;
import controller.Observateur;
import controller.Message;
import view.VuePlateau;
import java.util.*;
import modele.*;
import modele.aventurier.*;
import modele.carte.*;
import static utils.Tresor.*;

public class Controleur implements Observateur {

	VuePlateau vuePlateau;
	private Collection<Tresor> tresorPossedes;
	private int niveauEau;
	private boolean etatPartie;
        private Grille grille;
        private Joueur joueurs[] = new Joueur[4]; 
        private ArrayList<CarteTresor> defausseTresor;
        private ArrayList<CarteTresor> pileTresor;
        private ArrayList<CarteInondation> pileInond[];
        private ArrayList<CarteInondation> defausseInond;
        private ArrayList<Aventurier> alAventuriers;
	public void seDeplacer() {
		// TODO - implement Controleur.seDeplacer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numJoueur
	 */
	public Joueur getJoueur(int numJoueur) {
		// TODO - implement Controleur.getJoueur
		throw new UnsupportedOperationException();
	}
        
        public void createAventuriers(){
            Pilote pilote = new Pilote();
            Plongeur plongeur = new Plongeur();
            Explorateur explorateur = new Explorateur();
            Messager messager = new Messager();
            Ingenieur ingenieur = new Ingenieur();
            
            alAventuriers.add(pilote);
            alAventuriers.add(plongeur);
            alAventuriers.add(explorateur);
            alAventuriers.add(messager);
            alAventuriers.add(ingenieur);
            
            Collections.shuffle(alAventuriers);
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
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Tuile getTuile(int x, int y) {
		// TODO - implement Controleur.getTuile
		throw new UnsupportedOperationException();
	}

	public void assecher() {
		// TODO - implement Controleur.assecher
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numJoueur
	 */
	public void donnerCarte(int numJoueur) {
		// TODO - implement Controleur.donnerCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 */
	public void choixCarte(CarteTresor carte) {
		// TODO - implement Controleur.choixCarte
		throw new UnsupportedOperationException();
	}

        /**
         * Description méthode
         * 
         * Détails
         * 
         *
         * @return type
         */
	public void initJeux() {
		grille = new Grille();
                createAventuriers();
                createCartes();
                //cree carte innondation
                System.out.println("Nbr de joueur?");
                Scanner scan = new Scanner(System.in);
                int nbJoueur = Integer.parseInt(scan.nextLine());
                for (int i=0; i<nbJoueur; i++){
                    Joueur j = new Joueur("j1");
                    j.setRole(alAventuriers.get(i));
                    joueurs[i]=j;
                    for (int y=0; i<3; i++){
                        j.setCartes(pileTresor.get(pileTresor.size() - 1));
                    }
                }
                System.out.println("Niveau d'eau ?");
                int niveauEau = Integer.parseInt(scan.nextLine());
                setNiveauEau(niveauEau);
                setEtatParti(true);
	}

	public void setNiveauEau(int nvEau) {
             this.niveauEau=nvEau;
	}

	/**
	 * 
	 * @param etat
	 */
	public void setEtatParti(boolean etat) {
            this.etatPartie=etat;
	}
        
        public void utiliserCarte(CarteTresor carte) {
            
        }
        
        public void defausserCarte(CarteTresor carte) {
            
        }

        @Override
        public void traiterMessage(Message m) {
            if(m != null) {
                switch(m.getTypeMessage()) {
                    case UTILISER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();
                        
                         if(carte != null) {
                            utiliserCarte(carte);
                        }
                        break;
                    }
                    case DEFAUSSER_CARTE: {
                        CarteTresor carte = m.getCarteTresor();
                        
                        if(carte != null) {
                            defausserCarte(carte);
                        }
                        break;
                    }
                    case DEPLACEMENT: {
                        Joueur joueur = m.getJoueur();
                        Tuile tuileCible = m.getTuileCible();
                        
                        if(joueur != null) {
                            joueur.deplacer(tuileCible);
                        }
                        break;
                    }
                    case ASSECHEMENT: {
                        Joueur joueur = m.getJoueur();
                        Tuile tuileCible = m.getTuileCible();
                        
                        if(joueur != null) {
                            
                        }
                        break;
                    }
                    case DONNER_CARTE: {
                        Joueur joueur = m.getJoueur();
                        break;
                    }
                    case RECUPERER_TRESOR: {
                        Joueur joueur = m.getJoueur();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

}