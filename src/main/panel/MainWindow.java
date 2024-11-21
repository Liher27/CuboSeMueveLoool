package main.panel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pokemonFight.manager.StatusSingleton;
import pokemonFight.view.FightPanel;

/**
 * Clase para crear el frame y añadirle el Panel
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	FightPanel fightPanel = null;
	MainPanel mainPanel = null;

	/**
	 * constructor
	 */
	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Rojo se mueve (Ayuda)");
		setMainPanel();
		pack();

		setLocationRelativeTo(null);

		StatusSingleton.getInstance().setMainWindow(this);
	}

	public void setMainPanel() {
		try {
			mainPanel = new MainPanel();
			if (null != fightPanel) {
				fightPanel.setVisible(false);
			}
			add(mainPanel);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el panel de aventura correctamente", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void setFightPanel() {
		try {
			fightPanel = new FightPanel();
			add(fightPanel);

			mainPanel.setVisible(false);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					fightPanel.stopBattle();
					System.exit(0);
				}
			});
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el panel de combate", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}

}
