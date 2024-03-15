package entity;

import java.awt.image.BufferedImage;
/**
 * Pojo de los personajes del programa, para añadir mas personajes proximamente
 */
public abstract class Character {

	public int x = 0;
	public int y = 0;

	public int speed = 0;

	public BufferedImage redStill= null;
	public BufferedImage redMovesDown1 = null;
	public BufferedImage redMovesDown2 = null;
	public BufferedImage redStillLeft = null;
	public BufferedImage redMovesLeft = null;
	public BufferedImage redStillRight = null;
	public BufferedImage redMovesRight = null;
	public BufferedImage redStillUp = null;
	public BufferedImage redMovesUp1 = null;
	public BufferedImage redMovesUp2 = null;
	public String direction = null;
	
	public int spriteCounter = 0;
	
	public int spriteChanger = 1;
}
