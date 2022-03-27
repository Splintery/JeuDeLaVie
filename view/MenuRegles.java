package view;

import controller.Controller;
import model.Regles;
import java.util.List;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MenuRegles extends JFrame{
    JPanel menuRegle = new JPanel();
    JLabel[] titre = new JLabel[3];
    JSlider[] slider= new JSlider[3];
    JButton submit= new JButton("Submit");
    Controller c;

    public MenuRegles(Controller c){
        this.c=c;
        this.setSize(500,500);
        this.setVisible(true);
        this.getContentPane().add(menuRegle);

        titre[0]=new JLabel("Cellule minimum");
        titre[1]=new JLabel("Cellule max");
        titre[2]=new JLabel("Cellule morte revient a la vie");

        slider[0]=new JSlider(1,8,c.model.getRegles().getVivanteResteEnVie().getFirst()); // slider pour le min
        slider[0].setMajorTickSpacing(1);
        slider[0].setSnapToTicks(true);
        slider[0].setPaintTicks(true);

        slider[1]=new JSlider(1,8,c.model.getRegles().getVivanteResteEnVie().getLast());
        slider[1].setMajorTickSpacing(1);
        slider[1].setSnapToTicks(true);
        slider[1].setPaintTicks(true);

        slider[2]=new JSlider(1,8,c.model.getRegles().getMortePrendVie().get(0));
        slider[2].setMajorTickSpacing(1);
        slider[2].setSnapToTicks(true);
        slider[2].setPaintTicks(true);

        this.menuRegle.add(titre[0]);
        this.menuRegle.add(slider[0]);

        this.menuRegle.add(titre[1]);
        this.menuRegle.add(slider[1]);

        this.menuRegle.add(titre[2]);
        this.menuRegle.add(slider[2]);

        this.menuRegle.add(submit);

        submit.addActionListener(e-> {
            if(slider[0].getValue() >= slider[1].getValue())this.dispose(); 
            LinkedList<Integer> s1=new LinkedList<Integer>();
            LinkedList<Integer> s2=new LinkedList<Integer>(List.of(slider[2].getValue()));
            for(int i=slider[0].getValue();i<=slider[1].getValue();i++){
                s1.add(i);
            }
            Regles r = new Regles(1,s1,s2);
            c.model.setRegles(r);
           this.dispose();
        });

    }

}
