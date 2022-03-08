package model;
import java.awt.Color;
public class Cellule {
	
	private int x;
	private int y;
	private int tempsDeVie;
	private Color couleur;
	
	public Cellule(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor(){
        return couleur;
    }
	
	public int getTempsDeVie() {
		return this.tempsDeVie;
	}
	
    public void update(){
        tempsDeVie++;
        if(tempsDeVie==2) couleur=Color.ORANGE;
        if(tempsDeVie==3) couleur=Color.YELLOW;
        if(tempsDeVie==4) couleur=Color.GREEN;
        if(tempsDeVie==5) couleur=Color.BLUE;
        if(tempsDeVie>5) couleur=Color.PINK;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Cellule) {
			Cellule c = (Cellule) obj;
			return c.x == x && c.y == y;
		}
		return false;
	}
	
}
