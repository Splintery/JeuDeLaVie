package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;

import controller.Controller;
import view.MenuRegles;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	

	public JButton playButton = new JButton("Play");
	public JButton pauseButton = new JButton("Pause");
	public JButton clearButton = new JButton("Clear");
	public JButton rulesButton = new JButton("Rules");
	public JButton fullscreenButton = new JButton("Fullscreen");
	public JButton escapeFullscreenButton = new JButton("Windowed");
	public JButton helpButton = new JButton("Help");
	public JSlider speedSlider = new JSlider(0, 100, 1);
	public JLabel nbrRound;
	public JLabel speedLabel = new JLabel();
	public HelpFrame helpFrame;
	public MenuReglesFrame reglesFrame;
	
	public Controller controller;

	public MenuPanel(Controller controller){
		this.controller = controller;
	    //this.setSize(300,300);
	    this.setBounds(700, 0, 700, 700);
	    this.setBackground(new Color(200,200,200));
	    this.setLayout(null);
	    this.setVisible(true);
	    nbrRound=new JLabel("Round : "+controller.model.getGeneration());
	    
	    playButton.setBounds(100, 100, 100, 30);
	    add(playButton);
	    
	    pauseButton.setBounds(100, 170, 100, 30);
	    pauseButton.setEnabled(false);
	    add(pauseButton);
	    
	    speedSlider.setBounds(100, 240, 100, 30);
	    speedSlider.setSnapToTicks(true);
	    speedSlider.setMajorTickSpacing(10);
	    add(speedSlider);

		speedLabel.setBounds(120, 280, 100, 30);
		speedLabel.setText("Speed = "+1+"%");
		add(speedLabel);
		
	    
		rulesButton.setBounds(100, 350, 100, 30);
		add(rulesButton);
		

		clearButton.setBounds(100, 420, 100, 30);
		add(clearButton);

		fullscreenButton.setBounds(100, 490, 100, 30);
		add(fullscreenButton);

		escapeFullscreenButton.setBounds(100, 560, 100, 30);
		escapeFullscreenButton.setEnabled(false);
		add(escapeFullscreenButton);
		
		helpButton.setBounds(100, 630, 100, 30);
		add(helpButton);

		nbrRound.setBounds(100, 650, 100, 50);
		nbrRound.setForeground(Color.RED);
		add(nbrRound);

		this.speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				int speedValue = speedSlider.getValue();
				if (speedValue == 0) {
					speedValue++;
				}
				controller.setUps(speedValue);
				speedLabel.setText("Speed = "+speedValue+"%");
			}
		});


	}
	

	public void updateRound(){
		nbrRound.setText("Round : "+ controller.model.getGeneration());
		nbrRound.repaint();
	}
	
	public void addPlayButtonListener(ActionListener l) {
		playButton.addActionListener(l);
	}
	public void addPauseButtonListener(ActionListener l) {
		pauseButton.addActionListener(l);
	}
	public void addRulesButtonListener(ActionListener l) {
		this.rulesButton.addActionListener(l);
	}
	public void addClearButtonListener(ActionListener l) {
		clearButton.addActionListener(l);
	}
	public void addFullscreenButtonListener(ActionListener l) {
		this.fullscreenButton.addActionListener(l);
	}
	public void addEscapeFullscreenButtonListener(ActionListener l) {
		this.escapeFullscreenButton.addActionListener(l);
	}
	public void addHelpButtonListener(ActionListener l) {
		this.helpButton.addActionListener(l);
	}
}