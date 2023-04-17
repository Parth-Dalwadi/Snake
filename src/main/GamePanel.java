package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.Random;
import entity.Entity;
import entity.Snake;
import entity.Apple;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	int FPS = 60;
	int score = 0;
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int gameOverState = 2;

	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public Snake snake = new Snake(this, keyH);
	Apple apple = new Apple(this);
	UI ui = new UI(this);
	Sound sound = new Sound();

	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
		gameState = titleState;
		apple.setApplePosition();
		playMusic(1);
	}

	@Override
	public void run(){
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		double update_timer = 0;

		while(gameThread != null){
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			update_timer += delta;

			if (delta >= 1) {
				if (gameState == playState) {
					if (update_timer >= 800000) {
						snake.update();
						snake.checkCollision();
						update_timer = 0;
					}
					checkApple();
				}
				
				if (gameState == gameOverState) {
					snake.resetSnake();
					apple.setApplePosition();
				}
				repaint();
				delta--;	
				drawCount++;
			}

//			if (timer >= 1000000000) {
//				System.out.println("FPS:" + drawCount);
//				drawCount = 0;
//				timer = 0;
//			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;;
//		if (gameState == titleState) {
//			ui.draw(g2);
//		}
		ui.draw(g2);
		
//		if (gameState == playState) {
//			snake.draw(g2);
//			apple.draw(g2);
//		}
		g2.dispose();
	}
	
	public void checkApple() {
		if ((snake.snakeBody.get(0).x == apple.x) && (snake.snakeBody.get(0).y == apple.y)){
			playSE(0);
			Entity e = new Entity();
			e.x = snake.snakeBody.get(snake.body-1).x;
			e.y = snake.snakeBody.get(snake.body-1).y;
			snake.snakeBody.add(e);
			snake.body++;
			score++;
			apple.setApplePosition();
		}
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}