/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.gameobject.GameWorld;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private Thread thread;
    private boolean isRunning;
    private boolean isPause;
    
    private InputManagement input;
    private BufferedImage bufImage;
    private Graphics2D bufG2D;
    public GameWorld gameWorld;
    private ManagementPanel panel;
    
    private FrameImage time, life;
    
    private AudioClip loseSound;
    int temp;
    int count = 0;
    
    
    public GamePanel(ManagementPanel panel){
        
        this.panel = panel;
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        gameWorld = new GameWorld();
        temp = gameWorld.player.getLife();
        loseSound = CacheDataLoader.getInstance().getSound("loseSound");
        input = new InputManagement(gameWorld,this);
        //CacheDataLoader.getInstance().setGameWorld(gameWorld);
        time = CacheDataLoader.getInstance().getFrameImage("time");
        life = CacheDataLoader.getInstance().getFrameImage("life");
        //a = CacheDataLoader.getInstance().getAnimation("bahamut_down");
        
    }
    
    @Override
    public void paint(Graphics g){
        
        g.drawImage(bufImage, 0, 0, this);
    }
    public void UpdateGame(){
        if(!isPause){
            gameWorld.Update();
            if(gameWorld.time==0||gameWorld.player.getLife()<=0){
                loseSound.play();
                panel.showGameOver();

            }
        }
        //temp = gameWorld.player.getLife();
        
        
    }
    public void RenderGame(){
        
        if(bufImage == null){
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }
        else{
            bufG2D = (Graphics2D) bufImage.getGraphics();
            
        }
        if(!isPause){
            if(bufG2D!=null){
                bufG2D.setColor(Color.white);
                bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

                gameWorld.Render(bufG2D);
                bufG2D.setColor(Color.black);
                bufG2D.fillRect(900, 0, 200, GameFrame.SCREEN_HEIGHT);            
                bufG2D.setColor(Color.green);
                bufG2D.drawRect(900, 0, 200, GameFrame.SCREEN_HEIGHT);

                bufG2D.setColor(Color.white);
                bufG2D.setFont(new Font("Arial",Font.BOLD,40));
                bufG2D.drawString("Level: "+String.valueOf(CacheDataLoader.level), 910, 90);

                time.draw(bufG2D, 990, 250);
                life.draw(bufG2D, 990, 450);

                bufG2D.setColor(Color.white);
                bufG2D.setFont(new Font("Arial",Font.BOLD,50));
                bufG2D.drawString(gameWorld.t, 950, 330);

                bufG2D.setColor(Color.white);
                bufG2D.setFont(new Font("Arial",Font.BOLD,50));
                bufG2D.drawString(gameWorld.l, 980, 530);

                bufG2D.setColor(Color.white);
                bufG2D.setFont(new Font("Arial",Font.BOLD,50));
                bufG2D.drawString("Score: ", 910, 650);
                bufG2D.setColor(Color.white);
                bufG2D.setFont(new Font("Arial",Font.BOLD,50));
                bufG2D.drawString(gameWorld.s, 950, 730);
            }
        }
        else {
            bufG2D.setColor(Color.black);
            bufG2D.setFont(new Font("Arial",Font.BOLD,100));
            bufG2D.drawString("PAUSE", 300, 400);
        }
    }
    public void startGame(){
        //gameWorld = new GameWorld();
        if(thread == null){
            isRunning = true;
            isPause = false;
            thread = new Thread(this);
            thread.start();
            //gameWorld.backGroundSound.loop();
            //gameWorld.backGroundSound.play();
        }
        
    }
    public void newGame(){
        CacheDataLoader.level = 1;
        //panel.showNextStage();               
        try {
            CacheDataLoader.getInstance().LoadMap();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        gameWorld = new GameWorld();
        gameWorld.score = 0;
        gameWorld.player.setLife(3);
        gameWorld.player.setBombHaving(1);
        gameWorld.player.setRangeOfBomb(1);
        gameWorld.player.setSpeed(6);

        input = new InputManagement(gameWorld,this);
    }
    @Override
    public void run(){
        long FPS = 30;
        long period = 2000*1000000/FPS;
        long beginTime;
        long sleepTime;
        //beginTime = System.nanoTime();
        while(isRunning){
            while(temp > gameWorld.player.getLife()){
                try {
                
                    
                    count++;
                    if(count > 500) {
                        count = 0;
                        temp = gameWorld.player.getLife();
                    }
                    Thread.sleep(2);
                    
                } catch (InterruptedException ex) {}
            }
            
            gameWorld.player.setSpeed(gameWorld.player.getRunSpeed());
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
            
            if(GameWorld.endLevel == true){                            
                int tempScore = gameWorld.score;
                int tempLife = gameWorld.player.getLife();
                int tempBomb = gameWorld.player.getBombHaving();
                if(tempBomb == 0) tempBomb = 1;
                int tempRange = gameWorld.player.getRangeOfBomb();
                int tempSpeed = gameWorld.player.getSpeed();
                             
                CacheDataLoader.level++;
                if(CacheDataLoader.level > 5){
                    CacheDataLoader.level = 1;
                }
                panel.showNextStage();               
                
                try {
                    CacheDataLoader.getInstance().LoadMap();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
                
                gameWorld = new GameWorld();
                gameWorld.score = tempScore;
                gameWorld.player.setLife(tempLife);
                gameWorld.player.setBombHaving(tempBomb);
                gameWorld.player.setRangeOfBomb(tempRange);
                gameWorld.player.setSpeed(tempSpeed);
                
                input = new InputManagement(gameWorld,this);
                //nextStage();
            }
            
            
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

    public boolean isIsPause() {
        return isPause;
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }
    

    public InputManagement getInput() {
        return input;
    }

    public void setInput(InputManagement input) {
        this.input = input;
    }
    
    
    
    
    
    
    
}
