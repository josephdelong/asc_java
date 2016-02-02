/**
 * 
 */
package visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Joseph DeLong Tutorial by vanZeben, taken from
 *         https://www.youtube.com/watch?v=Vv7G5GMOre8
 *
 */
public class InputHandler implements KeyListener {

	private boolean pressedEnter = false;
	private boolean pressedEscape = false;
	
	public boolean inMenu = false;

	public InputHandler(GameWindow gameWindow) {
		gameWindow.addKeyListener(this);
	}

	public class Key {

		private int numTimesPressed = 0;
		private boolean pressed = false;

		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (isPressed) {
				numTimesPressed++;
			}
		}

		public boolean isPressed() {
			return pressed;
		}

		public int getNumTimesPressed() {
			return numTimesPressed;
		}
	}

	// public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key escape = new Key();
	public Key enter = new Key();
	public Key shift = new Key();

	public void keyPressed(KeyEvent e) {
		
//		if (pressedEscape) { // if IN MENU
//			switch (e.getKeyCode()) {
//			case KeyEvent.VK_ESCAPE:
//				pressedEscape = !pressedEscape; // toggle the key
//				// toggle OFF all other keys
//				pressedEnter = false;
//				break;
//			case KeyEvent.VK_ENTER:
//				pressedEnter = !pressedEnter; // toggle the key
//				enter.toggle(pressedEnter);
//				break;
//			case KeyEvent.VK_UP:
//				// toggle the key ON and OFF only once
//				up.toggle(true);
//				up.toggle(false);
//				break;
//			case KeyEvent.VK_DOWN:
//				// toggle the key ON and OFF only once
//				down.toggle(true);
//				down.toggle(false);
//				break;
//			case KeyEvent.VK_LEFT:
//				// toggle the key ON and OFF only once
//				left.toggle(true);
//				left.toggle(false);
//				break;
//			case KeyEvent.VK_RIGHT:
//				// toggle the key ON and OFF only once
//				right.toggle(true);
//				right.toggle(false);
//				break;
//			case KeyEvent.VK_W:
//				// toggle the key ON and OFF only once
//				up.toggle(true);
//				up.toggle(false);
//				break;
//			case KeyEvent.VK_S:
//				// toggle the key ON and OFF only once
//				down.toggle(true);
//				down.toggle(false);
//				break;
//			case KeyEvent.VK_A:
//				// toggle the key ON and OFF only once
//				left.toggle(true);
//				left.toggle(false);
//				break;
//			case KeyEvent.VK_D:
//				// toggle the key ON and OFF only once
//				right.toggle(true);
//				right.toggle(false);
//				break;
//			case KeyEvent.VK_SHIFT:
//				shift.
//			}
//		} else {
			toggleKey(e.getKeyCode(), true);
//		}
	}

	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			right.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			escape.toggle(isPressed);
			inMenu = !inMenu;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			enter.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_SHIFT) {
			shift.toggle(isPressed);
		}
	}

}
