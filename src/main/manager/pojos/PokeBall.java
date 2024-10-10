package main.manager.pojos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PokeBall extends Item {

	public PokeBall() throws IOException {
		itemName = "PokeBall";

		try {
			itemSprite = ImageIO.read(getClass().getResourceAsStream("/sprites/items/PokeBall.png"));
		} catch (IOException e) {
			throw e;
		}
	}

}
