package model;

import java.util.LinkedList;

import controller.Controller;


public class Model {
	
	public Controller controller;
	
	/**
	 * Liste contenant tous les points des cellules vivantes.
	 */
	private LinkedList<Cellule> cellulesVivantes = new LinkedList<>();
	
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	public LinkedList<Cellule> getCellulesVivantes(){
		return cellulesVivantes;
	}
	
	/**
	 * Met à jour la liste de cellules.
	 */
	public void update() {
		LinkedList<Cellule> cellulesASupprimer = cellulesASupprimer();
		LinkedList<Cellule> cellulesAFaireNaitre = cellulesAFaireNaitre();
		supprimerCellulesMortes(cellulesASupprimer);
		ajouterCellulesAFaireNaitre(cellulesAFaireNaitre);
	}
	
	/**
	 * Regarde si une cellule possède au moins une cellule voisine vivante.
	 * 
	 * @param une cellule c
	 * @return true si la cellule c possède une cellule voisine vivante, false sinon
	 */
	public boolean possedeVoisine(Cellule c) {
		return nbDecellulesVoisinesVivantes(c) > 0;
	}
	
	/**
	 * Vérifie si une cellule est vivante, c'est-à-dire si elle est présente dans la liste de cellules.
	 * 
	 * @param une cellule c
	 * @return true si c est contenue dans la liste de cellules, false sinon
	 */
	public boolean estVivante(Cellule c) {
		return cellulesVivantes.contains(c);
	}
	
	public boolean estVivante(int x, int y) {
		return estVivante(new Cellule(x, y));
	}
	
