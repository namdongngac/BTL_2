/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import java.applet.AudioClip;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class ExitLevel extends Item{
    

    public ExitLevel(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, w, h, gw, Item.exitlevel);       
    }

    @Override
    public void action() {
        super.action();
        Rectangle ExitRect = getBoundForCollision();
        if(getGameWorld().player.getBoundForCollision().intersects(ExitRect) ){
            GameWorld.endLevel = true;          
            //CacheDataLoader.level++;
            //System.out.print(GameWorld.endLevel);
            getGameWorld().score += 1000;
        }
    }
    
}
