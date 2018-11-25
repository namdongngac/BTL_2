package GameFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.RuntimeMXBean;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GameEffect.CacheDataLoader;
import GameEffect.FrameImage;
import GameEffect.GameAnimation;
import GameObject.GameWord;
import GameObject.bombMan;
import GameObject.physicalMap;

public class GamePanel extends JPanel implements Runnable,KeyListener {
	private Thread thread;
	boolean isRunning;
	private inputManager inputManager;
	private BufferedImage bufImage;
	private Graphics2D bufG2D;
	/*BufferedImage image;
	BufferedImage subImage;
	FrameImage frame1,frame2,frame3,frame4,frame5;
	GameAnimation animation ;*/
	
	GameWord gameWord;
	public GamePanel(){
		gameWord = new GameWord();
		inputManager = new inputManager(gameWord);
		
		bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
	}
	@Override
	public void paint(Graphics g) {
		/*g.setColor(Color.white);
		g.fillRect(0, 0,GameFrame.SCREEN_WIDTH , GameFrame.SCREEN_HEIGHT);
		Graphics2D g2 = (Graphics2D) g;*/
		g.drawImage(bufImage, 0, 0, this);
		
	}
	public void updateGame() {
		gameWord.Update();
		
	}
	public void gameRender() {
		if(bufImage == null) {
			bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			
		}
		if(bufImage!= null)
			bufG2D = (Graphics2D) bufImage.getGraphics();
		if(bufG2D != null) {
			bufG2D.setColor(Color.white);
			bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
			
			//bombMan.draw(bufG2D);
			//physicalMap.draw(bufG2D);
			gameWord.Render(bufG2D);
		}
			
		
	}
	public void startGame() {
		isRunning = true;
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		inputManager.processKeyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		inputManager.processKeyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		long FPS = 80;
		long feriod = 1000*1000000;
		long beginTime;
		long sleepTime;
		// TODO Auto-generated method stub
		beginTime = System.nanoTime();
		int a=0;
		while(isRunning) {
			//update
			//render
			updateGame();
			gameRender();
			repaint();
			long deltaTime = System.nanoTime()-beginTime;
			sleepTime = feriod - deltaTime;
			 
				try {
					if(sleepTime>0)
						Thread.sleep(sleepTime/100000000);//time to speed
					else Thread.sleep(feriod/200000000);
					 
				} catch (InterruptedException e) {
										
				}
				beginTime = System.nanoTime();
			
		}
	}

}
