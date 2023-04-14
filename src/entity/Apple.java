package entity;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple extends Entity{
	GamePanel gp;
	Random random;
	
	public Apple(GamePanel gp) {
		this.gp = gp;
		random = new Random();
	}
	
	public void setApplePosition() {
		x = random.nextInt((int)(gp.screenWidth/gp.tileSize)) * gp.tileSize;
		y = random.nextInt((int)(gp.screenHeight/gp.tileSize)) * gp.tileSize;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillOval(x, y, gp.tileSize/2, gp.tileSize/2);
	}
	
}
