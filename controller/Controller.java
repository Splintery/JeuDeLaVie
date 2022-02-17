package controller;

import java.util.concurrent.TimeUnit;

import model.Model;
import view.View;

public class Controller {
	
	public View view;
	public Model grille;
	//private boolean isPaused;
	
	public Controller() {
		this.view = new View(this);
		this.grille = new Model(this);
		
		/*
		this.view.menu.addPlayButtonListener(e -> {
			System.out.println("dedans");
			while(!isPaused) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				this.grille.update();
			}
			
		});
		
		
		this.view.menu.addPauseButtonListener(e -> {
			isPaused = true;
		});
		*/
	}

}
