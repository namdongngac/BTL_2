/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import com.effect.CacheDataLoader;
import com.gameobject.GameWorld;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private Thread thread;
    private boolean isRunning;
    private InputManagement input;
    private BufferedImage bufImage;
    private Graphics2D bufG2D;
    private GameWorld gameWorld;
    
    
    
    public GamePanel(){
        
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        gameWorld = new GameWorld();
        input = new InputManagement(gameWorld, this);
        //CacheDataLoader.getInstance().setGameWorld(gameWorld);
        
        //a = CacheDataLoader.getInstance().getAnimation("bahamut_down");
        
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(bufImage, 0, 0, this);
    }
    public void UpdateGame(){
        gameWorld.Update();
        
    }
    public void RenderGame(){
        if(bufImage == null){
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }
        else{
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }
        if(bufG2D!=null){
            bufG2D.setColor(Color.white);
            bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            
            gameWorld.Render(bufG2D);
            
        }
        
    }
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run(){
        long FPS = 30;
        long period = 2000*1000000/FPS;
        long beginTime;
        long sleepTime;
        //beginTime = System.nanoTime();
        while(isRunning){
            
            
                beginTime = System.nanoTime();
                UpdateGame();
                RenderGame();
                repaint();
                long deltaTime = System.nanoTime() - beginTime; 
                sleepTime = period - deltaTime;
                try {
                    if(sleepTime > 0)
                        Thread.sleep(sleepTime/1000000);
                    else   
                        Thread.sleep(period/2000000);
                } catch (InterruptedException ex) {}
            
            
            
            
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        //input.processKeyTyped(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        input.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        input.processKeyReleased(e.getKeyCode());
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    
    
    
    
    
    
}
