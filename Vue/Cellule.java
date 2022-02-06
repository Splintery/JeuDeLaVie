package Vue;
import java.awt.Color;

import javax.swing.*;

public class Cellule extends JPanel{
public int x;
public int y;

public Cellule(int x,int y){
    this.setBackground(Color.BLACK);
    this.setBounds(x,y,20,20);
    this.setVisible(true);
}
}
