package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import keyBoard.KeyBoard;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private KeyBoard keyBoard = new KeyBoard();
	private Thread gameThread; // Hilo sobre el cual se correra el juego
	Player player = new Player(this, keyBoard);

	// definir un sprite de 16x16 bloques
	private final int originalTileSize = 16;

	// tamaño de escalada para los bloques de sprites, ya que un sprite de 16x16
	// se vería demasiado pequeño en las pantallas de 1920x1080
	private final int scale = 3;
	private final int tileSize = originalTileSize * scale;// tamaño escalado

	// Resolución de la pantalla, 4:3 en este caso.
	private final int maxScreenCol = 16;
	private final int maxScreenRow = 12;
	private final int screenWidth = tileSize * maxScreenCol; // Para que los 16 bloques ocupen en total 760 pixeles
	private final int screenHeight = tileSize * maxScreenRow; // Para que los 12 bloques originales ocupen 576 pixeles

	// Para delimitar el ratio de refresco de la pantalla
	private final int FPS = 60;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true); // opcional, es para un mejor renderizado de los graficos del panel
		this.addKeyListener(keyBoard);
		this.setFocusable(true);
		this.requestFocusInWindow();
		startGameThread();
	}

	private void startGameThread() {
		// instanciar el hilo para despues iniciarlo
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {

			// Aquí se ejecutará el bucle principal sobre el que el juego se inicia,
			// mecanica basica sobre la cual funcionan muchos juegos.

			// Primero se refresca la informacion, y después se pinta la informacion
			// recogida.

			update();

			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime = nextDrawTime + drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {

		player.update();
	}

	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		// para mejorar las funciones del Graphics.
		Graphics2D graphics2D = (Graphics2D) graphics;

		player.draw(graphics2D, tileSize);

		graphics2D.dispose();
	}
}
