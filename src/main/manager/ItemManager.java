package main.manager;

import java.io.IOException;

import main.manager.pojos.Pokedex;
import main.panel.MainPanel;

public class ItemManager {

	MainPanel gamePanel = null;

	public ItemManager(MainPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void setItems() throws IOException {
			gamePanel.objects[0].entityWorldX = 36 * gamePanel.tileSize;
			gamePanel.objects[0].entityWorldY = 46 * gamePanel.tileSize;

			gamePanel.objects[0] = new Pokedex();
			gamePanel.objects[0].entityWorldX = 6 * gamePanel.tileSize;
			gamePanel.objects[0].entityWorldY = 8 * gamePanel.tileSize;
	}
}
