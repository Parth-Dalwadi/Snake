package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;

public class Snake extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public int speed = 4;
	public int body = 4;
	
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
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillRect(x, y, gp.tileSize/2, gp.tileSize/2);
	}
}
