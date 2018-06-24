package modele.carte;

import utils.Tresor;

public class CTresor extends CarteTresor {
    
    private Tresor tresor;
    
    public CTresor(Tresor tresor){
    	super(tresor.getLibelle());
        this.setTresor(tresor);
    }

    public Tresor getTresor() {
        return tresor;
    }

    private void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
}