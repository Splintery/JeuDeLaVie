package Modele;

import java.util.LinkedList;


public class Grille {
	private LinkedList<Cellule> cellulesV;
	
	public Grille() {
		cellulesV=new LinkedList<Cellule>();
	}
	
	public LinkedList<Cellule> getCelluleV(){
		return this.cellulesV;
	}
	
	public boolean possedeVoisine(Cellule c) {
		for(Cellule cell : cellulesV) {
			if((cell.getX()==c.getX()-1&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()-1&&cell.getY()==c.getY())||(cell.getX()==c.getX()-1&&cell.getY()==c.getY()+1)||(cell.getX()==c.getX()&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()&&cell.getY()==c.getY()+1)||(cell.getX()==c.getX()+1&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()+1&&cell.getY()==c.getY())||(cell.getX()==c.getX()+1&&cell.getY()==c.getY()+1)) {
				return true;
			}
		}
		return true;
	}
	
	
}