	public LinkedList<Cellule> getCellulesVoisines(Cellule c){
		LinkedList<Cellule> res = new LinkedList<>();
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++)
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++)
				if ((c.getX() != i || c.getY() != j))
					res.add(new Cellule(i, j));
		return res;
	}
	
	public LinkedList<Cellule> getCellulesVoisinesMortes(Cellule c) {
		LinkedList<Cellule> res = new LinkedList<>();
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++)
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++)
				if ((c.getX() != i || c.getY() != j) && !estVivante(i, j) && !res.contains(new Cellule(i, j)))
					res.add(new Cellule(i, j));
		return res;
	}

	/**
	 * Ajoute une cellule à la liste si et seulement si celle-ci n'est pas déjà présente
	 * (c'est à dire si le couple d'entiers (c.x, c.y) n'existe pas déjà dans la liste)
	 * 
	 * @param la cellule c à ajouter
	 */
	public boolean ajouterCellule(Cellule c){
	 	if (!cellulesVivantes.contains(c))
	 			return cellulesVivantes.add(c);
	 	return false;
	}
	
	public Model avecCellules(Cellule... cellules) {
		for (Cellule c : cellules)
			if (!cellulesVivantes.contains(c))
	 			cellulesVivantes.add(c);
	 	return this;
	}
	
	/**
	 * Version 2 de la méthode removeCelluleV(int x, int y) :
	 * une méthode existe déjà : remove(Object o)
	 * 
	 * @param la Cellule c à retirer de la liste
	 * @return true si la cellule a bien été retirée de la liste, false sinon
	 */
	public boolean retirerCellule(Cellule c) {
		return cellulesVivantes.remove(c);
	}

	/**
	 * Calcule le nombre de cellules voisines vivantes d'une cellule.
	 * 
	 * @param la cellule c
	 * @return le nombre de cellules voisines vivantes de la cellule c
	 */
	public int nbDecellulesVoisinesVivantes(Cellule c) {
		int n = 0;
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++)
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++)
				if ((c.getX() != i || c.getY() != j) && estVivante(new Cellule(i, j))) // 2 conditions : (i, j) =/= (c.x, c.y) && la liste contient bien le couple (i, j)
					n++;
		return n;
	}
	
	/**
	 * Calcule le nombre de cellules voisines mortes d'une cellule.
	 * Il suffit de retourner 8 - n.
	 * Où 8 représente le nombre total de cellules voisines et n le nombre de cellules voisines vivantes.
	 * 
	 * @param la cellule c
	 * @return le nombre de cellules voisines mortes de la cellule c
	 */
	public int nbDecellulesVoisinesMortes(Cellule c) {
		return 8 - nbDecellulesVoisinesVivantes(c); 
	}
	
	/**
	 * Supprime les cellules mortes après un tour.
	 * 
	 * @param l
	 */
	private void supprimerCellulesMortes(LinkedList<Cellule> l) {
		for (Cellule c : l) {
			cellulesVivantes.remove(c);
		}
	}
	
	private void ajouterCellulesAFaireNaitre(LinkedList<Cellule> l) {
		for (Cellule c : l) {
			cellulesVivantes.add(c);
		}
	}
	
	/**
	 * Retourne la liste des cellules à supprimer.
	 * 
	 * @return
	 */
	private LinkedList<Cellule> cellulesASupprimer() {
		LinkedList<Cellule> cellulesASupprimer = new LinkedList<>();
		for (Cellule c : cellulesVivantes) {
			int nombreVoisines = nbDecellulesVoisinesVivantes(c);
			if (nombreVoisines != 2 && nombreVoisines != 3)
				cellulesASupprimer.add(c);
		}
		return cellulesASupprimer;
	}
	
	public LinkedList<Cellule> cellulesAFaireNaitre() {
		LinkedList<Cellule> cellulesAFaireNaitre = new LinkedList<>();
		for (Cellule c : cellulesVivantes)
			for (Cellule d : getCellulesVoisinesMortes(c))
				if (nbDecellulesVoisinesVivantes(d) == 3 && !cellulesAFaireNaitre.contains(d))
					cellulesAFaireNaitre.add(d);
		return cellulesAFaireNaitre;
	}
	
	@Override
	public String toString() {
		return cellulesVivantes + "";
	}
	
	/**
	 * TEMPORAIRE
	 * Pour l'affichage de cellules avec un tableau de booléens.
	 * 
	 * @param t
	 * @return
	 */
	private String affichageTemp(boolean[][] t) {
		String s = "";
		
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				try {
					if (cellulesVivantes.contains(new Cellule(i, j))) {
						s += "O";
					} else {
						s+= " ";
					}
				} catch (Exception e) {
					System.out.print(' ');
				}
			}
			s += "\n";
		}
		return s;
	}
	
	/**
	 * TEMPORAIRE
	 * Lance une partie en mode console.
	 * 
	 * @param m
	 * @param tours
	 * @param ms
	 * @throws InterruptedException
	 */
	private static void exec(Model m, int tours, int ms) throws InterruptedException {
		boolean[][] t = new boolean[101][100];
		for (int i = 0; i < tours; i++) {
			System.out.println(m.affichageTemp(t));
			Thread.sleep(ms);
			m.update();
		}
	}
	
	// Pour tester
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		var grille1 = new Model(null).avecCellules(new Cellule(93, 11), new Cellule(93, 12), new Cellule(93, 13), new Cellule(93, 14), new Cellule(93, 15), new Cellule(92, 12));
		
		var grille2 = new Model(null).avecCellules(new Cellule(89, 11), new Cellule(89, 12), new Cellule(90, 10), new Cellule(90, 12), new Cellule(91, 10), new Cellule(91, 12), new Cellule(92, 11));
		
		var grille3 = new Model(null).avecCellules(new Cellule(88, 20), new Cellule(88, 21), new Cellule(89, 19), new Cellule(89, 20), new Cellule(90, 18), new Cellule(90, 19));
		
		var grille4 = new Model(null).avecCellules(new Cellule(89, 20), new Cellule(89, 21), new Cellule(90, 19), new Cellule(90, 20), new Cellule(91, 20));
		
		var canonAPlaneur = new Model(null).avecCellules(
				new Cellule(95, 5), new Cellule(95, 6), new Cellule(96, 5), new Cellule(96, 6), 
				new Cellule(94, 15), new Cellule(95, 15), new Cellule(96, 15), 
				new Cellule(93, 16), new Cellule(97, 16),
				new Cellule(92, 17), new Cellule(92, 18), new Cellule(98, 17), new Cellule(98, 18),
				new Cellule(95, 19),
				new Cellule(93, 20), new Cellule(97, 20),
				new Cellule(94, 21), new Cellule(95, 21), new Cellule(96, 21),
				new Cellule(95, 22),
				new Cellule(96, 25), new Cellule(96, 26), new Cellule(97, 25), new Cellule(97, 26), new Cellule(98, 25), new Cellule(98, 26),
				new Cellule(95, 27), new Cellule(99, 27),
				new Cellule(94, 29), new Cellule(95, 29), new Cellule(99, 29), new Cellule(100, 29),
				new Cellule(97, 39), new Cellule(97, 40), new Cellule(98, 39), new Cellule(98, 40)
				);
		
		exec(canonAPlaneur, 1500, 10);
	}
	
}
