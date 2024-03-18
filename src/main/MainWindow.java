package main;

import javax.swing.JFrame;
import panel.GamePanel;

/**
 * Clase para crear el frame y añadirle el Panel
 */
public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 */
	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Rojo se mueve (Ayuda)");
		
		GamePanel gamePanel = new GamePanel();
		add(gamePanel);
		
		pack();
		setLocationRelativeTo(null);
	}
	
}
