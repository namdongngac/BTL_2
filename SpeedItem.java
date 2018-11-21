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
public class SpeedItem extends Item{

    public SpeedItem(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, w, h, gw, Item.speeditem);
    }

    @Override
    public void action() {
        Rectangle speedItemRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(speedItemRect)){
            if(getGameWorld().player.getRunSpeed()<10){
                getGameWorld().player.setRunSpeed(getGameWorld().player.getRunSpeed() + 1);
                
            } 
            this.setRemoved(true);
        }
    }
    
}
