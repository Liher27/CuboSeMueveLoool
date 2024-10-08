package main.manager;

import java.io.IOException;

import main.manager.pojos.PokeBall;
import main.manager.pojos.Pokedex;
import main.panel.GamePanel;

public class ItemManager {

	GamePanel gamePanel = null;

	public ItemManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void setItems() throws IOException {

		try {
			gamePanel.objects[0] = new PokeBall();
			gamePanel.objects[0].entityWorldX = 36 * gamePanel.tileSize;
			gamePanel.objects[0].entityWorldY = 46 * gamePanel.tileSize;

			gamePanel.objects[0] = new Pokedex();
			gamePanel.objects[0].entityWorldX = 6 * gamePanel.tileSize;
			gamePanel.objects[0].entityWorldY = 8 * gamePanel.tileSize;

		} catch (IOException e) {
			throw e;
		}
	}
}
