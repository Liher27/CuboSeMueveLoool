package main.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.logic.CollisionDetector;
import main.logic.KeyBoard;
import main.manager.ItemManager;
import main.manager.PlayerManager;
import main.manager.TileManager;
import main.manager.pojos.OverMapEntities;
import pokemonFight.manager.PokemonManager;
import pokemonFight.manager.StatusSingleton;
import pokemonFight.manager.pojo.Pokemon;

/**
 * Clase en la que se crea el panel
 */
public class MainPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	public KeyBoard keyBoard = null;
	private Thread gameThread = null; // Hilo sobre el cual correra el juego
	public PlayerManager player = null;
	public TileManager tileManager = null;
	public CollisionDetector collisionDetector = null;
	public OverMapEntities objects[] = new OverMapEntities[10];
	public ItemManager itemSetter = new ItemManager(this);
	private List<Pokemon> allyPokemonTeam = null;
	// definir un sprite de 16x16 bloques
	private final int originalTileSize = 16;

	// tamaño de escalada para los bloques de sprites, ya que un sprite de 16x16
	// se vería demasiado pequeño en las pantallas de 1920x1080
	private final int scale = 3;
	public final int tileSize = originalTileSize * scale;// tamaño escalado

	// Resolución de la pantalla, 4:3 en este caso.
	public final int maxScreenColumn = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenColumn; // Para que los 16 bloques ocupen en total 760 pixeles
	public final int screenHeight = tileSize * maxScreenRow; // Para que los 12 bloques originales ocupen 576 pixeles

	public final int maxWorldColumn = 50;
	public final int maxWorldRow = 50;
	public final int worldScreenWidth = tileSize * maxWorldColumn;
	public final int worldScreenHeight = tileSize * maxWorldRow;

	// Para delimitar el ratio de refresco de la pantalla
	private final int FPS = 60;

	/**
	 * Constructor de la clase GamePanel
	 * 
	 * @throws IOException
	 */
	public MainPanel() throws IOException {
		keyBoard = new KeyBoard();
		player = new PlayerManager(keyBoard, this);
		tileManager = new TileManager(this);
		collisionDetector = new CollisionDetector(this);
		allyPokemonTeam = selectTeamPokemons("Escoge los pokemon para tu equipo");
		StatusSingleton.getInstance().setPokemonTeam(allyPokemonTeam);

		this.setPreferredSize(new Dimension(800, 600));
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
			JOptionPane.showMessageDialog(null, "Ha habido un error en la ejecucion del codigo", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
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

	/**
	 * metodo inicial para seleccionar los pokemon
	 * 
	 * @param message
	 * @return equipo inicial para el combate
	 * @throws IOException
	 */
	public List<Pokemon> selectTeamPokemons(String message) throws IOException {
		JOptionPane.showMessageDialog(null, message, "Bienvenido!!", JOptionPane.INFORMATION_MESSAGE);
		List<Pokemon> selectablePokemon = new PokemonManager().getPokemons();
		String selectablePokemonNames = null;

		JPanel panel = new JPanel();
		panel.add(new JLabel("Selecciona un Pokémon:"));

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < selectablePokemon.size(); i++) {
			selectablePokemonNames = selectablePokemon.get(i).getPokemonName();
			comboBoxModel.addElement(selectablePokemonNames);
		}

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(comboBoxModel);
		panel.add(comboBox);

		List<Pokemon> selectedPokemons = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			int result = JOptionPane.showConfirmDialog(null, panel, "Seleccionar Pokémon",
					JOptionPane.OK_CANCEL_OPTION);

			if (result == 0) {
				int selectedIndex = comboBox.getSelectedIndex();
				if (selectedIndex != -1) {
					Pokemon cloningPokemon = selectablePokemon.get(selectedIndex);
					selectedPokemons.add(new Pokemon(cloningPokemon.getPokemonLvl(), cloningPokemon.getPokemonHP(),
							cloningPokemon.getPokemonSpeed(), cloningPokemon.getPokemonDefense(),
							cloningPokemon.getPokemonAttackStat(), cloningPokemon.getPokemonName(),
							cloningPokemon.getPokemonAttack1(), cloningPokemon.getPokemonAttack2(),
							cloningPokemon.getPokemonAttack3(), cloningPokemon.getPokemonAttack4(),
							cloningPokemon.getPokemonFront(), cloningPokemon.getPokemonBack()));
				}
			} else {
				break;
			}
		}
		return selectedPokemons;
	}
}
