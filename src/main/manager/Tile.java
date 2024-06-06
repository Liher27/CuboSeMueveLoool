package main.manager;

import java.awt.image.BufferedImage;

public class Tile {

	public BufferedImage image = null;
	public boolean collision = false;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

}
