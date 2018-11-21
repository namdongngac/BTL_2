/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.userinterface.InputManagement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Player extends Mob{
    
    private Animation upAnim, downAnim, leftAnim, rightAnim;
    
    private int runSpeed = 6;
    private int bombHaving;
    private int rangeOfBomb = 1;
    
    public ArrayList<Boom> bombs;
    
    
    
    public Player(float x, float y, float w, float h, int blood, GameWorld gw){
        super(x,y,w,h,blood, gw);
        bombs = new ArrayList <Boom> ();
        //setSpeed(RUNSPEED);
        //setSpeedY(RUNSPEED);
        setTeamType(LEAGUE_TEAM);
        setTimeForCantBeHurt(2000000000);
        bombHaving = 1;      
        upAnim = CacheDataLoader.getInstance().getAnimation("bomberman_up");
        downAnim = CacheDataLoader.getInstance().getAnimation("bomberman_down");
        leftAnim = CacheDataLoader.getInstance().getAnimation("bomberman_left");
        rightAnim = CacheDataLoader.getInstance().getAnimation("bomberman_right");
        
    }
    @Override
    public void draw(Graphics2D g2d){
        //g2d.setColor(Color.red);
        //g2d.fillOval((int) (getPosX() - getWidth()/2)- (int) getGameWorld().camera.getPosX(), (int) (getPosY() - getHeight()/2)- (int) getGameWorld().camera.getPosY() , (int)getWidth(), (int)getHeight());
        //f.draw(g2d, (int) posX , (int) posY);
        drawBoundForCollision(g2d);
              
        switch(getState()){
            case ALIVE:
            case CANTBEHURT:
                if(getState() == CANTBEHURT && (System.nanoTime()/10000000)%2!=1){}
                else {
                    if(getDirection()==DIR_UP){
                        //upAnim.setCurrentFrame(downAnim.getCurrentFrame());
                        upAnim.draw(g2d, (int)getPosX() - (int)getGameWorld().camera.getPosX() , (int)getPosY());
                    }
                    if(getDirection()==DIR_DOWN){                    
                        downAnim.draw(g2d, (int)getPosX() - (int)getGameWorld().camera.getPosX() , (int)getPosY());
                    }
                    if(getDirection()==DIR_LEFT){
                        //leftAnim.setCurrentFrame(downAnim.getCurrentFrame());
                        leftAnim.draw(g2d, (int)getPosX() - (int)getGameWorld().camera.getPosX() , (int)getPosY());
                    }
                    if(getDirection()==DIR_RIGHT){   
                        //rightAnim.setCurrentFrame(downAnim.getCurrentFrame());
                        rightAnim.draw(g2d, (int)getPosX() - (int)getGameWorld().camera.getPosX() , (int)getPosY());
                    }
                }
        }
        
    }
    
    

    @Override
    public void update(){
        super.update();
        if(isWalking()){
            if(getDirection()==DIR_UP){
                upAnim.Update(System.nanoTime());
            }
            if(getDirection()==DIR_DOWN){                    
                downAnim.Update(System.nanoTime());
            }
            if(getDirection()==DIR_LEFT){
                leftAnim.Update(System.nanoTime());
            }
            if(getDirection()==DIR_RIGHT){   
                rightAnim.Update(System.nanoTime());
            }
        }
        for(int i = 0; i < bombs.size(); i++){
            Rectangle rect = bombs.get(i).getBoundForCollision();
            if(rect.intersects(getBoundForCollision())){
                if(getWidth() <= rect.x - getPosX() && getDirection() == DIR_RIGHT){
                //setSpeedX(0);
                setPosX(rect.x - getWidth()/2 );
               
                }
                if(getWidth() <= getPosX() - rect.x&& getDirection() == DIR_LEFT){
                    //setSpeedX(0);
                    setPosX(rect.x + getWidth()/2 + rect.width);

                }

                if(getHeight() <= rect.y - getPosY()&& getDirection() == DIR_UP ){
                    //setSpeedY(0);
                    setPosY(rect.y + getHeight()/2 + rect.height );

                }
                if(getHeight() <= rect.y - getPosY()&& getDirection() == DIR_DOWN ){
                    //setSpeedY(0);
                    setPosY(rect.y - getHeight()/2 );   

                }
            }
        }
        clearBomb();
    }
    
    
    public void plantBomb() {
        if(bombHaving > 0){
            Boom bomb = new Boom(getPosX(),getPosY(),60,60,this.getGameWorld(),rangeOfBomb);
            bomb.checkSpot();
            bombs.add(bomb);
            bombHaving--;
        }       
    }


    @Override
    public void move() {
        setIsWalking(true);
        setSpeed(runSpeed);
        switch (getDirection()) {
            case DIR_UP:
                setPosY(getPosY() - getSpeed());
                break;
            case DIR_DOWN:
                setPosY(getPosY() + getSpeed());
                break;
            case DIR_LEFT:
                setPosX(getPosX() - getSpeed());
                break;
            case DIR_RIGHT:
                setPosX(getPosX() + getSpeed());
                break;
            default:
                break;
        }
    }

    

    @Override
    public void stop() {
        //setSpeedX(0);
        setIsWalking(false);
        //leftAnim.resetAnimation();
        //rightAnim.resetAnimation();
        
        //leftAnim.unIgnoreFrame(0);
        //rightAnim.unIgnoreFrame(0);
        
    }

    public void clearBomb(){
        for(int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).isRemoved()==true) {
                    bombs.remove(i);
                    bombHaving++;
            }
	}
      
    }
    
    public int getBombHaving() {
        return bombHaving;
    }

    public void setBombHaving(int bombHaving) {
        this.bombHaving = bombHaving;
    }

    public int getRangeOfBomb() {
        return rangeOfBomb;
    }

    public void setRangeOfBomb(int rangeOfBomb) {
        this.rangeOfBomb = rangeOfBomb;
    }

    public int getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(int runSpeed) {
        this.runSpeed = runSpeed;
    }
    
 
}
