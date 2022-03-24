package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

// import java.awt.GraphicsEnvironment;
// import java.awt.Rectangle;

import controller.Controller;

public class GameFrame extends JFrame {
	
	public MenuPanel menuPanel;
	public GamePanel gamePanel;

	private final int FPS_SET = 144;
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	public GameFrame(Controller controller) {
	    this.setLayout(null);

	    this.gamePanel = new GamePanel(controller);
	    this.menuPanel = new MenuPanel(controller);

	    frameSetUp(1000, 740);
	}

	private void frameSetUp(int width, int height) {
		this.setTitle("Game of Life");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(this.gamePanel);
		this.getContentPane().add(this.menuPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}