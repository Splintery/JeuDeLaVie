package Modele;

import java.util.LinkedList;


public class GrilleM {
	private static LinkedList<Cellule> cellulesV=new LinkedList<Cellule>();
	
	public GrilleM() {
	}
	
	public static LinkedList<Cellule> getCelluleV(){
		return cellulesV;
	}
	
	public boolean possedeVoisine(Cellule c) {
		for(Cellule cell : cellulesV) {
			if((cell.getX()==c.getX()-1&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()-1&&cell.getY()==c.getY())||(cell.getX()==c.getX()-1&&cell.getY()==c.getY()+1)||(cell.getX()==c.getX()&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()&&cell.getY()==c.getY()+1)||(cell.getX()==c.getX()+1&&cell.getY()==c.getY()-1)||(cell.getX()==c.getX()+1&&cell.getY()==c.getY())||(cell.getX()==c.getX()+1&&cell.getY()==c.getY()+1)) {
				return true;
			}
		}
		return true;
	}	
	
	public static boolean estVivante(int x, int y) {
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

	public static void addCelluleV(Cellule a){
		cellulesV.add(a);
	}

	public static void removeCelluleV(int x,int y){
		for(Cellule c: cellulesV){
			if(c.getX()==x && c.getY()==y){
				 cellulesV.remove();
				 break;
			}
		}
	}


	public static int nbCelluleVoisineV(Cellule c){   // renvoie le nombre de cellules voisines vivantes
		int res=0;
		if(estVivante(c.getX()-1, c.getY()-1)) res++;
		if(estVivante(c.getX()-1, c.getY())) res++;
		if(estVivante(c.getX()-1, c.getY()+1)) res++;
		if(estVivante(c.getX(), c.getY()+1)) res++;
		if(estVivante(c.getX()+1, c.getY()+1)) res++;
		if(estVivante(c.getX()+1, c.getY())) res++;
		if(estVivante(c.getX()+1, c.getY()-1)) res++;
		if(estVivante(c.getX(), c.getY()-1)) res++;
		return res;
	}

	public static int nbCelluleVoisineM(Cellule c){  // renvoie le nombre de cellules voisines mortes
		int res=0;
		if(!estVivante(c.getX()-1, c.getY()-1)) res++;
		if(!estVivante(c.getX()-1, c.getY())) res++;
		if(!estVivante(c.getX()-1, c.getY()+1)) res++;
		if(!estVivante(c.getX(), c.getY()+1)) res++;
		if(!estVivante(c.getX()+1, c.getY()+1)) res++;
		if(!estVivante(c.getX()+1, c.getY())) res++;
		if(!estVivante(c.getX()+1, c.getY()-1)) res++;
		if(!estVivante(c.getX(), c.getY()-1)) res++;
		return res;
	}
	
}
