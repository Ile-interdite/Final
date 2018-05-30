public enum Pion {
	ROUGE,
	VERT,
	BLEU,
	ORANGE,
	VIOLET;

	private string libelle;
	private Color couleur;

	/**
	 * 
	 * @param libelle
	 * @param couleur
	 */
	private Pion(string libelle, Color couleur) {
		// TODO - implement Pion.Pion
		throw new UnsupportedOperationException();
	}

	public string toString() {
		// TODO - implement Pion.toString
		throw new UnsupportedOperationException();
	}

	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * 
	 * @param name
	 */
	Pion getFromName(string name) {
		// TODO - implement Pion.getFromName
		throw new UnsupportedOperationException();
	}

}