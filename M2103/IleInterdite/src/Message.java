public class Message {

    private TypeMessage typeMessage;
    private Joueur joueur;
    private CarteTresor carteTresor;
    private Tuile tuileCible;
        
    public Message(TypeMessage typeMessage) {
        this.setTypeMessage(typeMessage);
    }
        
    public Message(TypeMessage typeMessage, Joueur joueur) {
        this.setTypeMessage(typeMessage);
        this.setJoueur(joueur);
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
        
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
        
    public Joueur getJoueur() {
        return joueur;
    }
        
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public CarteTresor getCarteTresor() {
        return carteTresor;
    }

    public void setCarteTresor(CarteTresor carteTresor) {
        this.carteTresor = carteTresor;
    }

    public Tuile getTuileCible() {
        return tuileCible;
    }

    public void setTuileCible(Tuile tuileCible) {
        this.tuileCible = tuileCible;
    }      
}