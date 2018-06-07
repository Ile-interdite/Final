package modele;

public class Position {
    
    private int x, y;
    
    public Position() {
    	this.setX(0);
    	this.setY(0);
    }
    
    public Position(int x, int y) {
    	this.setX(x);
    	this.setY(y);
    }
    
    @Override
    public String toString() {
        return "[" + this.getX() + ", " + this.getY() + "]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
