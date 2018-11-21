/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class BombItem extends Item{
    
    //private FrameImage bombItem = CacheDataLoader.getInstance().getFrameImage("bomblv1_1");

    public BombItem(float posX, float posY, float w, float h, GameWorld gw) {
        
        super(posX, posY, w, h, gw, Item.bombitem);
        //bombItem = CacheDataLoader.getInstance().getFrameImage("bomblv1_1");
    }

    @Override
    public void action() {
        Rectangle bombItemRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(bombItemRect)){
            if(getGameWorld().player.getBombHaving()<10){
                getGameWorld().player.setBombHaving(getGameWorld().player.getBombHaving() + 1);               
            }
            this.setRemoved(true);
        }
    }
    
}
