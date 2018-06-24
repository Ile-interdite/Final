package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import modele.aventurier.Aventurier;
import modele.carte.CTresor;
import modele.carte.Carte;
import modele.carte.CarteHelicoptere;
import modele.carte.CarteInondation;
import modele.carte.CarteTresor;
import utils.Mode;
import utils.Tresor;
import utils.Utils;
import view.VueDonnerCarte;
import view.VueFin;
import view.VuePlateau;
import view.VueSelection;

public class Controleur implements Observateur {

	private static Controleur instance;
	
	public static void main(String[] args) {
		new Controleur();
	}
	
	public static Controleur getInstance() {
		return instance;
	}
	
    private int niveauEau = 0;
    private boolean partieActive = false;
    private String raisonFinPartie = "";
    private Grille grille;
    private Joueur joueurCourant;    
    private VueSelection vueSelection;
    private ArrayList<Tresor> tresorsPossedes = new ArrayList<>();

    
    public Controleur() {
        instance = this;
        vueSelection = new VueSelection();
    }
    
    public void initialiserJeu(List<String> nomsJoueurs) {
    	//============================================
        // Initialisation de la grille et des tuiles
        //============================================
    	this.setGrille(new Grille());
    	//============================================
        // Intialisation des aventuriers
        //============================================
    	Aventurier.createAventuriers();
    	//============================================
        // Intialisation des cartes
        //============================================
    	Carte.createCartes();
    	this.piocherCartesInondation(6);
    	//============================================
        // Intialisation des joueurs
        //============================================
    	Joueur.initialiserJoueurs(nomsJoueurs);
    	this.lancerPartie();
    }
    
    public void lancerPartie() {
    	this.setPartieActive(true, "");
    	Joueur joueur = Joueur.getJoueurs().get(0);
    	this.setJoueurCourant(joueur);
    	new VuePlateau();
    	Utils.sendMessage("Début du tour de jeu du joueur : " + Controleur.getInstance().getJoueurCourant().getNom());
    }
    
    public void finirTour() {
    	this.getJoueurCourant().piocherCarte(2);
    	this.piocherCartesInondation();
    	
    	if(isPartieActive()) {
    		this.joueurSuivant();    		
    	} else {
    		new VueFin();    		
    	}
    }
    
    public void joueurSuivant() {
    	Joueur joueur = this.getJoueurCourant();
    	joueur.setPointsAction(3);
    	int numJoueur = Joueur.getJoueurs().indexOf(joueur);
    	numJoueur = (numJoueur == Joueur.getJoueurs().size() - 1) ? 0 : numJoueur + 1;
    	this.setJoueurCourant(Joueur.getJoueurs().get(numJoueur));

    	Utils.sendMessage("Début du tour de jeu du joueur : " + Controleur.getInstance().getJoueurCourant().getNom());
    	VuePlateau.getInstance().setMode(Mode.NORMAL);
    }

    @Override
    public void traiterMessage(Message m) {
        if (m != null && m.getTypeMessage() != null) {
        	TypeMessage typeMessage = m.getTypeMessage();
            Joueur joueur = this.getJoueurCourant();
            CarteTresor carte = m.getCarteTresor();
            Tuile targetTuile = m.getTargetTuile();

            switch (typeMessage) {
            	case COMMENCER_PARTIE:
            		vueSelection.dispose();
                    this.setNiveauEau(m.getDifficulte());
            		this.initialiserJeu(m.getNomsJoueurs());
            		break;
            	case FIN_TOUR:
                    this.finirTour();
            		break;
                case UTILISER_CARTE:
                	if(targetTuile != null) {
                		this.getJoueurCourant().utiliserCarte(carte, targetTuile);
                	} else {
                		VuePlateau.getInstance().setMode(carte instanceof CarteHelicoptere ? Mode.DEPLACEMENT_SPECIAL : Mode.ASSECHEMENT_SPECIAL);
                	}
                    break;
                case DEFAUSSER_CARTE:
                    this.getJoueurCourant().defausserCarte(carte);
                    break;
                case FIN_PARTIE:
                    System.exit(0);
                    break;          
                default:
                	int pointsAction = joueur.getPointsAction();
                	Tresor tresor = m.getTresor();
                	
                    if (pointsAction > 0) {
                        switch (typeMessage) {
                            case DEPLACEMENT:                            	
                            	if(targetTuile != null) {
                            		joueur.getAventurier().seDeplacer(targetTuile);
                            		VuePlateau.getInstance().setMode(Mode.NORMAL);
                            	} else {
                            		VuePlateau.getInstance().setMode(Mode.DEPLACEMENT);
                            		Utils.sendMessage("Sélectionnez une tuile pour déplacer votre pion");
                            	}
                                break;
                            case ASSECHEMENT:                            	
                            	if(targetTuile != null) {
                            		joueur.getAventurier().assecher(targetTuile);
                            		VuePlateau.getInstance().setMode(Mode.NORMAL);
                            	} else {
                            		VuePlateau.getInstance().setMode(Mode.ASSECHEMENT);
                            		Utils.sendMessage("Sélectionnez une tuile à assécher");
                            	}
                                break;
                            case DONNER_CARTE:
                                if (m.getCarteTresor() != null && m.getJoueurCible() != null) {
                                    joueur.donnerCarte(m.getCarteTresor(), m.getJoueurCible());
                                } else {
                                    new VueDonnerCarte();
                                }
                                break;
                            case RECUPERER_TRESOR:
                                ArrayList<CarteTresor> cartes = joueur.getCartes();
                                int i = 0;
                                int nbCartes = 0;
                                
                                while(!cartes.isEmpty() && nbCartes < 4) {
                                	CarteTresor carteTresor = cartes.get(i);
                                	
                                	if(carteTresor instanceof CTresor) {
                                		CTresor cTresor = (CTresor) carteTresor;
                                		
                                		if(cTresor.getTresor() == tresor) {
                                			nbCartes++;
                                		}
                                	}
                                }

                                if(nbCartes >= 4) {
                                    this.addTresorPossede(tresor);
                                } else {
                                	Utils.afficherInformation("Nombre de cartes trésor insuffisant");
                                }
                                break;
                            default:
                                break;
                        }
                    } else {
                    	Utils.afficherInformation("Nombre de points d'action insuffisant");
                    }
                    break;
            }
        }
    }

