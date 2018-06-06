package controller;

import java.util.ArrayList;
import java.util.Scanner;

import modele.Joueur;
import modele.Tuile;
import modele.carte.CTresor;
import modele.carte.CarteTresor;
import utils.Tresor;

public class Message {

    private TypeMessage typeMessage;
    private Tresor tresor;
    private Joueur joueur, joueurCible;
    private CarteTresor carteTresor;
    private Tuile tuileCible;

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
        
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
    
    public Tresor getTresor() {
    	return tresor;
    }
    
    public void setTresor(Tresor tresor) {
    	this.tresor = tresor;
    }
        
    public Joueur getJoueur() {
        return joueur;
    }
        
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    public Joueur getJoueurCible() {
    	return joueurCible;
    }
    
    public void setJoueurCible(Joueur joueurCible) {
    	this.joueurCible = joueurCible;
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
    
    public void lancerPartie() {
		this.setPartieActive(true);
		
		int numJoueur = 1;
		
		while(this.isPartieActive()) {
			Joueur joueur = this.getJoueurs().get(numJoueur);
			int reponse = 0;
			
			while(!(reponse >= 1 && reponse <= 6)) {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("==============================");
				System.out.println("Joueur : " + joueur.getName());
				System.out.println("==============================");
				System.out.println("Action ?");
				System.out.println("1 - Déplacement");
				System.out.println("2 - Assèchement");
				System.out.println("3 - Donner carte \"Trésor\"");
				System.out.println("4 - Récupérer trésor");
				System.out.println("5 - Défausser carte \"Trésor\"");
				System.out.println("6 - Utiliser carte \"Trésor\"");
				System.out.println("==============================");
				System.out.print("Réponse : ");
				reponse = sc.nextInt();
				
				if(!(reponse >= 1 && reponse <= 6)) {
					System.out.println("\nErreur : chiffre incorrect\n");
				}
			}
			
			Message message = new Message();
			
			switch (reponse) {
			case 1:
				message.setTypeMessage(TypeMessage.DEPLACEMENT);
				break;
			case 2:
				message.setTypeMessage(TypeMessage.ASSECHEMENT);
				break;
			case 3:
				message.setTypeMessage(TypeMessage.DONNER_CARTE);
				break;
			case 4:
				message.setTypeMessage(TypeMessage.RECUPERER_TRESOR);
				break;
			case 5:		
				message.setTypeMessage(TypeMessage.DEFAUSSER_CARTE);
				break;
			case 6:
				message.setTypeMessage(TypeMessage.UTILISER_CARTE);
				break;
			default:
				break;
			}
			traiterMessage(message);
			
			if (this.isPartieActive()) {
				numJoueur = numJoueur == 4 ? 1 : numJoueur + 1;
			}	
		}
	}

	@Override
	public void traiterMessage(Message m) {
		if (m != null) {
			Joueur joueur = m.getJoueur();

			if (joueur != null) {
				switch (m.getTypeMessage()) {
				case UTILISER_CARTE:
					if (m.getCarteTresor() != null) {
						joueur.utiliserCarteTresor(m.getCarteTresor());
					}
					break;
				case DEFAUSSER_CARTE:
					if (m.getCarteTresor() != null) {
						joueur.defausserCarteTresor(m.getCarteTresor());
					}
					break;
				case DEPLACEMENT:
					if (m.getTuileCible() != null) {
						joueur.getRole().seDeplacer();
					}
					break;
				case ASSECHEMENT:
					if (m.getTuileCible() != null) {
						joueur.getRole().assecher();
					}
					break;
				case DONNER_CARTE:
					if (m.getCarteTresor() != null && m.getJoueurCible() != null) {
						joueur.donnerCarteTresor(m.getCarteTresor(), m.getJoueurCible());
					}
					break;
				case RECUPERER_TRESOR:
					if (m.getTresor() != null) {
						ArrayList<CarteTresor> cartesJoueur = joueur.getCartesTresor();
						int nbreCarteTresor = 0;

						for (CarteTresor ct : cartesJoueur) {
							if (ct instanceof CTresor) {
								CTresor ctresor = (CTresor)ct;
								
								if (ctresor.getTresor() == m.getTresor()) {
									nbreCarteTresor++;
								}
							}
						}
						
						if (nbreCarteTresor >= 4) {
							this.addTresorPossedes(m.getTresor());
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}
}