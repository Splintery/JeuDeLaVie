package Modele;

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
}
