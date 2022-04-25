package view;

import controller.Controller;
import java.awt.GridLayout;



import javax.swing.JPanel;


public class MenuHelp extends JPanel{
    Controller controller;

    public MenuHelp(Controller c){
        this.controller=c;
        this.setSize(600,200);
        this.setVisible(true);
        this.setLayout(new GridLayout(4,1));
    }

}
