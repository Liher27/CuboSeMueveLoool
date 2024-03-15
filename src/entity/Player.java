package entity;

import java.awt.Color;
import java.awt.Graphics2D;
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
	}

	private void setDefaultValues() {
		x = 100;

		y = 100;

		speed = 4;
	}

	public void getPlayerImage() {
		
		try {
			
			redStill= ImageIO.read(getClass().getResourceAsStream("/player/redStill.png"));
			redMovesDown1= ImageIO.read(getClass().getResourceAsStream("/player/redMovesDown1.png"));
			redMovesDown2= ImageIO.read(getClass().getResourceAsStream("/player/redMovesDown2.png"));
			redStillLeft= ImageIO.read(getClass().getResourceAsStream("/player/redStillLeft.png"));
			redMovesLeft= ImageIO.read(getClass().getResourceAsStream("/player/redMovesLeft.png"));
			redStillRight= ImageIO.read(getClass().getResourceAsStream("/player/redStillRight.png"));
			redMovesRight= ImageIO.read(getClass().getResourceAsStream("/player/redMovesRight.png"));
			redStillUp= ImageIO.read(getClass().getResourceAsStream("/player/redStillUp.png"));
			redMovesUp1= ImageIO.read(getClass().getResourceAsStream("/player/redMovesUp1.png"));
			redMovesUp2= ImageIO.read(getClass().getResourceAsStream("/player/redMovesUp2.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {

		if (keyBoard.upPressed == true) {
			y -= speed;
		}

		if (keyBoard.downPressed == true) {
			y += speed;
		}

		if (keyBoard.rightPressed == true) {
			x += speed;
		}

		if (keyBoard.leftPressed == true) {
			x -= speed;
		}
		
	}

	public void draw(Graphics2D graphics2D, int tileSize) {
		graphics2D.setColor(Color.white);

		graphics2D.fillRect(x, y, tileSize, tileSize);
	}

}
