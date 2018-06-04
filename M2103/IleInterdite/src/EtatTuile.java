public enum EtatTuile {
    ASSECHEE,
    INONDEE,
    COULEE;

    private String libelle;

    /**
     * 
     * @param libelle
     */

    private EtatTuile(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}