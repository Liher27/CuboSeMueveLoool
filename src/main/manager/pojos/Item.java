package main.manager.pojos;

import java.awt.image.BufferedImage;

public class Item extends OverMapEntities {

	public BufferedImage itemSprite = null;

	public String itemName = null;

	public BufferedImage getItemSprite() {
		return itemSprite;
	}

	public void setItemSprite(BufferedImage itemSprite) {
		this.itemSprite = itemSprite;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
