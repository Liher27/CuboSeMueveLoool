package main.tile;

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

	GamePanel gamePanel = null;
	
	int mapCoords[][] = null;
	HashMap<Integer, BufferedImage> tileImages = null;

	/**
	 * constructor de la clase
	 * 
	 * @param gamePanel
	 * @throws IOException
	 */
	public TileManager(GamePanel gamePanel) throws IOException {

		this.gamePanel = gamePanel;
		mapCoords = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

		tileImages = getTileImage();
		loadMap();
	}

	/**
	 * metodo para guardar las imagenes dentro del array de tipo tile.
	 * 
	 * @throws IOException
	 */
	private HashMap<Integer, BufferedImage> getTileImage() throws IOException {
		try {

			HashMap<Integer, BufferedImage> ret = new HashMap<Integer, BufferedImage>();

			ret.put(0, ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderLeft.png")));//
			ret.put(1, ImageIO.read(getClass().getResourceAsStream("/tiles/border.png")));//
			ret.put(2, ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
			ret.put(3, ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderRight.png")));//
			ret.put(4, ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderUp.png")));//
			ret.put(5, ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png")));
			ret.put(6, ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png")));
			ret.put(7, ImageIO.read(getClass().getResourceAsStream("/tiles/pOakStill.png")));//
			ret.put(8, ImageIO.read(getClass().getResourceAsStream("/tiles/whiteWithGrass.png")));
			ret.put(9, ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderUp.png")));//
			ret.put(10, ImageIO.read(getClass().getResourceAsStream("/tiles/house.png")));//
			ret.put(11, ImageIO.read(getClass().getResourceAsStream("/tiles/waterCornerRightUp.png")));//
			ret.put(12, ImageIO.read(getClass().getResourceAsStream("/tiles/waterBorderRight.png")));//
			ret.put(13, ImageIO.read(getClass().getResourceAsStream("/tiles/pOakStill.png")));//
			ret.put(14, ImageIO.read(getClass().getResourceAsStream("/tiles/flower.png")));
			ret.put(15, ImageIO.read(getClass().getResourceAsStream("/tiles/frame.png")));//
			ret.put(16, ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));
			ret.put(17, ImageIO.read(getClass().getResourceAsStream("/tiles/whiteFloor.png")));

			return ret;

		} catch (IOException ioe) {
			throw ioe;
		}
	}

	/**
	 * Método usado para leer el fichero de texto del mapa, en el que cada numero es
	 * un bloque del array tipo tile. lee columna a columna, y una vez llega al
	 * final de la columna pasa a la siguiente fila...
	 */
	private void loadMap() {

		try {
			InputStream inputStream = getClass().getResourceAsStream("/map/worldMap.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
				String mapCoord = bufferedReader.readLine();
				String[] mapInfo = mapCoord.split(",");

				for (int worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {
					int mapNumber = Integer.parseInt(mapInfo[worldColumn]);
					mapCoords[worldColumn][worldRow] = mapNumber;
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

		for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
			for (int worldColumn = 0; worldColumn < gamePanel.maxWorldColumn; worldColumn++) {

				int worldX = worldColumn * gamePanel.tileSize;
				int worldY = worldRow * gamePanel.tileSize;
				int screenX = worldX -gamePanel.player.characterWorldX  + gamePanel.player.playerPositionXInPanel ;
				int screenY = worldY - gamePanel.player.characterWorldY + gamePanel.player.playerPositionYInPanel ;

				int tileImageIndex = mapCoords[worldColumn][worldRow];
				graphics2D.drawImage(tileImages.get(tileImageIndex), screenX, screenY, gamePanel.tileSize,
						gamePanel.tileSize, null);
			}
		}
	}

}
