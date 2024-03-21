package main.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.keyBoard.KeyBoard;
import main.panel.GamePanel;

/**
 * Pojo especifico creado para el personaje del jugador (no deberia haber tanto
 * codigo aqui)
 */
public class Player extends Character {

	private KeyBoard keyBoard = null;

	private GamePanel gamePanel = null;

	public final int playerPositionXInPanel;
	public final int playerPositionYInPanel;

	/**
	 * Constructor de la clase
	 * 
	 * @param keyBoard
	 */
	public Player(KeyBoard keyBoard, GamePanel gamePanel) {
		this.keyBoard = keyBoard;

		this.gamePanel = gamePanel;

		characterWorldX = gamePanel.tileSize * 10;
		characterWorldY = gamePanel.tileSize * 9;

		playerPositionXInPanel = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2; //360
		playerPositionYInPanel = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2; //264

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
			waterPokemon1 = ImageIO.read(getClass().getResourceAsStream("/player/waterPokemon1.png"));
			waterPokemon2 = ImageIO.read(getClass().getResourceAsStream("/player/waterPokemon2.png"));

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
			characterWorldY -= speed;
		}

		else if (keyBoard.downPressed == true) {
			direction = "down";
			characterWorldY += speed;
		}

		else if (keyBoard.rightPressed == true) {
			direction = "right";
			characterWorldX += speed;
		}

		else if (keyBoard.leftPressed == true) {
			direction = "left";
			characterWorldX -= speed;
		}

		else {
			direction = null;
		}

		spriteCounter++;

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
		BufferedImage pokemonSprite = null;

		if (direction == null) {
			if (spriteChanger == 1) {
				sprite = redStill;
				pokemonSprite = waterPokemon1;
			}
			if (spriteChanger == 2) {
				sprite = redStill;
				pokemonSprite = waterPokemon2;
			}
			if (spriteChanger == 3) {
				sprite = redStill;
				pokemonSprite = waterPokemon2;
			}
		} else {
			switch (direction) {
			case "up":
				if (spriteChanger == 1) {
					sprite = redStillUp;
					pokemonSprite = waterPokemon1;
				}
				if (spriteChanger == 2) {
					sprite = redMovesUp1;
					pokemonSprite = waterPokemon2;
				}
				if (spriteChanger == 3) {
					sprite = redMovesUp2;
					pokemonSprite = waterPokemon2;
				}
				break;
			case "down":
				if (spriteChanger == 1) {
					sprite = redStill;
					pokemonSprite = waterPokemon1;
				}
				if (spriteChanger == 2) {
					sprite = redMovesDown1;
					pokemonSprite = waterPokemon2;
				}
				if (spriteChanger == 3) {
					sprite = redMovesDown2;
					pokemonSprite = waterPokemon2;
				}
				break;
			case "left":
				if (spriteChanger == 1) {
					sprite = redStillLeft;
					pokemonSprite = waterPokemon1;
				}
				if (spriteChanger == 2) {
					sprite = redMovesLeft;
					pokemonSprite = waterPokemon2;
				}
				if (spriteChanger == 3) {
					sprite = redMovesLeft;
					pokemonSprite = waterPokemon2;
				}
				break;
			case "right":
				if (spriteChanger == 1) {
					sprite = redStillRight;
					pokemonSprite = waterPokemon1;
				}
				if (spriteChanger == 2) {
					sprite = redMovesRight;
					pokemonSprite = waterPokemon2;
				}
				if (spriteChanger == 3) {
					sprite = redMovesRight;
					pokemonSprite = waterPokemon2;
				}
				break;

			}
		}

		graphics2D.drawImage(sprite, playerPositionXInPanel, playerPositionYInPanel, gamePanel.tileSize,
				gamePanel.tileSize, null);
		
		
	}
}