    /**
     * @return Le niveau d'eau.
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * Définit le niveau d'eau.
     *
     * @param niveauEau Le niveau d'eau.
     */
    public void setNiveauEau(int niveauEau) {
        this.niveauEau = niveauEau;
        
        if(VuePlateau.getInstance() != null) {
        	VuePlateau.getInstance().getVueJeu().getVueNiveau().repaint();
        }
               
        if(niveauEau >= 9) {
        	this.setPartieActive(false, "Niveau d'eau trop haut");
        }
    }
    
    public boolean isPartieActive() {
    	return partieActive;
    }
    
    public void setPartieActive(boolean partieActive, String raisonFinPartie) {
    	this.partieActive = partieActive;
    	this.setRaisonFinPartie(raisonFinPartie);
    }
    
    public String getRaisonFinPartie() {
    	return raisonFinPartie;
    }
    
    public void setRaisonFinPartie(String raisonFinPartie) {
    	this.raisonFinPartie = raisonFinPartie;
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
    
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    private void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * @return La liste des trésors possédés par les joueurs.
     */
    public ArrayList<Tresor> getTresorsPossedes() {
        return tresorsPossedes;
    }

    /**
     * Ajoute un trésor à la liste des trésors possédés si les joueurs ne le
     * possèdent pas déjà.
     *
     * @param tresor Le trésor à ajouter.
     */
    public void addTresorPossede(Tresor tresor) {
    	this.getTresorsPossedes().add(tresor);
    }

    public void piocherCartesInondation() {
    	int nombre = 0;
    	if(this.getNiveauEau() >= 0 && this.getNiveauEau() <= 1) {
    		nombre = 2;
    	} else if(this.getNiveauEau() >= 2 && this.getNiveauEau() <= 4) {
    		nombre = 3;
    	} else if(this.getNiveauEau() >= 5 && this.getNiveauEau() <= 6) {
    		nombre = 4;
    	} else if(this.getNiveauEau() >= 7 && this.getNiveauEau() <= 8) {
    		nombre = 5;
    	}
    	this.piocherCartesInondation(nombre);
    }
    
    public void piocherCartesInondation(int nombre) {
        for(int i = 0; i < nombre; i++) {
        	CarteInondation.piocher();
        }
    }
    
    public HashMap<Integer, HashMap<CarteTresor, Integer>> getCartesTriees(Joueur joueur) {
		HashMap<Integer, HashMap<CarteTresor, Integer>> cartes = new HashMap<>();
		ArrayList<CarteTresor> cartesTresor = new ArrayList<>();
		
		for(CarteTresor carte : joueur.getCartes()) {
			cartesTresor.add(carte);
		}
		int i = 0;
				
		while(!cartesTresor.isEmpty()) {
			int nbOccurence = 0;
			CarteTresor carte = cartesTresor.get(0);
			cartesTresor.remove(carte);
			boolean trouve = true;
			
			while(trouve) {
				nbOccurence++;
				trouve = false;
				
				if(!cartesTresor.isEmpty()) {
					CarteTresor carteTresor = cartesTresor.get(0);
					
					if(carteTresor.getLibelle().equals(carte.getLibelle())) {
						trouve = true;
						cartesTresor.remove(carteTresor);
					}
				}
			}
			HashMap<CarteTresor, Integer> ct = new HashMap<>();
			ct.put(carte, nbOccurence);
			cartes.put(i, ct);
			i++;
		}
		return cartes;
	}
}