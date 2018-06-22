package view;

import controller.Message;
import controller.Observateur;
import controller.Observe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueFin extends JFrame implements Observe {

    private Observateur observateur;
    private JPanel principal, header, center, footer;
    private boolean victoire;
    private JLabel statJeu;
    private JButton btnQuit;
    private Image backgroundImage,tr1,tr2,tr3,tr4;

    public VueFin() throws IOException {
        this.setTitle("Initialisation de la partie");
        setSize(700, 700);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
        victoire = true;
        
        principal = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    if (victoire == true){
                        backgroundImage = ImageIO.read(new File("images/ileinterdite_for2t.png"));
                        //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    } else {
                        backgroundImage = ImageIO.read(new File(""));
                        //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    }
                } catch (IOException ex) {
                    System.out.println("Erreur : image");
                }
            }
        };
        principal.setOpaque(false);
        principal.setLayout(new BorderLayout());
  
        if (victoire == true) {
            principal.add(setHeaderVictoire(), BorderLayout.NORTH);
            principal.add(setCenterVictoire(), BorderLayout.CENTER);
            principal.add(setFooter(), BorderLayout.SOUTH);
        } else {
            principal.add(setHeaderDefeat(), BorderLayout.NORTH);
            principal.add(setCenterDefeat(), BorderLayout.CENTER);
            principal.add(setFooter(), BorderLayout.SOUTH);
        }

        //préparation du JFrame (this)
        add(principal);
        //vues.repaint();
        setVisible(true);
    }

    public static void main(String[] args) throws IOException  {
        new VueFin();
    }

    public JPanel setHeaderVictoire() {
        header = new JPanel();

        statJeu = new JLabel("Victoire");
        statJeu.setForeground(new Color(203, 101, 80));
        statJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.add(statJeu);
        header.setOpaque(false);

        return header;

    }

    public JPanel setHeaderDefeat() {
        header = new JPanel();
        
        statJeu = new JLabel("Défaite");
        statJeu.setForeground(new Color(203, 101, 80));
        statJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.add(statJeu);
        header.setOpaque(false);
        return header;

    }

    public JPanel setCenterVictoire() {
        center = new JPanel(new GridLayout(2,2));
                JLabel t1 = new JLabel(new ImageIcon("images/tresors/CaliceOnde.png"));
                t1.setOpaque(false);
                JLabel t2 = new JLabel(new ImageIcon("images/tresors/CristalArdent.png"));
                t2.setOpaque(false);
                JLabel t3 = new JLabel(new ImageIcon("images/tresors/PierreSacrée.png"));
                t3.setOpaque(false);
                JLabel t4 = new JLabel(new ImageIcon("images/tresors/StatueZéphir.png"));
                t4.setOpaque(false);
                center.add(t1);
                center.add(t2);
                center.add(t3);
                center.add(t4);
           
       return center;

    }

    public JPanel setCenterDefeat() {
        center = new JPanel();
        return center;

    }

    public JPanel setFooter() {
        footer = new JPanel();
        btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        footer.add(btnQuit);
        return footer;

    }


    @Override
    public void setObservateur(Observateur observateur) {
        if (observateur != null) {
            this.observateur = observateur;
        }
    }

    @Override
    public void notifierObservateur(Message m) {
        getObservateur().traiterMessage(m);
    }

    @Override
    public Observateur getObservateur() {
        return observateur;
    }

}
