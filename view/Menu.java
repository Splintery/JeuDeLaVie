package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import controller.Controller;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton playButton = new JButton("Lecture");
	private JButton pauseButton = new JButton("Pause");
	private JButton refreshButton = new JButton("Clear");
	private JButton reglesButton = new JButton("Règles");
	private JSlider vitesseSlider = new JSlider(0, 100);
	public static int nbr=0;
	private JLabel label = new JLabel();
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
	    
	    vitesseSlider.setBounds(100, 240, 100, 30);
	    add(vitesseSlider);
	    
	    label.setBounds(120, 280, 100, 30);
	    add(label);
	    
	    reglesButton.setBounds(100, 400, 100, 30);
	    add(reglesButton);
	    
	    label.setText("Vitesse : " + this.vitesseSlider.getValue() + "%");
	    //this.vitesseSlider.addChangeListener(this);
	    
	    refreshButton.setBounds(100, 500, 100, 30);
	    add(refreshButton);

		nbrTour.setBounds(100, 650, 100, 50);
		nbrTour.setForeground(Color.RED);
		add(nbrTour);
	}
	
	public void stateChanged(ChangeEvent e) {
		label.setText("Vitesse : " + this.vitesseSlider.getValue() + "%");
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
	
	public void addreglesButtonListener(ActionListener l) {
		reglesButton.addActionListener(l);
	}
	
	public void addRefreshButtonListener(ActionListener l) {
		refreshButton.addActionListener(l);
	}

}