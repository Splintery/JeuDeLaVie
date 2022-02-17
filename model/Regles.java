package model;

import java.util.LinkedList;

public class Regles {
	private LinkedList<Integer> mortePrendVie; // Cette liste contient les nombres de cellules voisines en vie qu'il faut � une cellule morte pour prendre vie.
	private LinkedList<Integer> vivanteResteEnVie;//Cette liste contient les nombres de cellules voisines en vie qu'il faut � une cellule vivante pour rester en vie.

	public Regles(LinkedList<Integer> mortePrendVie,LinkedList<Integer> vivanteResteEnVie) {
		this.mortePrendVie=mortePrendVie;
		this.vivanteResteEnVie=vivanteResteEnVie;
	}
	
	public LinkedList<Integer> getMortePrendVie(){
		return this.mortePrendVie;
	}
	
	public LinkedList<Integer> getVivanteResteEnVie(){
		return this.mortePrendVie;
	}
}
