package model;

import java.util.LinkedList;
import java.util.Random;

import controller.Controller;

/**
 * La classe modèle contient la logique du jeu de la vie.
 * 
 * @author 
 *
 */
public class Model {
	
	/**
	 * Le controlleur associé au modèle afin de lui passer les données.
	 */
	public Controller controller;
	
	/**
	 * Liste contenant tous les points des cellules vivantes.
	 */
	private LinkedList<Cellule> cellulesVivantes = new LinkedList<>();
	
	/**
	 * Représente la génération courante, c'est-à-dire à quel tour on en est.
	 */
	private int generation;
	
	/**
	 * Le constructeur du Model.
	 * 
	 * @param controller le contrôleur associé au Model
	 */
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * Getter de liste des cellules vivantes.
	 * 
	 * @return la liste des cellules vivantes.
	 */
	public LinkedList<Cellule> getCellulesVivantes(){
		return cellulesVivantes;
	}
	
	/**
	 * Getter sur le nombre de générations.
	 * 
	 * @return le nombre de générations passées
	 */
	public int getGeneration() {
		return generation;
	}
	
	/**
	 * Met à jour la liste de cellules.
	 */
	public void update() {
		LinkedList<Cellule> cellulesASupprimer = cellulesASupprimer();
		LinkedList<Cellule> cellulesAFaireNaitre = cellulesAFaireNaitre();
		supprimerCellulesMortes(cellulesASupprimer);
		ajouterCellulesAFaireNaitre(cellulesAFaireNaitre);
		generation++;
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
	 * Vérifie à partir d'une instance de Cellule si une cellule est vivante, c'est-à-dire si elle est présente dans la liste de cellules.
	 * 
	 * @param une cellule c
	 * @return true si c est contenue dans la liste de cellules, false sinon
	 */
	public boolean estVivante(Cellule c) {
		return cellulesVivantes.contains(c);
	}
	
	/**
	 * Vérifie à partir de coordonnées si une cellule est vivante, c'est-à-dire si elle est présente dans la liste de cellules.
	 * 
	 * @param x l'entier sur l'axe des abscisses
	 * @param y l'entier sur l'axe des ordonnées
	 * @return true si c est contenue dans la liste de cellules, false sinon
	 */
	public boolean estVivante(int x, int y) {
		return estVivante(new Cellule(x, y));
	}
	
	/**
	 * Prend une Cellule c en argument et retourne la liste de ses voisines vivantes.
	 * 
	 * @param c la cellule dont on vérifie les voisines
	 * @return la liste des cellules voisines vivantes de la Cellule c
	 */
	public LinkedList<Cellule> getCellulesVoisinesVivantes(Cellule c){
		LinkedList<Cellule> res = new LinkedList<>();
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++)
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++)
				if ((c.getX() != i || c.getY() != j))
					res.add(new Cellule(i, j));
		return res;
	}
	
	/**
	 * Prend une Cellule c en argument et retourne la liste de ses voisines mortes.
	 * 
	 * @param c la cellule dont on vérifie les voisines
	 * @return la liste des cellules voisines mortes de la Cellule c
	 */
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
	
	/**
	 * Ajoute une liste de cellules aux cellules vivantes et retourne l'instance.
	 * 
	 * @param cellules les cellules à ajouter
	 * @return l'instance du Model sur laquelle est appelée cette méthode
	 */
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
	 * Supprime de la liste des cellules vivantes celles mortes après un tour.
	 * 
	 * @param l la liste des cellules à faire mourir
	 */
	private void supprimerCellulesMortes(LinkedList<Cellule> cellulesMortes) {
		cellulesVivantes.removeAll(cellulesMortes);
	}
	
	/**
	 * Ajoute à la liste des cellules vivantes les nouvelles cellules qui doivent naître.
	 * 
	 * @param l la liste des cellules à faire naitre
	 */
	private void ajouterCellulesAFaireNaitre(LinkedList<Cellule> cellulesAFaireNaitre) {
		cellulesVivantes.addAll(cellulesAFaireNaitre);
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
	
	/**
	 * Liste toutes les cellules à faire naître.
	 * 
	 * @return la liste des cellules à ajouter à la liste des cellules vivantes
	 */
	public LinkedList<Cellule> cellulesAFaireNaitre() {
		LinkedList<Cellule> cellulesAFaireNaitre = new LinkedList<>();
		LinkedList<Cellule> cellulesMortesDejaVisitees = new LinkedList<>(); // Liste des cellules mortes déjà visitées pour éviter de traîter plusieurs fois un même point
		for (Cellule c : cellulesVivantes) { // Pour toutes les cellules vivantes,
			for (Cellule d : getCellulesVoisinesMortes(c)) { // telles que pour toutes leurs cellules voisines mortes,
				if (!cellulesMortesDejaVisitees.contains(d)) { // si celle-ci n'a pas déjà été traîtée:
					cellulesMortesDejaVisitees.add(d);
					if (nbDecellulesVoisinesVivantes(d) == 3 && !cellulesAFaireNaitre.contains(d)) {
						cellulesAFaireNaitre.add(d);
					}
				}
			}
		}
		return cellulesAFaireNaitre;
	}
	
	@Override
	public String toString() {
		return cellulesVivantes.toString();
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
	
	/**
	 * Place des cellules aléatoirement (par rapport au centre de coordonnées (0, 0)) dans un certain rectangle.
	 * 
	 * @param nbCellules
	 * @param largeur
	 * @param hauteur
	 */
	public void placerCellulesAleatoirement(int nbCellulesAPlacer, int largeur, int hauteur) {
		Random r = new Random();
		int cellulesPlacees = 0;
		while (cellulesPlacees != nbCellulesAPlacer) {
			int x = -(largeur / 2) + r.nextInt(largeur + 1);
			int y = -(hauteur / 2) + r.nextInt(hauteur + 1);
			if (!estVivante(x, y)) {
				ajouterCellule(new Cellule(x, y));
				cellulesPlacees++;
			}
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
		
		exec(canonAPlaneur, 1500, 1000);
	}
	
}
