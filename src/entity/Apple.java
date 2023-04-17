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
		int tempx = random.nextInt((int)(gp.screenWidth/gp.tileSize)) * gp.tileSize;
		int tempy = random.nextInt((int)((gp.screenHeight-gp.tileSize)/gp.tileSize)) * gp.tileSize + (gp.tileSize/2);
		boolean onSnake = true;
		
		while(onSnake == true) {
			for(int i = 0 ; i < gp.snake.body; i++) {
				Entity curr = gp.snake.snakeBody.get(i);
				if (tempx == curr.x && tempy == curr.y) {
					tempx = random.nextInt((int)(gp.screenWidth/gp.tileSize)) * gp.tileSize;
					tempy = random.nextInt((int)((gp.screenHeight-gp.tileSize)/gp.tileSize)) * gp.tileSize + (gp.tileSize/2);
					break;
				}
//				
//				if (tempy < 24) {
//					tempy = random.nextInt((int)(gp.screenHeight/gp.tileSize)) * gp.tileSize;
//					break;
//				}

				if (i == gp.snake.body - 1) {
					onSnake = false;
				}
			}
		}
		onSnake = true;
		x = tempx;
		y = tempy;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillOval(x, y, gp.tileSize/2, gp.tileSize/2);
	}
	
}
