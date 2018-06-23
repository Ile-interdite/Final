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
    private JLabel statJeu,tr1,tr2,tr3,tr4;
    private JButton btnQuit;
    private Image backgroundImage;

    public VueFin(){
        this.setTitle("Résultat de la partie");
        setSize(700, 700);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
        victoire = false;
        
        principal = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    if (victoire == true){
                        backgroundImage = ImageIO.read(new File("M2103/IleInterdite/images/victoire.png"));
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    } else {
                        backgroundImage = ImageIO.read(new File("M2103/IleInterdite/images/defaite.png"));
                        //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        principal.setOpaque(false);
        principal.setLayout(new BorderLayout());
        principal.add(setFooter(), BorderLayout.SOUTH);

        //préparation du JFrame (this)
        add(principal);
        //vues.repaint();
        setVisible(true);
    }

    public static void main(String[] args)  {
        new VueFin();
    }

    public JPanel setFooter() {
        footer = new JPanel();
        footer.setOpaque(false);
        btnQuit = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/sortie.png"));
        btnQuit.setBorderPainted(false);
        btnQuit.setContentAreaFilled(false);
        btnQuit.setFocusPainted(false);
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
