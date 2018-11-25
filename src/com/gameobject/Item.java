/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract class Item extends Object{
    
    private FrameImage item;
    
    public final static char bombitem = 'b';
    public final static char flameitem = 'f';
    public final static char speeditem = 's';
    public final static char exitlevel = 'x';
    
    private char itemType;
    private boolean removed; 
    
    private AudioClip itemSound;

    public Item(float posX, float posY, float w, float h, GameWorld gw, char type) {
        super(posX, posY, w, h, gw);
        removed = false;
        itemType = type;
        switch(itemType){
            case bombitem:
                this.item = CacheDataLoader.getInstance().getFrameImage("bombItem");
                break;
            case flameitem:
                this.item = CacheDataLoader.getInstance().getFrameImage("flameItem");
                break;
            case speeditem:
                this.item = CacheDataLoader.getInstance().getFrameImage("speedItem");
                break;
            case exitlevel:
                this.item = CacheDataLoader.getInstance().getFrameImage("door");
                break;
        }
        if(itemType == exitlevel){
            itemSound = CacheDataLoader.getInstance().getSound("completeSound");
        }
        else itemSound = CacheDataLoader.getInstance().getSound("getItem");
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        item.draw(g2d, (int)(getPosX()-getGameWorld().camera.getPosX()), (int)getPosY());
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    
    
    public void action(){
        Rectangle itemR = getBoundForCollision();
    
        if(getGameWorld().player.getBoundForCollision().intersects(itemR)){
            itemSound.play();
        }
    }
}
