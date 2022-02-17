package Vue;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import Modele.GrilleM;
import Modele.Cellule;

public class GrilleV extends JFrame implements MouseInputListener{

public GrilleV(){
    this.setSize(1000,1000);
    this.setResizable(true);
    this.setBackground(Color.BLUE);
    this.setLayout(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    addMouseListener(this);
    addMouseMotionListener(this);
}

public void refresh(){
     for(Cellule c: GrilleM.getCelluleV()){
        JPanel celluleV = new JPanel();
        celluleV.setBackground(Color.BLACK);
        celluleV.setBounds(c.getX(),c.getY(),20,20);
        celluleV.setVisible(true);
        this.getContentPane().add(celluleV);
    }
    repaint();
}

@Override
public void mouseClicked(MouseEvent e) {
    if(Jeu.compteur<10){
    int x = e.getX()-(e.getX()%20); // moins lui meme %30 pour obtenir un jolie chiffre pour travailler plus facilement apres ( ex : x=97 -> x=80) et aussi pour aligner les cellulles
    int y = e.getY()-(e.getY()%20)-this.getInsets().top+11; // +1 juste pour avoir un chiffre rond, car avec des test on sait que inset.top=31
    Cellule a = new Cellule(x,y);
        if(GrilleM.estVivante(x,y)==false){   //verifier qu'on a pas déja la cellule
        GrilleM.addCelluleV(a);
        //System.out.println("Clicked ! "+"x="+x+" y="+y);
        //System.out.println(test);
        Jeu.compteur++;
        refresh();
        System.out.println(GrilleM.getCelluleV().getLast().getX());
        System.out.println(GrilleM.getCelluleV().getLast().getY());
        System.out.println(Jeu.compteur);
        }
    }
}

@Override
public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
}

@Override
public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
}

@Override
public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
}

@Override
public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
}

@Override
public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
}

@Override
public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    
}
}