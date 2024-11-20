package main.manager.pojos;

import java.awt.image.BufferedImage;

public class Tile {

	public int index = 0;
	public BufferedImage image = null;
	public boolean collision = false;
	public boolean grass = false;

	public boolean isGrass() {
		return grass;
	}

	public void setGrass(boolean grass) {
		this.grass = grass;
	}

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
