/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.userinterface.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class Flame extends Object{
    
    //private int range;
    private boolean lastFlame;
    private boolean ignore = false;
    
    private FrameImage flame;

    
    
    public Flame(float posX, float posY, float w, float h, GameWorld gw, int dir, boolean lastFlame) {
        super(posX, posY, w, h, gw);     
        //this.range = 1;  
        this.lastFlame = lastFlame;
        setDirection(dir);
        if(isLastFlame()){
            if(dir == DIR_UP){
                flame = CacheDataLoader.getInstance().getFrameImage("lastflamelv1_up5");
            }
            if(dir == DIR_DOWN){
                flame = CacheDataLoader.getInstance().getFrameImage("lastflamelv1_down5");
            }
            if(dir == DIR_LEFT){
                flame = CacheDataLoader.getInstance().getFrameImage("lastflamelv1_left5");
            }
            if(dir == DIR_RIGHT){
                flame = CacheDataLoader.getInstance().getFrameImage("lastflamelv1_right5");
            }
        }
        else{
            if(dir == DIR_UP || dir == DIR_DOWN){
                flame = CacheDataLoader.getInstance().getFrameImage("flamelv1_y5");
            }          
            if(dir == DIR_LEFT || dir == DIR_RIGHT){
                flame = CacheDataLoader.getInstance().getFrameImage("flamelv1_x5");
            }         
        }
        
    }
    
    

    @Override
    public void update() {}
    
    public void Collision(){
        Rectangle flameRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(flameRect)){
            
            getGameWorld().player.setState(Player.CANTBEHURT);
            getGameWorld().player.setLife(getGameWorld().player.getLife() - 1);
            //GamePanel.timebegin = System.nanoTime();
            getGameWorld().player.setPosX(getGameWorld().pMap.getPositionX('p'));
            getGameWorld().player.setPosY(getGameWorld().pMap.getPositionY('p'));
            System.out.println(getGameWorld().player.getState());
        }
        for(int i = 0; i < getGameWorld().player.bombs.size(); i++){
            if(getGameWorld().player.bombs.get(i).getBoundForCollision().intersects(flameRect)){
                if(!getGameWorld().player.bombs.get(i).isRemoved()){
                    getGameWorld().player.bombs.get(i).explosive();
                }
                
            }
        }
        for(int i = 0; i < getGameWorld().enemys.size(); i++){
            if(getGameWorld().enemys.get(i).getBoundForCollision().intersects(flameRect)){
                getGameWorld().enemys.get(i).setLife(getGameWorld().enemys.get(i).getLife() - 1);;
                getGameWorld().pMap.setNumberOfEnemy(getGameWorld().pMap.getNumberOfEnemy()-1);
                //getGameWorld().score += 100;
            }
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        
        //g2d.setColor(Color.yellow);       
        //g2d.fillRect((int)(getPosX()-getWidth()/2) - (int)getGameWorld().camera.getPosX(), (int)(getPosY()-getHeight()/2), (int)getWidth(), (int)getHeight());
        flame.draw(g2d, (int)(getPosX()) - (int)getGameWorld().camera.getPosX(), (int)getPosY());
        //this.drawBoundForCollision(g2d);
    }

    public boolean isLastFlame() {
        return lastFlame;
    }

    public void setLastFlame(boolean lastFlame) {
        this.lastFlame = lastFlame;
    }
    
    
   
    
    
}
