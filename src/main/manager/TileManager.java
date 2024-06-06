package main.manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.imageio.ImageIO;
import main.panel.GamePanel;

public class TileManager extends Tile {

	private GamePanel gamePanel;
	public int[][] mapCoords;
	private HashMap<Integer, Tile> tileImagesMap;
	public Tile[] tiles;

	/**
	 * Constructor de la clase
	 * 
	 * @param gamePanel
	 * @throws IOException
	 */
	public TileManager(GamePanel gamePanel) throws IOException {
		this.gamePanel = gamePanel;
		this.mapCoords = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
		this.tileImagesMap = loadTileImages(getTiles());
		loadMap();
	}

	/**
	 * Metodo creado para guardar nuestras imagenes en un array de tipo Tile. esto
	 * es util ya que si una tile tiene algun atributo especial, como es el caso de
	 * la colision, este no puede ser guardado directamente dentro del hashmap, por
	 * lo que despues insertaremos dentro del hashmap todo el array que se ha habra
	 * cargado con este metodo, en el cual cada tile contara con un tipo de colision
	 * 
	 * @return
	 * @throws IOException
	 */
	private Tile[] getTiles() throws IOException {
		tiles = new Tile[18];
		tiles[0] = new Tile();
		tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderLeft.png"));
		tiles[0].collision = true;
		tiles[1] = new Tile();
		tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/border.png"));
		tiles[1].collision = true;
		tiles[2] = new Tile();
		tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		tiles[3] = new Tile();
		tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderRight.png"));
		tiles[3].collision = true;
		tiles[4] = new Tile();
		tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderUp.png"));
		tiles[4].collision = true;
		tiles[5] = new Tile();
		tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png"));
		tiles[6] = new Tile();
		tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png"));
		tiles[7] = new Tile();
		tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pOakStill.png"));
		tiles[7].collision = true;
		tiles[8] = new Tile();
		tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png"));
		tiles[9] = new Tile();
		tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderUp.png"));
		tiles[9].collision = true;
		tiles[10] = new Tile();
		tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house.png"));
		tiles[10].collision = true;
		tiles[11] = new Tile();
		tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterCornerRightUp.png"));
		tiles[11].collision = true;
		tiles[12] = new Tile();
		tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderRight.png"));
		tiles[12].collision = true;
		tiles[13] = new Tile();
		tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pOakStill.png"));
		tiles[13].collision = true;
		tiles[14] = new Tile();
		tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flower.png"));
		tiles[15] = new Tile();
		tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/frame.png"));
		tiles[15].collision = true;
		tiles[16] = new Tile();
		tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
		tiles[17] = new Tile();
		tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteFloor.png"));
		return tiles;
	}

	/**
	 * Metodo para guardar las imagenes dentro de un hashmap de tipo BufferedImage,
	 * con un numero de código.
	 * 
	 * @param tilesInfo
	 * 
	 * @throws IOException
	 */
	private HashMap<Integer, Tile> loadTileImages(Tile[] tilesInfo) throws IOException {
		HashMap<Integer, Tile> ret = new HashMap<>();
		for (int i = 0; i < tilesInfo.length; i++) {
			ret.put(i, tilesInfo[i]);
		}
		return ret;
	}

	/**
	 * Método usado para leer el fichero de texto del mapa, en el que cada numero es
	 * un bloque del array tipo tile. lee columna a columna, y una vez llega al
	 * final de la columna pasa a la siguiente fila...
	 */
	private void loadMap() {
		try (InputStream inputStream = getClass().getResourceAsStream("/map/worldMap.txt");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

			for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
				String mapCoord = bufferedReader.readLine();
				String[] mapInfo = mapCoord.split(",");

				for (int worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {
					int mapNumber = Integer.parseInt(mapInfo[worldColumn]);
					mapCoords[worldColumn][worldRow] = mapNumber;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				int screenX = worldX - gamePanel.player.characterWorldX + gamePanel.player.playerPositionXInPanel;
				int screenY = worldY - gamePanel.player.characterWorldY + gamePanel.player.playerPositionYInPanel;

				int tileImageIndex = mapCoords[worldColumn][worldRow];
				Tile tile = tileImagesMap.get(tileImageIndex);
				if (tile != null) {
					graphics2D.drawImage(tile.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
				}
			}
		}
	}
}
