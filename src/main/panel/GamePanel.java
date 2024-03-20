package main.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.entity.Player;
import main.keyBoard.KeyBoard;
import main.tile.TileManager;

/**
 * Clase en la que se crea el panel
 */
public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private KeyBoard keyBoard = null;
	private Thread gameThread = null; // Hilo sobre el cual se correra el juego
	public Player player = null;
	private TileManager tileManager = null;

	// definir un sprite de 16x16 bloques
	private final int originalTileSize = 16;

	// tamaño de escalada para los bloques de sprites, ya que un sprite de 16x16
	// se vería demasiado pequeño en las pantallas de 1920x1080
	private final int scale = 3;
	private final int hi_scale = 4;
	public final int tileSize = originalTileSize * scale;// tamaño escalado
	public final int highTileSize = originalTileSize * hi_scale;
	
	// Resolución de la pantalla, 4:3 en este caso.
	public final int maxScreenColumn = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenColumn; // Para que los 16 bloques ocupen en total 760 pixeles
	public final int screenHeight = tileSize * maxScreenRow; // Para que los 12 bloques originales ocupen 576 pixeles
	
	public final int maxWorldColumn = 48;
	public final int maxWorldRow = 48;
	public final int worldScreenWidth = tileSize * maxWorldColumn; 
	public final int worldScreenHeight = tileSize * maxWorldRow; 

	
	// Para delimitar el ratio de refresco de la pantalla
	private final int FPS = 60;

	/**
	 * Constructor de la clase GamePanel
	 */
	public GamePanel() {
		keyBoard = new KeyBoard();
		player = new Player(keyBoard, this);
		tileManager = new TileManager(this);

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true); // opcional, es para un mejor renderizado de los graficos del panel
		this.addKeyListener(keyBoard);
		this.setFocusable(true);
		this.requestFocusInWindow();
		startGameThread();
	}

	/**
	 * Crea el hilo y se inicia
	 */
	private void startGameThread() {
		// instanciar el hilo
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Metodo que implementa "Runnable", para iniciar el hilo
	 */
	@Override
	public void run() {

		while (gameThread != null) {

			// Aquí se ejecutará el bucle principal sobre el que el juego se inicia,
			// mecanica basica sobre la cual funcionan muchos juegos.

			// Primero se refresca la informacion, y después se pinta la informacion
			// recogida.

			update();
			repaint();

			refrescoPantalla();
		}
	}

	/**
	 * Para limitar cada cuanto tiempo se refresca la pantalla (60FPS) , sino se
	 * refrescaria millones de veces por segundo
	 */
	private void refrescoPantalla() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;

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

	/**
	 * Metodo usado normalmente con los hilos para recoger la nueva informacion que
	 * se le pase por teclado
	 */
	private void update() {
		player.updateSprite();
	}

	/**
	 * Al igual que update, se usa mucho con los hilos y se utiliza para pintar por
	 * pantalla la informacion conseguida en el anterior metodo
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;
		tileManager.drawTiles(graphics2D);
		player.draw(graphics2D);
		graphics2D.dispose();
	}
}
