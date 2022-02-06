package Modele;

import java.util.LinkedList;

import Vue.Cellule;

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
			if((cell.x==c.x-1&&cell.y==c.y-1)||(cell.x==c.x-1&&cell.y==c.y)||(cell.x==c.x-1&&cell.y==c.y+1)||(cell.x==c.x&&cell.y==c.y-1)||(cell.x==c.x&&cell.y==c.y+1)||(cell.x==c.x+1&&cell.y==c.y-1)||(cell.x==c.x+1&&cell.y==c.y)||(cell.x==c.x+1&&cell.y==c.y+1)) {
				return true;
			}
		}
		return true;
	}
	
	
}
