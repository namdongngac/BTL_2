/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.userinterface.GameFrame;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class GameWorld {
    
    public Player player;
    public Player player2;
    //public Boom b;
    public PhysicalMap pMap;
    public Camera camera;
    public ArrayList <Flame> flames;
    public ArrayList <Enemy> enemys;
    
    public GameWorld(){   
        
        pMap = new PhysicalMap(0,0,this);
        player = new Player(pMap.getPositionX('p'),pMap.getPositionY('p'),40,50,1,this);
        player2 = new Player(pMap.getPositionX('o'),pMap.getPositionY('o'),40,50,1,this);
        flames = new ArrayList <Flame> ();
        camera = new Camera(0,0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);  
        
    }
    public void Update(){
        pMap.update();
        player.update();
        player2.update();
        for(int i = 0; i < player.bombs.size(); i++){
            player.bombs.get(i).update();
        }
        for(int i = 0; i < player2.bombs.size(); i++){
            player2.bombs.get(i).update();
        }
        camera.update();
    }
    
    public void Render(Graphics2D g2d){
        
        pMap.draw(g2d);
        for(int i = 0; i < player.bombs.size(); i++){
            player.bombs.get(i).draw(g2d);
        }
        for(int i = 0; i < player2.bombs.size(); i++){
            player2.bombs.get(i).draw(g2d);
        }
        for(int i = 0; i < flames.size(); i++){
            flames.get(i).draw(g2d);
        }
        player.draw(g2d);
        player2.draw(g2d);
        
        
        
    }
    
    public void addFlame(Flame flame){
        flames.add(flame);
    }
    
    public void InitEnemy(PhysicalMap map){
        
    }
    
    
}
