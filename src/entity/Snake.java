package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import main.GamePanel;
import main.KeyHandler;

public class Snake extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public int speed = 24;
	public int body = 6;
	public ArrayList<Entity> snakeBody;
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		snakeBody = new ArrayList<Entity>();
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = 24;
		y = 0;
		for (int i = 0; i < body; i++) {
			Entity e = new Entity();
			snakeBody.add(e);
			snakeBody.get(i).x = x;
			snakeBody.get(i).y = y;
			y+=speed;
		}
	}
	
	public void update(){
		if (keyH.direction != 'N') {
			for(int i = body-1; i>0; i--) {
				snakeBody.get(i).x = snakeBody.get(i-1).x;
				snakeBody.get(i).y = snakeBody.get(i-1).y;
			}
		}
		
		if (keyH.direction == 'U') {
			snakeBody.get(0).y -= speed;	
		} else if (keyH.direction == 'S') {
			snakeBody.get(0).y += speed;	
		} else if (keyH.direction == 'L') {
			snakeBody.get(0).x -= speed;	
		} else if (keyH.direction == 'R') {
			snakeBody.get(0).x += speed;	
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < body; i++) {
			if (i == 0) {
				g2.setColor(Color.yellow);
			} else {
				g2.setColor(Color.green);
			}
			g2.fillRect(snakeBody.get(i).x, snakeBody.get(i).y, gp.tileSize/2, gp.tileSize/2);
		}
	}
	
	public void checkCollision() {
		for (int i = body-1; i > 0; i--) {
			if ((snakeBody.get(i).x == snakeBody.get(0).x) && (snakeBody.get(i).y == snakeBody.get(0).y)) {
				playCollision();
				gp.gameState = gp.gameOverState;
			}
		}
		
		if (snakeBody.get(0).x < 0) {
			playCollision();
			gp.gameState = gp.gameOverState;
		}
		
		if (snakeBody.get(0).x >= gp.screenWidth) {
			playCollision();
			gp.gameState = gp.gameOverState;
		}
		
		if (snakeBody.get(0).y < 0) {
			playCollision();
			gp.gameState = gp.gameOverState;
		}
		
		if (snakeBody.get(0).y >= gp.screenHeight) {
			playCollision();
			gp.gameState = gp.gameOverState;
		}
		
//		if (gp.gameState == gp.gameOverState) {
//			playCollision(2);
//		}
	}
	
	public void playCollision() {
		gp.playSE(2);
	}
	
	public void resetSnake() {
		snakeBody.clear();
		body = 6;
		setDefaultValues();
	}
}
