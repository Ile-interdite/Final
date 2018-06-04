public enum Tresor {

    PIERRE_SACREE,
    STATUE_ZEPHIR,
    CRISTAL_ARDENT,
    CALICE_ONDE;

    private String libelle;

    /**
     * 
     * @param libelle
     */
    
    private Tresor(String libelle) {
        this.libelle=libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }

}