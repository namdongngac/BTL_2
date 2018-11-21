/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import java.awt.Graphics2D;

/**
 *
 * @author admin
 */
public class BreakBrick extends Object{
    
    private Animation breakBrick;
    private boolean remove;

    public BreakBrick(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, w, h, gw);
        remove = false;
        breakBrick = CacheDataLoader.getInstance().getAnimation("breakBreaklv1");
    }
    @Override
    public void update(){
        breakBrick.Update(System.nanoTime());
    }
    @Override
    public void draw(Graphics2D g2d) {
        breakBrick.draw(g2d, (int)getPosX() - (int)getGameWorld().camera.getPosX() , (int)getPosY());
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public Animation getBreakBrick() {
        return breakBrick;
    }

    public void setBreakBrick(Animation breakBrick) {
        this.breakBrick = breakBrick;
    }
    
    
}
