package modele.carte;

import controller.Controleur;
import modele.Tuile;
import utils.Tresor;

public class Carte {
	
	private static int ID = 0;
	
	private static void incrementID() {
		ID++;
	}
	
	private static int getID() {
		incrementID();
		return ID;
	}
	
	public static void createCartes() {
        for(Tuile tuile : Controleur.getInstance().getGrille().getTuiles()) {
        	CarteInondation.addCarteToPile(new CarteInondation(tuile));
        }
        CarteInondation.melangerPile();
        
        for (int i = 0; i < 5; i++) {
            CarteTresor.addCarteToPile(new CTresor(Tresor.PIERRE_SACREE));
        }
        
        for (int i = 0; i < 5; i++) {
        	CarteTresor.addCarteToPile(new CTresor(Tresor.CALICE_ONDE));
        }
        
        for (int i = 0; i < 5; i++) {
        	CarteTresor.addCarteToPile(new CTresor(Tresor.CRISTAL_ARDENT));
        }
        
        for (int i = 0; i < 5; i++) {
        	CarteTresor.addCarteToPile(new CTresor(Tresor.STATUE_ZEPHIR));
        }
        
        for (int i = 0; i < 2; i++) {
        	CarteTresor.addCarteToPile(new CarteMDE("Montée des eaux"));
        }
        
        for (int i = 0; i < 2; i++) {
        	CarteTresor.addCarteToPile(new CarteSacsDeSable("Sacs de sable"));
        }
        
        for (int i = 0; i < 3; i++) {
        	CarteTresor.addCarteToPile(new CarteHelicoptere("Hélicoptère"));
        }
        CarteTresor.melangerPile();
    }

	private String libelle;
	private int uniqueID;
	
	public Carte(String libelle) {
		this.setLibelle(libelle);
		this.setUniqueID(getID());
	}
    
    public String getLibelle() {
    	return libelle;
    }
    
    public void setLibelle(String libelle) {
    	this.libelle = libelle;
    }

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
}
