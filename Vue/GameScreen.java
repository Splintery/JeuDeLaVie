package Vue;

import javax.swing.*;
import java.awt.*;
import Modele.*;

public class GameScreen extends JPanel {

	protected int midWidth;
	protected int midHeight;

	public GrilleM board;

	protected int sizeCell;
	protected int maxX;
	protected int maxY;

	public GameScreen(GrilleM board) {
		this.midWidth = this.getSize().width/2;
		this.midHeight = this.getSize().height/2;
		this.board = board;
		//Pour le moment la taille des Cellules est fixee a 10
		sizeCell = 10;
		//La taille de maxX et maxY depend de sizeCell et la taille du JPanel
		//IL faudras que ca evolue avec la taille du JPanel
		maxX = this.getSize().width / this.sizeCell;
		maxY = this.getSize().height / this.sizeCell;
	}
	public void paintComponent(Graphics g) {
		//On actualize les valeurs de midWidth et midHeight
		this.actualizeSize();
		//On clear le panel
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		//En gros on repaint en blanc un rectangle de la taille du JPanel
		g.setColor(Color.BLACK);
		this.paintCell(g);
	}

	private void actualizeSize() {
		this.midWidth = this.getSize().width/2;
		this.midHeight = this.getSize().height/2;

		maxX = this.getSize().width / this.sizeCell;
		maxY = this.getSize().height / this.sizeCell;
	}
	private void paintCell(Graphics g) {
		for (Modele.Cellule c : this.board.getCelluleV()) {
			if (isValid(c)) {
				g.fillRect(this.midWidth + c.getX()*sizeCell, this.midHeight + c.getY()*sizeCell, this.sizeCell, this.sizeCell);
			}
		}
	}
	private boolean isValid(Modele.Cellule c) {
		//La verif consiste a verifier si la cellule est comprise
		//entre (-maxX, -maxY) et (maxX, maxY)
		return (c.getX() < this.maxX && c.getY() < this.maxY) && (c.getX() > -this.maxX && c.getY() > -this.maxY);
	}
}