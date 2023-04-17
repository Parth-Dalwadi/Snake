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
	boolean isMoving = false;
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		snakeBody = new ArrayList<Entity>();
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = gp.screenWidth/2;
		y = gp.screenHeight/2;
		for (int i = 0; i < body; i++) {
			Entity e = new Entity();
			snakeBody.add(e);
			snakeBody.get(i).x = x;
			snakeBody.get(i).y = y;
		}
	}
	
	public void update(){
		if (keyH.direction != 'N') {
			isMoving = true;
			for(int i = body-1; i>0; i--) {
				snakeBody.get(i).x = snakeBody.get(i-1).x;
				snakeBody.get(i).y = snakeBody.get(i-1).y;
			}
		}
		
		if (keyH.direction == 'U') {
			snakeBody.get(0).y -= speed;	
		} else if (keyH.direction == 'D') {
			snakeBody.get(0).y += speed;	
		} else if (keyH.direction == 'L') {
			snakeBody.get(0).x -= speed;	
		} else if (keyH.direction == 'R') {
			snakeBody.get(0).x += speed;	
		}
	}
	
	public void draw(Graphics2D g2) {
		if (isMoving == true) {
			for (int i = 0; i < body; i++) {
				if (i == 0) {
					g2.setColor(Color.green);
				} else {
					g2.setColor(new Color(0, 100, 20));
				}
				g2.fillRect(snakeBody.get(i).x, snakeBody.get(i).y, gp.tileSize/2, gp.tileSize/2);
			}
		} else {
			g2.setColor(Color.green);
			g2.fillRect(snakeBody.get(0).x, snakeBody.get(0).y, gp.tileSize/2, gp.tileSize/2);
		}
	}
	
	public void checkCollision() {
		if (isMoving == true) {
			for (int i = body-1; i > 0; i--) {
				if ((snakeBody.get(i).x == snakeBody.get(0).x) && (snakeBody.get(i).y == snakeBody.get(0).y)) {
					collision();
				}
			}
			
			if (snakeBody.get(0).x < 24) {
				collision();
			}
			
			if (snakeBody.get(0).x >= gp.screenWidth-24) {
				collision();
			}
			
			if (snakeBody.get(0).y < 24) {
				collision();
			}
			
			if (snakeBody.get(0).y >= gp.screenHeight-24) {
				collision();
			}
		}
	}
	
	public void collision() {
		gp.gameState = gp.gameOverState;
		keyH.direction = 'N';
		gp.playSE(2);
		isMoving = false;
	}
	
	public void resetSnake() {
		snakeBody.clear();
		body = 6;
		setDefaultValues();
	}
}
