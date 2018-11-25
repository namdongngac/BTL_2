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
    
    public long timerun = 500000000L;
    public long timebegin;
    
    public Player(float x, float y, float w, float h, int life, GameWorld gw){
        super(x,y,w,h, life, gw);
        bombs = new ArrayList <Boom> ();
        //setSpeed(RUNSPEED);
        //setSpeedY(RUNSPEED);
        //this.setLife(life);
        setTeamType(LEAGUE_TEAM);
        setTimeForCantBeHurt(2000*1000000);
        bombHaving = 1;      
        upAnim = CacheDataLoader.getInstance().getAnimation("bomberman_up");
        downAnim = CacheDataLoader.getInstance().getAnimation("bomberman_down");
        leftAnim = CacheDataLoader.getInstance().getAnimation("bomberman_left");
        rightAnim = CacheDataLoader.getInstance().getAnimation("bomberman_right");
        timebegin = System.nanoTime();
        
    }
    @Override
    public void draw(Graphics2D g2d){
        //g2d.setColor(Color.red);
        //g2d.fillOval((int) (getPosX() - getWidth()/2)- (int) getGameWorld().camera.getPosX(), (int) (getPosY() - getHeight()/2)- (int) getGameWorld().camera.getPosY() , (int)getWidth(), (int)getHeight());
        //f.draw(g2d, (int) posX , (int) posY);
        //drawBoundForCollision(g2d);
              
        switch(getState()){
            case ALIVE:
                
            case CANTBEHURT:
                if(getState() == CANTBEHURT && (System.nanoTime()/10000000)%2!=1){
                    System.out.println("hu");
                }
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
                break;
            case DEATH:
                
                //setPosX(getGameWorld().pMap.getPositionX('p'));
                //setPosX(getGameWorld().pMap.getPositionY('p'));
                break;
        }
        
    }
    
    

    @Override
    public void update(){
        super.update();
        switch(getState()){
            case ALIVE:
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
                break;
            case CANTBEHURT:
                setPosX(getGameWorld().pMap.getPositionX('p'));
                setPosY(getGameWorld().pMap.getPositionY('p'));
                break;
        }
        
        
        clearBomb();
    }
    
    
    public void plantBomb() {
        if(bombHaving > 0 && !this.checkSpot()){
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
    
    public boolean checkSpot() {
        for(int i = 0; i < bombs.size(); i++) {
            if(getPosX() >= bombs.get(i).getPosX() && getPosX() < (bombs.get(i).getPosX() + 60) && (getPosY() + 10) >= bombs.get(i).getPosY() && (getPosY() + 10) < (bombs.get(i).getPosY() + 60)) return true;
            if((getPosX() + 60) >= bombs.get(i).getPosX() && (getPosX() + 60) < (bombs.get(i).getPosX() + 60) && (getPosY() + 10) >= bombs.get(i).getPosY() && (getPosY() + 10) < (bombs.get(i).getPosY() + 60)) return true;
            if(getPosX() >= bombs.get(i).getPosX() && getPosX() < (bombs.get(i).getPosX() + 60) && (getPosY() + 10 + 60) >= bombs.get(i).getPosY() && (getPosY() + 10 + 60) < (bombs.get(i).getPosY() + 60)) return true;
            if((getPosX() + 60) >= bombs.get(i).getPosX() && (getPosX() + 60) < (bombs.get(i).getPosX() + 60) && (getPosY() + 10 + 60) >= bombs.get(i).getPosY() && (getPosY() + 10 + 60) < (bombs.get(i).getPosY() + 60)) return true;
        }

        return false;
    }
}
