/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import java.awt.Graphics2D;

/**
 *
 * @author admin
 */
public class Enemy extends Mob{

    public Enemy(float posX, float posY, float w, float h, int blood, GameWorld gw) {
        super(posX, posY, w, h, blood, gw);
    }

    @Override
    public void move() {}

    @Override
    public void stop() {}

    @Override
    public void draw(Graphics2D g2d) {}
    
}
