package utils;

public enum Niveau {
	LEGENDAIRE("Légendaire"),
	ELITE("Elite"),
	NORMAL("Normal"),
	NOVICE("Novice");
	
	private String libelle;
	
	private Niveau(String libelle) {
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
