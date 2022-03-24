package controller;

import model.Model;
import view.GameFrame;

/**
 * 
 * @author 
 *
 */
public class Controller implements Runnable {
	
	public GameFrame view;
	public Model model;

	private Thread gameLoopThread;
	
	private int ups = 1;
	private double timePerUpdate = 1000000000.0 / ups;
	private final int FPS_SET = 144;
	private double timePerFrame = 1000000000.0 / FPS_SET;
	// Le jeu est en pause par default
	private boolean exit = true;


	public Controller() {
		this.view = new GameFrame(this);
		this.model = new Model(this);
		
		gameLoopThread = new Thread(this);
		addPlayButtonListener();	
		addPauseButtonListener();

		startGameLoop();
	}
	
	/**
	 * Pour l'instant: fait jouer 1 seul tour par click.
	 */
	private void addPlayButtonListener() {
		view.menuPanel.addPlayButtonListener(e -> {
			resumeUpdateLoop();
		});
	}
	
	private void addPauseButtonListener() {
		view.menuPanel.addPauseButtonListener(e -> {
			stopUpdateLoop();
		});
	}
	
	public void run() {

		// On a 2 champs pour traquer les updates
		int updates = 0;
		double updateTracker = 0;
		// Ainsi que 2 champs pour traquer les frames
		int frames = 0;
		double frameTracker = 0;


		// Ces 2 champs sont pour compter le temps en nanoSeconde entre deux passage dans 
		// la boucle while
		long lastCheck = System.currentTimeMillis();
		long previousTime = System.nanoTime();
		

		while(true) {
			long currentTime = System.nanoTime();

			frameTracker += (currentTime - previousTime) / timePerFrame;
			// On ne mets jamais en pause l'affichage
			if (frameTracker >= 1) {
				this.view.repaint();
				frames++;
				frameTracker--;
			}



			updateTracker += (currentTime - previousTime) / timePerUpdate;
			// Si le jeu est en pause alors exit = true
			if (!exit) {
				if (updateTracker >= 1) {
					update();
					this.view.menuPanel.updateRound();
					updates++;
					updateTracker--;
				}
			}
			// On ne rentre dans ce if que si au moins 1 seconde viens de passer
			// Puis on affiche les FPS ainsi que les UPS
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS :"+frames+"\nUPS :"+updates);
				updates = 0;
				frames = 0;
			}
			previousTime = currentTime;

		}
	}
	private void startGameLoop() {
		gameLoopThread.start();
	}

	private void resumeUpdateLoop() {
		if (exit) {
			this.exit = false;
			this.view.menuPanel.playButton.setEnabled(false);
			this.view.menuPanel.pauseButton.setEnabled(true);
		}
		
	}
	private void stopUpdateLoop() {
		if (!exit) {
			this.exit = true;
			this.view.menuPanel.playButton.setEnabled(true);
			this.view.menuPanel.pauseButton.setEnabled(false);
		}
	}
	
	private void update() {
		this.model.update();
	}
	public void setUps(int newUps) {
		this.ups = newUps;
		this.timePerUpdate = 1000000000.0 / ups;
	}
}
