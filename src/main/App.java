package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App{
	public static JFrame window;
	
	public static void main(String[] args){
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Snake Game!");
		new App().setIcon();
		window.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();
	}
	
	public void setIcon() {
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));
		window.setIconImage(icon.getImage());
	}
}
