package controller;

import static utils.Tresor.CALICE_ONDE;
import static utils.Tresor.CRISTAL_ARDENT;
import static utils.Tresor.PIERRE_SACREE;
import static utils.Tresor.STATUE_ZEPHIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
import modele.carte.SacDeSable;
import utils.Mode;
import utils.Tresor;
import utils.Utils;
import utils.Utils.EtatTuile;
import view.VueDonnerCarte;
import view.VueFin;
import view.VuePlateau;
import view.VueSelection;

public class Controleur implements Observateur {

	private static Controleur controleur;
	
	public static void main(String[] args) {
		new Controleur();
	}
	
	public static Controleur getInstance() {
		return controleur;
	}
	
    private int niveauEau = 0;
    private Grille grille;
    private boolean partieActive;
    private Joueur joueurCourant;

    private VuePlateau vuePlateau;
    private VueSelection vueSelect;
    
    //Collections
    private ArrayList<Tresor> tresorPossedes = new ArrayList<>();
    private Stack<CarteTresor> pileTresor = new Stack<>();
    private ArrayList<CarteTresor> defausseTresor = new ArrayList<>();
    private Stack<CarteInondation> pileInondation = new Stack<>();
    private ArrayList<CarteInondation> defausseInondation = new ArrayList<>();
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();
    private ArrayList<Joueur> joueurs = new ArrayList<>();

    
    public Controleur() {
        controleur = this;
        vueSelect = new VueSelection();
        vueSelect.setObservateur(this);        
    }


