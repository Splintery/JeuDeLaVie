package Modele;

import java.util.LinkedList;

public class Regles {
	private LinkedList<Integer> mortePrendVie; // Cette liste contient les nombres de cellules voisines en vie qu'il faut à une cellule morte pour prendre vie.
	private LinkedList<Integer> vivanteResteEnVie;//Cette liste contient les nombres de cellules voisines en vie qu'il faut à une cellule vivante pour rester en vie.

	public Regles(LinkedList<Integer> mortePrendVie,LinkedList<Integer> vivanteResteEnVie) {
		this.mortePrendVie=mortePrendVie;
		this.vivanteResteEnVie=vivanteResteEnVie;
	}

}
