package main.manager.pojos;

public class OverMapEntities {

	public int entityWorldX = 0;
	public int entityWorldY = 0;

	public boolean collisioned = false;

	public int getCharacterWorldX() {
		return entityWorldX;
	}

	public void setCharacterWorldX(int characterWorldX) {
		this.entityWorldX = characterWorldX;
	}

	public int getCharacterWorldY() {
		return entityWorldY;
	}

	public void setCharacterWorldY(int characterWorldY) {
		this.entityWorldY = characterWorldY;
	}

	public boolean isCollisioned() {
		return collisioned;
	}

	public void setCollisioned(boolean collisioned) {
		this.collisioned = collisioned;
	}

}
