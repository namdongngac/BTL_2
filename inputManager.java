package GameFrame;

import java.awt.event.KeyEvent;

import GameObject.GameWord;
import GameObject.bombMan;

public class inputManager {
	
	private GameWord gameWord;
	public inputManager(GameWord gameWord) {
		this.gameWord = gameWord;
	}
	public void processKeyPressed(int keycode) {
		switch(keycode) {
		case KeyEvent.VK_UP:
			gameWord.bombMan.setDirection(bombMan.DIR_UP);
			gameWord.bombMan.setSpeedY(-3);
			
			break;
		case KeyEvent.VK_DOWN:
			gameWord.bombMan.setDirection(bombMan.DIR_DOWN);
			gameWord.bombMan.setSpeedY(3);
			break;
		case KeyEvent.VK_LEFT:
			gameWord.bombMan.setDirection(bombMan.DIR_LEFT);
			gameWord.bombMan.setSpeedX(-3);
			break;
		case KeyEvent.VK_RIGHT:
			gameWord.bombMan.setDirection(bombMan.DIR_RIGHT);
			gameWord.bombMan.setSpeedX(3);
			break;
			
		
		case KeyEvent.VK_SPACE:
			break;
		}
	}
	public void processKeyReleased(int keycode) {
		switch(keycode) {
		case KeyEvent.VK_UP:
			gameWord.bombMan.setSpeedY(0);
			break;
		case KeyEvent.VK_DOWN:
			gameWord.bombMan.setSpeedY(0);
			break;
		case KeyEvent.VK_RIGHT:
			gameWord.bombMan.setSpeedX(0);
			break;
			
		case KeyEvent.VK_LEFT:
			gameWord.bombMan.setSpeedX(0);
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}

}
