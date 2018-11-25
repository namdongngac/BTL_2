/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract class Sprite extends GameObject{
    
    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;
    private int teamType;
            
    public static final int DIR_UP = 0;
    public static final int DIR_LEFT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_RIGHT = 3;
    
    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int DEATH = 2;
    public static final int CANTBEHURT = 3;
    private int state = ALIVE;
    
    private float width;
    private float height;
    
    private int speed;
    //private float speedY;
    
    private int life;
    
    private int direction;
    
    protected Animation animBehurtBefore, animBehurtAfter;
    
    private long startTimeCantBeHurt;
    private long timeForCantBeHurt;

    public Sprite(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, gw);
        this.width = w;
        this.height = h;
        
        direction = DIR_DOWN;
        
    }
    
    public Rectangle getBoundForCollision(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (this.getPosX() - getWidth()/2);
        bound.y = (int) (this.getPosY() - getHeight()/2);
        bound.width = (int) this.getWidth();
        bound.height = (int) this.getHeight();         
        return bound;
    }
    
    @Override
    public void update(){
        switch(state){
            case ALIVE:
                break;
            case BEHURT:
                if(animBehurtAfter == null){
                    state = CANTBEHURT;
                    if(getLife() == 0)
                        state = DEATH;
                }
                else{
                    animBehurtBefore.Update(System.nanoTime());
                    if(animBehurtBefore.isLastFrame()){
                        animBehurtBefore.resetAnimation();
                        state = CANTBEHURT;
                        if(getLife() == 0)
                            state = DEATH;
                        startTimeCantBeHurt = System.nanoTime();
                    }
                }
                break;
            case DEATH:
                
                break;
            case CANTBEHURT:
                if(System.nanoTime() - startTimeCantBeHurt > timeForCantBeHurt)
                    state = ALIVE;
                break;
        }
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public long getStartTimeCantBeHurt() {
        return startTimeCantBeHurt;
    }

    public void setStartTimeCantBeHurt(long startTimeCantBeHurt) {
        this.startTimeCantBeHurt = startTimeCantBeHurt;
    }

    public long getTimeForCantBeHurt() {
        return timeForCantBeHurt;
    }

    public void setTimeForCantBeHurt(long timeForCantBeHurt) {
        this.timeForCantBeHurt = timeForCantBeHurt;
    }
    
    public void drawBoundForCollision(Graphics2D g2){
        Rectangle rect = getBoundForCollision();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
       
    }

    
    public abstract void draw(Graphics2D g2d);
    
    
}
