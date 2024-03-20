package main;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.panel.GamePanel;

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
		
		GamePanel gamePanel;
		try {
			gamePanel = new GamePanel();
			
			add(gamePanel);
			
			pack();
			setLocationRelativeTo(null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han cargado los archivos correctamente", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
