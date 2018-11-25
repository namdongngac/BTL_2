/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class FlameItem extends Item{

    public FlameItem(float posX, float posY, float w, float h, GameWorld gw ) {
        super(posX, posY, w, h, gw, Item.flameitem);
    }

    @Override
    public void action() {
        super.action();
        Rectangle flameItemRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(flameItemRect)){
            if(getGameWorld().player.getRangeOfBomb()<10){
                getGameWorld().player.setRangeOfBomb(getGameWorld().player.getRangeOfBomb() + 1);
            }
            getGameWorld().score += 100;
            this.setRemoved(true);
        }
    }
    
}
