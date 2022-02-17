package model;

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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Cellule) {
			Cellule c = (Cellule) obj;
			return c.x == x && c.y == y;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
