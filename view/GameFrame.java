package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

import java.awt.Dimension;


public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public MenuPanel menuPanel;
	public GamePanel gamePanel;

	private Dimension oldDimension;
	private final int MENU_WIDTH = 300;

	private Dimension nonFullscreenDimension;
	public boolean isFullscreen = false;
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	public GameFrame(Controller controller) {
	    this.setLayout(null);

	    this.gamePanel = new GamePanel(controller);
	    this.menuPanel = new MenuPanel(controller);

	    frameSetUp(1080, 720);
	}

	private void frameSetUp(int width, int height) {
		this.setTitle("Game of Life");
		this.setSize(width, height);
		this.oldDimension = this.getSize();
		this.nonFullscreenDimension = this.getSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(this.gamePanel);
		this.getContentPane().add(this.menuPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	public void repaint() {
		Dimension currentDimension = this.getSize();
		if (!currentDimension.equals(this.oldDimension)) {
			this.menuPanel.setBounds(currentDimension.width - this.MENU_WIDTH, 0, this.MENU_WIDTH, currentDimension.height);
			this.gamePanel.setBounds(0, 0, currentDimension.width - this.MENU_WIDTH, currentDimension.height);
			oldDimension = currentDimension;
		}
		super.repaint();
	}
	// public void setFullScreen() {
	// 	if (!this.isFullscreen) {
	// 		this.nonFullscreenDimension = this.getSize();
	// 		this.isFullscreen = true;
	// 		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	// 		this.dispose();
	// 		this.setUndecorated(true);
	// 		this.pack();
	// 		this.setVisible(true);
	// 	}
		
	// }
	// public void escapeFullScreen() {
	// 	if (this.isFullscreen) {
	// 		this.isFullscreen = false;
	// 		this.dispose();
	// 		this.setUndecorated(false);
	// 		this.pack();
	// 		this.setSize(this.nonFullscreenDimension.width, this.nonFullscreenDimension.height);
	// 		this.setVisible(true);
	// 	}
	// }
}