package controller;

import static utils.Tresor.CALICE_ONDE;
import static utils.Tresor.CRISTAL_ARDENT;
import static utils.Tresor.PIERRE_SACREE;
import static utils.Tresor.STATUE_ZEPHIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import modele.aventurier.*;
import modele.carte.*;
import utils.Mode;
import utils.Tresor;
import utils.Utils;
import utils.Utils.EtatTuile;
import view.*;

public class Controleur implements Observateur {

    private int niveauEau;
    private Grille grille;
    private boolean partieActive;

    private static Controleur controleur;
    private Scanner scanner = new Scanner(System.in);
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

    public static void main(String[] args) {
        new Controleur();
    }

    public Controleur() {
        controleur = this;
        //this.initialiserJeu(Arrays.asList("titi","tata","toto","tutu"));
        vueSelect = new VueSelection();
        vueSelect.setObservateur(this);        
        this.getScanner().close();
    }

    public static Controleur getInstance() {
        return controleur;
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
            tirerCarteTresor(joueur);
        }
    	this.lancerPartie();
    }

    public void initJeu() {
	//============================================
        // Initialisation de la grille et des tuiles
        //============================================
        this.setGrille(new Grille());        
	//============================================
        // Intialisation des aventuriers
        //============================================
        createAventuriers();
        
	//============================================
        // Intialisation des cartes
        //============================================
        createCartes();
        
        //============================================
        // Intialisation du niveau d'eau
        //============================================
//        int niveauEau = 0;
//
//        while (!(niveauEau >= 1 && niveauEau <= 4)) {
//            System.out.println("Niveau de difficulté (1|2|3|4) : ");
//            niveauEau = this.getScanner().nextInt();
//            this.getScanner().nextLine();
//            setNiveauEau(niveauEau);
//        }
//        
//	//============================================
//        // Intialisation des joueurs
//        //============================================
//        int nbJoueurs = 0;
//
//        while (!(nbJoueurs >= 2 && nbJoueurs <= 4)) {
//            System.out.print("Nombre de joueurs (2 à 4) : ");
//            nbJoueurs = this.getScanner().nextInt();
//            this.getScanner().nextLine();
//        }
//
//        for (int i = 0; i < nbJoueurs; i++) {
//            System.out.print("Nom du joueur n°" + (i + 1) + " : ");
//            String nomJoueur = this.getScanner().nextLine();
//
//            Joueur joueur = new Joueur(nomJoueur);
//            Aventurier role = this.getAventuriers().get(i);
//            joueur.setRole(role);
//            role.spawn();
//            this.addJoueur(joueur);
//            //============================================
//            // Distribution des cartes "Trésor"
//            //============================================
//            tirerCarteTresor(joueur);
//        }
//        
//	//============================================
//        // Affichage des joueurs
//        //============================================
//        for (Joueur j : this.getJoueurs()) {
//            System.out.println(j + "\n");
//            for (CarteTresor t : j.getCartesTresor()){
//                System.out.println("\t"+ t);
//            }
//        }
        
        //============================================
        // Lancement de la partie
        //============================================
        this.setPartieActive(true);
        vuePlateau = new VuePlateau();
        //this.lancerPartie(); 
    }
    
    public void lancerPartie() {
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
        
        tirerCarteInnondation();
        
    	Utils.sendMessage("Début du tour de jeu du joueur : " + Controleur.getInstance().getJoueurCourant().getName());
    	vuePlateau.setMode(Mode.NORMAL);
    }
    
