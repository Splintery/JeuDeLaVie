
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.Controller;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton playButton = new JButton("Lecture");
	private JButton pauseButton = new JButton("Pause");
	private JSlider zoomSlider = new JSlider(0, 100);
	
	public Controller controller;
	
	private boolean isPaused;

	public Menu(Controller controller){
		this.controller = controller;
	    //this.setSize(300,300);
	    this.setBounds(700, 0, 700, 700);
	    this.setBackground(Color.DARK_GRAY);
	    this.setLayout(null);
	    this.setVisible(true);
	    
	    playButton.addActionListener(e -> {
			while(!isPaused) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				this.controller.grille.update();
				this.controller.view.grille.refresh();
			}
			
		});
	    
	    pauseButton.addActionListener(e -> {
	    	isPaused = !isPaused;
	    });
	    
	    playButton.setBounds(100, 100, 100, 30);
	    add(playButton);
	    
	    pauseButton.setBounds(100, 170, 100, 30);
	    add(pauseButton);
	    
	    zoomSlider.setBounds(100, 240, 100, 30);
	    add(zoomSlider);
	}
	
	public void addPlayButtonListener(ActionListener l) {
		playButton.addActionListener(l);
	}
	
	public void addPauseButtonListener(ActionListener l) {
		pauseButton.addActionListener(l);
	}

}