    public void createAventuriers() {
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

    public void createCartes() {
        for(Tuile tuile : this.getGrille().getAlTuiles()) {
            this.getPileInondation().push(new CarteInondation(tuile));
        }
        
        for (int i = 0; i < 5; i++) {
            this.getPileTresor().push(new CTresor(PIERRE_SACREE));
        }
        for (int i = 0; i < 5; i++) {
            this.getPileTresor().push(new CTresor(CALICE_ONDE));
        }
        for (int i = 0; i < 5; i++) {
            this.getPileTresor().push(new CTresor(CRISTAL_ARDENT));
        }
        for (int i = 0; i < 5; i++) {
            this.getPileTresor().push(new CTresor(STATUE_ZEPHIR));
        }
        for (int i = 0; i < 3; i++) {
            this.getPileTresor().push(new CMDE("Montée des eaux"));
        }
        for (int i = 0; i < 2; i++) {
            this.getPileTresor().push(new SacDeSable("Sacs de sable"));
        }
        for (int i = 0; i < 3; i++) {
            this.getPileTresor().push(new Helicoptere("Hélicoptère"));
        }
        Collections.shuffle(this.getPileInondation());
        Collections.shuffle(this.getPileTresor());
    }
    
    public void initialiserJeu(List<String> nomsJoueurs) {
    	//============================================
        // Initialisation de la grille et des tuiles
        //============================================
    	this.setGrille(new Grille());
    	//============================================
        // Intialisation des aventuriers
        //============================================
    	this.createAventuriers();
    	//============================================
        // Intialisation des cartes
        //============================================
    	this.createCartes();
    	this.tirerCarteInondation(6);
    	//============================================
        // Intialisation des joueurs
        //============================================
    	for (int i = 0; i < nomsJoueurs.size(); i++) {
            Joueur joueur = new Joueur(nomsJoueurs.get(i));
            Aventurier role = this.getAventuriers().get(i);
            joueur.setRole(role);
            role.spawn();
            this.addJoueur(joueur);
            //============================================
            // Distribution des cartes "Trésor"
            //============================================
            this.tirerCarteTresor(joueur);
        }
    	this.lancerPartie();
    }
    
    public void lancerPartie() {
    	this.setPartieActive(true);
    	Joueur joueur = this.getJoueurs().get(0);
    	this.setJoueurCourant(joueur);
    	vuePlateau = new VuePlateau();
    	Utils.sendMessage("Début du tour de jeu du joueur : " + Controleur.getInstance().getJoueurCourant().getName());
    }
    
    public void nextPlayer() {
    	Joueur joueur = this.getJoueurCourant();
    	int numJoueur = this.getJoueurs().indexOf(joueur);
    	joueur.setPointsAction(3);
    	numJoueur = numJoueur == this.getJoueurs().size() - 1 ? 0 : numJoueur + 1;
    	this.setJoueurCourant(this.getJoueurs().get(numJoueur));

    	Utils.sendMessage("Début du tour de jeu du joueur : " + Controleur.getInstance().getJoueurCourant().getName());
    	vuePlateau.setMode(Mode.NORMAL);
    }

    @Override
    public void traiterMessage(Message m) {
        if (m != null && m.getTypeMessage() != null) {
            Joueur joueur = this.getJoueurCourant();

            switch (m.getTypeMessage()) {
            	case COMMENCER_PARTIE:                        
            		vueSelect.dispose();
                    setNiveauEau(m.getDifficulte());
            		this.initialiserJeu(m.getNomsJoueurs());
            		break;
            	case FIN_TOUR:
            		this.tirerCarteInondation();
                    this.isPartieActive();
                    
                    if (!getPartieActive()){
                        new VueFin();
                    } else {
                    	this.nextPlayer();                    	
                    }
            		break;
                case UTILISER_CARTE:
                	CarteTresor carte = m.getCarteTresor();
                	
                    if (carte != null) {
                    	Tuile targetTuile = m.getTargetTuile();
                    	
                    	if(targetTuile != null) {
                    		this.getJoueurCourant().utiliserCarteTresor(carte, targetTuile);
                    	} else {
                    		this.getVuePlateau().setMode(m.getCarteTresor() instanceof Helicoptere ? Mode.DEPLACEMENT_SPECIAL : Mode.ASSECHEMENT_SPECIAL);
                    	}
                    } else {
                        throw new Error();
                    }
                    break;
                case DEFAUSSER_CARTE:
                    if (m.getCarteTresor() != null) {
                        this.getJoueurCourant().defausserCarteTresor(m.getCarteTresor());
                    } else {
                        throw new Error();
                    }
                    break;
                case FIN_PARTIE:
                    System.exit(0);
                    break;
                    
                default:
                	int pointsAction = joueur.getPointsAction();
                	Tuile targetTuile = m.getTargetTuile();
                	
                    if (pointsAction > 0) {
                        switch (m.getTypeMessage()) {
                            case DEPLACEMENT:                            	
                            	if(targetTuile != null) {
                            		joueur.getRole().seDeplacer(targetTuile);
                            		vuePlateau.setMode(Mode.NORMAL);
                            	} else {
                            		vuePlateau.setMode(Mode.DEPLACEMENT);
                            		Utils.sendMessage("Sélectionnez une tuile pour déplacer votre pion");
                            	}
                                break;
                            case ASSECHEMENT:                            	
                            	if(targetTuile != null) {
                            		joueur.getRole().assecher(targetTuile);
                            		vuePlateau.setMode(Mode.NORMAL);
                            	} else {
                            		vuePlateau.setMode(Mode.ASSECHEMENT);
                            		Utils.sendMessage("Sélectionnez une tuile à assécher");
                            	}
                                break;
                            case DONNER_CARTE:
                                if (m.getCarteTresor() != null && m.getJoueurCible() != null) {
                                    joueur.donnerCarteTresor(m.getCarteTresor(), m.getJoueurCible());
                                } else {
                                    new VueDonnerCarte();
                                }
                                break;
                            case RECUPERER_TRESOR:
                                if (m.getTresor() != null) {
                                    ArrayList<CarteTresor> cartesJoueur = joueur.getCartesTresor();
                                    int nbreCarteTresor = 0;

                                    for (CarteTresor ct : cartesJoueur) {
                                        if (ct instanceof CTresor) {
                                            CTresor cTresor = (CTresor) ct;

                                            if (cTresor.getTresor() == m.getTresor()) {
                                                nbreCarteTresor++;
                                            }
                                        }
                                    }

                                    if (nbreCarteTresor >= 4) {
                                        this.addTresorPossedes(m.getTresor());
                                    } else {
                                        throw new Error();
                                    }
                                } else {
                                    throw new Error();
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
            vuePlateau.refresh();
        }
    }

    
    public Tuile getTuile(int x, int y) {
        return this.getGrille().getTuiles()[x][y];
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

    public void isPartieActive() {
        if (this.getNiveauEau() >= 10) {
            setPartieActive(false);
        } else {
            int nbPierreSacreeCoulee = 0;
            int nbStatueZephirCoulee = 0;
            int nbCristalArdentCoulee = 0;
            int nbCaliceOndeCoulee = 0;

            Iterator<Tuile> iteratorTuile = this.getGrille().getAlTuiles().iterator();

            while (iteratorTuile.hasNext() && getPartieActive()) {
                Tuile tuile = iteratorTuile.next();

                if (tuile.getEtatTuile() == EtatTuile.COULEE) {
                    if (tuile.getNom().equals("Héliport")) {
                        setPartieActive(false);
                    } else {
                        Tresor tresor = tuile.getTresor();

                        if (tresor != null) {
                            switch (tuile.getTresor()) {
                                case PIERRE_SACREE:
                                    nbPierreSacreeCoulee++;
                                    break;
                                case STATUE_ZEPHIR:
                                    nbStatueZephirCoulee++;
                                    break;
                                case CRISTAL_ARDENT:
                                    nbCristalArdentCoulee++;
                                    break;
                                case CALICE_ONDE:
                                    nbCaliceOndeCoulee++;
                                    break;
                                default:
                                    break;
                            }

                            if (nbPierreSacreeCoulee == 2 || nbStatueZephirCoulee == 2
                                || nbCristalArdentCoulee == 2 || nbCaliceOndeCoulee == 2) {
                                    setPartieActive(false);
                            }
                        }
                    }
                }
            }

            if (getPartieActive()) {
                Iterator<Joueur> iteratorJoueur = this.getJoueurs().iterator();

                while (iteratorJoueur.hasNext() && getPartieActive()) {
                    Joueur joueur = iteratorJoueur.next();
                    Aventurier role = joueur.getRole();

                    if (role.getDeplacement(role.getTuileCourante()).isEmpty() && role.getTuileCourante().getEtatTuile() == EtatTuile.COULEE) {
                        setPartieActive(false);
                    }
                }
            }
        }
    }

    public boolean getPartieActive() {
        return partieActive;
    }

    public void setPartieActive(boolean partieActive) {
        this.partieActive = partieActive;
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

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    private void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * @return La liste des trésors possédés par les joueurs.
     */
    public ArrayList<Tresor> getTresorPossedes() {
        return tresorPossedes;
    }

    /**
     * Ajoute un trésor à la liste des trésors possédés si les joueurs ne le
     * possèdent pas déjà.
     *
     * @param tresor Le trésor à ajouter.
     */
    public void addTresorPossedes(Tresor tresor) {
        if (tresor != null && !this.tresorPossedes.contains(tresor)) {
            this.tresorPossedes.add(tresor);
        }
    }

    /**
     * Retire un trésor à la liste des trésors possédés si les joueurs le
     * possèdent déjà.
     *
     * @param tresor Le trésor à retirer.
     */
    public void removeTresorPossedes(Tresor tresor) {
        if (tresor != null && this.getTresorPossedes().contains(tresor)) {
            this.getTresorPossedes().remove(tresor);
        }
    }

    /**
     * @return La liste des cartes "Trésor" dans la pile associée.
     */
    public Stack<CarteTresor> getPileTresor() {
        return pileTresor;
    }

    /**
     * Ajoute une carte "Trésor" à la pile associée.
     *
     * @param carteTresor La carte "Trésor" à ajouter à la pile.
     */
    public void addPileTresor(CarteTresor carteTresor) {
        if (carteTresor != null) {
            this.getPileTresor().push(carteTresor);
            Collections.shuffle(this.getPileTresor());
        }
    }

    /**
     * Retire une carte "Trésor" à la pile associée.
     *
     * @param carteTresor La carte "Trésor" à retirer de la pile.
     */
    public void removePileTresor(CarteTresor carteTresor) {
        if (carteTresor != null) {
            this.getPileTresor().remove(carteTresor);
        }
    }

    /**
     * @return La carte au sommet de la pile
     */
    public CarteTresor popCarteTresor() {
        CarteTresor carteTresor = null;

        if (!this.getPileTresor().isEmpty()) {
            carteTresor = this.getPileTresor().pop();
        } else {
            if (!this.getDefausseTresor().isEmpty()) {
                Collections.shuffle(this.getDefausseTresor());

                for (CarteTresor ct : this.getDefausseTresor()) {
                    this.getPileTresor().push(ct);
                }
                this.getDefausseTresor().clear();
            }
        }
        return carteTresor;
    }

    /**
     * @return La liste des cartes "Trésor" dans la défausse associée.
     */
    public ArrayList<CarteTresor> getDefausseTresor() {
        return defausseTresor;
    }

    /**
     * Ajoute une carte "Tr�sor" à la défausse associée.
     *
     * @param carteTresor La carte "Trésor" à ajouter à la défausse.
     */
    public void addDefausseTresor(CarteTresor carteTresor) {
        if (carteTresor != null) {
            this.defausseTresor.add(carteTresor);
        }

    }

    /**
     * @return La liste des cartes "Inondation" dans la pile associée.
     */
    public Stack<CarteInondation> getPileInondation() {
        return pileInondation;
    }

    /**
     * Ajoute une carte "Inondation" à la pile associée.
     *
     * @param carteInondation La carte "Inondation" à ajouter à la pile.
     */
    public void addPileInondation(CarteInondation carteInondation) {
        if (carteInondation != null) {
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
        if (carteInondation != null && !this.getDefausseInondation().contains(carteInondation)) {
            this.getDefausseInondation().add(carteInondation);
        }
    }
    
    public void removeDefausseInondation(CarteInondation carteInondation) {
        if (carteInondation != null && this.getDefausseInondation().contains(carteInondation)) {
            this.getDefausseInondation().remove(carteInondation);
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
        if (aventurier != null) {
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
        if (joueur != null) {
            this.getJoueurs().add(joueur);
        }
    }

    public void tirerCarteTresor(Joueur j) {
        int nbCMDE = 0;
        for (int i = 0; i < 5; i++) {
            CarteTresor carte = popCarteTresor();
            if (carte instanceof CMDE) {
                ///LE CODE SUIVANT EST DEGEU... SI POSSIBLE TROUVER UN AUTRE MOYEN
                CMDE c = (CMDE) carte;
                nbCMDE ++ ;
                c.utiliserCarte(nbCMDE);
                this.addDefausseTresor(carte);
            } else {
                j.addCarteTresor(carte);
            }
            this.getPileTresor().remove(carte);
        }
        
    }

    public void tirerCarteInondation() {
    	int nbCartes = 0;
    	if(this.getNiveauEau() >= 0 && this.getNiveauEau() <= 1) {
    		nbCartes = 2;
    	} else if(this.getNiveauEau() >= 2 && this.getNiveauEau() <= 4) {
    		nbCartes = 3;
    	} else if(this.getNiveauEau() >= 5 && this.getNiveauEau() <= 6) {
    		nbCartes = 4;
    	} else if(this.getNiveauEau() >= 7 && this.getNiveauEau() <= 8) {
    		nbCartes = 5;
    	}
    	this.tirerCarteInondation(nbCartes);
    }
    
    public void tirerCarteInondation(int nbCartes) {
        for(int i = 0; i < nbCartes; i++) {
        	if(!this.getPileInondation().isEmpty()) {
        		CarteInondation carte = getPileInondation().lastElement();
        		this.getPileInondation().remove(carte);
        		this.addDefausseInondation(carte);
        		carte.utiliserCarte();        		
        	} else {
        		CarteInondation carte = null;
        		
        		while(!this.getDefausseInondation().isEmpty()) {
        			carte = this.getDefausseInondation().get(0);
        			this.removeDefausseInondation(carte);
        			this.addPileInondation(carte);
        		}
        		//On ajoute un tour de boucle supplémentaire
        		nbCartes++;
        	}
        }
    }
    
    public HashMap<Integer, HashMap<CarteTresor, Integer>> getCartesTriees(Joueur joueur) {
		HashMap<Integer, HashMap<CarteTresor, Integer>> cartes = new HashMap<>();
		ArrayList<CarteTresor> cartesTresor = new ArrayList<>();
		
		for(CarteTresor carte : joueur.getCartesTresor()) {
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

