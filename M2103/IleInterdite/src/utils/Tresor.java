package utils;

public enum Tresor {
    PIERRE_SACREE("Pierre Sacrée", 1),
    STATUE_ZEPHIR("Statue Zéphir", 2),
    CRISTAL_ARDENT("Cristal Ardent", 3),
    CALICE_ONDE("Calice Onde", 4);

    private String libelle;
    private int num;
    
    private Tresor(String libelle, int num) {
        this.setLibelle(libelle);
        this.setNum(num);
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
    
    public int getNum() {
    	return num;
    }
    
    public void setNum(int num) {
    	this.num = num;
    }
}
