package entity;

import main.GamePanel;
import java.util.Random;

public class Apple extends Entity{
	GamePanel gp;
	Random random;
	
	public Apple(GamePanel gp) {
		this.gp = gp;
		random = new Random();
	}
	
	public void setApplePosition(int screenWidth, int screenHeight, int tileSize) {
		x = random.nextInt((int)(screenWidth/tileSize)) * tileSize;
		y = random.nextInt((int)(screenHeight/tileSize)) * tileSize;
	}
	
}
