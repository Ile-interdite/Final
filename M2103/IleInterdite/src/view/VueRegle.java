package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class VueRegle extends JFrame {
	
	
	private String chemin = "M2103/IleInterdite/images/Ile_Interdite-regles/Ile_Interdite-regles-";
	private JPanel principal;
	private Image backgroundImage;
	private JButton suiv,pred,exit;
	private int i = 1;

	public VueRegle() {
        this.setTitle("Règles du jeu");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        setSize(width/3+100,height-100);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
        this.setUndecorated(true);
        
        principal = new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {
                try {
                    backgroundImage = ImageIO.read(new File(chemin+i+".png"));
                    //backgroundImage = ImageIO.read(new File("images/ileinterdite.jpg"));
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException ex) {
                    System.out.println("Erreur : image");
                }

                
            }
        };

        

        principal.add(main(), BorderLayout.CENTER);
        principal.add(footer(), BorderLayout.SOUTH);
        add(principal);
        setVisible(true);
        
	}
	
	public JPanel main() {
		JPanel main = new JPanel(new BorderLayout());
        main.setOpaque(false);
        pred= new JButton(new ImageIcon("M2103/IleInterdite/images/icones/back.png"));
        pred.addMouseListener(new CustomMouseListener(pred));
        suiv= new JButton(new ImageIcon("M2103/IleInterdite/images/icones/next.png"));
        suiv.addMouseListener(new CustomMouseListener(suiv));
        pred.setOpaque(false);
        pred.setBorderPainted(false);
        pred.setContentAreaFilled(false);
        pred.setFocusPainted(false);
        
        suiv.setOpaque(false);
        suiv.setBorderPainted(false);
        suiv.setContentAreaFilled(false);
        suiv.setFocusPainted(false);
        
        
        pred.setVisible(false);
        
        suiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = i + 1;
                repaint();
                if(i == 8) {
                	suiv.setVisible(false);
                }
                if(i>1) {
                	pred.setVisible(true);
                }
            }
        });
        
        pred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = i - 1;
                repaint();
                if(i < 8) {
                	suiv.setVisible(true);
                }
                if(i==1) {
                	pred.setVisible(false);
                }
            }
        });
        

        main.add(pred,BorderLayout.WEST);
        main.add(suiv,BorderLayout.EAST);
		return main;
	}
	public JPanel footer() {
		JPanel footer = new JPanel();
		footer.setOpaque(false);
		
		exit = new JButton(new ImageIcon("M2103/IleInterdite/images/icones/sortie.png"));	
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
		exit.addMouseListener(new CustomMouseListener(exit));
		
		
		footer.add(exit);
		return footer;
	}
	
}
