/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import static com.gameobject.Sprite.DIR_DOWN;
import static com.gameobject.Sprite.DIR_LEFT;
import static com.gameobject.Sprite.DIR_RIGHT;
import static com.gameobject.Sprite.DIR_UP;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract class Mob extends Sprite {
    
    private boolean isWalking;
    //private int life;
    
    public Mob(float posX, float posY, float w, float h, int life, GameWorld gw) {
        super(posX, posY, w, h, gw);
        this.setLife(life);
    }

    public boolean isWalking() {
        return isWalking;
    }

    public void setIsWalking(boolean isWalking) {
        this.isWalking = isWalking;
    }
    
    @Override
    public void update(){
        super.update();
        
        if(getState() == ALIVE || getState() == CANTBEHURT){
        
            
                Rectangle boundX = getBoundForCollision();
                if(getDirection()==DIR_LEFT)
                    boundX.x -= (getSpeed()!=0?getSpeed(): 2);
                if(getDirection()==DIR_RIGHT)
                    boundX.x += (getSpeed()!=0?getSpeed(): 2);
                Rectangle rectLeftWall = getGameWorld().pMap.haveColLeft(boundX);
                if( getDirection()== DIR_LEFT && rectLeftWall!=null){
                    
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                Rectangle rectRightWall = getGameWorld().pMap.haveColRight(boundX);
                if( getDirection()== DIR_RIGHT && rectRightWall!=null){
                    
                    setPosX(rectRightWall.x - getWidth()/2);

                }



                

                Rectangle boundY = getBoundForCollision();
                if(getDirection()==DIR_DOWN)
                    boundY.y += (getSpeed()!=0?getSpeed(): 2);
                if(getDirection()==DIR_UP)
                    boundY.y -= (getSpeed()!=0?getSpeed(): 2);
                Rectangle rectDown = getGameWorld().pMap.haveColDown(boundY);
                
                Rectangle rectUp = getGameWorld().pMap.haveColUp(boundY);
                
                if(getDirection()== DIR_UP&&rectUp !=null){
                    
                    
                    setPosY(rectUp.y + getGameWorld().pMap.getTileSize() + getHeight()/2);
                    
                } 
                if(getDirection()== DIR_DOWN&&rectDown != null){
                    
                    
                    setPosY(rectDown.y - getHeight()/2);
                }
                
                for(int i = 0; i < getGameWorld().player.bombs.size(); i++){
                    Rectangle rect = getGameWorld().player.bombs.get(i).getBoundForCollision();
                    if(rect.intersects(getBoundForCollision())){
                        if(getDirection() == DIR_RIGHT){
                        //setSpeedX(0);
                        setPosX(rect.x - getWidth()/2 );

                        }
                        if(getDirection() == DIR_LEFT){
                            //setSpeedX(0);
                            setPosX(rect.x + getWidth()/2 + rect.width);

                        }

                        if(getDirection() == DIR_UP ){
                            //setSpeedY(0);
                            setPosY(rect.y + getHeight()/2 + rect.height );

                        }
                        if(getDirection() == DIR_DOWN ){
                            //setSpeedY(0);
                            setPosY(rect.y - getHeight()/2 );   

                        }
                    }
                }
         
        }
        
        /*
        Rectangle futureRectY = getBoundForCollision();
        Rectangle futureRectX = getBoundForCollision();
        //futureRectX.x += (getSpeedX()!=0?getSpeedY():2);
        
        
       
        Rectangle rectR = getGameWorld().pMap.haveColRight(futureRectX);
        Rectangle rectL = getGameWorld().pMap.haveColLeft(futureRectX);
        //setPosX(getPosX() + getSpeedX());
        if(getDirection() == DIR_RIGHT && rectR!=null){
            //setSpeedX(0);
            //setPosX(x);
            setPosX(rectR.x - getWidth()/2 );   
        }
        else if(getDirection() == DIR_LEFT &&rectL!=null){
            //setSpeedX(0);
            setPosX(rectL.x + getWidth()/2 + rectL.width);
            //setPosX(x);
        }
        else {
            //setPosX(getPosX() + getSpeed());
        }
        //futureRectY.y += (getSpeedY()!=0?getSpeedY():2);
        Rectangle rectD = getGameWorld().pMap.haveColDown(futureRectY);
        Rectangle rectU = getGameWorld().pMap.haveColUp(futureRectY);
          
        //setPosY(getPosY() + getSpeedY());
        if(getDirection() == DIR_DOWN&&rectD!=null){    
            //setSpeedY(0);
            setPosY(rectD.y - getHeight()/2 ); 
            //setPosY(y);
        }
        else if(getDirection() == DIR_UP&&rectU!=null){
            //setSpeedY(0);
            //setPosY(y);
            setPosY(rectU.y + getHeight()/2 + rectU.height );
        }
        else {
            //setSpeedY(getSpeedY());
            //setPosY(getPosY() + getSpeed());
        }
        
        
        */
      
    }
    
    public abstract void move();
    
    public abstract void stop();
    
}