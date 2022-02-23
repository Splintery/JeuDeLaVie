package model;

import java.util.LinkedList;

public class Jeu {
	private Model grille;
	private Regles regles;
	
	public Jeu() {
		grille=new Model(null);
	}
	
	public Model getGrille() {
		return this.grille;
	}
	
	public Regles getRegles() {
		return this.regles;
	}
		
	public LinkedList<Cellule> cellulesAVerif() {
		LinkedList<Cellule> res=new LinkedList<Cellule>();
		LinkedList<Cellule> cellulesAModif = new LinkedList<Cellule>();
		for(Cellule c : this.grille.getCellulesVivantes()) {
			Cellule cell=new Cellule(c.getX(),c.getY());
			res.add(cell);
			LinkedList<Cellule> voisinesCellule=this.grille.getCellulesVoisinesVivantes(cell);
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
			if(regles.getVivanteResteEnVie().contains(this.grille.nbDecellulesVoisinesVivantes(c))==false) {
				return true;
			}
		}else {
			if(regles.getMortePrendVie().contains(this.grille.nbDecellulesVoisinesVivantes(c))) {
				return true;
			}
		}
		return false;
	}
	
	
	public void changeEtatGrille(LinkedList<Cellule> cellules){
		for (Cellule c : cellules ) {
			boolean vivante=false;
			for(Cellule cell : this.grille.getCellulesVivantes()) {
				if(cell.getX()==c.getX()||cell.getY()==c.getY()) {
					vivante=true;
				}
			}
			if (vivante) {
				grille.retirerCellule(c);
			} else {
				grille.ajouterCellule(c);
			}
		}
		
	}
}
