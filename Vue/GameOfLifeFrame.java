package Vue;

import javax.swing.*;
import java.awt.*;
import Vue.GameScreen;
import Modele.GrilleM;

public class GameOfLifeFrame extends JFrame {

	public int initWindowWidth;
	public int initWindowHeight;
	public GameScreen gameScreen;

	public GameOfLifeFrame(GrilleM board, int width, int height) {
		//Le rectangle recupere possede 4 champs, x, y, width et height
		//Il represente la taille maximale disponible pour afficher une fenetre en pixels
		Rectangle tmpBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.gameScreen = new GameScreen(board);
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
		GrilleM board = new GrilleM();
		board.addCelluleV(new Modele.Cellule(10, 5));
		board.addCelluleV(new Modele.Cellule(4, 9));
		board.addCelluleV(new Modele.Cellule(7, 12));
		board.addCelluleV(new Modele.Cellule(-50, -23));

		GameOfLifeFrame test = new GameOfLifeFrame(board, 1000, 500);
	}
}