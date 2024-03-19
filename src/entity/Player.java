package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import keyBoard.KeyBoard;

/**
 * Pojo especifico creado para el personaje del jugador (no deberia haber tanto codigo aqui)
 */
public class Player extends Character {

	private KeyBoard keyBoard = null;

	/**
	 * Constructor de la clase
	 * @param keyBoard
	 */
	public Player(KeyBoard keyBoard) {
		this.keyBoard = keyBoard;

		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
		getPlayerImage();
	}

	/**
	 * Para obtener las imagenes de los sprites del personaje
	 */
	public void getPlayerImage() {
		try {
			redStill = ImageIO.read(getClass().getResourceAsStream("/player/redStill.png"));
			redMovesDown1 = ImageIO.read(getClass().getResourceAsStream("/player/redMoves1.png"));
			redMovesDown2 = ImageIO.read(getClass().getResourceAsStream("/player/redMoves2.png"));
			redStillLeft = ImageIO.read(getClass().getResourceAsStream("/player/redStillLeft.png"));
			redMovesLeft = ImageIO.read(getClass().getResourceAsStream("/player/redMovesLeft.png"));
			redStillRight = ImageIO.read(getClass().getResourceAsStream("/player/redStillRight.png"));
			redMovesRight = ImageIO.read(getClass().getResourceAsStream("/player/redMovesRight.png"));
			redStillUp = ImageIO.read(getClass().getResourceAsStream("/player/redStillUp.png"));
			redMovesUp1 = ImageIO.read(getClass().getResourceAsStream("/player/redMovesUp1.png"));
			redMovesUp2 = ImageIO.read(getClass().getResourceAsStream("/player/redMovesUp2.png"));
		} catch (IOException e) {
			// No puede ocurrir...
		}
	}

	/**
	 * Para hacer que el player se mueva en una direccion u otra
	 */
	public void updateSprite() {

		if (keyBoard.upPressed == true) {
			direction = "up";
			y -= speed;
		}

		if (keyBoard.downPressed == true) {
			direction = "down";
			y += speed;
		}

		if (keyBoard.rightPressed == true) {
			direction = "right";
			x += speed;
		}

		if (keyBoard.leftPressed == true) {
			direction = "left";
			x -= speed;
		}

		spriteCounter++;

		warpForSpriteMovement();

	}

	/**
	 * para ir cambiando el sprite del personaje: Changer=1, una imagen. Changer=2, otra ...
	 */
	private void warpForSpriteMovement() {

		if (spriteCounter > 10) {
			if (spriteChanger == 1) {
				spriteChanger = 2;
			} else if (spriteChanger == 2) {
				spriteChanger = 3;
			} else if (spriteChanger == 3) {
				spriteChanger = 1;
			}
			spriteCounter = 0;
		}

	}
	
	/**
	 * Para dibujar el player e ir cambiando sus sprites por cada ciclo del anterior metodo
	 * @param graphics2D
	 * @param tileSize
	 */
	public void draw(Graphics2D graphics2D, int tileSize) {

		BufferedImage sprite = null;

		switch (direction) {
		case "up":
			if (spriteChanger == 1) {
				sprite = redStillUp;
			}
			if (spriteChanger == 2) {
				sprite = redMovesUp1;
			}
			if (spriteChanger == 3) {
				sprite = redMovesUp2;
			}
			break;
		case "down":
			if (spriteChanger == 1) {
				sprite = redStill;
			}
			if (spriteChanger == 2) {
				sprite = redMovesDown1;
			}
			if (spriteChanger == 3) {
				sprite = redMovesDown2;
			}
			break;
		case "left":
			if (spriteChanger == 1) {
				sprite = redStillLeft;
			}
			if (spriteChanger == 2) {
				sprite = redMovesLeft;
			}
			if (spriteChanger == 3) {
				sprite = redStillLeft;
			}
			break;
		case "right":
			if (spriteChanger == 1) {
				sprite = redStillRight;
			}
			if (spriteChanger == 2) {
				sprite = redMovesRight;
			}
			if (spriteChanger == 3) {
				sprite = redStillRight;
			}
			break;

		}
		graphics2D.drawImage(sprite, x, y, tileSize, tileSize, null);
	}
}
