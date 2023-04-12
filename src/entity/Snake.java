package entity;

import main.GamePanel;
import main.KeyHandler;

public class Snake extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public int speed = 4;
	public int snakeBody = 6;
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
	}
	
	public void update(){
		if (keyH.direction == 'U') {
			y -= speed;	
		} else if (keyH.direction == 'S') {
			y += speed;	
		} else if (keyH.direction == 'L') {
			x -= speed;	
		} else if (keyH.direction == 'R') {
			x += speed;	
		}
	}
}
