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
                        backgroundImage = ImageIO.read(new File("M2103/IleInterdite/images/plages.png"));
                        //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    } else {
                        backgroundImage = ImageIO.read(new File("M2103/IleInterdite/images/abysses.jpg"));
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

    public static void main(String[] args)  {
        new VueFin();
    }

    public JPanel setHeaderVictoire() {
        header = new JPanel();
        header.setOpaque(false);
        statJeu = new JLabel("Victoire");
        statJeu.setForeground(new Color(144 , 238, 144 ));
        statJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.setOpaque(false);
        header.add(statJeu);
        return header;

    }

    public JPanel setHeaderDefeat() {
        header = new JPanel();
        header.setOpaque(false);
        statJeu = new JLabel("Défaite");
        statJeu.setForeground(new Color(198 , 8, 0));
        statJeu.setFont(new Font("Arial", Font.BOLD, 100));
        header.add(statJeu);
        return header;

    }

    public JPanel setCenterVictoire() {
        center = new JPanel(new GridLayout(2,2));
                tr1 = new JLabel(new ImageIcon("M2103/IleInterdite/images/tresors/CaliceOnde2.png"));
                tr1.setOpaque(false);
                tr2 = new JLabel(new ImageIcon("M2103/IleInterdite/images/tresors/CristalArdent2.png"));
                tr2.setOpaque(false);
                tr3 = new JLabel(new ImageIcon("M2103/IleInterdite/images/tresors/PierreSacrée2.png"));
                tr3.setOpaque(false);
                tr4 = new JLabel(new ImageIcon("M2103/IleInterdite/images/tresors/StatueZéphir2.png"));
                tr4.setOpaque(false);
                center.setOpaque(false);
                center.add(tr1);
                center.add(tr2);
                center.add(tr3);
                center.add(tr4);

       return center;

    }

    public JPanel setCenterDefeat() {
        center = new JPanel();
        center.setOpaque(false);
        JLabel stat = new JLabel("Vous avez sombré");
        stat.setForeground(new Color(198 , 8, 0));
        
        
        center.add(stat);

        return center;
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
