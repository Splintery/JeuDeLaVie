package view;

import controller.Controller;
import model.Cellule;
import model.Model;
import view.MenuPanel;

import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements KeyListener, MouseWheelListener, MouseMotionListener, MouseListener{

	private static final long serialVersionUID = 1L;

	protected int midWidth;
	protected int midHeight;
	//Pour le moment la taille des Cellules est fixee a 10
	protected int sizeCell = 30;
	protected int maxX;
	protected int maxY;

	//Ces deux entiers representent des coordonnees x et y, elles sont utilisees pour
	//decaler les cellules dans la vue, les cellules ne sont pas alterees
	private int decalageX = 0;
	private int decalageY = 0;

	private Point prevPoint = new Point(0, 0);
	private Point currentPoint = new Point(0, 0);
	private boolean isInSameCell = true;

	private Controller controller;
	
	public GamePanel(Controller controller) {
		this.controller = controller;
		this.setBackground(Color.gray);

		this.setSize(700,700);
	    addMouseMotionListener(this);
	    addKeyListener(this);
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);

	    this.setVisible(true);

	    this.midWidth = this.getSize().width/2;
		this.midHeight = this.getSize().height/2;
		//La taille de maxX et maxY depend de sizeCell et la taille du JPanel
		//IL faudras que ca evolue avec la taille du JPanel
		maxX = this.getSize().width / this.sizeCell;
		maxY = this.getSize().height / this.sizeCell;

	}

	public void paintComponent(Graphics g) {
		//On update les valeurs de midWidth et midHeight
		//Ainsi que le nombre max de Cellules affichable
		this.updateSize();
		//On clear le panel
		super.paintComponent(g);
		// this.drawCross(g);
		//En gros on repaint avec la couleur du fond un rectangle de la taille du JPanel
		this.paintCell(g);
	}

	private void updateSize() {
		this.midWidth = this.getSize().width/2;
		this.midHeight = this.getSize().height/2;

		maxX = this.getSize().width / this.sizeCell;
		maxY = this.getSize().height / this.sizeCell;
	}
	private void drawCross(Graphics g) {
		g.drawLine(this.midWidth, 0, this.midWidth, (int) (this.getSize().getHeight() * 2));
		g.drawLine(0, this.midHeight, (int) (this.getSize().getWidth() * 2), this.midHeight);

	}
	private void paintCell(Graphics g) {
		for (Cellule c : this.controller.model.getCellulesVivantes()) {
			if (isValid(c)) {
					g.setColor(c.getColor());
					Point pointToPaint = convertToPixel(c);
					g.fillRect((int) pointToPaint.getX(), (int) pointToPaint.getY(), this.sizeCell, this.sizeCell);
			}
		}
	}

	private boolean isValid(Cellule c) {
		//La verif consiste a verifier si la cellule est comprise
		//entre (-maxX, -maxY) et (maxX, maxY)
		return (c.getX()+this.decalageX < this.maxX && c.getY()+this.decalageY < this.maxY) && (c.getX()+this.decalageX > -this.maxX && c.getY()+this.decalageY > -this.maxY);
	}

	private Point convertToPixel(Cellule c) {
		return new Point(this.midWidth + (c.getX()+this.decalageX)*sizeCell, this.midHeight + (-c.getY()+this.decalageY)*sizeCell);
	}
	private Point convertToActualCoordinate(Point p) {
		return new Point((int)(Math.floor((p.getX()-this.midWidth) / this.sizeCell) - this.decalageX), -(int)(Math.floor((p.getY()-this.midHeight) / this.sizeCell) - this.decalageY));
	}

	public void mouseClicked(MouseEvent e) {
		Cellule c = new Cellule(convertToActualCoordinate(e.getPoint()));
	    if(this.controller.model.estVivante(c)) {
	    	controller.model.retirerCellule(c);
	    }else {
	    	controller.model.ajouterCellule(c);
	    }
	}
	
	
	//Lorsque la souris est appuye qqPart sur le JPanel on garde en memoire un point
	public void mousePressed(MouseEvent e) {
		this.prevPoint = this.convertToActualCoordinate(e.getPoint());
	}
	
	//Lorsque le click de la souris est maintenu et la souris est deplace on ajoute aux valeurs
	//decalageX et decalageY (qui sont deja utilise pour deplacer les cellules avec les fleches du 
	//clavier) la distance entre le point de click et la position actuelle du curseur sur l'ecran
	public void mouseDragged(MouseEvent e) {
	    this.currentPoint = this.convertToActualCoordinate(e.getPoint());
	  
	    if (!this.currentPoint.equals(this.prevPoint)) {
	    	this.decalageX += (int)(-this.prevPoint.getX() - -this.currentPoint.getX());
			this.decalageY += (int)(this.prevPoint.getY() - this.currentPoint.getY());
			this.prevPoint = this.convertToActualCoordinate(e.getPoint());
	    }
	}

	//getWheelRotation() retourne 1 si l'on zoom vers le haut et -1 vers le bas
	public void mouseWheelMoved(MouseWheelEvent e) {
		//Ici 1000 represente la taille maximale en pixel d'une cellule
		if (e.getWheelRotation() > 0 && this.sizeCell < 1000) {
			this.sizeCell++;
		//Ici 1 represente la taille minimale en pixel d'une cellule
		} else if (e.getWheelRotation() < 0 && this.sizeCell > 1){
			this.sizeCell--;
		}
	}

	//Les valeurs 37, 38, 39, et 40 representent les fleches gauche, haut, droite et bas respectivement
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 37 :
				this.decalageX++;
				break;
			case 38 :
				this.decalageY++;
				break;
			case 39 :
				this.decalageX--;
				break;
			case 40 :
				this.decalageY--;
				break; 
		}
	}

	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}