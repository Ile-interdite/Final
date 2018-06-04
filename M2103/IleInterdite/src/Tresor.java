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
		// TODO - implement Tresor.Tresor
		this.libelle=libelle;
	}

        @Override
	public String toString() {
		// TODO - implement Tresor.toString
                return libelle;
	}

}