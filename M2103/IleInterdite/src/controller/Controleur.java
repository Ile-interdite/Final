package controller;

import static utils.Tresor.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import modele.Grille;
import modele.Joueur;
import modele.Tuile;
import modele.aventurier.*;
import modele.carte.*;
import utils.Tresor;
import view.*;

public class Controleur implements Observateur {

    private int niveauEau;
    private Grille grille;
    private boolean etatPartie;

    private static Controleur controleur;
    private VuePlateau vuePlateau;

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
        initialiserJeu();
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

        /*TEST*/
        //Collections.shuffle(aventuriers);
    }

    public void createCartes() {
        this.getPileInondation().push(new CarteInondation("Le Pont des Abimes"));
        this.getPileInondation().push(new CarteInondation("La Porte de Bronze"));
        this.getPileInondation().push(new CarteInondation("La Caverne des Ombres"));
        this.getPileInondation().push(new CarteInondation("La Porte de Fer"));
        this.getPileInondation().push(new CarteInondation("La Porte d’Or"));
        this.getPileInondation().push(new CarteInondation("Les Falaises de l’Oubli"));
        this.getPileInondation().push(new CarteInondation("Le Palais de Corail"));
        this.getPileInondation().push(new CarteInondation("La Porte d’Argent"));
        this.getPileInondation().push(new CarteInondation("Les Dunes de l’Illusion"));
        this.getPileInondation().push(new CarteInondation("Heliport"));
        this.getPileInondation().push(new CarteInondation("La Porte de Cuivre"));
        this.getPileInondation().push(new CarteInondation("Le Jardin des Hurlements"));
        this.getPileInondation().push(new CarteInondation("La Foret Pourpre"));
        this.getPileInondation().push(new CarteInondation("Le Lagon Perdu"));
        this.getPileInondation().push(new CarteInondation("Le Marais Brumeux"));
        this.getPileInondation().push(new CarteInondation("Observatoire"));
        this.getPileInondation().push(new CarteInondation("Le Rocher Fantome"));
        this.getPileInondation().push(new CarteInondation("La Caverne du Brasier"));
        this.getPileInondation().push(new CarteInondation("Le Temple du Soleil"));
        this.getPileInondation().push(new CarteInondation("Le Temple de La Lune"));
        this.getPileInondation().push(new CarteInondation("Le Palais des Marees"));
        this.getPileInondation().push(new CarteInondation("Le Val du Crepuscule"));
        this.getPileInondation().push(new CarteInondation("La Tour du Guet"));
        this.getPileInondation().push(new CarteInondation("Le Jardin des Murmures"));

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
            this.getPileTresor().push(new CMDE());
        }
        for (int i = 0; i < 3; i++) {
            this.getPileTresor().push(new Helicoptere());
        }
        for (int i = 0; i < 2; i++) {
            this.getPileTresor().push(new SacDeSable());
        }

        Collections.shuffle(this.getPileInondation());
        Collections.shuffle(this.getPileTresor());
    }

    public void initialiserJeu() {
        grille = new Grille();
        grille.afficher();
        createAventuriers();
        //createCartes();

        for (int i = 0; i < 4; i++) {
            Joueur joueur = new Joueur("joueur" + i);
            joueur.setRole(aventuriers.get(i));
            this.addJoueur(joueur);

            //for (int y = 0; i < 2; i++) {
            //joueur.addCarteTresor(pileTresor.get(pileTresor.size() - 1));
            //ENLEVER LES CARTES DE LA PILE
            //}
        }
        for (Joueur j : this.getJoueurs()) {
            System.out.println(j);
        }

        /*System.out.println("Niveau d'eau ?");
                Scanner scan = new Scanner(System.in);
		int niveauEau = Integer.parseInt(scan.nextLine());
		setNiveauEau(niveauEau);*/
        //inondée les Tuiles en conséquence
        setEtatPartie(true);

        /* TEST */
        System.out.println("_________TEST DEPLACEMENT_________");
        Aventurier joueur0 = aventuriers.get(0);
        joueur0.seDeplacer();
        System.out.println("nouvelle coordonée : " + joueur0.getTuile().getPosition().getX() + " ," + joueur0.getTuile().getPosition().getY());
        System.out.println();

        System.out.println("_________TEST ASSECHEMENT_________");
        /*POUR CE TEST : Collections.shuffle(aventuriers); a ete mis en comcom*/
        joueur0.assecher();
    }

    @Override
    public void traiterMessage(Message m) {
        if (m != null) {
            Joueur joueur = m.getJoueur();

            if (joueur != null) {
                switch (m.getTypeMessage()) {
                    case UTILISER_CARTE:
                        if (m.getCarteTresor() != null) {
                            joueur.utiliserCarteTresor(m.getCarteTresor());
                        }
                        break;
                    case DEFAUSSER_CARTE:
                        if (m.getCarteTresor() != null) {
                            joueur.defausserCarteTresor(m.getCarteTresor());
                        }
                        break;
                    case DEPLACEMENT:
                        if (m.getTuileCible() != null) {

                        }
                        break;
                    case ASSECHEMENT:
                        if (m.getTuileCible() != null) {

                        }
                        break;
                    case DONNER_CARTE:
                        if (m.getCarteTresor() != null && m.getJoueurCible() != null) {
                            joueur.donnerCarteTresor(m.getCarteTresor(), m.getJoueurCible());
                        }
                        break;
                    case RECUPERER_TRESOR:
                        if (m.getTresor() != null) {
                            ArrayList<CarteTresor> cartesJoueur = joueur.getCartesTresor();
                            int nbreCarteTresor = 0;

                            for (CarteTresor ct : cartesJoueur) {
                                if (ct instanceof CTresor) {

                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public Tuile getTuile(int x, int y) {
        return this.getGrille().getTuiles()[x][y];
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
        if (niveauEau >= 2) {
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
}
