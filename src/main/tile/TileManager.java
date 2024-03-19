package main.tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.panel.GamePanel;

public class TileManager {

	Tile[] tile = null;

	GamePanel gamepanel = null;

	public TileManager(GamePanel gamePanel) {

		this.gamepanel = gamePanel;

		tile = new Tile[9];

		getTileImage();
	}

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

	public void drawTiles(Graphics2D graphics2D, int tileSize) {

		graphics2D.drawImage(tile[0].tileImage, 0, 0, tileSize, tileSize, null);

	}

}
