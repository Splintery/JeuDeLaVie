package view;

import controller.Controller;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuHelp extends JPanel{
    Controller controller;
    public JComboBox<String> menuDeroulant;
    public JButton Valider;

    public MenuHelp(Controller c){
        this.controller=c;
        this.setSize(200,200);
        this.setVisible(true);
        String[] options={"acorn","gosper_glider_gun","pulsar","p42_glidershuttle","p856glidergun","spacefiller"};
        menuDeroulant=new JComboBox<>(options);
        Valider = new JButton("Valider");
        this.add(new JLabel("Selectionnez une grille : "));
        this.add(menuDeroulant);
        this.add(Valider);
    }
    
    public void addValiderButtonListener(ActionListener l) {
		Valider.addActionListener(l);
	}

}
