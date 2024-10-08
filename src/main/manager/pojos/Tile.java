package main.manager.pojos;

import java.awt.image.BufferedImage;

public class Tile {

	public int index = 0;
	public BufferedImage image = null;
	public boolean collision = false;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
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
