package main.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.panel.GamePanel;

public class TileManager {

	Tile[] tile = null;
	GamePanel gamePanel = null;
	int mapCoords[][] = null;

	/**
	 * constructor de la clase
	 * 
	 * @param gamePanel
	 */
	public TileManager(GamePanel gamePanel) {

		this.gamePanel = gamePanel;
		tile = new Tile[10];
		mapCoords = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

		getTileImage();
		loadMap();
	}

	/**
	 * metodo para guardar las imagenes dentro del array de tipo tile.
	 */
	private void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/border.png"));

			tile[1] = new Tile();
			tile[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/flower.png"));

			tile[2] = new Tile();
			tile[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/frame.png"));

			tile[3] = new Tile();
			tile[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[4] = new Tile();
			tile[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/house.png"));

			tile[5] = new Tile();
			tile[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

			tile[6] = new Tile();
			tile[6].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorder.png"));

			tile[7] = new Tile();
			tile[7].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteFloor.png"));

			tile[8] = new Tile();
			tile[8].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png"));

		} catch (IOException e) {
			// No puede ocurrir...
		}
	}

	/**
	 * Método usado para leer el fichero de texto del mapa, en el que cada numero es
	 * un bloque del array tipo tile. lee columna a columna, y una vez llega al
	 * final de la columna pasa a la siguiente fila...
	 */
	private void loadMap() {

		try {
			InputStream inputStream = getClass().getResourceAsStream("/map/map.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			for (int row = 0; row < gamePanel.maxWorldRow; row++) {
				String mapCoord = bufferedReader.readLine();
				String[] mapInfo = mapCoord.split(" ");

				for (int column = 0; column < gamePanel.maxWorldColumn; column++) {
					int mapNumber = Integer.parseInt(mapInfo[column]);
					mapCoords[column][row] = mapNumber;
				}
			}
			bufferedReader.close();
		} catch (Exception e) {

		}
	}

	/**
	 * metodo para dibujar los bloques en el mapa del fichero de texto, dependiendo
	 * del valor asignado en el fichero se le otrga una imagen u otra del array de
	 * tiles. continua igual que el anterior metodo
	 * 
	 * @param graphics2D
	 * @param tileSize
	 */
	public void drawTiles(Graphics2D graphics2D) {

		int worldRow = 0;
		int worldColumn = 0;

		for (worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
			for (worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {

				int worldX = worldColumn * gamePanel.tileSize;
				int worldY = worldRow * gamePanel.tileSize;
				int screenX = worldX - (gamePanel.player.characterWorldX * gamePanel.player.playerPositionXInPanel);
				int screenY = worldY - (gamePanel.player.characterWorldY * gamePanel.player.playerPositionYInPanel);

				int tileImageIndex = mapCoords[worldColumn][worldRow];
				graphics2D.drawImage(tile[tileImageIndex].tileImage, screenX, screenY, gamePanel.tileSize,
						gamePanel.tileSize, null);

			}
			worldColumn = 0;
		}
	}

}