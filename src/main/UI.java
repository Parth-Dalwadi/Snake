package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	public boolean gameFinished = false;
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if (gp.gameState == gp.playState) {
			drawGameScreen();
		}
		
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
	}
	
	public void drawTitleScreen() {
		//Title
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Snake Game!";
		int x = getXForCenteredText(text);
		int y = gp.tileSize * 3;
		g2.setColor(Color.green);
		g2.drawString(text, x+2, y+2);
		
		g2.setColor(new Color(21,71,52));
		g2.drawString(text, x, y);
		
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		g2.setColor(Color.green);
		
		text = "PLAY";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXForCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}
	
	public void drawGameScreen() {
//		int count = 0;
//		for(int i = 0; i < gp.screenWidth; i+=24) {
//			if (count%2==0) {
//				g2.setColor(Color.gray);
//			} else {
//				g2.setColor(Color.darkGray);
//			}
//			g2.fillRect(i, 0, gp.tileSize/2, gp.tileSize/2);
//			count++;
//		}
		int count1 = 0;
		int count2 = 0;
		for(int i = 0; i < gp.screenWidth; i+=24) {
			for(int j = 0; j < gp.screenHeight; j+=24) {
				if (j == 0) {
					g2.setColor(new Color(60, 150, 60));
				} else {
					if (count2 % 2 == 0) {
						changeColor(count1, "No");
					} else {
						changeColor(count1, "Yes");
					}
				}
				g2.fillRect(i, j, gp.tileSize/2, gp.tileSize/2);
				count1++;
			}
			count2++;
		}
		gp.snake.draw(g2);
		gp.apple.draw(g2);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
		String text = "Score: " + gp.score;
		int x = getXForCenteredText(text);
		int y = gp.tileSize/2;
		g2.setColor(Color.black);
		g2.drawString(text, x+2, y-4);
	}
	
	public void drawGameOverScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 104F));
		String text = "Game Over!";
		int x = getXForCenteredText(text);
		int y = gp.tileSize * 3;
		g2.setColor(Color.red);
		g2.drawString(text, x+2, y+2);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 52F));
		text = "Score: " + gp.score;
		x = getXForCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.green);
		text = "RETRY";
		x = getXForCenteredText(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXForCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public void changeColor(int count, String swap) {
		if (swap.equals("No")) {
			if ((count % 2 == 0)) {
				g2.setColor(Color.black);
			} else {
				g2.setColor(new Color(30, 30, 30));
			}
		} else {
			if ((count % 2 == 0)) {
				g2.setColor(new Color(30, 30, 30));
			} else {
				g2.setColor(Color.black);
			}
		}
	}
}