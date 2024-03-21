package main.entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/**
 * Pojo de los personajes del programa, para añadir mas personajes proximamente
 */
public abstract class Character {

	public int characterWorldX = 0;
	public int characterWorldY = 0;
	
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
	public BufferedImage waterPokemon1 = null;
	public BufferedImage waterPokemon2 = null;
	
	public String direction = null;
	
	public int spriteCounter = 0;
	
	public int spriteChanger = 1;
}
