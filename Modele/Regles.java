package Modele;

import java.util.LinkedList;

public class Regles {
	private LinkedList<Integer> mortePrendVie; // Cette liste contient les nombres de cellules voisines en vie qu'il faut � une cellule morte pour prendre vie.
	private LinkedList<Integer> vivanteResteEnVie;//Cette liste contient les nombres de cellules voisines en vie qu'il faut � une cellule vivante pour rester en vie.

	public Regles(LinkedList<Integer> mortePrendVie,LinkedList<Integer> vivanteResteEnVie) {
		this.mortePrendVie=mortePrendVie;
		this.vivanteResteEnVie=vivanteResteEnVie;
	}

public static void regle1(Cellule c){  //Toute cellule vivante qui a moins de deux voisines vivantes meurt.
	if(GrilleM.celluleVoisineV(c)<2){
		GrilleM.removeCelluleV(c.getX(), c.getY());
	}
}

public static void regle3(Cellule c){ //Toute cellule qui a plus de trois voisines vivantes meurt.
	if(GrilleM.celluleVoisineV(c)>3){
		GrilleM.removeCelluleV(c.getX(), c.getY());
	}
}

public static void testRegle(){
	for(Cellule c:GrilleM.getCelluleV()){
		regle1(c);
		regle3(c);
	}
}
}
