package main.logic;

import main.panel.GamePanel;

import main.manager.Character;

public class CollisionDetector {

	GamePanel gamePanel = null;

	public CollisionDetector(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void checkHitBox(Character character) {
		// Definimos las distancias de los lados del rectangulo de nuestra hitbox (al
		// ser el sprite(CharacterWorldX/Y) mayor que nuestra hitbox(charcterHitBox),
		// tenemos que hacer
		// unos calculos).

		int hitBoxLeftSideXCoord = character.characterWorldX + character.getCharacterHitbox().x;
		int hitBoxRightSideXCoord = character.characterWorldX + character.getCharacterHitbox().x
				+ character.getCharacterHitbox().width;
		int hitBoxTopSideYCoord = character.characterWorldY + character.getCharacterHitbox().y;
		int hitBoxBottomSideYCoord = character.characterWorldY + character.getCharacterHitbox().y
				+ character.getCharacterHitbox().height;

		// una vez obtenidos los valores de nuestra hitbox, calcularemos donde esta
		// nuestro caracter en los terminos de columna y linea del mapa

		int characterLeftCol = hitBoxLeftSideXCoord / gamePanel.tileSize;
		int characterRightCol = hitBoxRightSideXCoord / gamePanel.tileSize;
		int characterTopRow = hitBoxTopSideYCoord / gamePanel.tileSize;
		int characterBottomRow = hitBoxBottomSideYCoord / gamePanel.tileSize;

		int tileNum1 = 0;
		int tileNum2 = 0;

		// Y ahora, predecirmos donde va a estar la hitbox de nuestro personaje una vez
		// el usuario presiona una tecla, para poder despues definir si el bloque que va
		// a pisar tras presionar una tecla sera un bloque solido o no. el tileNum se
		// utiliza ya que a la hora de calcular una hitbox solo necesitamos dos lados,
		// ya que la hitbox no puede chocar con cuatro lados ni tres lados
		// simultaneamente.
		if (null != character.direction) {
			switch (character.direction) {
			case "up":
				characterTopRow = (hitBoxTopSideYCoord - character.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapCoords[characterLeftCol][characterTopRow];
				tileNum2 = gamePanel.tileManager.mapCoords[characterRightCol][characterTopRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision == true
						|| gamePanel.tileManager.tiles[tileNum2].collision == true) {
					character.characterCollisioned = true;
				}
				break;
			case "down":
				characterBottomRow = (hitBoxBottomSideYCoord + character.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapCoords[characterLeftCol][characterBottomRow];
				tileNum2 = gamePanel.tileManager.mapCoords[characterRightCol][characterBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision == true
						|| gamePanel.tileManager.tiles[tileNum2].collision == true) {
					character.characterCollisioned = true;
				}
				break;
			case "left":
				characterLeftCol = (hitBoxLeftSideXCoord - character.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapCoords[characterLeftCol][characterTopRow];
				tileNum2 = gamePanel.tileManager.mapCoords[characterLeftCol][characterBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision == true
						|| gamePanel.tileManager.tiles[tileNum2].collision == true) {
					character.characterCollisioned = true;
				}
				break;
			case "right":
				characterRightCol = (hitBoxRightSideXCoord + character.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapCoords[characterRightCol][characterTopRow];
				tileNum2 = gamePanel.tileManager.mapCoords[characterRightCol][characterBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision == true
						|| gamePanel.tileManager.tiles[tileNum2].collision == true) {
					character.characterCollisioned = true;
				}
				break;
			}
		}
	}

}
