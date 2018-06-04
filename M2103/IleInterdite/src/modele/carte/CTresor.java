package modele.carte;

import utils.Tresor;

public class CTresor extends CarteTresor {
    
    private Tresor tresor;
   
    
    public CTresor(Tresor tresor){
        this.tresor = tresor;
    }

    public Tresor getTresor() {
        return tresor;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    
    
}