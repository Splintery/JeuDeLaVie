package Modele;

import java.util.LinkedList;

public class Jeu {
	private GrilleM grille;
	private Regles regles;
	
	public Jeu() {
		grille=new GrilleM();
	}
	
	public GrilleM getGrille() {
		return this.grille;
	}
	
	public Regles getRegles() {
		return this.regles;
	}
	
	public void regle1(Cellule c){  //Toute cellule vivante qui a moins de deux voisines vivantes meurt.
		if(grille.nbCelluleVoisineV(c)<2){
			grille.removeCelluleV(c.getX(), c.getY());
		}
	}

	public void regle3(Cellule c){ //Toute cellule qui a plus de trois voisines vivantes meurt.
		if(grille.nbCelluleVoisineV(c)>3){
			grille.removeCelluleV(c.getX(), c.getY());
		}
	}

	public void testRegle(){
		for(Cellule c:GrilleM.getCelluleV()){
			regle1(c);
			regle3(c);
		}
	}
	
	public LinkedList<Coordonnees> cellulesAModif() {
		
	}
	
	public void changeEtatGrille(LinkedList<Coordonnees> cellules){
		for(Coordonnees c : cellules ) {
			boolean vivante=false;
			for(Cellule cell : this.grille.getCelluleV()) {
				if(cell.getX()==c.getX()||cell.getY()==c.getY()) {
					vivante=true;
				}
			}
			if(vivante) {
				grille.removeCelluleV(c.getX(), c.getY());
			}else {
				grille.addCelluleV(new Cellule(c.getX(),c.getY()));
			}
		}
		
	}
}
