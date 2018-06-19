package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VueTuile extends JPanel {
    
    public static int num = 0;
    private VueGrille grille;
    private int xO, yO, cote;
    
    public VueTuile(int xO, int yO, int cote) {
        this.setXO(xO);
        this.setYO(yO);
        this.setCote(cote);
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println("test " + num);
        num++;
        System.out.println("x = " + this.getXO() + " y = " + this.getYO());
        System.out.println(this.getSize());
        g.setColor(Color.GRAY);
        g.fillRect(this.getXO(), this.getYO(), this.getCote(), this.getCote());
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

    public int getCote() {
        return cote;
    }

    private void setCote(int cote) {
        this.cote = cote;
    }
}
