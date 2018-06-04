package modele.carte;

public class SacSable extends CarteTresor {
    
    private CarteTresor sac;
    
    public SacSable(CarteTresor sac){
        this.sac = sac;
    }

    public CarteTresor getSac() {
        return sac;
    }

    public void setSac(CarteTresor sac) {
        this.sac = sac;
    }
    
    
}