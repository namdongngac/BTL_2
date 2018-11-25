/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.userinterface.GamePanel;
import java.awt.Graphics2D;

/**
 *
 * @author admin
 */
public abstract class Enemy extends Mob{

    public Enemy(float posX, float posY, float w, float h, int life, GameWorld gw) {
        super(posX, posY, w, h, life, gw);
    }

    @Override
    public abstract void move();
    
    @Override
    public void update(){
        super.update();
        if(this.getBoundForCollision().intersects(getGameWorld().player.getBoundForCollision())){
            //GamePanel.timebegin = System.nanoTime();
            getGameWorld().player.setPosX(getGameWorld().pMap.getPositionX('p'));
            getGameWorld().player.setPosY(getGameWorld().pMap.getPositionY('p'));
            getGameWorld().player.setLife(getGameWorld().player.getLife()-1);
            getGameWorld().player.setState(CANTBEHURT);
            
        }
    }
    
    public abstract int CalculateDirection();

    @Override
    public void draw(Graphics2D g2d) {}
    
}
