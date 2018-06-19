package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VueGrille extends JPanel {
    
    private JPanel grille;
    private int cote;
    private int xO;
    private int yO;
    
    public VueGrille() {
        this.setLayout(new BorderLayout());
        grille = new JPanel();
        grille.setBackground(Color.green);
        
        Dimension size = this.getSize();
        cote = 600;
        xO = (int)(size.getWidth()/2 - (cote/2));
        yO = (int)(size.getHeight()/2 - (cote/2));
        
        this.add(grille, BorderLayout.CENTER);
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(10, 10, 100, 100);
    }
    
    public void drawTuiles() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                int xD = (int)(this.getXO() + (this.getCote() / 6)*i);
                int yD = (int)(this.getYO() + (this.getCote() / 6)*j);
                
//                if(i % 2 != 0 && j == 0) {
//                    g.setColor(Color.GREEN);
//                } else if(i % 2 == 0 && j == 0) {
//                    g.setColor(Color.red);
//                }
//                
//                g.setColor(g.getColor() == Color.RED ? Color.GREEN : Color.RED);
//
//                g.fillRect(xD + 5, yD + 5, this.getCote()/6 - 10, this.getCote()/6 - 10);
                
                VueTuile vueTuile = new VueTuile(xD + 5, yD + 5, this.getCote()/6 - 10);
                vueTuile.setBackground(Color.red);
                this.add(vueTuile);
            }
        }
    }

    public int getCote() {
        return cote;
    }

    private void setCote(int cote) {
        this.cote = cote;
    }
    
    public int getXO() {
        return xO;
    }
    
    private void setXO(int xO) {
        this.xO = xO;
    }
    
    public int getYO() {
        return yO;
    }
    
    private void setYO(int yO) {
        this.yO = yO;
    }
}
