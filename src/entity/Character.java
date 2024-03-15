package entity;

import java.awt.image.BufferedImage;

public class Character {

	public int x = 0;
	public int y = 0;

	public int speed = 0;

	public BufferedImage redStill, redMovesDown1, redMovesDown2, redStillLeft, redMovesLeft, redStillRight,
			redMovesRight, redStillUp, redMovesUp1, redMovesUp2 = null;
	public String direction = null;
	
	public int spriteCounter = 0;
	
	public int spriteChanger = 1;
}
