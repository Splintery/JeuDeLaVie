package model;

public class Cellule {
	
	private int x;
	private int y;
	
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
