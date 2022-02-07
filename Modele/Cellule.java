package Modele;

public class Cellule {
	private int x;
	private int y;
	private boolean AEteVisitee;
	
	public Cellule(int x,int y) {
		this.x=x;
		this.y=y;
		this.AEteVisitee=false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
}
