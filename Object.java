/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

/**
 *
 * @author admin
 */
public abstract class Object extends Sprite{
    
    public Object(float posX, float posY, float w, float h, GameWorld gw) {
        super(posX, posY, w, h, gw);
    }
    
}
