package main.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase utilizada para recibir la informacion que se le pasa por teclado
 */
public class KeyBoard implements KeyListener {

	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean rightPressed = false;
	public boolean leftPressed = false;

	@Override
	public void keyTyped(KeyEvent e) {
		// no lo usaremos
	}

	/**
	 * Metodo implementado por "KeyListener", sirve para indicar que deberia de
	 * hacerse cuando una tecla es pulsada
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}

	}

	/**
	 * Metodo implementado por "KeyListener", sirve para indicar que deberia de
	 * hacerse cuando una tecla deja de ser pulsada
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}

	}

}
