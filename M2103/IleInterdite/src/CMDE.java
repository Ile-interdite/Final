public class CMDE extends CarteTresor {
    
    private CarteTresor cmde;
    
    public CMDE(CarteTresor carte){
        this.cmde = carte;
    }

    public CarteTresor getCarte() {
        return cmde;
    }

    public void setCarte(CarteTresor carte) {
        this.cmde = carte;
    }
    
}