package view;

import controller.Controller;
import model.Regles;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class MenuRegles extends JPanel{
    public JPanel reglesVivantes=new JPanel(new GridLayout(1,8));
	public JPanel reglesMortes=new JPanel(new GridLayout(1,8));
	public JLabel[] titre = new JLabel[2];
	public JCheckBox[] cocheVivantes=new JCheckBox[8];
	public JCheckBox[] cocheMortes=new JCheckBox[8];
    Controller controller;

    public MenuRegles(Controller c){
        this.controller=c;
        this.setSize(600,200);
        this.setVisible(true);
        this.setLayout(new GridLayout(4,1));
        titre[0]=new JLabel("Nombre de cellules voisines vivantes pour\n qu'une cellule vivante reste en vie :");
		titre[0].setOpaque(false);
	    titre[1]=new JLabel("Nombre de cellules voisines vivantes pour\n qu'une cellule morte revienne a la vie :");
		titre[1].setOpaque(false);
	    //reglesVivantes.add(titre[0]);
	    //reglesMortes.add(titre[1]);
	    reglesVivantes.setBackground(new Color(200,200,200));
	    reglesVivantes.setVisible(true);
	    reglesMortes.setBackground(new Color(200,200,200));
	    reglesMortes.setVisible(true);
	    for(int i=0;i<8;i++) {
	    	cocheVivantes[i]=new JCheckBox(Integer.toString(i+1));
	    	cocheMortes[i]=new JCheckBox(Integer.toString(i+1));
	    	reglesVivantes.add(cocheVivantes[i]);
	    	reglesMortes.add(cocheMortes[i]);
	    	cocheVivantes[i].addItemListener(this::boxes_itemStateChanged);
	    	cocheMortes[i].addItemListener(this::boxes_itemStateChanged);
	    	cocheVivantes[i].setBackground(new Color(200,200,200));
	    	cocheMortes[i].setBackground(new Color(200,200,200));
	    }
	    cocheVivantes[1].setSelected(true);
	    cocheVivantes[2].setSelected(true);
	    cocheMortes[0].setSelected(true);
	    JPanel titre0=new JPanel();
	    titre0.setBackground(new Color(200,200,200));
	    titre0.add(titre[0]);
	    JPanel titre1=new JPanel();
	    titre1.setBackground(new Color(200,200,200));
	    titre1.add(titre[1]);
	    this.add(titre0);
	    this.add(reglesVivantes);
	    this.add(titre1);
	    this.add(reglesMortes);

       

    }
    
    void boxes_itemStateChanged(ItemEvent e) {
        for(int i=1;i<9;i++) {
     	   boolean selected=cocheVivantes[i-1].isSelected();
     	   boolean contains=controller.model.getRegles().getVivanteResteEnVie().contains(i);
     	   if(selected) {
     		   if(!contains)
     		   controller.model.getRegles().getVivanteResteEnVie().add((Integer)i);
     	   }else {
     		   if(contains) {
     			   controller.model.getRegles().getVivanteResteEnVie().remove((Integer)i);
     		   }
     	   }
        }
     }

}
