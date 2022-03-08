package view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.Controller;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton playButton = new JButton("Lecture");
	private JButton pauseButton = new JButton("Pause");
	private JSlider zoomSlider = new JSlider(0, 100);
	public static int nbr=0;
	private static JLabel nbrTour = new JLabel("Tour : "+nbr);
	
	public Controller controller;

	public Menu(Controller controller){
		this.controller = controller;
	    //this.setSize(300,300);
	    this.setBounds(700, 0, 700, 700);
	    this.setBackground(Color.DARK_GRAY);
	    this.setLayout(null);
	    this.setVisible(true);
	    
	    playButton.setBounds(100, 100, 100, 30);
	    add(playButton);
	    
	    pauseButton.setBounds(100, 170, 100, 30);
	    add(pauseButton);
	    
	    zoomSlider.setBounds(100, 240, 100, 30);
	    add(zoomSlider);

		nbrTour.setBounds(100, 650, 100, 50);
		nbrTour.setForeground(Color.RED);
		add(nbrTour);
	}

	public static void updateTour(){
		nbr++;
		nbrTour.setText("Tour : "+nbr);
		nbrTour.repaint();
	}
	


	
	public void addPlayButtonListener(ActionListener l) {
		playButton.addActionListener(l);
	}
	
	public void addPauseButtonListener(ActionListener l) {
		pauseButton.addActionListener(l);
	}

}