/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Boom extends Object{
    
    private Flame flameUp, flameDown, flameLeft, flameRight;
    private long timePlant;
    private long timeBeginExplosive;
    private long timeToExplosive ;
    private long timeForExplosive;
    private boolean removed;
    private boolean removeEx = true;
    private int range;
    
    private ArrayList <Flame> flames;
    
    private Animation bomb;
    private FrameImage explosion;
    
    public Boom(float posX, float posY, float w, float h, GameWorld gw, int range) {
        super(posX, posY, w, h, gw); 
        flames = new ArrayList <Flame> ();
        timePlant = System.nanoTime();
        timeToExplosive = 4000000000L;
        timeForExplosive = 5000000000L;
        this.range = range;
        removed = false;
        bomb = CacheDataLoader.getInstance().getAnimation("bomblv1");
        explosion = CacheDataLoader.getInstance().getFrameImage("explosionlv1_5");
    }
    

    @Override
    public void update() {      
        bomb.Update(System.nanoTime());
        
        //if(timePlant==0)timePlant = System.nanoTime();
        if(System.nanoTime() - timePlant > timeToExplosive )
            
            explosive();
    }
    

    @Override
    public void draw(Graphics2D g2d) {
        if(removed==false){
            //g2d.setColor(Color.black);
            //g2d.fillOval((int) (getPosX() - getWidth()/2) - (int)getGameWorld().camera.getPosX(), (int) (getPosY() - getHeight()/2) , (int)getWidth(), (int)getHeight());
            bomb.draw(g2d, (int)getPosX()- (int)getGameWorld().camera.getPosX(), (int)getPosY());
        }
        else{
            
                explosion.draw(g2d, (int)getPosX()- (int)getGameWorld().camera.getPosX(), (int)getPosY());
                for(int i = 0; i < flames.size(); i++){
                flames.get(i).draw(g2d);
            }
            
               
            
        }
        
    }

    public void explosive(){
        //setPosX(-100);
        //setPosY(-100);
        removed = true;
        timeBeginExplosive = System.nanoTime();
        
        if(System.nanoTime() - timeBeginExplosive > timeForExplosive){
            
            for(int i = 0; i < flames.size(); i++){
                flames.remove(i);
            }
         
        }
        else{
            //explosion.Update(System.nanoTime());
            // UP FLAME
            for(int i = 0; i < range; i++){
                Rectangle rectU = new Rectangle((int)getPosX()-25,(int)getPosY() - getGameWorld().pMap.getTileSize()*(i+1)-25, 50, 50);
           
                if(getGameWorld().pMap.haveColWithFlame(rectU)!=null){
                    getGameWorld().pMap.breakMap(getGameWorld().pMap.haveColWithFlame(rectU));
                    break;
                }
                if(i == range-1){
                    flameUp = new Flame((int)getPosX(),(int)getPosY() - getGameWorld().pMap.getTileSize()*(i+1),50,50,getGameWorld(),DIR_UP,true);
                    flames.add(flameUp);                 
                }
                else{
                    flameUp = new Flame((int)getPosX(),(int)getPosY() - getGameWorld().pMap.getTileSize()*(i+1),50,50,getGameWorld(),DIR_UP,false);
                    flames.add(flameUp);                   
                }
                
                
            
            }
            // DOWN FLAME
            for(int i = 0; i < range; i++){
                Rectangle rectD = new Rectangle((int)getPosX()-25,(int)getPosY() + getGameWorld().pMap.getTileSize()*(i+1)-25, 50, 50);
                
                
                if(getGameWorld().pMap.haveColWithFlame(rectD)!=null){
                    getGameWorld().pMap.breakMap(getGameWorld().pMap.haveColWithFlame(rectD));
                    break;
                }       
                if(i == range-1){                
                    flameDown = new Flame((int)getPosX(),(int)getPosY()+getGameWorld().pMap.getTileSize()*(i+1),50,50,getGameWorld(),DIR_DOWN,true);
                    flames.add(flameDown);
                    
                }
                else{
                    
                    flameDown = new Flame((int)getPosX(),(int)getPosY()+getGameWorld().pMap.getTileSize()*(i+1),50,50,getGameWorld(),DIR_DOWN,false);
                    flames.add(flameDown);
                    
                }
                       
                
            }
            // LEFT FLAME
            for(int i = 0; i < range; i++){
                Rectangle rectL = new Rectangle((int)getPosX()- getGameWorld().pMap.getTileSize()*(i+1)-25,(int)getPosY()-25 , 50, 50);
                
                
                if(getGameWorld().pMap.haveColWithFlame(rectL)!=null){
                    getGameWorld().pMap.breakMap(getGameWorld().pMap.haveColWithFlame(rectL));
                    break;
                }
                if(i == range-1){
                    
                    flameLeft = new Flame((int)getPosX()-getGameWorld().pMap.getTileSize()*(i+1),(int)getPosY(),50,50,getGameWorld(),DIR_LEFT,true);
                    flames.add(flameLeft);
                    
                }
                else{
                    
                    flameLeft = new Flame((int)getPosX()-getGameWorld().pMap.getTileSize()*(i+1),(int)getPosY(),50,50,getGameWorld(),DIR_LEFT,false);
                    flames.add(flameLeft);
                    
                }
                
                
                
            }
            // RIGHT FLAME
            for(int i = 0; i < range; i++){
                
                Rectangle rectR = new Rectangle((int)getPosX()+ getGameWorld().pMap.getTileSize()*(i+1)-25,(int)getPosY()-25 , 50, 50);
                
                if(getGameWorld().pMap.haveColWithFlame(rectR)!=null){
                    getGameWorld().pMap.breakMap(getGameWorld().pMap.haveColWithFlame(rectR));
                    break;
                }   
                
                if(i == range-1){
                    
                    flameRight = new Flame((int)getPosX()+getGameWorld().pMap.getTileSize()*(i+1),(int)getPosY(),50,50,getGameWorld(),DIR_RIGHT,true);
                    flames.add(flameRight);
                }
                else{
                    
                    flameRight = new Flame((int)getPosX()+getGameWorld().pMap.getTileSize()*(i+1),(int)getPosY(),50,50,getGameWorld(),DIR_RIGHT,false);
                    flames.add(flameRight);
                }
         
            }
                     
        }    
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    
    public void checkSpot(){
        if(getGameWorld().pMap.haveCol(getBoundForCollision(), ' ')!=null){
            setPosX(getGameWorld().pMap.haveCol(getBoundForCollision(), ' ').x+getGameWorld().pMap.getTileSize()/2);
            setPosY(getGameWorld().pMap.haveCol(getBoundForCollision(), ' ').y+getGameWorld().pMap.getTileSize()/2);
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
    
    
}
