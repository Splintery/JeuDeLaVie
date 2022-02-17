package view;

import controller.Controller;

public class View {
	
	public Controller controller;
	public GameScreen gameScreen;
	public Fenetre fenetre;
	public Grille grille;
	public Menu menu;
	
	public View(Controller controller) {
		this.controller = controller;
		this.grille = new Grille(controller);
		this.gameScreen = new GameScreen(controller);
		this.fenetre = new Fenetre(controller);
		this.menu = new Menu(controller);
	}
	
}
