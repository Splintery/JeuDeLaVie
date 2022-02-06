package Modele;

public class Jeu {
	private Grille grille;
	private Regles regles;
	
	public Jeu() {
		grille=new Grille();
	}
	
	public Grille getGrille() {
		return this.grille;
	}
	
	public Regles getRegles() {
		return this.regles;
	}
}
