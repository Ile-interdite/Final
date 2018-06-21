package utils;

public enum Tresor {
    PIERRE_SACREE("Pierre Sacrée"),
    STATUE_ZEPHIR("Statue Zéphir"),
    CRISTAL_ARDENT("Cristal Ardent"),
    CALICE_ONDE("Calice Onde");

    private String libelle;
    
    private Tresor(String libelle) {
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
