package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.event.ChangeEvent;

import controller.Controller;
import view.MenuRegles;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	

	public JButton playButton = new JButton("Play");
	public JButton pauseButton = new JButton("Pause");
	public JButton refreshButton = new JButton("Clear");
	public JButton rulesButton = new JButton("Rules");
	public JButton fullscreenButton = new JButton("Fullscreen");
	public JButton escapeFullscreenButton = new JButton("Windowed");
	public JPanel reglesVivantes=new JPanel(new GridLayout(1,8));
	public JPanel reglesMortes=new JPanel(new GridLayout(1,8));
	public JLabel[] titre = new JLabel[2];
	public JSlider speedSlider = new JSlider(0, 100, 1);
	public static int nbr=0;
	public JCheckBox[] cocheVivantes=new JCheckBox[8];
	public JCheckBox[] cocheMortes=new JCheckBox[8];
	public static JLabel nbrRound = new JLabel("Round : "+nbr);
	public JLabel speedLabel = new JLabel();
	
	public Controller controller;

	public MenuPanel(Controller controller){
		this.controller = controller;
	    //this.setSize(300,300);
	    this.setBounds(700, 0, 700, 700);
	    this.setBackground(Color.DARK_GRAY);
	    this.setLayout(null);
	    this.setVisible(true);
	    
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
		
		titre[0]=new JLabel("Cellule vivante reste en vie");
	    titre[1]=new JLabel("Cellule morte revient a la vie");
	    //reglesVivantes.add(titre[0]);
	    //reglesMortes.add(titre[1]);
	    reglesVivantes.setBounds(0, 325,700,70);
	    reglesVivantes.setBackground(Color.WHITE);
	    reglesVivantes.setVisible(true);
	    reglesMortes.setBounds(0, 395,700,70);
	    reglesMortes.setBackground(Color.PINK);
	    reglesMortes.setVisible(true);
	    for(int i=1;i<9;i++) {
	    	cocheVivantes[i-1]=new JCheckBox(Integer.toString(i));
	    	cocheMortes[i-1]=new JCheckBox(Integer.toString(i));
	    	reglesVivantes.add(cocheVivantes[i-1]);
	    	reglesMortes.add(cocheMortes[i-1]);
	    	cocheVivantes[i-1].addItemListener(this::boxes_itemStateChanged);
	    	cocheMortes[i-1].addItemListener(this::boxes_itemStateChanged);
	    }
	
	    add(reglesVivantes);
	    add(reglesMortes);
	    
		rulesButton.setBounds(100, 500, 100, 30);
		add(rulesButton);

		refreshButton.setBounds(100, 550, 100, 30);
		add(refreshButton);

		fullscreenButton.setBounds(100, 600, 100, 30);
		add(fullscreenButton);

		escapeFullscreenButton.setBounds(100, 700, 100, 30);
		add(escapeFullscreenButton);

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


		//A metre dans le fichier controlleur jlai mis la car je sais pas comment le mettre
		rulesButton.addActionListener(e-> {
		System.out.println("test");
		new MenuRegles(controller);
		});

	}
	

	public static void updateRound(){
		nbr++;
		nbrRound.setText("Round : "+nbr);
		nbrRound.repaint();
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
	
	public void addPlayButtonListener(ActionListener l) {
		playButton.addActionListener(l);
	}
	public void addPauseButtonListener(ActionListener l) {
		pauseButton.addActionListener(l);
	}
	public void addRulesButtonListener(ActionListener l) {
		rulesButton.addActionListener(l);
	}
	public void addRefreshButtonListener(ActionListener l) {
		refreshButton.addActionListener(l);
	}
	public void addFullscreenButtonListener(ActionListener l) {
		this.fullscreenButton.addActionListener(l);
	}
	public void addEscapeFullscreenButtonListener(ActionListener l) {
		this.escapeFullscreenButton.addActionListener(l);
	}
}