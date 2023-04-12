package main;

import javax.swing.JFrame;

public class App{
	public static void main(String[] args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Snake Game!");
		window.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//window.setUndecorated(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();
	}
}
