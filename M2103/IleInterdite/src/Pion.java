
import java.awt.Color;

public enum Pion {
    ROUGE,
    VERT,
    BLEU,
    ORANGE,
    VIOLET;

    private String libelle;
    private Color couleur;

    /**
    * 
    * @param libelle
    * @param couleur
    */
    
    private Pion(String libelle, Color couleur) {
        this.setLibelle(libelle);
        this.couleur = couleur;
    }

    public String getLibelle() {
        return libelle;
    }

    private void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Color getCouleur() {
        return couleur;
    }

    private void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
        
    @Override
    public String toString() {
        return libelle;
    }

    /**
     * 
     * @param name
     */
    public Pion getFromName(String name) {
        Pion pion = null;
        
        for(Pion p : values()) {
            if(p.getLibelle().equalsIgnoreCase(name)) {
                pion = p;
                break;
            }
        }
        return pion;
    }

}