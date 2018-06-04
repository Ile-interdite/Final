package modele.carte;

public class CTrésor extends CarteTresor {
    
    private CarteTresor tresor;
    
    public CTrésor(CarteTresor tresor){
        this.tresor = tresor;
    }

    public CarteTresor getTresor() {
        return tresor;
    }

    public void setTresor(CarteTresor tresor) {
        this.tresor = tresor;
    }
    
    
}