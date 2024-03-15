package main;

import javax.swing.JFrame;
import panel.GamePanel;

public class MainWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Cubo que se mueve (Ayuda)");
		
		GamePanel gamePanel = new GamePanel();
		add(gamePanel);
		
		pack();
		
		setLocationRelativeTo(null);
	}
	
}
