/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import static com.gameobject.Sprite.DEATH;
import static com.gameobject.Sprite.DIR_DOWN;
import static com.gameobject.Sprite.DIR_LEFT;
import static com.gameobject.Sprite.DIR_RIGHT;
import static com.gameobject.Sprite.DIR_UP;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author admin
 */
public class EnemyType2 extends Enemy{
    
    private Random random;
    private Animation upAnim, downAnim, leftAnim, rightAnim;

    public EnemyType2(float posX, float posY, float w, float h, int life, GameWorld gw) {
        super(posX, posY, w, h, life, gw);
        random = new Random();
        setSpeed(3);
        upAnim = CacheDataLoader.getInstance().getAnimationE("enemy2_up");
        downAnim = CacheDataLoader.getInstance().getAnimationE("enemy2_down");
        leftAnim = CacheDataLoader.getInstance().getAnimationE("enemy2_left");
        rightAnim = CacheDataLoader.getInstance().getAnimationE("enemy2_right");
        
    }
  
    @Override
    public void update(){
        super.update();
        if((System.nanoTime()/10000000)%15==0)
            setDirection(CalculateDirection());
        //if()
        switch (getDirection()) {
            case DIR_UP:
                upAnim.Update(System.nanoTime());
                break;
            case DIR_DOWN:
                downAnim.Update(System.nanoTime());
                break;
            case DIR_LEFT:
                leftAnim.Update(System.nanoTime());
                break;
            case DIR_RIGHT:
                rightAnim.Update(System.nanoTime());
                break;
            
        }
        if(getLife()<=0)setState(DEATH);
        move();
    }
    
    @Override
    public void move(){
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
            
        }
    }
    
    @Override
    public void draw(Graphics2D g2d){
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

    @Override
    public int CalculateDirection() {
        return random.nextInt(4);
    }

    @Override
    public void stop() {}
}
