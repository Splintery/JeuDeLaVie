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
	
	public void regle1(Coordonnees c){  //Toute cellule vivante qui a moins de deux voisines vivantes meurt.
		if(grille.nbCelluleVoisineV(c)<2){
			grille.removeCelluleV(c.getX(), c.getY());
		}
	}

	public void regle3(Coordonnees c){ //Toute cellule qui a plus de trois voisines vivantes meurt.
		if(grille.nbCelluleVoisineV(c)>3){
			grille.removeCelluleV(c.getX(), c.getY());
		}
	}
	
	public LinkedList<Coordonnees> cellulesAVerif() {
		LinkedList<Coordonnees> res=new LinkedList<Coordonnees>();
		LinkedList<Coordonnees> cellulesAModif=new LinkedList<Coordonnees>();
		for(Cellule c : this.grille.getCelluleV()) {
			Coordonnees cell=new Coordonnees(c.getX(),c.getY());
			res.add(cell);
			LinkedList<Coordonnees> voisinesCoordonnees=this.grille.getCellulesVoisines(cell);
			for(Coordonnees voisine : voisinesCoordonnees) {
				if(res.contains(voisine)==false) {
					res.add(voisine);
				}
			}
		}
		return res;
	}
	
	public LinkedList<Coordonnees> cellulesAModif(LinkedList<Coordonnees> cellulesAVerif) {
		LinkedList<Coordonnees> res=new LinkedList<Coordonnees>();
		for(Coordonnees c : cellulesAVerif) {
			if(this.doitEtreModif(c)) {
				res.add(c);
			}
		}
		return res;
		
	}
	
	public boolean doitEtreModif(Coordonnees c) {
		if(grille.estVivante(c.getX(), c.getY())) {
			if(regles.getVivanteResteEnVie().contains(this.grille.nbCelluleVoisineV(c))==false) {
				return true;
			}
		}else {
			if(regles.getMortePrendVie().contains(this.grille.nbCelluleVoisineV(c))==false) {
				return true;
			}
		}
		return false;
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
