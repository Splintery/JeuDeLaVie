package model;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Rectangle;

import controller.Controller;

public class ImprovedModel {
	
	public Rectangle boundary;
	private int capacity;

	public Controller controller;
	public QuadTree cellulesVivantes;
	private QuadTree cellulesASupprimer;
	private QuadTree cellulesAFaireNaitre;
	private int generation;
	private Regles regles;
	
	public ImprovedModel(Controller controller, Rectangle boundary, int capacity,  Regles regles) {
		this.boundary = boundary;
		this.capacity = capacity;
		this.cellulesVivantes = new QuadTree(boundary, capacity);
		this.cellulesASupprimer = new QuadTree(boundary, capacity);
		this.cellulesAFaireNaitre = new QuadTree(boundary, capacity);
		this.controller = controller;
		if (regles == null) this.regles = Regles.getReglesDeBase();
		else this.regles = regles;
	}
	
	public ImprovedModel(Controller controller, Rectangle boundary, int capacity) {
		this(controller, boundary, capacity, null);
	}
	
	public CopyOnWriteArrayList<Cellule> getCellulesVivantes(){
		return cellulesVivantes.getList(this.boundary);
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public void changerRegles(Regles regles) {
		this.regles = regles;
	}
	
	public void update() {
		this.cellulesASupprimerUpdate();
		this.cellulesAFaireNaitreUpdate();
		supprimerCellulesMortes(cellulesASupprimer.getList(this.boundary));
		ajouterCellulesAFaireNaitre(cellulesAFaireNaitre.getList(this.boundary));
		this.cellulesASupprimer.clear();
		this.cellulesAFaireNaitre.clear();
		updateColor();
		generation++;
	}
	public void updateColor() {
		cellulesVivantes.updateColor();
	}
	
	public boolean possedeVoisine(Cellule c) {
		return nbDecellulesVoisinesVivantes(c) > 0;
	}
	
	public boolean estVivante(Cellule c) {
		return cellulesVivantes.contains(c);
	}
	
	public boolean estVivante(int x, int y) {
		return estVivante(new Cellule(x, y));
	}
	public QuadTree retirerCellule(Cellule c) {
		return cellulesVivantes.remove(c);
	}

	/**
	 * Prend une Cellule c en argument et retourne la liste de ses voisines vivantes.
	 * 
	 * @param c la cellule dont on verifie les voisines
	 * @return la liste des cellules voisines vivantes de la Cellule c
	 */
	public QuadTree getCellulesVoisinesVivantes(Cellule c){
		int rayon = regles.getRayon();
		return this.cellulesVivantes.getSurroundingCellAsTree(c, rayon);
	}
	
	/**
	 * Prend une Cellule c en argument et retourne la liste de ses voisines mortes.
	 * 
	 * @param c la cellule dont on verifie les voisines
	 * @return la liste des cellules voisines mortes de la Cellule c
	 */
	public CopyOnWriteArrayList<Cellule> getCellulesVoisinesMortes(Cellule c) {
		int rayon = regles.getRayon();
		CopyOnWriteArrayList<Cellule> res = new CopyOnWriteArrayList<>();
		QuadTree voisineVivantes = getCellulesVoisinesVivantes(c);

		for (int i = c.getX() - rayon; i < c.getX() + 2 + (rayon - 1) * 2; i++) {
			for (int j = c.getY() - rayon; j < c.getY() + 2 + (rayon - 1) * 2; j++) {
				if (!voisineVivantes.contains(i, j) && (i != c.getX() || j != c.getY())) {
					res.add(new Cellule (i, j));
				}
			}
		}
		return res;
	}

	/**
	 * Ajoute une cellule a la liste si et seulement si celle-ci n'est pas d�j� pr�sente
	 * (c'est-a-dire si le couple d'entiers (c.x, c.y) n'existe pas deja dans la liste)
	 * 
	 * @param la cellule c a ajouter
	 */
	public boolean ajouterCellule(Cellule c){
	 	if (!cellulesVivantes.contains(c))	return cellulesVivantes.add(c);
	 	return false;
	}

	/**
	 * Calcule le nombre de cellules voisines vivantes d'une cellule.
	 * 
	 * @param la cellule c
	 * @return le nombre de cellules voisines vivantes de la cellule c
	 */
	public int nbDecellulesVoisinesVivantes(Cellule c) {
		return this.cellulesVivantes.getSurroundingCellAsList(c).size();
	}
	
	/**
	 * Calcule le nombre de cellules voisines mortes d'une cellule.
	 * Il suffit de retourner 8 - n.
	 * Ou 8 represente le nombre total de cellules voisines et n le nombre de cellules voisines vivantes.
	 * 
	 * @param la cellule c
	 * @return le nombre de cellules voisines mortes de la cellule c
	 */
	public int nbDecellulesVoisinesMortes(Cellule c) {
		return 8 - nbDecellulesVoisinesVivantes(c); 
	}
	
	/**
	 * Supprime de la liste des cellules vivantes celles mortes apres un tour.
	 * 
	 * @param l la liste des cellules a faire mourir
	 */
	private void supprimerCellulesMortes(CopyOnWriteArrayList<Cellule> cellulesMortes) {
		cellulesVivantes.removeAll(cellulesMortes);
	}
	
	/**
	 * Ajoute a la liste des cellules vivantes les nouvelles cellules qui doivent naitre.
	 * 
	 * @param l la liste des cellules a faire naitre
	 */
	private void ajouterCellulesAFaireNaitre(CopyOnWriteArrayList<Cellule> cellulesAFaireNaitre) {
		cellulesVivantes.addAll(cellulesAFaireNaitre);
	}
	
	/**
	 * Retourne la liste des cellules a supprimer.
	 * 
	 * @return
	 */
	private void cellulesASupprimerUpdate() {
		for (Cellule c : this.cellulesVivantes.getList(this.boundary)) {
			if (!regles.getVivanteResteEnVie().contains(nbDecellulesVoisinesVivantes(c))) {
				this.cellulesASupprimer.add(c);
			}
		}
	}
	
	/**
	 * Liste toutes les cellules a faire naitre.
	 * 
	 * @return la liste des cellules a ajouter a la liste des cellules vivantes
	 */
	public QuadTree cellulesAFaireNaitreUpdate() {
		QuadTree cellulesMortesDejaVisitees = new QuadTree(this.boundary, this.capacity); // Liste des cellules mortes deja visitees pour inviter de traiter plusieurs fois un meme point
		for (Cellule c : this.cellulesVivantes.getList(this.boundary)) { // Pour toutes les cellules vivantes,
			for (Cellule d : getCellulesVoisinesMortes(c)) { // telles que pour toutes leurs cellules voisines mortes,
				if (!cellulesMortesDejaVisitees.contains(d)) { // si celle-ci n'a pas deja visitee
					cellulesMortesDejaVisitees.add(d);
					if (regles.getMortePrendVie().contains(nbDecellulesVoisinesVivantes(d)) && !this.cellulesAFaireNaitre.contains(d)) {
						this.cellulesAFaireNaitre.add(d);
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
	 * Place des cellules aleatoirement (par rapport au centre de coordonnees (0, 0)) dans un certain rectangle.
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

	public Regles getRegles(){
		return this.regles;
	}

	public void setRegles(Regles nouvelleR){
		regles = nouvelleR;
	}
	
}
