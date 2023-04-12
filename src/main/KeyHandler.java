package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public char direction;

	@Override
	public void keyTyped(KeyEvent e){
	}

	@Override
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
			if (direction != 'S') {
				direction = 'U';
			}	
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
			if (direction != 'U') {
				direction = 'S';
			}
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
			if (direction != 'R') {
				direction = 'L';
			}
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
			if (direction != 'L') {
				direction = 'R';
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
	}
}
