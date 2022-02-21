package controller;

import java.util.concurrent.TimeUnit;

import model.Model;
import view.Fenetre;
import model.Jeu;

/**
 * 
 * @author 
 *
 */
public class Controller {
	
	/**
	 * Vue associ�e au contr�leur.
	 */
	public Fenetre view;
	
	/**
	 * Mod�le associ� au contr�leur.
	 */
	public Model model;
	
	/**
	 * Constructeur du Controller.
	 */
	public Controller() {
		this.view = new Fenetre(this);
		this.model = new Model(this);
		
		addPlayButtonListener();	
		addPauseButtonListener();
	}
	
	/**
	 * Pour l'instant: fait jouer 1 seul tour par click.
	 */
	private void addPlayButtonListener() {
		view.menu.addPlayButtonListener(e -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			model.update();
			view.grille.refresh();
		});
	}
	
	private void addPauseButtonListener() {
		view.menu.addPauseButtonListener(e -> {
			// TODO: faire un boutton pause fonctionnel
		});
	}

}
