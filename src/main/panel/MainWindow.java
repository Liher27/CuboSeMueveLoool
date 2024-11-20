package main.panel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pokemonFight.view.FightPanel;

/**
 * Clase para crear el frame y añadirle el Panel
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 */
	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Rojo se mueve (Ayuda)");

		try {
			MainPanel gamePanel = new MainPanel();

			
			
//			FightPanel fightPanel = new FightPanel();

			add(gamePanel);
//			add(fightPanel);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
//					fightPanel.stopBattle();
					System.exit(0);
				}
			});
			
			pack();

			setLocationRelativeTo(null);
			
//			fightPanel.setVisible(false);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han cargado los archivos correctamente", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}

	}

}
