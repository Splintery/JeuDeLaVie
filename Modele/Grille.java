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
	
	public boolean estVivante(int x, int y) {
		for(Cellule c : cellulesV) {
			if(c.getX()==x&&c.getY()==y) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<Coordonnees> getCellulesVoisines(Cellule c){
		LinkedList<Coordonnees> res = new LinkedList<Coordonnees>();
		res.add(new Coordonnees(c.getX()-1,c.getY()-1));
		res.add(new Coordonnees(c.getX()-1,c.getY()));
		res.add(new Coordonnees(c.getX()-1,c.getY()+1));
		res.add(new Coordonnees(c.getX(),c.getY()-1));
		res.add(new Coordonnees(c.getX(),c.getY()+1));
		res.add(new Coordonnees(c.getX()+1,c.getY()-1));
		res.add(new Coordonnees(c.getX()+1,c.getY()));
		res.add(new Coordonnees(c.getX()+1,c.getY()+1));
		return res;
	}
	
}
