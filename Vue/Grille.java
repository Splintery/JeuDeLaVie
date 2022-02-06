package Vue;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Grille extends JFrame implements MouseInputListener{
private LinkedList<Cellule> CelluleV;
private boolean run=false;
private int test; // juste pour tester quelque fonction en attendant d'avoir un vrai bouton pause/go

public Grille(){
    CelluleV = new LinkedList<Cellule>();
    this.setSize(1000,1000);
    this.setResizable(false);
    this.setBackground(Color.BLUE);
    this.setLayout(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    addMouseListener(this);
    addMouseMotionListener(this);
}

@Override
public void mouseClicked(MouseEvent e) {
    if(test<=10){
    int x = e.getX()-(e.getX()%20); // moins lui meme %30 pour obtenir un jolie chiffre pour travailler plus facilement apres ( ex : x=97 -> x=80) et aussi pour aligner les cellulles
    int y = e.getY()-(e.getY()%20)-this.getInsets().top+11; // +1 juste pour avoir un chiffre rond, car avec des test on sait que inset.top=31
    Cellule a = new Cellule(x,y);
    CelluleV.add(a);
    this.getContentPane().add(a);
    System.out.println("Clicked ! "+"x="+x+" y="+y);
    System.out.println(test);
    test++;
    repaint();
    }else{
        run=true;
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