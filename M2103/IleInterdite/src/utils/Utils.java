package utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import modele.aventurier.Aventurier;
import view.plateau.jeu.VueJeu;

public class Utils {
 
    public static enum EtatTuile {
        ASSECHEE("Asséchée"), 
        INONDEE("Inondée"),
        COULEE("Coulée");

        private String libelle;
        
        private EtatTuile(String libelle) {
            this.setLibelle(libelle);
        }

        @Override
        public String toString() {
            return this.getLibelle();
        }
        
        public String getLibelle() {
        	return libelle;
        }
        
        private void setLibelle(String libelle) {
        	this.libelle = libelle;
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55,194,198)),
        ORANGE("Orange", new Color(255, 148, 0)),
        VIOLET("Violet", new Color(204, 94, 255)),
        JAUNE("Jaune", new Color(255, 255, 0)) ;    

        private String libelle;
        private Color couleur;

        private Pion (String libelle, Color couleur) {
            this.setLibelle(libelle);
            this.setCouleur(couleur);
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }
        
        private void setCouleur(Color couleur) {
        	this.couleur = couleur;
        }
        
        public String getLibelle() {
        	return libelle;
        }
        
        private void setLibelle(String libelle) {
        	this.libelle = libelle;
        }
        
        public static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) return ROUGE;
            else if (VERT.name().equals(name)) return VERT;
            else if (BLEU.name().equals(name)) return BLEU;
            else if (ORANGE.name().equals(name)) return ORANGE;
            else if (VIOLET.name().equals(name)) return VIOLET;
            else if (JAUNE.name().equals(name)) return JAUNE;
            return null;
        }
    }

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList;
    }
    
    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
    
    public static void sendMessage(String message) {
    	VueJeu.setLabelInfoText(message);
    }
}
