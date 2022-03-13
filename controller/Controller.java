package controller;

import model.Model;
import view.TestFrame;

/**
 * 
 * @author 
 *
 */
public class Controller {
	
	/**
	 * Vue associ�e au contr�leur.
	 */
	public TestFrame view;
	
	/**
	 * Mod�le associ� au contr�leur.
	 */
	public Model model;
	
	/**
	 * Constructeur du Controller.
	 */
	public Controller() {
		this.view = new TestFrame(this);
		this.model = new Model(this);
		
		addPlayButtonListener();	
		addPauseButtonListener();
	}
	
	/**
	 * Pour l'instant: fait jouer 1 seul tour par click.
	 */
	private void addPlayButtonListener() {
		view.menu.addPlayButtonListener(e -> {
			model.update();
			model.updateColor();
			view.testPanel.refresh();
		});
	}
	
	private void addPauseButtonListener() {
		view.menu.addPauseButtonListener(e -> {
			// TODO: faire un boutton pause fonctionnel
		});
	}

}
