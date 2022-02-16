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
	
	public LinkedList<Cellule> cellulesAVerif() {
		LinkedList<Cellule> res=new LinkedList<Cellule>();
		LinkedList<Cellule> cellulesAModif=new LinkedList<Cellule>();
		for(Cellule c : this.grille.getCelluleV()) {
			Cellule cell=new Cellule(c.getX(),c.getY());
			res.add(cell);
			LinkedList<Cellule> voisinesCellule=this.grille.getCellulesVoisines(cell);
			for(Cellule voisine : voisinesCellule) {
				if(res.contains(voisine)==false) {
					res.add(voisine);
				}
			}
		}
		return res;
	}
	
	public LinkedList<Cellule> cellulesAModif(LinkedList<Cellule> cellulesAVerif) {
		LinkedList<Cellule> res=new LinkedList<Cellule>();
		for(Cellule c : cellulesAVerif) {
			if(this.doitEtreModif(c)) {
				res.add(c);
			}
		}
		return res;
		
	}
	
	public boolean doitEtreModif(Cellule c) {
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
	
	
	public void changeEtatGrille(LinkedList<Cellule> cellules){
		for(Cellule c : cellules ) {
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
