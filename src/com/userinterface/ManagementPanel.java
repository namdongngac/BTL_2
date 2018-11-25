/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class ManagementPanel extends JPanel{
    
    private CardLayout cardLayout;
    
    public GamePanel gamePanel;
    public NextStagePanel nextPanel;
    public GameOverPanel overPanel;
    
    private long timebegin;
    private long timerun = 3000000000L;
    
    public ManagementPanel(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        gamePanel = new GamePanel(this);
        add(gamePanel, "game");
        
        nextPanel = new NextStagePanel(this);
        add(nextPanel, "next");
        
        overPanel = new GameOverPanel(this);
        add(overPanel, "over");
        
        showNextStage();
    }
    
    public void showGame(){
        gamePanel.setIsRunning(true);
        
        cardLayout.show(this, "game");
        //gamePanel.requestFocus();
        //gamePanel.setInput(input);
        gamePanel.gameWorld.backGroundSound.loop();
        gamePanel.gameWorld.backGroundSound.play();
    }
    
    public void showNextStage(){
        cardLayout.show(this, "next");
        //nextPanel.requestFocus();
        gamePanel.setIsPause(true);
        gamePanel.gameWorld.backGroundSound.stop();
    }
    
    public void showGameOver(){
        cardLayout.show(this, "over");
        //overPanel.requestFocus();
        //gamePanel.setIsRunning(false);
        gamePanel.setIsPause(true);
        gamePanel.gameWorld.backGroundSound.stop();
    }
}
