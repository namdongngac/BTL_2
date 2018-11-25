/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.userinterface.GameFrame;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class GameWorld {
    
    public Player player;
    //public Player player2;
    public PhysicalMap pMap;
    public Camera camera;
    public ArrayList <Flame> flames;
    public ArrayList <Enemy> enemys;
    
    public int score = 0;
    public int time = 300;
    public static boolean endLevel;
    
    public long timerun = 1000000000L;
    public long timebegin;
    
    public String s;
    public String l;
    public String t;
    
    public AudioClip backGroundSound;
    
    public GameWorld(){   
        
        endLevel = false;
        
        pMap = new PhysicalMap(0,0,this);
        player = new Player(pMap.getPositionX('p'),pMap.getPositionY('p'),40,50,3,this);
        //player2 = new Player(pMap.getPositionX('o'),pMap.getPositionY('o'),40,50,3,this);
        backGroundSound = CacheDataLoader.getInstance().getSound("gameSound");
        enemys = new ArrayList <Enemy> ();
        flames = new ArrayList <Flame> ();
        camera = new Camera(0,0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);  
        this.InitEnemy(pMap);
        timebegin = System.nanoTime();
        //backGroundSound.loop();
        //backGroundSound.play();
    }
    public void Update(){
        
        pMap.update();
        player.update();
        //player2.update();
        if(System.nanoTime()-timebegin>timerun){
            timebegin = System.nanoTime();
            time--;
        }
        for(int i = 0; i < player.bombs.size(); i++){
            player.bombs.get(i).update();
        }
        
        for(int i = 0; i < enemys.size(); i++){
            enemys.get(i).update();
        }
        camera.update();
        
        t = String.valueOf(time);
        s = String.valueOf(score);
        l = String.valueOf(player.getLife());
        for(int i = 0; i < enemys.size(); i++){
            if(enemys.get(i).getState() == Sprite.DEATH ){
                enemys.remove(i);
                score += 100;
            }
                
            
        }
        
    }
    
    public void Render(Graphics2D g2d){
        
        pMap.draw(g2d);
        for(int i = 0; i < player.bombs.size(); i++){
            player.bombs.get(i).draw(g2d);
        }
        
        for(int i = 0; i < flames.size(); i++){
            flames.get(i).draw(g2d);
        }
        player.draw(g2d);
        
        
        for(int i = 0; i < enemys.size(); i++){
            enemys.get(i).draw(g2d);
        }
        
        
    }
    
    
    public void InitEnemy(PhysicalMap map){
        for(int i = 0; i < map.getMap().length ; i++){
            for(int j= 0; j < map.getMap()[0].length; j++){
                if(map.getMap()[i][j] == '1'){
                    EnemyType1 e1 = new EnemyType1((int)(map.getPosX()+j*map.getTileSize())+map.getTileSize()/2, (int)(map.getPosY()+i*map.getTileSize())+map.getTileSize()/2, 60, 60, 1, this);
                    enemys.add(e1);
                }
                if(map.getMap()[i][j] == '2'||map.getMap()[i][j] == '4'){
                    EnemyType2 e2 = new EnemyType2((int)(map.getPosX()+j*map.getTileSize())+map.getTileSize()/2, (int)(map.getPosY()+i*map.getTileSize())+map.getTileSize()/2, 60, 60, 2, this);
                    enemys.add(e2);
                }
                if(map.getMap()[i][j] == '3'||map.getMap()[i][j] == '5'){
                    EnemyType3 e3 = new EnemyType3((int)(map.getPosX()+j*map.getTileSize())+map.getTileSize()/2, (int)(map.getPosY()+i*map.getTileSize())+map.getTileSize()/2, 60, 60, 1, this, player);
                    enemys.add(e3);
                }
            }
        }
    }
    
    
}
