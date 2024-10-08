package main.manager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.logic.KeyBoard;
import main.manager.pojos.Character;
import main.panel.GamePanel;

public class PlayerManager extends Character {

	private KeyBoard keyBoard = null;

	private GamePanel gamePanel = null;

	public final int playerPositionXInPanel;
	public final int playerPositionYInPanel;

	/**
	 * Constructor de la clase
	 * 
	 * @param keyBoard
	 * @throws IOException
	 */
	public PlayerManager(KeyBoard keyBoard, GamePanel gamePanel) throws IOException {
		this.keyBoard = keyBoard;

		this.gamePanel = gamePanel;

		entityWorldX = gamePanel.tileSize * 10;
		entityWorldY = gamePanel.tileSize * 9;

		playerPositionXInPanel = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2; // 360
		playerPositionYInPanel = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2; // 264

		// Definimos cual va a ser la hitbox de nuestro personaje, o de el pokemon que
		// nos siga tambien, si se programa...[quien sabe ;)]
		characterHitBox = new Rectangle(8, 16, 32, 32);

		speed = 4;
		direction = "down";

		try {
			getPlayerImage();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Para obtener las imagenes de los sprites del personaje
	 * 
	 * @throws IOException
	 */
	public void getPlayerImage() throws IOException {
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
			waterPokemon1 = ImageIO.read(getClass().getResourceAsStream("/player/waterPokemon1.png"));
			waterPokemon2 = ImageIO.read(getClass().getResourceAsStream("/player/waterPokemon2.png"));

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Para hacer que el player se mueva en una direccion u otra
	 */
	public void updateSprite() {

		if (keyBoard.upPressed == true) {
			direction = "up";
		} else if (keyBoard.downPressed == true) {
			direction = "down";
		} else if (keyBoard.rightPressed == true) {
			direction = "right";
		} else if (keyBoard.leftPressed == true) {
			direction = "left";
		} else {
			direction = null;
		}

		spriteCounter++;

		// Para comprobar si nuestro personaje ha chocado contra una pared o no, le
		// pasamos el Player, que es una clase hija de Character, por lo que en el
		// metodo podra operar con el player
		collisioned = false;
		gamePanel.collisionDetector.checkHitBox(this);

		// Hacemos que solo se pueda mover si el personaje no ha colisionado
		if (direction != null) {
			if (collisioned == false) {
				switch (direction) {
				case "up":
					entityWorldY -= speed;
					break;
				case "down":
					entityWorldY += speed;
					break;
				case "left":
					entityWorldX -= speed;
					break;
				case "right":
					entityWorldX += speed;
					break;
				}
			}
		}

		warpForSpriteMovement();

	}

	/**
	 * para ir cambiando el sprite del personaje: Changer=1, una imagen. Changer=2,
	 * otra ...
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
	 * Para dibujar el player e ir cambiando sus sprites por cada ciclo del anterior
	 * metodo
	 * 
	 * @param graphics2D
	 * @param tileSize
	 */
	public void draw(Graphics2D graphics2D) {

		BufferedImage sprite = null;

		if (direction == null) {
			if (spriteChanger == 1) {
				sprite = redStill;
			}
			if (spriteChanger == 2) {
				sprite = redStill;
			}
			if (spriteChanger == 3) {
				sprite = redStill;
			}
		} else {
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
					sprite = redMovesLeft;
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
					sprite = redMovesRight;
				}
				break;

			}
		}

		graphics2D.drawImage(sprite, playerPositionXInPanel, playerPositionYInPanel, gamePanel.tileSize,
				gamePanel.tileSize, null);
	}
}
