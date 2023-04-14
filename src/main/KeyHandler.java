package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public char direction;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e){
	}

	@Override
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		
		if (gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			
			if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					gp.gameState = 1;
					gp.apple.setApplePosition();
				}
				if (gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		if (gp.gameState == gp.playState) {
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
	}

	@Override
	public void keyReleased(KeyEvent e){
	}
}
