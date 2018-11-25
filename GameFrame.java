package GameFrame;



import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import GameEffect.CacheDataLoader;
import GameEffect.FrameImage;
import GameEffect.GameAnimation;



public class GameFrame extends JFrame{
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 600;
	GamePanel gamePanel;
	FrameImage frame1;
	GameAnimation animation1;
	public GameFrame() {
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		this.setBounds(((dimension.width)-SCREEN_WIDTH)/2,((dimension.height)-SCREEN_HEIGHT)/2,  SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		try {
			CacheDataLoader.getInstance().loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gamePanel = new GamePanel();
		add(gamePanel);
		this.addKeyListener(gamePanel);
		
	}
	public void startGame() {
		gamePanel.startGame();
	}
	public static void main(String[] args) {
		
		GameFrame gameFrame = new GameFrame();
		gameFrame.setVisible(true);
		gameFrame.startGame();
		
	}

}
