/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import com.effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;


/**
 *
 * @author admin
 */
public class GameFrame extends JFrame{
    
    public static final int SCREEN_WIDTH = 1100;
    public static final int SCREEN_HEIGHT = 820;
    //GamePanel gamePanel;
    ManagementPanel mp;
    public GameFrame(){
        
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width-SCREEN_WIDTH)/2, (dimension.height-SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman");
        
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //gamePanel = new GamePanel();
        mp = new ManagementPanel();
        //this.add(gamePanel);
        this.add(mp);
        //this.addKeyListener(gamePanel);
        this.addKeyListener(mp.gamePanel);
    }
    public void startGame(){
        mp.gamePanel.startGame();
        
    }
    public static void main(String[] args){
        GameFrame gameframe = new GameFrame();
        gameframe.setVisible(true);
        gameframe.startGame();
    }
}