//    public void lancerPartie() {
//        int numJoueur = 0;
//
//        while (this.isPartieActive()) {
//            Joueur joueur = this.getJoueurs().get(numJoueur);
//            this.setJoueurCourant(joueur);
//            boolean finTour = false;
//
//            while (this.isPartieActive() && !finTour) {
//                int reponse = 0;
//
//                while (!(reponse >= 1 && reponse <= 10)) {
//                    System.out.println("==============================");
//                    System.out.println("Joueur : " + joueur.getName() + " (joueur n°" + (numJoueur + 1) + ")");
//                    System.out.println("==============================");
//                    System.out.println("Position actuelle : " + joueur.getRole().getTuileCourante());
//                    System.out.println("Points d'action disponibles : " + joueur.getPointsAction());
//                    System.out.println("Rôle : " + joueur.getRole().getClass().getSimpleName());
//                    System.out.println("==============================");
//                    System.out.println("Action ?");
//                    System.out.println("1 - Déplacement");
//                    System.out.println("2 - Assèchement");
//                    System.out.println("3 - Donner carte \"Trésor\"");
//                    System.out.println("4 - Récupérer trésor");
//                    System.out.println("5 - Défausser carte \"Trésor\"");
//                    System.out.println("6 - Utiliser carte \"Trésor\"");
//                    System.out.println("7 - Afficher grille");
//                    System.out.println("8 - Afficher grille : détails");
//                    System.out.println("9 - Afficher cartes");
//                    System.out.println("10 - Fin de tour");
//                    System.out.println("==============================");
//                    System.out.print("Réponse : ");
//                    reponse = this.getScanner().nextInt();
//
//                    if (!(reponse >= 1 && reponse <= 10)) {
//                        System.out.println("\nErreur : chiffre incorrect\n");
//                    }
//                }
//                Message message = new Message();
//
//                switch (reponse) {
//                    case 1:
//                        message.setTypeMessage(TypeMessage.DEPLACEMENT);
//                        break;
//                    case 2:
//                        message.setTypeMessage(TypeMessage.ASSECHEMENT);
//                        break;
//                    case 3:
//                        message.setTypeMessage(TypeMessage.DONNER_CARTE);
//                        message.setJoueurCible(joueur);
//                        break;
//                    case 4:
//                        message.setTypeMessage(TypeMessage.RECUPERER_TRESOR);
////                        getJoueurCourant()
//                        break;
//                    case 5:
//                        message.setTypeMessage(TypeMessage.DEFAUSSER_CARTE);
//                        break;
//                    case 6:
//                        message.setTypeMessage(TypeMessage.UTILISER_CARTE);
//                        this.afficherCartesTresor();
//                        
//                        if(!this.getJoueurCourant().getCartesTresor().isEmpty()) {
//                            int numCarte = 0;
//                        
//                            while(!(numCarte >= 1 && numCarte <= getJoueurCourant().getCartesTresor().size())) {
//                                System.out.println("Sélectionnez une carte : ");
//                                numCarte = this.getScanner().nextInt();
//                            }
//                            CarteTresor carteTresor = this.getJoueurCourant().getCartesTresor().get(numCarte - 1);
//                            message.setCarteTresor(carteTresor);
//                        } else {
//                            System.out.println("Pas de carte");
//                            reponse = 0;
//                        }
//                        break;
//                    case 7:
//                        this.getGrille().afficherGrille();
//                        break;
//                    case 8:
//                        this.getGrille().afficherGrilleDetail();
//                        break;
//                    case 9:
//                        this.afficherCartesTresor();
//                        break;
//                    case 10: 
//                        //ajouter Message pour IHM
//                        finTour = true;
//                        tirerCarteTresor(joueur);
//                        tirerCarteInnondation();
//                        break;
//                    default:
//                        break;
//                }
//
//                if (reponse >= 1 && reponse <= 6) {
//                    this.traiterMessage(message);
//                }
//            }
//
//            if (this.isPartieActive()) {
//                numJoueur = numJoueur == this.getJoueurs().size() - 1 ? 0 : numJoueur + 1;
//                joueur.setPointsAction(3);
//            }
//             
//        }
//        System.out.println("Partie terminée.");
//    }

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
            		this.nextPlayer();
            		break;
                case UTILISER_CARTE:
                    if (m.getCarteTresor() != null) {
                        this.getJoueurCourant().utiliserCarteTresor(m.getCarteTresor());
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

    public boolean isPartieActive() {
        boolean partieActive = this.getPartieActive();

        if (this.getNiveauEau() >= 10) {
            partieActive = false;
        } else {
            int nbPierreSacreeCoulee = 0;
            int nbStatueZephirCoulee = 0;
            int nbCristalArdentCoulee = 0;
            int nbCaliceOndeCoulee = 0;

            Iterator<Tuile> iteratorTuile = this.getGrille().getAlTuiles().iterator();

            while (iteratorTuile.hasNext() && partieActive) {
                Tuile tuile = iteratorTuile.next();

                if (tuile.getEtatTuile() == EtatTuile.COULEE) {
                    if (tuile.getNom().equals("Heliport")) {
                        partieActive = false;
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
                                partieActive = false;
                            }
                        }
                    }
                }
            }

            if (partieActive) {
                Iterator<Joueur> iteratorJoueur = this.getJoueurs().iterator();

                while (iteratorJoueur.hasNext() && partieActive) {
                    Joueur joueur = iteratorJoueur.next();
                    Aventurier role = joueur.getRole();

                    if (role.getDeplacement(role.getTuileCourante()).isEmpty() && role.getTuileCourante().getEtatTuile() == EtatTuile.COULEE) {
                        partieActive = false;
                    }
                }
            }
        }

        return partieActive;
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

    public Scanner getScanner() {
        return scanner;
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
        if (carteInondation != null) {
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

    public void tirerCarteInnondation() { 
        for (int i = 0; i < getNiveauEau(); i++) {
            CarteInondation carte = getPileInondation().lastElement();
            this.getPileInondation().remove(carte);
            this.addDefausseInondation(carte);
            carte.utiliserCarte();
        }
    }
}

