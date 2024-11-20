package main.manager;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import main.manager.pojos.Tile;
import main.panel.MainPanel;

public class TileManager extends Tile {

	private MainPanel gamePanel;
	public int[][] mapCoords;
	public HashMap<Integer, Tile> tileImagesMap;

	/**
	 * Constructor de la clase
	 * 
	 * @param gamePanel
	 * @throws IOException
	 */
	public TileManager(MainPanel gamePanel) throws IOException {
		this.gamePanel = gamePanel;
		this.mapCoords = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
		this.tileImagesMap = loadTileImages();
		loadMap();
	}

	/**
	 * Metodo creado para guardar nuestras imagenes en un array de tipo Tile. esto
	 * es util ya que si una tile tiene algun atributo especial, como es el caso de
	 * la colision, este no puede ser guardado directamente dentro del hashmap, por
	 * lo que despues insertaremos dentro del hashmap todo el array que se ha habra
	 * cargado con este metodo, en el cual cada tile contara con un tipo de colision
	 * Metodo para guardar las imagenes dentro de un hashmap de tipo BufferedImage,
	 * con un numero de código.
	 * 
	 * @param tilesInfo
	 * 
	 * @throws IOException
	 */
	private HashMap<Integer, Tile> loadTileImages() throws IOException {
		HashMap<Integer, Tile> ret = new HashMap<>();
		File tilesInfoFile = new File("src/sprites/map/tileInfo.csv");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tilesInfoFile));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			String[] values = line.split(",");
			String tileIndex = values[0];
			String tileName = values[1];
			String tileCollission = values[2];
			String tileGrass = values[3];
			Tile tile = new Tile();
			tile.image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/" + tileName + ".png"));
			tile.collision = Boolean.parseBoolean(tileCollission);
			tile.grass = Boolean.parseBoolean(tileGrass);
			ret.put(Integer.parseInt(tileIndex), tile);
		}
		bufferedReader.close();

		return ret;
	}

	/**
	 * Método usado para leer el fichero de texto del mapa, en el que cada numero es
	 * un bloque del array tipo tile. lee columna a columna, y una vez llega al
	 * final de la columna pasa a la siguiente fila...
	 * 
	 * @throws IOException
	 */
	private void loadMap() throws IOException {
		File map = new File("src/sprites/map/worldMap.csv");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(map));

		for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
			String mapCoord = bufferedReader.readLine();
			String[] mapInfo = mapCoord.split(",");

			for (int worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {
				int mapNumber = Integer.parseInt(mapInfo[worldColumn]);
				mapCoords[worldColumn][worldRow] = mapNumber;
			}
		}
		bufferedReader.close();

	}

	/**
	 * Metodo para dibujar los bloques en el mapa del fichero de texto, dependiendo
	 * del valor asignado en el fichero se le otrga una imagen u otra del array de
	 * tiles. continua igual que el anterior metodo
	 * 
	 * @param graphics2D
	 */
	public void drawTiles(Graphics2D graphics2D) {
		for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
			for (int worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {

				int worldX = worldColumn * gamePanel.tileSize;
				int worldY = worldRow * gamePanel.tileSize;
				int screenX = worldX - gamePanel.player.entityWorldX + gamePanel.player.playerPositionXInPanel;
				int screenY = worldY - gamePanel.player.entityWorldY + gamePanel.player.playerPositionYInPanel;

				int tileImageIndex = mapCoords[worldColumn][worldRow];
				Tile tile = tileImagesMap.get(tileImageIndex);
				if (tile != null) {
					graphics2D.drawImage(tile.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
				}
			}
		}
	}
}
