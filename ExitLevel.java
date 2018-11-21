/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class ExitLevel extends Item{
    
    private boolean endLevel;

    public ExitLevel(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, w, h, gw, Item.exitlevel);
        endLevel = false;
    }

    @Override
    public void action() {
        Rectangle ExitRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(ExitRect) && getGameWorld().pMap.getNumberOfEnemy()<=0){
            endLevel = true;
            CacheDataLoader.level++;
        }
    }
    
}
