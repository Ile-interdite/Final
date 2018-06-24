package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VuePioche extends JFrame {
	
	private JPanel principal;
	private JLabel info,tresor,innond;
	private JButton tourS;
	private String joueur;

	public static void main(String[] args) {
		new VuePioche();
	}
	
	public VuePioche() {
		
        this.setTitle("Règles du jeu");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        setSize(width/3+100,height-100);
        //pour ne pas changer la taille de la fenetre
        setResizable(false);
        //pour mettre au centre de l'écran
        setLocationRelativeTo(null);
		
		principal = new JPanel(new BorderLayout());
		principal.add(header(),BorderLayout.NORTH);
		principal.add(center(),BorderLayout.CENTER);
		principal.add(footer(),BorderLayout.SOUTH);
		
		add(principal);
		setVisible(true);
	}
	
	
	public JPanel header() {
		JPanel header = new JPanel();
		info = new JLabel("Fin du tour du joueur : " + joueur);
		header.add(info);
		
		return header;
	}
	
	public JPanel center() {
		JPanel center = new JPanel(new GridLayout(2,1));
		JPanel haut = new JPanel(new BorderLayout());
		JPanel bas = new JPanel(new BorderLayout());
		tresor = new JLabel("Cartes Trésors piochées : ");
		JPanel hautpan = new JPanel(new GridLayout(1,2));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		
		JPanel tr1 = new JPanel(new BorderLayout());
		tr1.setBackground(Color.red);
		tr1.setBorder(new EmptyBorder(20, 20, 20, 20));
		//new EmptyBorder(top, left, bottom, right)
		tr1.add(panel, BorderLayout.CENTER);
		
		JPanel tr2 = new JPanel();
		tr2.setBackground(Color.green);
		tr2.setBorder(new EmptyBorder(0,0,20,0));

		hautpan.add(tr1);
		hautpan.add(tr2);
		
		haut.add(tresor, BorderLayout.NORTH);
		haut.add(hautpan, BorderLayout.CENTER);
		
		innond = new JLabel("Cartes Inondation piochées : ");
		JPanel basPan = new JPanel(new GridLayout(1,3));
		
		JPanel ind1 = new JPanel();
		ind1.setBackground(Color.red);
		JPanel ind2 = new JPanel();
		ind2.setBackground(Color.green);
		JPanel ind3 = new JPanel();
		ind3.setBackground(Color.blue);

		basPan.add(ind1);
		basPan.add(ind2);
		basPan.add(ind3);
		
		bas.add(innond, BorderLayout.NORTH);
		bas.add(basPan, BorderLayout.CENTER);
		
		center.add(haut);
		center.add(bas);
		return center;
	}
	
	
	
	public JPanel footer() {
		JPanel footer = new JPanel();
		
		
		return footer;
	}
}
