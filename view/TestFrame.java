package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

// import java.awt.GraphicsEnvironment;
// import java.awt.Rectangle;

import controller.Controller;

public class TestFrame extends JFrame {
	
	public Menu menu;
	public TestPanel testPanel;

	private final int FPS_SET = 144;
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	public TestFrame(Controller controller) {
	    this.setLayout(null);

	    this.testPanel = new TestPanel(controller);
	    this.menu = new Menu(controller);

	    frameSetUp(1000, 740);
	}

	private void frameSetUp(int width, int height) {
		this.setTitle("Game of Life");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(this.testPanel);
		this.getContentPane().add(this.menu);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}