package utils;

public enum Tresor {

    PIERRE_SACREE("Pierre Sacrée"),
    STATUE_ZEPHIR("Statue Zéphir"),
    CRISTAL_ARDENT("Cristal Ardent"),
    CALICE_ONDE("Calide Onde");

    private String libelle;

    /**
     * 
     * @param libelle
     */
    private Tresor(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
