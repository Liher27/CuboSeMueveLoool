package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import keyBoard.KeyBoard;
import panel.GamePanel;

public class Player extends Character {

	GamePanel gp = null;
	KeyBoard keyBoard = null;

	public Player(GamePanel gp, KeyBoard keyBoard) {
		this.gp = gp;
		this.keyBoard = keyBoard;

		setDefaultValues();
		getPlayerImage();
	}

	private void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}

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
			e.printStackTrace();
		}

	}

	public void update() {

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

		if (spriteCounter > 10) {
			if (spriteChanger == 1) {
				spriteChanger = 2;
			} else if (spriteChanger == 2) {
				spriteChanger = 1;
			}
			spriteCounter = 0;
		}

	}

	public void draw(Graphics2D graphics2D, int tileSize) {
//		graphics2D.setColor(Color.white);
//
//		graphics2D.fillRect(x, y, tileSize, tileSize);

		BufferedImage sprite = null;

		switch (direction) {
		case "up":
			if (spriteChanger == 1) {
				sprite = redMovesUp1;
			}
			if (spriteChanger == 2) {
				sprite = redMovesUp2;
			}

			break;
		case "down":
			if (spriteChanger == 1) {
				sprite = redMovesDown1;
			}
			if (spriteChanger == 2) {
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
			break;
		case "right":
			if (spriteChanger == 1) {
				sprite = redStillRight;
			}
			if (spriteChanger == 2) {
				sprite = redMovesRight;
			}
			break;

		}
		graphics2D.drawImage(sprite, x, y, tileSize, tileSize, null);
	}

}
