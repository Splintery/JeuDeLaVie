package view;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import controller.Controller;
import model.Model;

public class GameOfLifeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public int initWindowWidth;
	public int initWindowHeight;
	public GameScreen gameScreen;

	public GameOfLifeFrame(Controller controller, int width, int height) {
		//Le rectangle recupere possede 4 champs, x, y, width et height
		//Il represente la taille maximale disponible pour afficher une fenetre en pixels
		Rectangle tmpBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.gameScreen = new GameScreen(controller);
		frameSetUp(width, height);
	}
	private void frameSetUp(int width, int height) {
		this.setTitle("Game of Life");
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(this.gameScreen);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Model board = new Model(null);
		board.ajouterCellule(new model.Cellule(10, 5));
		board.ajouterCellule(new model.Cellule(4, 9));
		board.ajouterCellule(new model.Cellule(7, 12));
		board.ajouterCellule(new model.Cellule(-50, -23));

//		GameOfLifeFrame test = new GameOfLifeFrame(board, 1000, 500);
	}
}